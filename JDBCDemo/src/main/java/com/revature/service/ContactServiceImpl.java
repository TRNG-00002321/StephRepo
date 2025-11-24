package com.revature.service;

import com.revature.dao.ContactDAO;
import com.revature.dao.ContactsJDBCImpl;
import com.revature.model.Contacts;

public class ContactServiceImpl implements ContactsService
{
    public Contacts getContact(int id){
        ContactDAO cdao = new ContactsJDBCImpl();
        Contacts contact = null;
        if(id>0)
        {
            contact = cdao.getContacts(id);
        }
        else {
            return null;
        }
        return contact;
    }
}
