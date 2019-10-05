package com.api.account.service.impl;

import com.api.account.exception.BusinessException;
import com.api.account.model.Account;
import com.api.account.model.Transaction;
import com.api.account.service.AccountService;
import com.api.account.service.TransactionService;

import java.math.BigDecimal;

import static com.api.account.utils.HttpUtils.HTTP_BAD_REQUEST_STATUS;

public class WithdrawServiceImpl implements TransactionService {

    private AccountService accountService;

    public WithdrawServiceImpl() {
        accountService = new AccountServiceImpl();
    }

    @Override
    public void execute(Transaction transaction) {
        Account account = accountService.findById(transaction.getAccountSenderId());
        if(account.getBalance().compareTo(transaction.getAmount()) < BigDecimal.ZERO.intValue()) {
            throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "Insufficient funds");
        }
        account.withdraw(transaction.getAmount());
        accountService.updateBalance(account);
    }

}
