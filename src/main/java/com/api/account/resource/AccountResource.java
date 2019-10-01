package com.api.account.resource;

import com.api.account.model.Account;
import com.api.account.service.AccountService;
import com.api.account.service.impl.AccountServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;

import java.util.List;

import static com.api.account.constants.HttpConstants.*;
import static com.api.account.utils.UtilsApplication.convertToJson;
import static com.api.account.utils.UtilsApplication.readFromJson;
import static io.undertow.util.Headers.CONTENT_TYPE;

public class AccountResource {

    private static final AccountService accountService = new AccountServiceImpl();

    public static void create(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_CREATED_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            Account account = readFromJson(message, new TypeReference<>() {});
            if (account != null) {
                accountService.save(account);
                exchange.getResponseSender().send("Account created successfully");
            }
        });
    }

    public static void update(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_CREATED_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
        String id = exchange.getQueryParameters().get("id").getFirst();
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            Account account = readFromJson(message, new TypeReference<>() {});
            if (account != null) {
                account.setId(Long.valueOf(id));
                accountService.save(account);
                exchange.getResponseSender().send("Account updated successfully");
            }
        });
    }

    public static void delete(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_NO_CONTENT_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
        String id = exchange.getQueryParameters().get("id").getFirst();
        accountService.delete(Long.valueOf(id));
    }

    public static void findAll(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_OK_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);

        List<Account> accounts = accountService.findAll();
        exchange.getResponseSender().send(convertToJson(accounts));
    }

    public static void findById(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_OK_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
        String id = exchange.getQueryParameters().get("id").getFirst();

        Account account = accountService.findById(Long.valueOf(id));
        exchange.getResponseSender().send(convertToJson(account));
    }
}
