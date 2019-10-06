package com.api.account.model;

import java.math.BigDecimal;

import static com.api.account.utils.NumericConverter.convertTwoDecimalPlace;

public class Account {

    private Long id;
    private String name;
    private BigDecimal balance = BigDecimal.ZERO;

    public Account() {
    }

    public Account(String name) {
        this.name = name;
    }

    public Account(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Account(Long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized BigDecimal getBalance() {
        return convertTwoDecimalPlace(balance);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
}
