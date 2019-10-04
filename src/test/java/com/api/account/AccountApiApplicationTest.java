package com.api.account;

import com.api.account.repository.impl.AccountDaoImplTest;
import com.api.account.resource.AccountResourceTest;
import com.api.account.service.impl.AccountServiceImplTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountDaoImplTest.class,
        AccountServiceImplTest.class,
        AccountResourceTest.class
})
public class AccountApiApplicationTest {

    @Test
    public void contextLoads() {
    }


}
