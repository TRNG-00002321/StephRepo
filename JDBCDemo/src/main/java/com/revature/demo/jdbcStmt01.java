package com.revature.demo;

import java.sql.*;

public class jdbcStmt01
{
    static void main(String[] args) {

        Connection conn = null;
        Statement sm = null;
        ResultSet rs = null;

        try{
            //Step 1: get driver
            //Class.forName("com.mysql.cj.jdbc.Driver"); (Optional)
            //Step 2: create connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb2","root","tacocat");
            //Step 3: Create the statement object
            sm = conn.createStatement();
            String selectQuery = "select * from contact";

            //Step 4: execute the query and get result set back;
            rs = sm.executeQuery(selectQuery);

            //Step 5: process the result set
            while(rs.next())
            {
                System.out.println(rs.getInt("id") + ", " +
                        rs.getString(2).toUpperCase() + ", " + rs.getString("email"));
            }




        } //catch (ClassNotFoundException cle)
//        {
//            cle.printStackTrace();
//        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }

        System.out.println("Database Connected....");



    }
}
