package com.api.account.repository;

import com.api.account.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    void insert(Account account);

    void update(Account account);

    void delete(Long id);

    List<Account> findAll();

    Account findById(Long id);

    void updateBalance(Account account);

}
