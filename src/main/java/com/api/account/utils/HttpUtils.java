package com.api.account.utils;

import com.api.account.exception.BusinessException;
import io.undertow.server.HttpServerExchange;

import static io.undertow.util.Headers.CONTENT_TYPE;

public abstract class HttpUtils {

    public static final int APP_PORT = 8080;
    public static final String APP_HOST = "localhost";

    public static final int HTTP_OK_STATUS = 200;
    public static final int HTTP_CREATED_STATUS = 201;
    public static final int HTTP_NO_CONTENT_STATUS = 204;
    public static final int HTTP_BAD_REQUEST_STATUS = 400;

    public static final String HEADER_JSON = "application/json";

    public static String getQueryParameter(HttpServerExchange exchange) {
        return exchange.getQueryParameters().get("id").getFirst();
    }

    public static void handleStatusAndHeaders(HttpServerExchange exchange, int httpCreatedStatus) {
        exchange.setStatusCode(httpCreatedStatus);
        exchange.getResponseHeaders().put(CONTENT_TYPE, HEADER_JSON);
    }

    public static void handleException(HttpServerExchange exchange, BusinessException e) {
        exchange.setStatusCode(e.getHttpStatus());
        exchange.getResponseSender().send(e.getMessage());
    }
}
