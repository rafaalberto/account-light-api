package com.api.account.service.impl;

import com.api.account.repository.impl.AccountDaoImpl;
import com.api.account.exception.BusinessException;
import com.api.account.model.Account;
import com.api.account.repository.AccountDao;
import com.api.account.service.AccountService;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.api.account.utils.HttpUtils.HTTP_BAD_REQUEST_STATUS;
import static com.api.account.utils.HttpUtils.HTTP_NOT_FOUND_STATUS;
import static java.util.Optional.ofNullable;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public AccountServiceImpl() {
        accountDao = new AccountDaoImpl();
    }

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = accountDao.findAll();
        if(accounts.isEmpty()) {
            throw new BusinessException(HTTP_NOT_FOUND_STATUS, "No accounts found");
        }
        return accounts;
    }

    @Override
    public Account findById(Long id) {
        Optional<Account> account = ofNullable(accountDao.findById(id));
        return account.orElseThrow(() -> new BusinessException(HTTP_NOT_FOUND_STATUS, "Account not found"));
    }

    @Override
    public Account save(Account account) {
        verifyData(account);
        if(account.getId() == null) {
            return accountDao.insert(account);
        }else {
            return accountDao.update(account);
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

    private void verifyData(Account account) {
        if(StringUtils.isBlank(account.getName())) {
            throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "Name must be informed");
        }
        if(account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            throw new BusinessException(HTTP_BAD_REQUEST_STATUS, "Balance is not allowed to be saved for this operation");
        }
        if(account.getId() != null) {
            verifyIfExists(account.getId());
        }
    }

    private void verifyIfExists(Long id) {
        Optional<Account> account = ofNullable(accountDao.findById(id));
        if (account.isPresent()) {
            return;
        }
        throw new BusinessException(HTTP_NOT_FOUND_STATUS, "Account not found");
    }

}
