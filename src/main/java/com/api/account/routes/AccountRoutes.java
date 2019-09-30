package com.api.account.routes;

import com.api.account.model.Account;
import com.api.account.repository.AccountDao;
import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;

import java.util.List;

import static com.api.account.constants.HttpConstants.*;
import static com.api.account.utils.AppUtils.convertToJson;
import static com.api.account.utils.AppUtils.readFromJson;
import static io.undertow.util.Headers.CONTENT_TYPE;

public class AccountRoutes {

    private static final AccountDao accountDao = new AccountDao();

    public static void findAll(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_OK_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);

        List<Account> accounts = accountDao.findAll();
        exchange.getResponseSender().send(convertToJson(accounts));
    }

    public static void findById(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_OK_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
        String id = exchange.getQueryParameters().get("id").getFirst();

        Account account = accountDao.findById(Long.valueOf(id));
        exchange.getResponseSender().send(convertToJson(account));
    }

    public static void create(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_CREATED_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            Account account = readFromJson(message, new TypeReference<>(){});
            if(account != null) {
                accountDao.insert(account);
                exchange.getResponseSender().send("Account created successfully");
            }
        });
    }

    public static void update(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_CREATED_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
        String id = exchange.getQueryParameters().get("id").getFirst();
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            Account account = readFromJson(message, new TypeReference<>(){});
            if(account != null) {
                account.setId(Long.valueOf(id));
                accountDao.update(account);
                exchange.getResponseSender().send("Account updated successfully");
            }
        });
    }

    public static void delete(HttpServerExchange exchange) {
        exchange.setStatusCode(HTTP_NO_CONTENT_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
        String id = exchange.getQueryParameters().get("id").getFirst();

        Account account = accountDao.findById(Long.valueOf(id));
        if(account != null) {
            accountDao.delete(account.getId());
        }
    }
}
