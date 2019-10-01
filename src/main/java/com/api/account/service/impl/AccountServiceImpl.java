package com.api.account.service.impl;

import com.api.account.model.Account;
import com.api.account.repository.AccountDao;
import com.api.account.repository.impl.AccountDaoImpl;
import com.api.account.service.AccountService;

import java.util.List;

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
        return accountDao.findById(id);
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
