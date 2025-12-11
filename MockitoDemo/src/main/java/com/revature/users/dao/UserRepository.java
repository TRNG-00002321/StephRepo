package com.revature.users.dao;

import com.revature.users.model.User;

import java.util.List;

public interface UserRepository
{
    User findById(Long id);
    User save(User user);
    User findByEmail(String email);
    void deleteById(Long id);
    List<User> findAllActive();
    boolean existsByEmail(String email);
    long count();
}
