package com.revature.expense.service;

import com.revature.expense.model.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseService {

    Expense getExpense(int id) throws Exception;
    List<Expense> listPending() throws Exception;
    List<Expense> listByUser(int userId) throws Exception;

    List<EmployeeTotal> reportByEmployee() throws SQLException;
    List<CategoryTotal> reportByCategory() throws SQLException;
    List<DateTotal> reportByDateRange(LocalDateTime from, LocalDateTime to) throws SQLException;
}
