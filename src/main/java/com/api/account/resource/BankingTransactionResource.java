package com.api.account.resource;

import com.api.account.constants.HttpConstants;
import com.api.account.exception.BusinessException;
import com.api.account.model.BankingTransaction;
import com.api.account.service.BankingTransactionService;
import com.api.account.service.impl.BankingTransactionServiceImpl;
import com.api.account.utils.UtilsApplication;
import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;

import static io.undertow.util.Headers.CONTENT_TYPE;

public class BankingTransactionResource {

    private static final BankingTransactionService bankingTransactionService = new BankingTransactionServiceImpl();

    public static void deposit(HttpServerExchange exchange) {
        exchange.setStatusCode(HttpConstants.HTTP_CREATED_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HttpConstants.HEADER_JSON);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            BankingTransaction bankingTransaction = UtilsApplication.readFromJson(message, new TypeReference<>() {});
            if (bankingTransaction != null) {
                try {
                    bankingTransactionService.deposit(bankingTransaction);
                    exchange.getResponseSender().send("Deposit ok");
                } catch(BusinessException e) {
                    exchange.setStatusCode(e.getHttpStatus());
                    exchange.getResponseSender().send(e.getMessage());
                }
            }
        });
    }

    public static void withdraw(HttpServerExchange exchange) {
        exchange.setStatusCode(HttpConstants.HTTP_CREATED_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HttpConstants.HEADER_JSON);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            BankingTransaction bankingTransaction = UtilsApplication.readFromJson(message, new TypeReference<>() {});
            if (bankingTransaction != null) {
                try {
                    bankingTransactionService.withdraw(bankingTransaction);
                    exchange.getResponseSender().send("Withdraw ok");
                } catch(BusinessException e) {
                    exchange.setStatusCode(e.getHttpStatus());
                    exchange.getResponseSender().send(e.getMessage());
                }
            }
        });
    }

    public static void transfer(HttpServerExchange exchange) {
        exchange.setStatusCode(HttpConstants.HTTP_CREATED_STATUS);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HttpConstants.HEADER_JSON);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            BankingTransaction bankingTransaction = UtilsApplication.readFromJson(message, new TypeReference<>() {});
            if (bankingTransaction != null) {
                try {
                    bankingTransactionService.transfer(bankingTransaction);
                    exchange.getResponseSender().send("Transfer ok");
                } catch(BusinessException e) {
                    exchange.setStatusCode(e.getHttpStatus());
                    exchange.getResponseSender().send(e.getMessage());
                }
            }
        });
    }

}
