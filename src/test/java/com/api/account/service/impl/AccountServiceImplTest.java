package com.api.account.service.impl;

import com.api.account.exception.BusinessException;
import com.api.account.model.Account;
import com.api.account.repository.AccountDao;
import com.api.account.repository.impl.AccountDaoImpl;
import com.api.account.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static com.api.account.utils.NumericConverter.convertTwoDecimalPlace;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
    public void createAccountSuccessfully() {
        Account accountToCreate = new Account( "Rafael");
        when(accountDao.insert(accountToCreate)).thenReturn(accountInDB);
        Account accountCreated = accountService.save(accountToCreate);

        assertThat(accountCreated.getId()).isEqualTo(1L);
        assertThat(accountCreated.getName()).isEqualTo("Rafael");
        assertThat(accountCreated.getBalance()).isEqualTo(convertTwoDecimalPlace(BigDecimal.ZERO));
    }

    @Test
    public void shouldDenyCreateAccountIfNameNotInformed() {
        Account accountToCreate = new Account( " ");
        when(accountDao.insert(accountToCreate)).thenReturn(accountInDB);

        assertThatExceptionOfType(BusinessException.class).isThrownBy(() ->
                accountService.save(accountToCreate)).withMessage("Name must be informed");
    }

    @Test
    public void shouldDenyCreateAccountIfBalanceGreaterThanZero() {
        Account accountToCreate = new Account( "Rafael");
        accountToCreate.setBalance(convertTwoDecimalPlace(new BigDecimal(3000)));
        when(accountDao.insert(accountToCreate)).thenReturn(accountInDB);

        assertThatExceptionOfType(BusinessException.class).isThrownBy(() ->
                accountService.save(accountToCreate)).withMessage("Balance is not allowed to be saved for this operation");
    }

    @Test
    public void updateAccountSuccessfullyNoBalance() {
        Account accountToUpdate = new Account(1L, "John");
        when(accountDao.findById(1L)).thenReturn(accountInDB);
        when(accountDao.update(accountToUpdate)).thenReturn(accountToUpdate);
        when(accountDao.findById(1L)).thenReturn(accountToUpdate);
        Account accountUpdated = accountService.save(accountToUpdate);

        assertThat(accountUpdated.getId()).isEqualTo(1L);
        assertThat(accountUpdated.getName()).isEqualTo("John");
        assertThat(accountUpdated.getBalance()).isEqualTo(convertTwoDecimalPlace(BigDecimal.ZERO));
    }

    @Test
    public void updateAccountSuccessfullyWithBalance() {

        /* This account already has 3000 in balance */
        accountInDB.setBalance(convertTwoDecimalPlace(new BigDecimal(3000)));
        when(accountDao.findById(1L)).thenReturn(accountInDB);

        /* even if we put the balance to update, the app will not do that,
        because only transactions operations are allowed to do it */
        Account accountToUpdate = new Account(1L, "John");
        accountToUpdate.setBalance(new BigDecimal(5000));

        Account accountUpdated = new Account(1L, "John");
        accountUpdated.setBalance(convertTwoDecimalPlace(new BigDecimal(3000)));
        when(accountDao.update(accountToUpdate)).thenReturn(accountUpdated);
        when(accountDao.findById(1L)).thenReturn(accountUpdated);

        accountUpdated = accountService.save(accountToUpdate);

        assertThat(accountUpdated.getId()).isEqualTo(1L);
        assertThat(accountUpdated.getName()).isEqualTo("John");
        assertThat(accountUpdated.getBalance()).isEqualTo(convertTwoDecimalPlace(new BigDecimal(3000)));
    }

    @Test
    public void shouldDenyUpdateAccountIfNameNotInformed() {
        Account accountToUpdate = new Account(1L, "  ");
        when(accountDao.findById(1L)).thenReturn(accountInDB);
        when(accountDao.update(accountToUpdate)).thenReturn(accountToUpdate);

        assertThatExceptionOfType(BusinessException.class).isThrownBy(() ->
                accountService.save(accountToUpdate)).withMessage("Name must be informed");
    }

}
