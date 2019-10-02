package com.api.account;

import com.api.account.repository.impl.AccountDaoImplTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountDaoImplTest.class,
})
public class AccountApiApplicationTest {

    @Test
    public void contextLoads() {
    }

}
