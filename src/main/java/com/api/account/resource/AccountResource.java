package com.api.account.resource;

import com.api.account.exception.BusinessException;
import com.api.account.model.Account;
import com.api.account.service.AccountService;
import com.api.account.service.impl.AccountServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;

import java.util.List;

import static com.api.account.utils.HttpUtils.*;
import static com.api.account.utils.JsonConverter.convertToJson;
import static com.api.account.utils.JsonConverter.readFromJson;

public class AccountResource {

    private static final AccountService accountService = new AccountServiceImpl();

    public static void create(HttpServerExchange exchange) {
        handleStatusAndHeaders(exchange, HTTP_CREATED_STATUS);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            Account account = readFromJson(message, new TypeReference<>() {});
            if (account != null) {
                accountService.save(account);
                exchange.getResponseSender().send("Account created successfully");
            }
        });
    }

    public static void update(HttpServerExchange exchange) {
        handleStatusAndHeaders(exchange, HTTP_CREATED_STATUS);
        String id = getQueryParameter(exchange);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            Account account = readFromJson(message, new TypeReference<>() {});
            if (account != null) {
                try {
                    account.setId(Long.valueOf(id));
                    accountService.save(account);
                    exchange.getResponseSender().send("Account updated successfully");
                } catch (BusinessException e) {
                    handleException(exchange, e);
                }
            }
        });
    }

    public static void delete(HttpServerExchange exchange) {
        handleStatusAndHeaders(exchange, HTTP_NO_CONTENT_STATUS);
        String id = getQueryParameter(exchange);
        try {
            accountService.delete(Long.valueOf(id));
        } catch (BusinessException e) {
            handleException(exchange, e);
        }
    }

    public static void findAll(HttpServerExchange exchange) {
        handleStatusAndHeaders(exchange, HTTP_OK_STATUS);
        try {
            List<Account> accounts = accountService.findAll();
            exchange.getResponseSender().send(convertToJson(accounts));
        } catch(BusinessException e) {
            handleException(exchange, e);
        }
    }

    public static void findById(HttpServerExchange exchange) {
        handleStatusAndHeaders(exchange, HTTP_OK_STATUS);
        String id = getQueryParameter(exchange);
        try {
            Account account = accountService.findById(Long.valueOf(id));
            exchange.getResponseSender().send(convertToJson(account));
        } catch (BusinessException e) {
            handleException(exchange, e);
        }
    }

}
