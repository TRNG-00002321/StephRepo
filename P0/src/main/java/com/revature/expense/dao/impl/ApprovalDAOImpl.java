package com.revature.expense.dao.impl;

import com.revature.expense.dao.ApprovalDAO;
import com.revature.expense.model.Approval;
import com.revature.expense.utils.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;

public class ApprovalDAOImpl implements ApprovalDAO {
    @Override
    public Approval findByExpense(int expenseId) throws SQLException {
        String sql = "SELECT id, expense_id, status, reviewer, comment, review_date FROM approvals WHERE expense_id = ?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, expenseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Integer reviewer = rs.getObject("reviewer") == null ? null : rs.getInt("reviewer");
                    return new Approval(rs.getInt("id"), rs.getInt("expense_id"), rs.getString("status"),
                            reviewer, rs.getString("comment"), rs.getString("review_date"));
                }
                return null;
            }
        }
    }

    @Override
    public boolean createPendingForExpense(int expenseId) throws SQLException {
        if (findByExpense(expenseId) != null) return false;
        String sql = "INSERT INTO approvals (expense_id, status) VALUES (?, 'pending')";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, expenseId);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean setDecision(int expenseId, String status, Integer reviewerId, String comment) throws SQLException {
        String now = LocalDate.now().toString();
        String sql = "UPDATE approvals SET status = ?, reviewer = ?, comment = ?, review_date = ? WHERE expense_id = ?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, status);
            if (reviewerId == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, reviewerId);
            ps.setString(3, comment);
            ps.setString(4, now);
            ps.setInt(5, expenseId);
            return ps.executeUpdate() > 0;
        }


    }
}
