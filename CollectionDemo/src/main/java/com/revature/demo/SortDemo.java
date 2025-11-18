package com.revature.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortDemo
{
    static void main(String[] args)
    {
        List<Double> c = new ArrayList<Double>();
        c.add(12.3);
        c.add(56.7);
        c.add(56.7);
        c.add(34.5);


        System.out.println(c);

        Collections.sort(c);
        System.out.println(c);

        Collections.reverse(c);
        System.out.println(c);






    }
}
