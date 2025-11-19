package com.revature.demo;

import java.util.*;
import java.util.stream.Collectors;

public class PersonListDemo
{
    static void main(String[] args) {
        Person p1 = new Person(1,"Bob",23000);
        Person p2 = new Person(2,"Katie",34000);
        Person p3 = new Person(3,"Joe",28000);
        Person p4 = new Person(4,"Alice",25000);
        Person p5 = new Person(5,"Peter",32000);

        List<Person> vals = new ArrayList<Person>();
        vals.add(p1);
        vals.add(p2);
        vals.add(p3);
        vals.add(p4);
        vals.add(p5);

        System.out.println("Enhance For Loop");
        Iterator iter = vals.iterator();
        while(iter.hasNext())
        {
            System.out.println(iter.next());
        }

        System.out.println("\nFor Each Loop");
        for(Person i : vals)
        System.out.println(i);

        List<Person> munsPeeps = vals.stream()
                .filter(x->x.getSalary() > 27000)
                .collect(Collectors.toList());

        System.out.println(vals.stream()
                .map(x->x.getName().toUpperCase())
                .collect(Collectors.toList()));

        System.out.println(munsPeeps);





//        Comparator myComparator = new SortbyAge();
//
//        Collections.sort(vals, myComparator);




    }
}
