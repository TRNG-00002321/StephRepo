package com.revature.demo;

import java.util.Comparator;

public class SortbyName implements Comparator<Person>
{

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public Comparator<Person> reversed() {
        return Comparator.super.reversed();
    }
}

