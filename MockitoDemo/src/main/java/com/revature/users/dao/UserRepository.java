package com.revature.users.dao;

import com.revature.users.model.User;

public interface UserRepository
{
    User findById(Long id);
    void save(User user);
    User findByEmail(String email);
}
