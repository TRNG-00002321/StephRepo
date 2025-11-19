package com.revature.demo;

import java.util.Comparator;
import java.util.Objects;

public class Person implements Comparable<Person>
{
    private int id;
    private String name;
    private int age;
    private int salary;

    public Person()
    {

    }

    public Person(int id, String name,int salary)
    {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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
        return salary == person.salary && Objects.equals(name,person.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(salary);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id +
                " name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return id - o.getId();
    }
}

