package com.revature.unit;

// UserRepository.java
public class UserRepository {
    private final MockDatabase database;

    public UserRepository(MockDatabase database) {
        this.database = database;
    }

    public void save(UserSimple user) {
        database.insert(user);
    }

    public UserSimple findById(int id) {
        return database.findById(id);
    }

    public int count() {
        return database.count();
    }
}
