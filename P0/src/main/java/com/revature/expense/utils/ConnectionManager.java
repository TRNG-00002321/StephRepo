package com.revature.expense.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public final class ConnectionManager
{
    private static final Logger log = LoggerFactory.getLogger(ConnectionManager.class);
    private static final String DB_URL = "jdbc:mysql://localhost:3306/expense_manager";
    private static final String DB_ROOT = "root";
    private static final String DB_PASSWORD = "manage";

    private static Connection conn;

    private ConnectionManager(){}

    public static Connection getConnection() throws SQLException
    {
        if (conn == null || conn.isClosed())
        {
            conn = DriverManager.getConnection(DB_URL,DB_ROOT,DB_PASSWORD);
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
