package com.revature.demo;

import java.util.Comparator;

public class SortbyAge implements Comparator<Person>
{

    @Override
    public int compare(Person o1, Person o2) {return o1.getSalary() - o2.getSalary();}

    @Override
    public Comparator<Person> reversed() {
        return Comparator.super.reversed();
    }
}
