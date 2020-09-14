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

        Throwable failure = ctx.failure();
        int statusCode = response.getStatusCode();

        if (statusCode == -1) {
            statusCode = 500;
        }

        response.setStatusCode(statusCode)
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .end(new JsonObject()
                        .put("error", failure.getClass().getSimpleName())
                        .put("message", failure.getMessage())
                        .toBuffer());
    }
}
