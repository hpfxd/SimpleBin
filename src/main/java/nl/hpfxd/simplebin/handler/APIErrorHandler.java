package nl.hpfxd.simplebin.handler;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class APIErrorHandler implements Handler<RoutingContext>  {
    @Override
    public void handle(RoutingContext ctx) {
        HttpServerResponse response = ctx.response();

        response.putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.end(new JsonObject()
                .put("error", ctx.failure().getClass().getSimpleName())
                .put("message", ctx.failure().getMessage())
        .toBuffer());
    }
}
