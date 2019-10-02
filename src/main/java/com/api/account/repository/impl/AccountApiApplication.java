package com.api.account.repository.impl;

import com.api.account.repository.impl.config.RoutesApplication;
import com.api.account.repository.impl.database.DatabaseConnection;
import io.undertow.Undertow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.api.account.repository.impl.constants.HttpConstants.APP_HOST;
import static com.api.account.repository.impl.constants.HttpConstants.APP_PORT;

public class AccountApiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountApiApplication.class);

    public static void main(String[] args) {
        Undertow.Builder builder = Undertow.builder();
        builder.addHttpListener(APP_PORT, APP_HOST);
        builder.setHandler(RoutesApplication.ROUTES);

        Undertow server = builder.build();
        server.start();

        LOGGER.info("Server connected at " + APP_PORT);

        DatabaseConnection.connect();
        DatabaseConnection.createTables();
    }

}