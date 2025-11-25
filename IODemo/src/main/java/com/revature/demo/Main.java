package com.revature.demo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.revature.demo.serialize.Employee;
import java.util.*;
import java.io.*;

public class Main
{

    static void main(String[] args)
    {
        ObjectMapper om = new ObjectMapper();

        Employee e1 = new Employee(101,"meowdy","345 read lane","QEA");
        Employee e2 = new Employee(102,"pardner","123 read line","QA");

        Map<Integer, Employee> emps = new HashMap<>();

        emps.put(1,e1);
        emps.put(2,e2);
        //System.out.println(emps);
        try{

        om.writeValue(new File("employeejson.json"),emps);

        Map<Integer, Employee> readMap = om.readValue(
                new File("employeejson.json"),
                new TypeReference<Map<Integer, Employee>>(){}
        );

        for(int i = 0; i< readMap.size(); i++)
        {
            Employee e = readMap.get(i+1);
            System.out.println(e);
        }





        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
