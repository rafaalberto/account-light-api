package com.api.account.model;

public class Account {

    private Long accountId;
    private String name;

    public Account() {}

    public Account(Long accountId, String name) {
        this.accountId = accountId;
        this.name = name;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
