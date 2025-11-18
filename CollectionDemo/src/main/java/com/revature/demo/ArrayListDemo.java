package com.revature.demo;

import java.util.*;

public class ArrayListDemo
{
    static void main(String[] args) {
        List<Double> vals = new ArrayList<Double>();
        vals.add(23.5);
        vals.add(0,12.3);

        vals.addAll(vals);

        if (vals.contains(23.5)) System.out.println("Has 23.5");

        vals.clear();

        System.out.println(vals.isEmpty());
        List<Double> c = new ArrayList<Double>();
        c.add(12.3);
        c.add(34.5);
        c.add(56.7);
        c.add(56.7);
        vals.addAll(c);
        System.out.println(vals);

    }
}
