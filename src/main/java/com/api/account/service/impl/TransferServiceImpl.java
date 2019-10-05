package com.api.account.service.impl;

import com.api.account.exception.BusinessException;
import com.api.account.model.Account;
import com.api.account.model.Transaction;
import com.api.account.service.AccountService;
import com.api.account.service.TransactionService;

import java.math.BigDecimal;

import static com.api.account.utils.HttpUtils.HTTP_BAD_REQUEST_STATUS;

public class TransferServiceImpl implements TransactionService {

    private AccountService accountService;

    public TransferServiceImpl() {
        accountService = new AccountServiceImpl();
    }

    @Override
    public void execute(Transaction transaction) {
        Account accountSender = accountService.findById(transaction.getAccountSenderId());
        Account accountReceiver = accountService.findById(transaction.getAccountReceiverId());
        if(accountSender.getBalance().compareTo(transaction.getAmount()) < BigDecimal.ZERO.intValue()) {
            throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "Insufficient funds");
        }
        accountSender.withdraw(transaction.getAmount());
        accountReceiver.deposit(transaction.getAmount());
        accountService.updateBalanceByTransaction(accountSender, accountReceiver);
    }
}
