package com.revature.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo
{
    static void main(String[] args)
    {
        List<Double> vals = new LinkedList<Double>();
        vals.add(23.5);
        vals.add(0,12.3);

        vals.addAll(vals);

        if (vals.contains(23.5)) System.out.println("Has 23.5");

        vals.clear();

        System.out.println(vals.isEmpty());
        Collection<Double> c = new ArrayList<Double>();
        c.add(12.3);
        c.add(34.5);
        c.add(56.7);
        c.add(56.7);
        vals.addAll(c);
        System.out.println(vals);

    }
}
