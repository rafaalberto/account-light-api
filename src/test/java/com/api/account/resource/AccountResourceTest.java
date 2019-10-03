package com.api.account.resource;

import com.api.account.model.Account;
import com.api.account.service.AccountService;
import com.api.account.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AccountResourceTest {

    private Account account;

    @Mock
    private AccountService accountService = new AccountServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        account = new Account(1L, "Rafael");
    }

    @Test
    public void create() {
        BDDMockito.given(accountService.findById(Mockito.anyLong())).willReturn(account);
    }

}
