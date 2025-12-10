package com.revature.users.service;

import com.revature.users.dao.UserRepository;
import com.revature.users.model.User;

public class UserService
{
    private UserRepository ur;

    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    public User getUserById(Long id){
        return ur.findById(id);
    }

    public User getUserByEmail(String email){
        return ur.findByEmail(email);
    }

    public boolean register(User user){
        if(ur.findByEmail(user.getEmail()) != null) return false;

        ur.save(user);
        return true;
    }
}
