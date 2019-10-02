package com.api.account.repository.impl;

import com.api.account.AccountDaoImpl;
import com.api.account.model.Account;
import com.api.account.repository.AccountDao;
import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountDaoImplTest {

    private AccountDao accountDao = new AccountDaoImpl();

    @Test
    public void insert() {
        Account accountInserted = accountDao.insert(new Account("Rafael"));
        accountInserted = accountDao.findById(accountInserted.getId());

        assertThat(accountInserted.getId()).isNotNull();
        assertThat(accountInserted.getName()).isEqualTo("Rafael");
    }

    @Test
    public void update() {
        Account accountInserted = accountDao.insert(new Account("Maria"));
        Account accountUpdated = accountDao.update(new Account(accountInserted.getId(),"Rafael"));
        assertThat(accountUpdated.getName()).isEqualTo("Rafael");
    }

    @Test
    public void delete() {
        Account accountInserted = accountDao.insert(new Account("Rafael"));
        Account accountFound = accountDao.findById(accountInserted.getId());
        accountDao.delete(accountFound.getId());
        Account accountDeleted = accountDao.findById(accountFound.getId());
        assertThat(accountDeleted).isNull();
    }

    /* Unit Test to findById does not implement because this one is has used in the previous tests */

    @Test
    public void findAll() {
        accountDao.insert(new Account("Rafael"));
        accountDao.insert(new Account("John"));
        accountDao.insert(new Account("Pedro"));
        List<Account> accounts = accountDao.findAll();
        assertThat(accounts.size()).isEqualTo(3);
    }

    @After
    public void tearDown() {
        accountDao.deleteAll();
    }

}
