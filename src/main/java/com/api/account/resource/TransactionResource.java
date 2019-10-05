package com.api.account.resource;

import com.api.account.exception.BusinessException;
import com.api.account.model.Transaction;
import com.api.account.utils.JsonConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;

import static com.api.account.utils.HttpUtils.*;

public class TransactionResource {

    public static void execute(HttpServerExchange exchange) {
        handleStatusAndHeaders(exchange, HTTP_CREATED_STATUS);
        exchange.getRequestReceiver().receiveFullString((serverExchange, message) -> {
            Transaction transaction = JsonConverter.readFromJson(message, new TypeReference<>() {});
            if (transaction != null) {
                try {
                    var transactionType = transaction.getType();
                    transactionType.getService().execute(transaction);
                    exchange.getResponseSender().send("Transaction ok");
                } catch(BusinessException e) {
                    handleException(exchange, e);
                }
            }
        });
    }
}
