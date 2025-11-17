package com.revature.demo;

public class Checking extends Bank
{

    public Checking() {
    }

    public Checking(String id, String name, double balance) {
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
//        double sur = (amount * 0.01)/100;
//        amount = amount + sur;
        if(amount<0)
            throw new ArithmeticException("can't use negative values");
        else if ((super.getBalance()-(amount + ((amount*0.01)/100))) < 5000)
            throw new ArithmeticException("This puts your balance below $5,000");
        else
            super.setBalance(super.getBalance()-(amount + ((amount*0.01)/100)));
    }

    @Override
    public String toString()
    {
        return "Checking{}" + super.toString();
    }
}
