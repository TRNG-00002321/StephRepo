package com.revature.expense.utils;

import com.revature.expense.model.User;

public class Session
{
    private static User current_user;

    public static void setCurrentUser(User u){ current_user = u;}

    public static User getCurrentUser(){return current_user;}

    public static void logout(){ current_user = null;}

    public static boolean isLoggedIn(){return current_user != null;}
}
