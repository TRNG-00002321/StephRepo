package com.revature.expense.dao;

import com.revature.expense.model.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseDAO {
    Expense findById(int id) throws Exception;
    List<Expense> findPendingExpenses() throws Exception;
    List<Expense> findByUser(int userId) throws Exception;

    List<EmployeeTotal> reportByEmployee() throws SQLException;
    List<CategoryTotal> reportByCategory() throws SQLException;
    List<DateTotal> reportByDateRange(LocalDateTime from, LocalDateTime to) throws SQLException;
}
