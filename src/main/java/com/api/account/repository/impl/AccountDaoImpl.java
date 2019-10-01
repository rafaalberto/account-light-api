package com.api.account.repository.impl;

import com.api.account.database.ConnectionFactory;
import com.api.account.model.Account;
import com.api.account.repository.AccountDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private Connection connection;

    public AccountDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void insert(Account account) {
        String sql = "insert into accounts (name) values (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getName());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Account account) {
        String sql = "update accounts set name = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getName());
            preparedStatement.setLong(2, account.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from accounts where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Account> findAll() {
        String sql = "select * from accounts";
        try {
            List<Account> accounts = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setName(resultSet.getString("name"));
                accounts.add(account);
            }
            resultSet.close();
            preparedStatement.close();
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account findById(Long id) {
        String sql = "select * from accounts where id = ?";
        try {
            Account account = new Account();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account.setId(resultSet.getLong("id"));
                account.setName(resultSet.getString("name"));
            }
            resultSet.close();
            preparedStatement.close();
            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
