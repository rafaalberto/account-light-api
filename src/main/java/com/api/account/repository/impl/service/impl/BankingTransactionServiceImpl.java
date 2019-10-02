package com.api.account.repository.impl.service.impl;

import com.api.account.repository.impl.enumeration.BankingTransactionType;
import com.api.account.repository.impl.exception.BusinessException;
import com.api.account.repository.impl.model.Account;
import com.api.account.repository.impl.service.AccountService;
import com.api.account.repository.impl.service.BankingTransactionService;
import com.api.account.repository.impl.model.BankingTransaction;

import java.math.BigDecimal;

import static com.api.account.repository.impl.constants.HttpConstants.*;

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
