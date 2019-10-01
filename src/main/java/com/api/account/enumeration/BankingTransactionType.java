package com.api.account.enumeration;

public enum BankingTransactionType {

    DEPOSIT("D", "Deposit"),
    WITHDRAW("W", "Withdraw"),
    TRANSFER("T", "Transfer");

    private final String code;
    private final String description;

    BankingTransactionType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
