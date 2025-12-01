package com.revature.expense.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class ConnectionManager
{
    private static final Logger log = LoggerFactory.getLogger(ConnectionManager.class);
    private static final String DB_URL = "jdbc:sqlite:C:/Users/Stephenie/Documents/Revature/StephRepo/PyCharmPractice/Project1/expenses.db";

    private static Connection conn;

    private ConnectionManager(){}

    public static synchronized Connection getConnection() throws SQLException
    {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DB_URL);
            try (Statement s = conn.createStatement()) {
                s.execute("PRAGMA foreign_keys = ON;");
            }
            log.info("Opened SQLite connection to {}", DB_URL);
        }
        return conn;
    }

    public static synchronized void closeConnection() {
        if (conn == null) return;
        try {
            conn.close();
            log.info("Closed connection");
        } catch (SQLException e) {
            log.error("Error closing connection", e);
        } finally {
            conn = null;
        }
    }
}
