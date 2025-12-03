package com.revature.expense.service.impl;

import com.revature.expense.dao.ApprovalDAO;
import com.revature.expense.dao.impl.ApprovalDAOImpl;
import com.revature.expense.model.Approval;
import com.revature.expense.service.ApprovalService;

public class DefApprovalService implements ApprovalService {
    private final ApprovalDAO approvalDao = new ApprovalDAOImpl();

    public DefApprovalService() { }

    @Override
    public Approval getApprovalForExpense(int expenseId) throws Exception {
        return approvalDao.findByExpense(expenseId);
    }

    @Override
    public boolean approve(int expenseId, int reviewerId, String comment) throws Exception {
        return approvalDao.setDecision(expenseId, "approved", reviewerId, comment);
    }

    @Override
    public boolean deny(int expenseId, int reviewerId, String comment) throws Exception {
        return approvalDao.setDecision(expenseId, "denied", reviewerId, comment);
    }
}
