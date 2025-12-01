package com.revature.expense.service.impl;

import com.revature.expense.dao.ExpenseDAO;
import com.revature.expense.model.Expense;
import com.revature.expense.service.ExpenseService;

import java.util.List;

public class DefExpenseService implements ExpenseService {
    private final ExpenseDAO expenseDao;

    public DefExpenseService(ExpenseDAO expenseDao) { this.expenseDao = expenseDao; }

    @Override
    public int submitExpense(Expense e) throws Exception {
        return expenseDao.create(e);
    }

    @Override
    public Expense getExpense(int id) throws Exception {
        return expenseDao.findById(id);
    }

    @Override
    public List<Expense> listPending() throws Exception {
        return expenseDao.findPendingExpenses();
    }

    @Override
    public List<Expense> listByUser(int userId) throws Exception {
        return expenseDao.findByUser(userId);
    }
}
