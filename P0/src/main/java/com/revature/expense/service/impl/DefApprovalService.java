package com.revature.expense.service.impl;

import com.revature.expense.dao.*;
import com.revature.expense.dao.impl.ApprovalDAOImpl;
import com.revature.expense.dao.impl.ExpenseDAOImpl;
import com.revature.expense.model.Approval;
import com.revature.expense.model.Expense;
import com.revature.expense.service.ApprovalService;

public class DefApprovalService implements ApprovalService {
    private final ApprovalDAO approvalDao = new ApprovalDAOImpl();
    private final ExpenseDAO expenseDao = new ExpenseDAOImpl();

    public DefApprovalService() { }

    @Override
    public Approval getApprovalForExpense(int expenseId) throws Exception {
        return approvalDao.findByExpense(expenseId);
    }

    @Override
    public boolean approve(int expenseId, int reviewerId, String comment) throws Exception {
        Expense e = expenseDao.findById(expenseId);
        if (e == null) return false;
        Approval a = approvalDao.findByExpense(expenseId);
        if (a != null && !"pending".equalsIgnoreCase(a.getStatus())) return false;
        return approvalDao.setDecision(expenseId, "approved", reviewerId, comment);
    }

    @Override
    public boolean deny(int expenseId, int reviewerId, String comment) throws Exception {

        Expense e = expenseDao.findById(expenseId);
        if (e == null) return false;
        Approval a = approvalDao.findByExpense(expenseId);
        if (a != null && !"pending".equalsIgnoreCase(a.getStatus())) return false;
        return approvalDao.setDecision(expenseId, "denied", reviewerId, comment);
    }
}
