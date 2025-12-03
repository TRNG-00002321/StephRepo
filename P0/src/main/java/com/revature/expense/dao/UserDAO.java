package com.revature.expense.dao;

import com.revature.expense.model.User;
import java.sql.SQLException;

public interface UserDAO {
    User findByUsername(String username) throws SQLException;
    int create(User user) throws SQLException;
}