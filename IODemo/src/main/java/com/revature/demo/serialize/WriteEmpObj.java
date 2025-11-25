package com.revature.demo.serialize;

import java.io.*;

public class WriteEmpObj
{
    static void main(String[] args) {
        Employee e1 = new Employee(101,"bob","123 readline lane","QEA");
        Employee e2 = new Employee(102,"joe","123 writeline lane","QEAD");

        FileOutputStream fos;
        ObjectOutputStream oos;

        try{
            fos = new FileOutputStream("employee.dat");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(e1);
            oos.writeObject(e2);

        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
