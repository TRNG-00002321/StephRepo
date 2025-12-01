package com.revature.expense.service.impl;

import com.revature.expense.dao.UserDAO;
import com.revature.expense.model.User;
import com.revature.expense.service.UserService;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class DefUserService implements UserService {
    private final UserDAO userDao;

    public DefUserService(UserDAO userDao)
    {
        this.userDao = userDao;
    }

    // NOTE: simple SHA-256 used here for demo. Prefer bcrypt/PBKDF2 in production.
    private String hash(String pwd) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(pwd.getBytes(StandardCharsets.UTF_8));
        return String.format("%064x", new BigInteger(1, digest));
    }

    @Override
    public User authenticate(String username, String password) throws Exception {
        User u = userDao.findByUsername(username);
        if (u == null) return null;

        String hashed = hash(password);
        if (u.getPassword().equals(hashed)) {
            return u;
        }
        return null;
    }


    @Override
    public int register(String username, String password, String role) throws Exception {
        String hashed = hash(password);
        return userDao.create(new User(-1, username, hashed, role));
    }
}
