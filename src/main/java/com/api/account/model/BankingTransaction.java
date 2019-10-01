package com.api.account.model;

import com.api.account.enumeration.BankingTransactionType;

import java.math.BigDecimal;

public class BankingTransaction {

    private Long accountId;
    private BigDecimal amount;
    private BankingTransactionType type;

    public BankingTransaction() { }

    public BankingTransaction(Long accountId, BigDecimal amount, BankingTransactionType type) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    @Override
    public String toString() {
        return "BankingTransaction{" +
                "accountId=" + accountId +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}

