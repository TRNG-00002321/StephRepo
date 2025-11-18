package com.revature.demo;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo
{
    static void main(String[] args) {
        Set<String> v = new LinkedHashSet<String>(); //maintain insertion order
        //Set<String> v = new HashSet<String>(); //maintain insertion order unsorted
        //Set<String> v = new TreeSet<String>(); //maintain insertion order sorted

        v.add("Why");
        v.add("Meowdy");
        v.add("Pardner");

        System.out.println(v);

        v.remove("Why");
        System.out.println(v + " size: " + v.size());

        System.out.println(v.toArray());

        v.clear();
        System.out.println(v);


    }
}
