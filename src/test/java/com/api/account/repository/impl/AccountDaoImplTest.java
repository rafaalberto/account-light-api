package com.api.account.repository.impl;

import com.api.account.model.Account;
import com.api.account.repository.AccountDao;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

    @Test
    public void updateBalance() {
        Account accountInserted = accountDao.insert(new Account("Maria"));
        accountInserted.setBalance(new BigDecimal(2000));

        Account accountBalanceUpdated = accountDao.updateBalance(accountInserted);

        assertThat(accountBalanceUpdated.getName()).isEqualTo("Maria");
        assertThat(accountBalanceUpdated.getBalance()).isEqualTo(new BigDecimal(2000));
    }

    @Test
    public void updateBalanceByTransfer() {
        Account accountSender = accountDao.insert(new Account("Rafael"));
        accountSender.deposit(new BigDecimal(1000));
        accountSender = accountDao.update(accountSender);

        assertThat(accountSender.getBalance()).isEqualTo(new BigDecimal(1000).setScale(2));

        Account accountReceiver = accountDao.insert(new Account("Maria"));
        accountReceiver.deposit(new BigDecimal(500).setScale(2));
        accountReceiver = accountDao.update(accountReceiver);

        assertThat(accountReceiver.getBalance()).isEqualTo(new BigDecimal(500).setScale(2));

        //transfer

        accountSender.withdraw(new BigDecimal(100));
        accountReceiver.deposit(new BigDecimal(100));

        accountDao.updateBalanceByTransfer(accountSender, accountReceiver);

        accountSender = accountDao.findById(accountSender.getId());
        accountReceiver = accountDao.findById(accountReceiver.getId());

        assertThat(accountSender.getBalance()).isEqualTo(new BigDecimal(900).setScale(2));
        assertThat(accountReceiver.getBalance()).isEqualTo(new BigDecimal(600).setScale(2));

    }

    @After
    public void tearDown() {
        accountDao.deleteAll();
    }

}
