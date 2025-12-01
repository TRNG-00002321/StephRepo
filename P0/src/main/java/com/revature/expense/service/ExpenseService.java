package com.revature.expense.service;

import com.revature.expense.model.Expense;
import java.util.List;

public interface ExpenseService {
    int submitExpense(Expense e) throws Exception;
    Expense getExpense(int id) throws Exception;
    List<Expense> listPending() throws Exception;
    List<Expense> listByUser(int userId) throws Exception;
}
