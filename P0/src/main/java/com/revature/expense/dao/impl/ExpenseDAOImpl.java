package com.revature.expense.dao.impl;

import com.revature.expense.dao.ExpenseDAO;
import com.revature.expense.model.Expense;
import com.revature.expense.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOImpl implements ExpenseDAO {
    @Override
    public int create(Expense e) throws SQLException {
        String sql = "INSERT INTO expenses (user_id, amount, description, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, e.getUserId());
            ps.setDouble(2, e.getAmount());
            ps.setString(3, e.getDescription());
            ps.setString(4, e.getDate());
            ps.executeUpdate();
            try (ResultSet gk = ps.getGeneratedKeys()) {
                return gk.next() ? gk.getInt(1) : -1;
            }
        }
    }

    @Override
    public Expense findById(int id) throws SQLException {
        String sql = "SELECT id, user_id, amount, description, date FROM expenses WHERE id = ?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Expense(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("amount"),
                            rs.getString("description"), rs.getString("date"));
                }
                return null;
            }
        }
    }

    @Override
    public List<Expense> findPendingExpenses() throws SQLException {
        String sql = "SELECT e.id, e.user_id, e.amount, e.description, e.date " +
                "FROM expenses e LEFT JOIN approvals a ON e.id = a.expense_id " +
                "WHERE a.status IS NULL OR a.status = 'pending' " +
                "ORDER BY e.date, e.id";
        List<Expense> out = new ArrayList<>();
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Expense(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("amount"),
                        rs.getString("description"), rs.getString("date")));
            }
        }
        return out;
    }

    @Override
    public List<Expense> findByUser(int userId) throws SQLException {
        String sql = "SELECT id, user_id, amount, description, date FROM expenses WHERE user_id = ? ORDER BY date";
        List<Expense> out = new ArrayList<>();
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Expense(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("amount"),
                            rs.getString("description"), rs.getString("date")));
                }
            }
        }
        return out;
    }
}
