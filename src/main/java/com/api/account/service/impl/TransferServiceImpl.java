package com.api.account.service.impl;

import com.api.account.exception.BusinessException;
import com.api.account.model.Account;
import com.api.account.model.Transaction;
import com.api.account.service.AccountService;
import com.api.account.service.TransactionService;
import com.api.account.utils.HttpUtils;

import static com.api.account.service.CalculationService.deposit;
import static com.api.account.service.CalculationService.withdraw;

public class TransferServiceImpl implements TransactionService {

    private AccountService accountService;

    public TransferServiceImpl() {
        accountService = new AccountServiceImpl();
    }

    @Override
    public void execute(Transaction transaction) {
        Account accountSender = accountService.findById(transaction.getAccountSenderId());
        Account accountReceiver = accountService.findById(transaction.getAccountReceiverId());

        verifyData(transaction);

        accountSender.setBalance(withdraw(accountSender.getBalance(), transaction.getAmount()));
        accountReceiver.setBalance(deposit(accountReceiver.getBalance(), transaction.getAmount()));

        accountService.updateBalanceByTransaction(accountSender, accountReceiver);
    }

    private void verifyData(Transaction transaction) {
        if(transaction.getAccountSenderId().equals(transaction.getAccountReceiverId())) {
            throw new BusinessException(HttpUtils.HTTP_BAD_REQUEST_STATUS, "Account Sender and Receiver must be different");
        }
    }
}
