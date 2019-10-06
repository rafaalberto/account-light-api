package com.api.account.enumeration;

import com.api.account.service.TransactionService;
import com.api.account.service.impl.DepositServiceImpl;
import com.api.account.service.impl.TransferServiceImpl;
import com.api.account.service.impl.WithdrawServiceImpl;

public enum TransactionType {

    DEPOSIT("Deposit", new DepositServiceImpl()),
    TRANSFER("Transfer", new TransferServiceImpl()),
    WITHDRAW("Withdraw", new WithdrawServiceImpl());

    private String description;
    private TransactionService service;

    TransactionType(String description, TransactionService service) {
        this.description = description;
        this.service = service;
    }

    public TransactionService getService() {
        return service;
    }

    public void setService(TransactionService service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }
}
