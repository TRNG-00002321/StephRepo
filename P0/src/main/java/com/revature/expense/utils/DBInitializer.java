package com.revature.expense.utils;

import java.sql.Connection;
import java.sql.Statement;

public final class DBInitializer
{
    public static void ensureSchema() throws Exception
    {
        try (Connection c = ConnectionManager.getConnection();
             Statement s = c.createStatement()) {

            s.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT, " +
                            "username VARCHAR(50) NOT NULL UNIQUE, " +
                            "password VARCHAR(100) NOT NULL, " +
                            "role VARCHAR(20) DEFAULT 'Employee'" +
                            ");"
            );


            s.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS expenses (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT, " +
                            "user_id INT NOT NULL, " +
                            "amount DOUBLE NOT NULL DEFAULT 0.0, " +
                            "description VARCHAR(255), " +
                            "clock DATETIME NOT NULL, " +
                            "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE" +
                            ");"
            );


            s.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS approvals (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT, " +
                            "expense_id INT NOT NULL, " +
                            "status VARCHAR(20) NOT NULL DEFAULT 'Pending', " +
                            "reviewer_id INT DEFAULT NULL, " +
                            "comment VARCHAR(255) DEFAULT '', " +
                            "clock DATETIME, " +
                            "FOREIGN KEY (expense_id) REFERENCES expenses(id) ON DELETE CASCADE" +
                            ");"
            );

        }
    }
}

