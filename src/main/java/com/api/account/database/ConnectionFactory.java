package com.api.account.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            String databaseUrl = "jdbc:h2:~/test";
            String databaseUser = "sa";
            String databasePassword = "";

            return DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to database: ", ex);
        }
    }
}
