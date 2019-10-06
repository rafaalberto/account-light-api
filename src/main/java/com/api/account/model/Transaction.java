package com.api.account.model;

import com.api.account.enumeration.TransactionType;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(accountSenderId, that.accountSenderId) &&
                Objects.equals(accountReceiverId, that.accountReceiverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSenderId, accountReceiverId);
    }
}

