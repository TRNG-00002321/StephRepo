package com.revature.demo;

import java.util.Comparator;
import java.util.Objects;

public class Person implements Comparable<Person>
{
    private int id;
    private String name;
    private int age;

    public Person()
    {

    }

    public Person(int id, String name,int age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name,person.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(age);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id +
                " name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return id - o.getId();
    }
}

