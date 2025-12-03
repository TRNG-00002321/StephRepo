package com.revature.expense.service.impl;

import com.revature.expense.dao.UserDAO;
import com.revature.expense.dao.impl.UserDAOImpl;
import com.revature.expense.model.User;
import com.revature.expense.service.UserService;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class DefUserService implements UserService {
    private final UserDAO userDao = new UserDAOImpl();

    public DefUserService()
    {
    }

    @Override
    public User authenticate(String username, String password) throws Exception {
        User u = userDao.findByUsername(username);
        if (u == null) return null;

        if (u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

    @Override
    public int register(String username, String password, String role) throws Exception {
      return userDao.create(new User(-1, username, password, role));
    }
}
