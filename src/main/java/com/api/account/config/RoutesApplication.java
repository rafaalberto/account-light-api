package com.api.account.config;

import com.api.account.resource.AccountResource;
import com.api.account.resource.TransactionResource;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;

import static io.undertow.util.Methods.*;

public abstract class RoutesApplication {

    public static final RoutingHandler ROUTES = new RoutingHandler()
            .add(GET, "/", RoutesApplication::index)
            .add(GET, "/accounts", AccountResource::findAll)
            .add(GET, "/accounts/{id}", AccountResource::findById)
            .add(POST, "/accounts", AccountResource::create)
            .add(PUT, "/accounts/{id}", AccountResource::update)
            .add(DELETE, "/accounts/{id}", AccountResource::delete)
            .add(POST, "/transactions", TransactionResource::execute);

    private static void index(HttpServerExchange exchange) {
        exchange.getResponseSender().send("Application Started");
    }
}
