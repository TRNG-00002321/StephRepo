package com.revature.expense.dao;

import com.revature.expense.model.User;

public interface UserDAO {
    User findByUsername(String username) throws Exception;
    int create(User user) throws Exception;
}