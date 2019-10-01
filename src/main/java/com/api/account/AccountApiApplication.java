package com.api.account;

import com.api.account.database.DatabaseConnection;
import com.api.account.resource.AccountResource;
import com.api.account.resource.BankingTransactionResource;
import io.undertow.Undertow;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.api.account.constants.HttpConstants.APP_HOST;
import static com.api.account.constants.HttpConstants.APP_PORT;
import static io.undertow.util.Methods.*;

public class AccountApiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountApiApplication.class);

    private static final RoutingHandler ROUTES = new RoutingHandler()
            .add(GET, "/", AccountApiApplication::handleRequest)
            .add(GET, "/accounts", AccountResource::findAll)
            .add(GET, "/accounts/{id}", AccountResource::findById)
            .add(POST, "/accounts", AccountResource::create)
            .add(PUT, "/accounts/{id}", AccountResource::update)
            .add(DELETE, "/accounts/{id}", AccountResource::delete)
            .add(POST, "/transactions/deposit", BankingTransactionResource::deposit)
            .add(POST, "/transactions/withdraw", BankingTransactionResource::withdraw);

    public static void main(String[] args) {
        Undertow.Builder builder = Undertow.builder();
        builder.addHttpListener(APP_PORT, APP_HOST);
        builder.setHandler(ROUTES);
        Undertow server = builder.build();
        server.start();

        LOGGER.info("Server connected at " + APP_PORT);

        DatabaseConnection.connect();
        DatabaseConnection.createTables();
    }

    private static void handleRequest(HttpServerExchange exchange) {
        exchange.getResponseSender().send("Application Started");
    }
}