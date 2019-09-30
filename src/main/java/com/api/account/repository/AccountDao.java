package com.api.account.repository;

import com.api.account.database.ConnectionFactory;
import com.api.account.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    private Connection connection;

    public AccountDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void save(Account account) {
        String sql = "insert into accounts (id, name) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, account.getId());
            preparedStatement.setString(2, account.getName());
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
}
