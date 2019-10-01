package com.api.account.service.impl;

import com.api.account.constants.HttpConstants;
import com.api.account.enumeration.BankingTransactionType;
import com.api.account.exception.BusinessException;
import com.api.account.model.Account;
import com.api.account.model.BankingTransaction;
import com.api.account.service.AccountService;
import com.api.account.service.BankingTransactionService;

import java.math.BigDecimal;

import static com.api.account.constants.HttpConstants.*;

public class BankingTransactionServiceImpl implements BankingTransactionService {

    private AccountService accountService;

    public BankingTransactionServiceImpl() {
        accountService = new AccountServiceImpl();
    }

    @Override
    public void deposit(BankingTransaction bankingTransaction) {
        Account account = accountService.findById(bankingTransaction.getAccountSenderId());
        if(bankingTransaction.getType().equals(BankingTransactionType.DEPOSIT)) {
            account.deposit(bankingTransaction.getAmount());
            accountService.updateBalance(account);
        } else {
            throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "Transaction not valid for this operation");
        }
    }

    @Override
    public void withdraw(BankingTransaction bankingTransaction) {
        Account account = accountService.findById(bankingTransaction.getAccountSenderId());
        if(bankingTransaction.getType().equals(BankingTransactionType.WITHDRAW)) {
            if(account.getBalance().compareTo(bankingTransaction.getAmount()) < BigDecimal.ZERO.intValue()) {
                throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "Insufficient funds");
            }
            account.withdraw(bankingTransaction.getAmount());
            accountService.updateBalance(account);
        } else {
            throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "Transaction not valid for this operation");
        }
    }

    @Override
    public void transfer(BankingTransaction bankingTransaction) {
        Account accountSender = accountService.findById(bankingTransaction.getAccountSenderId());
        Account accountReceiver = accountService.findById(bankingTransaction.getAccountReceiverId());
        if(bankingTransaction.getType().equals(BankingTransactionType.TRANSFER)) {
            if(accountSender.getBalance().compareTo(bankingTransaction.getAmount()) < BigDecimal.ZERO.intValue()) {
                throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "Insufficient funds");
            }
            accountSender.withdraw(bankingTransaction.getAmount());
            accountReceiver.deposit(bankingTransaction.getAmount());
            accountService.updateBalanceByTransaction(accountSender, accountReceiver);
        } else {
            throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "Transaction not valid for this operation");
        }
    }
}
