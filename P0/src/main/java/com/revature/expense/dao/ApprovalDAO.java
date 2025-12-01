package com.revature.expense.dao;

import com.revature.expense.model.Approval;

public interface ApprovalDAO {
    Approval findByExpense(int expenseId) throws Exception; // null if none
    boolean createPendingForExpense(int expenseId) throws Exception;
    boolean setDecision(int expenseId, String status, Integer reviewerId, String comment) throws Exception;
}
