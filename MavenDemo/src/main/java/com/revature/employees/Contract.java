package com.revature.employees;

public class Contract extends Employee
{
    private double rate;

    public Contract(String id,String name,int rate)
    {
        super(id,name);
        this.rate = rate;
    }
    @Override
    public void addHours(double hours)
    {
        super.setTime(super.getTime()+hours);
    }

    @Override
    public double salaryPay() //based off of time worked in hours
    {
        return rate * super.getTime();
    }

    @Override
    public String toString()
    {
        return "Contract{} " + super.toString() + " Hourly Rate: " + rate;
    }
}
