package com.api.account.repository.impl;

import com.api.account.repository.impl.model.Account;
import com.api.account.repository.impl.repository.AccountDao;
import com.api.account.repository.impl.repository.impl.AccountDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountDaoImplTest {

    private AccountDao accountDao = new AccountDaoImpl();

    private Account account;

    @Before
    public void setUp() {
        account = new Account();
        account.setName("Rafael");
        account.setBalance(new BigDecimal(2000.00));
    }

    @Test
    public void insert() {
        Account accountInserted = accountDao.insert(account);
        accountInserted = accountDao.findById(accountInserted.getId());

        assertNotNull(accountInserted.getId());
        assertEquals("Rafael", accountInserted.getName());
    }

    @After
    public void tearDown() {
        accountDao.deleteAll();
    }

}
