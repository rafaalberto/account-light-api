package com.api.account.repository.impl.service.impl;

import com.api.account.repository.impl.exception.BusinessException;
import com.api.account.repository.impl.repository.impl.AccountDaoImpl;
import com.api.account.repository.impl.service.AccountService;
import com.api.account.repository.impl.model.Account;
import com.api.account.repository.impl.repository.AccountDao;

import java.util.List;
import java.util.Optional;

import static com.api.account.repository.impl.constants.HttpConstants.HTTP_BAD_REQUEST_STATUS;
import static java.util.Optional.*;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public AccountServiceImpl() {
        accountDao = new AccountDaoImpl();
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = accountDao.findAll();
        if(accounts.isEmpty()) {
            throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "No accounts found");
        }
        return accounts;
    }

    @Override
    public Account findById(Long id) {
        Optional<Account> account = ofNullable(accountDao.findById(id));
        return account.orElseThrow(() -> new BusinessException(HTTP_BAD_REQUEST_STATUS, "Account not found"));
    }

    @Override
    public void save(Account account) {
        if(account.getId() == null) {
            accountDao.insert(account);
        }else {
            verifyIfExists(account.getId());
            accountDao.update(account);
        }
    }

    @Override
    public void delete(Long id) {
        Account account = findById(id);
        if(account != null) {
            accountDao.delete(account.getId());
        }
    }

    @Override
    public void updateBalance(Account account) {
        accountDao.updateBalance(account);
    }

    @Override
    public void updateBalanceByTransaction(Account accountSender, Account accountReceiver) {
        accountDao.updateBalanceByTransfer(accountSender, accountReceiver);
    }

    private void verifyIfExists(Long id) {
        Optional<Account> account = ofNullable(accountDao.findById(id));
        if (account.isPresent()) {
            return;
        }
        throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "Account not found");
    }
}
