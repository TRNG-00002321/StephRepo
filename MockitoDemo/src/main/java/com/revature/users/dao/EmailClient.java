package com.revature.users.dao;

public interface EmailClient {
    boolean send(String to, String subject, String body);
}
