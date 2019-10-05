package com.api.account.service.impl;

import com.api.account.model.Account;
import com.api.account.model.Transaction;
import com.api.account.service.AccountService;
import com.api.account.service.TransactionService;

public class DepositServiceImpl implements TransactionService {

    private AccountService accountService;

    public DepositServiceImpl() {
        accountService = new AccountServiceImpl();
    }

    @Override
    public void execute(Transaction transaction) {
        Account account = accountService.findById(transaction.getAccountSenderId());
        account.deposit(transaction.getAmount());
        accountService.updateBalance(account);
    }
}
