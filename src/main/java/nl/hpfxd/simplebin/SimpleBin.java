package nl.hpfxd.simplebin;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.templ.handlebars.HandlebarsTemplateEngine;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.hpfxd.simplebin.handler.APIErrorHandler;
import nl.hpfxd.simplebin.mongo.Paste;
import nl.hpfxd.simplebin.mongo.SimpleBinDatabase;
import org.bson.types.ObjectId;

@Slf4j
public class SimpleBin {
    @Getter private static final SimpleBinDatabase database = new SimpleBinDatabase();

    public static void main(String[] args) {
        int port = 8080;
        if (System.getenv("PORT") != null) {
            port = Integer.parseInt(System.getenv("PORT"));
        }

        if (System.getenv("MONGO_URI") != null) {
            database.init(System.getenv("MONGO_URI"));
        }

        log.info("Starting SimpleBin.");
        Vertx vertx = Vertx.vertx();

        log.info("Setting up server...");
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        APIErrorHandler apiErrorHandler = new APIErrorHandler();

        log.info("Setting up template engine...");
        HandlebarsTemplateEngine engine = HandlebarsTemplateEngine.create(vertx);
        TemplateHandler templateHandler = TemplateHandler.create(engine);

        engine.getHandlebars().registerHelper("isdefined", (ctx, options) -> ctx != null);

        log.info("Registering routes...");
        router.route().handler(BodyHandler.create()
                .setBodyLimit(52428800) // 50 MB body limit
                .setHandleFileUploads(false));

        // basic routes
        router.get("/").handler(templateHandler);
        router.route("/static/*").handler(StaticHandler.create());
        router.route("/api/*").handler(CorsHandler.create("*")
                .allowCredentials(false)
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST));

        // -- API ROUTES
        router.post("/api/paste/new")
                .consumes("application/json")
                .blockingHandler((ctx) -> {
                    JsonObject json = ctx.getBodyAsJson();

                    String name = json.getString("name");
                    if (name != null && name.isEmpty()) name = null;
                    String syntaxName = json.getString("syntax");
                    if (syntaxName == null) syntaxName = "Plain Text";
                    String content = json.getString("content");
                    if (content == null) {
                        ctx.fail(400, new IllegalArgumentException("\"content\" cannot be null."));
                        return;
                    }

                    content = content.trim();
                    if (content.isEmpty()) {
                        ctx.fail(400, new IllegalArgumentException("\"content\" cannot be empty."));
                        return;
                    }

                    SyntaxHighlighting syntax = SyntaxHighlighting.findFromName(syntaxName);
                    if (syntax == null) {
                        ctx.fail(400, new IllegalArgumentException("\"syntax\" must be a valid value."));
                        return;
                    }

                    Paste paste = new Paste();
                    paste.setName(name);
                    paste.setSyntax(syntax);
                    paste.setContent(content);
                    paste.save();

                    ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(new JsonObject()
                            .put("id", paste.getId().toHexString())
                            .toBuffer());
                }, false).failureHandler(apiErrorHandler);

        router.get("/api/paste/:pasteId").blockingHandler((ctx) -> {
            String pasteId = ctx.request().getParam("pasteId");

            if (!ObjectId.isValid(pasteId)) {
                ctx.fail(400, new IllegalArgumentException("Parameter \"pasteId\" is not a valid ObjectId."));
                return;
            }

            ObjectId id = new ObjectId(pasteId);

            Paste paste = database.getDatastore().createQuery(Paste.class)
                    .field("id").equal(id)
                    .first();

            if (paste == null) {
                ctx.fail(404, new IllegalArgumentException("Paste not found."));
                return;
            }

            ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(new JsonObject()
                    .put("id", paste.getId())
                    .put("content", paste.getContent())
                    .put("syntax", new JsonObject()
                            .put("id", paste.getSyntax().getId())
                            .put("name", paste.getSyntax().name())
                            .put("displayName", paste.getSyntax().getName())
                            .put("mime", paste.getSyntax().getMime())
                    )
                    .toBuffer());
        }, false).failureHandler(apiErrorHandler);

        // paste route
        router.get("/:pasteId").blockingHandler((ctx) -> {
            String pasteId = ctx.request().getParam("pasteId");

            if (!ObjectId.isValid(pasteId)) {
                ctx.fail(400, new IllegalArgumentException("Parameter \"pasteId\" is not a valid ObjectId."));
                return;
            }

            ObjectId id = new ObjectId(pasteId);

            Paste paste = database.getDatastore().createQuery(Paste.class)
                    .field("id").equal(id)
                    .first();

            if (paste == null) {
                ctx.fail(404, new IllegalArgumentException("Paste not found."));
                return;
            }

            if (paste.getName() != null) {
                ctx.put("pasteName", paste.getName());
            }

            String content = paste.getContent();

            ctx.put("pasteId", paste.getId().toHexString());
            ctx.put("pasteContent", content);
            if (content.length() > 100) {
                ctx.put("pasteContentPreview", content.substring(0, 100));
            } else {
                ctx.put("pasteContentPreview", content);
            }
            ctx.put("pasteSyntaxName", paste.getSyntax().getName());
        }, false).handler((ctx) -> engine.render(ctx.data(),
                TemplateHandler.DEFAULT_TEMPLATE_DIRECTORY + "/paste", (res) -> {
                    if (res.succeeded()) {
                        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html")
                                .end(res.result());
                    } else {
                        ctx.fail(res.cause());
                    }
                })).failureHandler(ErrorHandler.create(false));

        log.info("Binding...");
        server.requestHandler(router)
                .listen(port, "0.0.0.0");
        log.info("Listening on *:{}.", port);
    }
}
