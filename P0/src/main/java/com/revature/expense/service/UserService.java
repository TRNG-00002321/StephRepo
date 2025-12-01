package com.revature.expense.service;
import com.revature.expense.model.User;

public interface UserService
{
    User authenticate(String username, String password) throws Exception;
    int register(String username, String password, String role) throws Exception;
}
