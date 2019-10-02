package com.api.account.repository.impl.config;

import com.api.account.repository.impl.resource.AccountResource;
import com.api.account.repository.impl.resource.BankingTransactionResource;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;

import static io.undertow.util.Methods.*;

public abstract class RoutesApplication {

    public static final RoutingHandler ROUTES = new RoutingHandler()
            .add(GET, "/", RoutesApplication::handleRequest)
            .add(GET, "/accounts", AccountResource::findAll)
            .add(GET, "/accounts/{id}", AccountResource::findById)
            .add(POST, "/accounts", AccountResource::create)
            .add(PUT, "/accounts/{id}", AccountResource::update)
            .add(DELETE, "/accounts/{id}", AccountResource::delete)
            .add(POST, "/transactions/deposit", BankingTransactionResource::deposit)
            .add(POST, "/transactions/withdraw", BankingTransactionResource::withdraw)
            .add(POST, "/transactions/transfer", BankingTransactionResource::transfer);

    private static void handleRequest(HttpServerExchange exchange) {
        exchange.getResponseSender().send("Application Started");
    }
}
