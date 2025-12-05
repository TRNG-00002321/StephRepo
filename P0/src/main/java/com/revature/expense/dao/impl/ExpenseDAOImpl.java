package com.revature.expense.dao.impl;

import com.revature.expense.dao.ExpenseDAO;
import com.revature.expense.model.CategoryTotal;
import com.revature.expense.model.DateTotal;
import com.revature.expense.model.EmployeeTotal;
import com.revature.expense.model.Expense;
import com.revature.expense.utils.ConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOImpl implements ExpenseDAO {

    @Override
    public Expense findById(int id) throws SQLException {
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT id, user_id, amount, description, clock FROM expenses WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Expense(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("amount"),
                            rs.getString("description"), rs.getString("clock"));
                }
                return null;
            }
        }
    }

    @Override
    public List<Expense> findPendingExpenses() throws SQLException {
        String sql = "SELECT e.id, e.user_id, e.amount, e.description, e.clock " +
                "FROM expenses e LEFT JOIN approvals a ON e.id = a.expense_id " +
                "WHERE a.status IS NULL OR a.status = 'pending' " +
                "ORDER BY e.clock, e.id";
        List<Expense> out = new ArrayList<>();
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Expense(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("amount"),
                        rs.getString("description"), rs.getString("clock")));
            }
        }
        return out;
    }

    @Override
    public List<Expense> findByUser(int userId) throws SQLException {
        String sql = "SELECT id, user_id, amount, description, clock FROM expenses WHERE user_id = ? ORDER BY clock";
        List<Expense> out = new ArrayList<>();
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Expense(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("amount"),
                            rs.getString("description"), rs.getString("clock")));
                }
            }
        }
        return out;
    }

    @Override
    public List<EmployeeTotal> reportByEmployee() throws SQLException {
        String sql = """
            SELECT u.id AS user_id, u.username, COALESCE(SUM(e.amount), 0) AS total
            FROM users u
            LEFT JOIN expenses e ON u.id = e.user_id
            WHERE u.role = 'Employee'
            GROUP BY u.id, u.username
            ORDER BY total DESC
            """;
        List<EmployeeTotal> out = new ArrayList<>();
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new EmployeeTotal(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getBigDecimal("total")
                ));
            }
        }
        return out;
    }

    @Override
    public List<CategoryTotal> reportByCategory() throws SQLException {
        String sql = """
            SELECT COALESCE(description, '(none)') AS description, COALESCE(SUM(amount),0) AS total
            FROM expenses
            GROUP BY description
            ORDER BY total DESC
            """;
        List<CategoryTotal> out = new ArrayList<>();
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new CategoryTotal(
                        rs.getString("description"),
                        rs.getBigDecimal("total")
                ));
            }
        }
        return out;
    }

    @Override
    public List<DateTotal> reportByDateRange(LocalDateTime from, LocalDateTime to) throws SQLException {
        String sql = """
            SELECT DATE(clock) AS day, COALESCE(SUM(amount),0) AS total
            FROM expenses
            WHERE clock BETWEEN ? AND ?
            GROUP BY DATE(clock)
            ORDER BY DATE(clock)
            """;
        List<DateTotal> out = new ArrayList<>();
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            // Convert LocalDateTime -> Timestamp
            Timestamp tFrom = Timestamp.valueOf(from);
            Timestamp tTo = Timestamp.valueOf(to);
            ps.setTimestamp(1, tFrom);
            ps.setTimestamp(2, tTo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new DateTotal(
                            rs.getDate("day").toLocalDate(),
                            rs.getBigDecimal("total")
                    ));
                }
            }
        }
        return out;
    }
}
