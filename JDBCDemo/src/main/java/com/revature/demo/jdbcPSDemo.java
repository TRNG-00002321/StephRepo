package com.revature.demo;

import java.sql.*;

public class jdbcPSDemo
{
    static Connection conn = null;
    static PreparedStatement sm = null;
    static ResultSet rs = null;

    static void main(String[] args) {
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb2","root","tacocat");
            String insertQuery = "insert into primecontacts(name,email,phonenumber) values(?,?,?)";

            sm = conn.prepareStatement(insertQuery);
            sm.setString(1,"Stephenie");
            sm.setString(2,"emailTest4@email.org");
            sm.setString(3,"1234567890");
            sm.execute();

            String selectQuery = "select * from primecontacts where name like ?";
            sm = conn.prepareStatement(selectQuery);
            sm.setString(1,"Perry");

            rs = sm.executeQuery();

            while(rs.next())
            {
                System.out.println(rs.getInt("id") + ", " +
                        rs.getString(2).toUpperCase() + ", " + rs.getString("email")
                        + ", " + rs.getString("phonenumber"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
