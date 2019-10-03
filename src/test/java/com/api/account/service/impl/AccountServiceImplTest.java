package com.api.account.service.impl;

import com.api.account.AccountDaoImpl;
import com.api.account.model.Account;
import com.api.account.repository.AccountDao;
import com.api.account.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {

    private Account accountInDB;

    private AccountService accountService;

    @Mock
    private AccountDao accountDao = new AccountDaoImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImpl(accountDao);
        accountInDB = new Account(1L, "Rafael");
    }

    @Test
    public void insert() {
        Account account = new Account( "Rafael");
        when(accountDao.insert(account)).thenReturn(accountInDB);
        Account accountSaved = accountService.save(account);
        assertThat(accountSaved.getId()).isEqualTo(1L);
    }

    @Test
    public void update() {
        Account accountToUpdate = new Account(1L, "Maria");
        when(accountDao.findById(1L)).thenReturn(accountInDB);
        when(accountDao.update(accountToUpdate)).thenReturn(accountToUpdate);
        Account accountSaved = accountService.save(accountToUpdate);
        assertThat(accountSaved.getName()).isEqualTo("Maria");
    }

}
