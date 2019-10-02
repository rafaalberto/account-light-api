package com.api.account.exception;

public class BusinessException extends RuntimeException {

    private Integer httpStatus;
    private String message;

    public BusinessException(Integer httpStatus, String message) {
        this.httpStatus= httpStatus;
        this.message = message;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
