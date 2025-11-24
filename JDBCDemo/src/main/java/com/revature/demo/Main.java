package com.revature.demo;

import com.revature.dao.*;
import com.revature.model.Contacts;
import com.revature.service.ContactServiceImpl;
import com.revature.service.ContactsService;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {
//        ContactDAO cdao = new ContactsJDBCImpl();
//        Contacts contact = cdao.getContacts(2);

        ContactsService cs = new ContactServiceImpl();
        Contacts contacts = cs.getContacts(2);
        System.out.println(contacts);

    }
}
