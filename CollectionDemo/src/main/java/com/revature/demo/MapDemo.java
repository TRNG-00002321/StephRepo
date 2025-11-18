package com.revature.demo;

import java.util.HashMap;
import java.util.Map;

public class MapDemo
{
    static void main(String[] args)
    {
        Map<String,Double> m = new HashMap<>();
        m.put("Alex",100000.0);
        m.put("James",100500.0);
        m.put("Alice",100650.0);
        m.put("Bob",109000.0);
        m.put("Joe",100320.0);

        for(int i =0; i<m.size();i++)
        {
            System.out.println(m.get(i));
        }

    }
}
