package com.api.account.repository;

import com.api.account.model.Account;

import java.util.List;

public interface AccountDao {

    void insert(Account account);

    void update(Account account);

    void delete(Long id);

    void deleteAll();

    List<Account> findAll();

    Account findById(Long id);

    void updateBalance(Account account);

    void updateBalanceByTransfer(Account accountSender, Account accountReceiver);

}
