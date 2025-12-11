package com.revature.users.dao;

public interface EmailClient {
    void send(String to, String subject, String body);
}
