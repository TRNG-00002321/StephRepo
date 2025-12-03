package com.revature.expense.utils;

import java.sql.Connection;
import java.sql.Statement;

public final class DBInitializer
{
    public static void ensureSchema() throws Exception
    {
        try (Connection c = ConnectionManager.getConnection();
             Statement s = c.createStatement()) {

            s.execute("""
                CREATE TABLE IF NOT EXISTS users (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  username TEXT NOT NULL UNIQUE,
                  password TEXT NOT NULL,
                  role TEXT DEFAULT 'Employee'
                );
            """);

            s.execute("""
                CREATE TABLE IF NOT EXISTS expenses (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  user_id INTEGER NOT NULL,
                  amount REAL NOT NULL DEFAULT 0.0,
                  description TEXT,
                  clock TEXT NOT NULL,
                  FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
                );
            """);

            s.execute("""
                CREATE TABLE IF NOT EXISTS approvals (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  expense_id INTEGER NOT NULL,
                  status TEXT NOT NULL DEFAULT 'pending',
                  reviewer INTEGER,
                  comment TEXT,
                  review_date TEXT,
                  FOREIGN KEY(expense_id) REFERENCES expenses(id) ON DELETE CASCADE,
                  FOREIGN KEY(reviewer) REFERENCES users(id) ON DELETE SET NULL
                );
            """);
        }
    }
}

