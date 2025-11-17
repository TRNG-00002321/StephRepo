package com.revature.employees;

public class Salary extends Employee implements Benefits
{
    private int salary;
    private boolean hasHealthcare;

    public Salary()
    {

    }


    public Salary(String id,String name,int salary)
    {
        super(id,name);
        this.salary = salary;
    }

    @Override
    public void addHours(double days)
    {
        super.setTime(super.getTime() + (days*8));
    }
    @Override
    public double salaryPay() //calculated salary earned so far based on present days
    {
        double rate = salary/2080;  //if $50,000 rate is $24.04 /hr
        return super.getTime()*rate;
    }

    @Override
    public String toString() {
        return "Salary{} " + super.toString() + " Annual Salary: " + salary;
    }

    @Override
    public void Healthcare(boolean hasHealth)
    {
        this.hasHealthcare = hasHealth;
    }
}
