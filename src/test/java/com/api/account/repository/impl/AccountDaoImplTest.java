package com.api.account.repository.impl;

import com.api.account.repository.impl.model.Account;
import com.api.account.repository.impl.repository.AccountDao;
import com.api.account.repository.impl.repository.impl.AccountDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountDaoImplTest {

    private AccountDao accountDao = new AccountDaoImpl();

    private Account account;

    @Before
    public void setUp() {
        account = new Account();
        account.setName("Rafael");
    }

    @Test
    public void insert() {
        Account accountInserted = accountDao.insert(account);
        accountInserted = accountDao.findById(accountInserted.getId());

        assertThat(accountInserted.getId()).isNotNull();
        assertThat(accountInserted.getName()).isEqualTo("Rafael");
    }

    @Test
    public void update() {
        account.setName("Maria");
        Account accountSaved = accountDao.insert(account);
        assertThat(accountSaved.getName()).isEqualTo("Maria");

        accountSaved.setName("Rafael");
        accountSaved = accountDao.update(accountSaved);
        assertThat(accountSaved.getName()).isEqualTo("Rafael");
    }

    @After
    public void tearDown() {
        accountDao.deleteAll();
    }

}
