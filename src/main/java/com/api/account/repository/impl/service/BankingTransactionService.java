package com.api.account.repository.impl.service;

import com.api.account.repository.impl.model.BankingTransaction;

public interface BankingTransactionService {

    void deposit(BankingTransaction bankingTransaction);

    void withdraw(BankingTransaction bankingTransaction);

    void transfer(BankingTransaction bankingTransaction);
}
