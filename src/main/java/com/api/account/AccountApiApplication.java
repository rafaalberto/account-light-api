package com.api.account;

import com.api.account.routes.AccountRoutes;
import io.undertow.Undertow;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;

import static com.api.account.constants.HttpConstants.APP_HOST;
import static com.api.account.constants.HttpConstants.APP_PORT;
import static io.undertow.util.Methods.*;

public class AccountApiApplication {

    private static final RoutingHandler ROUTES = new RoutingHandler()
            .add(GET, "/", AccountApiApplication::handleRequest)
            .add(GET, "/accounts", AccountRoutes::findAll)
            .add(GET, "/accounts/{id}", AccountRoutes::findById)
            .add(POST, "/accounts", AccountRoutes::create)
            .add(DELETE, "/accounts", AccountRoutes::delete);

    public static void main(String[] args) {
        Undertow.Builder builder = Undertow.builder();
        builder.addHttpListener(APP_PORT, APP_HOST);
        builder.setHandler(ROUTES);
        Undertow server = builder.build();
        server.start();
    }

    private static void handleRequest(HttpServerExchange exchange) {
        exchange.getResponseSender().send("Application Started");
    }
}