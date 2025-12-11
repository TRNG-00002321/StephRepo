package com.revature.unit;

import java.util.HashMap;
import java.util.Map;

// MockDatabase.java
public class MockDatabase {
    private Map<Integer, UserSimple> users = new HashMap<>();
    private boolean connected = false;

    public void connect() {
        connected = true;
        //System.out.println("[DB] Connected");
    }

    public void disconnect() {
        connected = false;
        users.clear();
        //System.out.println("[DB] Disconnected");
    }

    public void insert(UserSimple user) {
        if (!connected) throw new IllegalStateException("Not connected");
        users.put(user.getId(), user);
    }

    public UserSimple findById(int id) {
        return users.get(id);
    }

    public void clearAll() {
        users.clear();
        //System.out.println("[DB] All data cleared");
    }

    public int count() {
        return users.size();
    }
}

