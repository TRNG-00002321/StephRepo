package com.revature.expense.service;

import com.revature.expense.model.Approval;

public interface ApprovalService {
    Approval getApprovalForExpense(int expenseId) throws Exception; // null if none
    boolean approve(int expenseId, int reviewerId, String comment) throws Exception;
    boolean deny(int expenseId, int reviewerId, String comment) throws Exception;
}
