package com.api.account.repository.impl.model;

import com.api.account.repository.impl.enumeration.BankingTransactionType;

import java.math.BigDecimal;

public class BankingTransaction {

    private Long accountSenderId;
    private Long accountReceiverId;
    private BigDecimal amount;
    private BankingTransactionType type;

    public BankingTransaction() { }

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

    public BankingTransactionType getType() {
        return type;
    }

    public void setType(BankingTransactionType type) {
        this.type = type;
    }

}

