package com.api.account.service;

import com.api.account.model.BankingTransaction;

public interface BankingTransactionService {

    void deposit(BankingTransaction bankingTransaction);

    void withdraw(BankingTransaction bankingTransaction);
}
