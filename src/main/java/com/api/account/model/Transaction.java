package com.api.account.model;

import com.api.account.enumeration.TransactionType;

import java.math.BigDecimal;

public class Transaction {

    private Long accountSenderId;
    private Long accountReceiverId;
    private BigDecimal amount;
    private TransactionType type;

    public Transaction() { }

    public Long getAccountSenderId() {
        return accountSenderId;
    }

    public void setAccountSenderId(Long accountSenderId) {
        this.accountSenderId = accountSenderId;
    }

    public Long getAccountReceiverId() {
        return accountReceiverId;
    }

    public void setAccountReceiverId(Long accountReceiverId) {
        this.accountReceiverId = accountReceiverId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

}

