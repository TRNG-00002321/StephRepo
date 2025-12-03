package com.revature.expense.dao.impl;

import com.revature.expense.dao.UserDAO;
import com.revature.expense.model.User;
import com.revature.expense.utils.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class UserDAOImpl implements UserDAO
{
    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

    @Override
    public User findByUsername(String username) throws SQLException
    {
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement("" +
                "SELECT id, username, password, role FROM users WHERE username = %s")) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                }
                return null;
            }
        }
    }


    @Override
    public int create(User user) throws SQLException {

        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement("" +
                "INSERT INTO users (username, password, role) VALUES (%s, %s, %s)", Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.executeUpdate();
            try (ResultSet gk = ps.getGeneratedKeys()) {
                if (gk.next()) return gk.getInt(1);
                return -1;
            }
        }
    }
}
