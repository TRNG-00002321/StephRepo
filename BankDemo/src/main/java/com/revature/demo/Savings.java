package com.revature.demo;

public class Savings extends Bank implements SimpleInterest
{
    public Savings() {
    }

    public Savings(String id, String name, double balance) {
        super(id, name, balance);
    }

    public void deposit(double amount)
    {
        if(amount<0)
            throw new ArithmeticException("can't use negative values");
        else
            super.deposit(amount);
    }

    @Override
    public void withdraw(double amount) throws UnavailableBalanceException
    {
        if(amount<0)
            throw new ArithmeticException("can't use negative values");
        else if(super.getBalance()-amount < 5000)
            throw new ArithmeticException("This puts your balance below $5,000");
        else
            super.setBalance(super.getBalance()-amount);
    }


    @Override
    public String toString()
    {
        return "Checking{}" + super.toString();
    }

    @Override
    public double calcInterest(double percentage)
    {
        return super.getBalance() * percentage;
    }
}
