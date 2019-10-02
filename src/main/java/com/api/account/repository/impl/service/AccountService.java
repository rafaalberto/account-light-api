package com.api.account.repository.impl.service;

import com.api.account.repository.impl.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();
    Account findById(Long id);
    void save(Account account);
    void delete(Long id);
    void updateBalance(Account account);
    void updateBalanceByTransaction(Account accountSender, Account accountReceiver);
}
