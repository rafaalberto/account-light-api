package com.api.account.service;

import com.api.account.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();
    Account findById(Long id);
    void save(Account account);
    void delete(Long id);
    void updateBalance(Account account);
    void updateBalanceByTransaction(Account accountSender, Account accountReceiver);
}
