package com.revature.employees;

public abstract class Employee
{
    private String id;
    private String name;
    private double time;    //in hours


    public Employee(){}
    public Employee(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public double getTime()
    {
        return time;
    }

    public void setTime(double time)
    {
        this.time = time;
    }

    public abstract void addHours(double hours);

    public abstract double salaryPay();

    @Override
    public String toString()
    {
        return "Employee{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", time=" + this.time +
                '}';
    }
}
