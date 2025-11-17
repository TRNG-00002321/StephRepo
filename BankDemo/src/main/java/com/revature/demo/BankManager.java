package com.revature.demo;
import java.util.Scanner;

public class BankManager
{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Checking c1 = new Checking("C001","Steph",20000);
        System.out.println(c1);
        try{
            c1.withdraw(1600);

        } catch (UnavailableBalanceException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println(c1);

        System.out.println("how much would you like to add: ");
        double add = scan.nextDouble();

        System.out.println("how much do you want to withdraw: ");
        double sub = scan.nextDouble();

        try{
            c1.deposit(add);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + "\n" + c1);
        }
        try{
            c1.withdraw(sub);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + "\n" + c1);
        }



    }
}
