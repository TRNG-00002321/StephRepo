package com.revature.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersonListDemo
{
    static void main(String[] args) {
        Person p1 = new Person(1,"Bob",23);
        Person p2 = new Person(2,"Katie",34);
        Person p3 = new Person(3,"Joe",28);
        Person p4 = new Person(4,"Alice",25);
        Person p5 = new Person(5,"Peter",32);

        List<Person> vals = new ArrayList<Person>();
        vals.add(p1);
        vals.add(p2);
        vals.add(p3);
        vals.add(p4);
        vals.add(p5);

        for(Person i : vals)
        System.out.println(i);

        Comparator myComparator = new SortbyAge();

        Collections.sort(vals, myComparator);
        System.out.println(vals);




    }
}
