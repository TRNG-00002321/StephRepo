package com.revature.demo.serialize;

import java.io.*;

public class ReadEmpObj
{
    static void main(String[] args) {
        FileInputStream fis;
        ObjectInputStream ois;

        try{
            fis = new FileInputStream("employee.dat");
            ois = new ObjectInputStream(fis);

            Employee e1 = (Employee) ois.readObject();
            System.out.println(e1);

        }catch (IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
}
