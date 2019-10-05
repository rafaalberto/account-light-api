package com.api.account.enumeration;

import com.api.account.service.impl.DepositServiceImpl;
import com.api.account.service.TransactionService;
import com.api.account.service.impl.TransferServiceImpl;
import com.api.account.service.impl.WithdrawServiceImpl;

public enum TransactionType {

    DEPOSIT(new DepositServiceImpl()),
    TRANSFER(new TransferServiceImpl()),
    WITHDRAW(new WithdrawServiceImpl());

    private TransactionService service;

    TransactionType(TransactionService service) {
        this.service = service;
    }

    public TransactionService getService() {
        return service;
    }

    public void setService(TransactionService service) {
        this.service = service;
    }
}
