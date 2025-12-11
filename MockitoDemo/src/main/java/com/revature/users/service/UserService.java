package com.revature.users.service;

import com.revature.users.dao.UserNotFoundException;
import com.revature.users.dao.UserRepository;
import com.revature.users.model.User;

import java.util.Objects;

public class UserService
{
    private UserRepository ur;

    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    public User getUser(Long id){
        User u = ur.findById(id);
        if(u == null) {
            throw new UserNotFoundException("User not found.");
        }
        return u;
    }

    public User getUserByEmail(String email){
        return ur.findByEmail(email);
    }

    public boolean register(User user){
        if(ur.findByEmail(user.getEmail()) != null) return false;
        ur.save(user);
        return true;
    }

    public boolean existsByEmail(String email)
    {
        if(ur.findByEmail(email) != null) return true;

        return false;
    }
}
