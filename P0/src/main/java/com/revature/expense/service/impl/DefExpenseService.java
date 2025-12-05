package com.revature.expense.service.impl;

import com.revature.expense.dao.ExpenseDAO;
import com.revature.expense.dao.impl.ExpenseDAOImpl;
import com.revature.expense.model.CategoryTotal;
import com.revature.expense.model.DateTotal;
import com.revature.expense.model.EmployeeTotal;
import com.revature.expense.model.Expense;
import com.revature.expense.service.ExpenseService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class DefExpenseService implements ExpenseService {
    private final ExpenseDAO expenseDao = new ExpenseDAOImpl();

    public DefExpenseService() { }



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

    @Override
    public List<EmployeeTotal> reportByEmployee() throws SQLException {
        return expenseDao.reportByEmployee();
    }

    @Override
    public List<CategoryTotal> reportByCategory() throws SQLException {
        return expenseDao.reportByCategory();
    }

    @Override
    public List<DateTotal> reportByDateRange(LocalDateTime from, LocalDateTime to) throws SQLException {
        return expenseDao.reportByDateRange(from, to);
    }
}
