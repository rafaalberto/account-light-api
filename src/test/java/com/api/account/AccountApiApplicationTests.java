package com.api.account;

import com.api.account.model.Account;
import com.api.account.repository.AccountDao;
import com.api.account.repository.impl.AccountDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountApiApplicationTests {

    private AccountDao accountDao = new AccountDaoImpl();

    private Account account;

    @Before
    public void setUp() {
        account = new Account();
        account.setName("Rafael");
        account.setBalance(new BigDecimal(2000.00));
    }

    @Test
    public void save() {
        accountDao.insert(account);
        List<Account> accounts = accountDao.findAll();
        assertEquals(1, accounts.size());
    }

    @After
    public void tearDown() {
        accountDao.deleteAll();
    }

}
