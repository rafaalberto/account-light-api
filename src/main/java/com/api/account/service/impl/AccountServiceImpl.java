package com.api.account.service.impl;

import com.api.account.exception.BusinessException;
import com.api.account.model.Account;
import com.api.account.repository.AccountDao;
import com.api.account.repository.impl.AccountDaoImpl;
import com.api.account.service.AccountService;

import java.util.List;

import static com.api.account.constants.HttpConstants.HTTP_BAD_REQUEST_STATUS;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public AccountServiceImpl() {
        accountDao = new AccountDaoImpl();
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account findById(Long id) {
        Account account = accountDao.findById(id);
        if(account == null || account.getId() == null) {
            throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "Account not found");
        }
        return account;
    }

    @Override
    public void save(Account account) {
        if(account.getId() == null) {
            accountDao.insert(account);
        }else {
            accountDao.update(account);
        }
    }

    @Override
    public void delete(Long id) {
        accountDao.delete(id);
    }
}
