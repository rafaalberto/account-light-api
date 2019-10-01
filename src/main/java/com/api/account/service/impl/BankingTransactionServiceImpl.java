package com.api.account.service.impl;

import com.api.account.constants.HttpConstants;
import com.api.account.enumeration.BankingTransactionType;
import com.api.account.exception.BusinessException;
import com.api.account.model.Account;
import com.api.account.model.BankingTransaction;
import com.api.account.service.AccountService;
import com.api.account.service.BankingTransactionService;

import java.math.BigDecimal;

public class BankingTransactionServiceImpl implements BankingTransactionService {

    private AccountService accountService;

    public BankingTransactionServiceImpl() {
        accountService = new AccountServiceImpl();
    }

    @Override
    public void deposit(BankingTransaction bankingTransaction) {
        Account account = accountService.findById(bankingTransaction.getAccountId());
        if(bankingTransaction.getType().equals(BankingTransactionType.DEPOSIT)) {
            account.deposit(bankingTransaction.getAmount());
            accountService.updateBalance(account);
        } else {
            throw new BusinessException(HttpConstants.HTTP_BAD_REQUEST_STATUS, "Transaction not valid for this operation");
        }
    }

    @Override
    public void withdraw(BankingTransaction bankingTransaction) {
        Account account = accountService.findById(bankingTransaction.getAccountId());
        if(bankingTransaction.getType().equals(BankingTransactionType.WITHDRAW)) {
            if(account.getBalance().compareTo(bankingTransaction.getAmount()) < BigDecimal.ZERO.intValue()) {
                throw new BusinessException(HttpConstants.HTTP_BAD_REQUEST_STATUS, "Insufficient funds");
            }
            account.withdraw(bankingTransaction.getAmount());
            accountService.updateBalance(account);
        } else {
            throw new BusinessException(HttpConstants.HTTP_BAD_REQUEST_STATUS, "Transaction not valid for this operation");
        }
    }
}
