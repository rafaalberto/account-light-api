package com.api.account.resource;

import com.api.account.exception.BusinessException;
import com.api.account.model.BankingTransaction;
import com.api.account.service.BankingTransactionService;
import com.api.account.service.impl.BankingTransactionServiceImpl;
import com.api.account.utils.JsonConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;

import static com.api.account.utils.HttpUtils.*;

public class BankingTransactionResource {

    private static final BankingTransactionService bankingTransactionService = new BankingTransactionServiceImpl();

    public static void deposit(HttpServerExchange exchange) {
        handleStatusAndHeaders(exchange, HTTP_OK_STATUS);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            BankingTransaction bankingTransaction = JsonConverter.readFromJson(message, new TypeReference<>() {});
            if (bankingTransaction != null) {
                try {
                    bankingTransactionService.deposit(bankingTransaction);
                    exchange.getResponseSender().send("Deposit ok");
                } catch(BusinessException e) {
                    handleException(exchange, e);
                }
            }
        });
    }

    public static void withdraw(HttpServerExchange exchange) {
        handleStatusAndHeaders(exchange, HTTP_OK_STATUS);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            BankingTransaction bankingTransaction = JsonConverter.readFromJson(message, new TypeReference<>() {});
            if (bankingTransaction != null) {
                try {
                    bankingTransactionService.withdraw(bankingTransaction);
                    exchange.getResponseSender().send("Withdraw ok");
                } catch(BusinessException e) {
                    handleException(exchange, e);
                }
            }
        });
    }

    public static void transfer(HttpServerExchange exchange) {
        handleStatusAndHeaders(exchange, HTTP_OK_STATUS);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            BankingTransaction bankingTransaction = JsonConverter.readFromJson(message, new TypeReference<>() {});
            if (bankingTransaction != null) {
                try {
                    bankingTransactionService.transfer(bankingTransaction);
                    exchange.getResponseSender().send("Transfer ok");
                } catch(BusinessException e) {
                    handleException(exchange, e);
                }
            }
        });
    }

}
