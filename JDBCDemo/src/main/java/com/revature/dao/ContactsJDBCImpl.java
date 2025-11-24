package com.revature.dao;

import com.revature.model.Contacts;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsJDBCImpl implements ContactDAO
{
    Connection conn = null;
    public Contacts getContact(int id){
        conn = ConnectionUtil.dbConnection();
        Contacts contacts = null;
        String getContact = "select * from primecontacts where id=?";
        try{
            PreparedStatement ps = conn.prepareStatement(getContact);
            ps.setInt(1,3);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                contacts = new Contacts(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }
}
