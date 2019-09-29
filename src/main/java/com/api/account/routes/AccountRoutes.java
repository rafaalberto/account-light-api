package com.api.account.routes;

import com.api.account.model.Account;
import io.undertow.server.HttpServerExchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.api.account.constants.HttpConstants.*;
import static com.api.account.utils.AppUtils.convertToJson;
import static com.api.account.utils.AppUtils.readFromJson;
import static io.undertow.util.Headers.CONTENT_TYPE;

public class AccountRoutes {

    public static void findAll(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_OK_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1L, "Rafa"));

        exchange.getResponseSender().send(convertToJson(accounts));
    }

    public static void findById(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_OK_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
        String id = exchange.getQueryParameters().get("id").getFirst();

        Account account = new Account();
        account.setId(Long.valueOf(id));
        account.setName("Rafael");

        exchange.getResponseSender().send(convertToJson(account));
    }

    public static void create(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_CREATED_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            var account = readFromJson(message);
            exchange.getResponseSender().send(Optional.ofNullable(account).toString());
        });
    }

    public static void delete(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_NO_CONTENT_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
    }
}
