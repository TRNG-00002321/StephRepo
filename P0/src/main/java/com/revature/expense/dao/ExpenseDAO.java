package com.revature.expense.dao;

import com.revature.expense.model.Expense;
import java.util.List;

public interface ExpenseDAO {
    int create(Expense e) throws Exception;
    Expense findById(int id) throws Exception;
    List<Expense> findPendingExpenses() throws Exception;
    List<Expense> findByUser(int userId) throws Exception;
}
