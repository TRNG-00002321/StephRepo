package com.revature.employees;
import java.util.Scanner;

public class EmployeeManager
{
    static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int count = 0;
        System.out.println("" +
                "1) Salary\n" +
                "2) Contract\n"+
                "Who would you like to hire?\n");

        int option = scan.nextInt();

        while(option>2||option<1)
        {
            System.out.println("Invalid option, try again:\n" +
                    "1) Salary\n" +
                    "2) Contract\n"+
                    "Who would you like to hire?\n");

            option = scan.nextInt();
        }

        switch (option){
            case 1:
                System.out.println("What is your name?:\n");
                String name = scan.next();
                System.out.println("What is your annual salary?:\n");
                int salary = scan.nextInt();

                while(salary<1){
                    System.out.println("Not possible, try again.\nWhat is your annual salary?:\n");
                    salary = scan.nextInt();
                }
                Salary s1 = new Salary("S00" + count, name,salary);
                s1.addHours(6);

                System.out.println("Hello " + name + ", what would you like to do?\n" +
                        "1) add days worked\n" +
                        "2) view total days worked\n" +
                        "3) view salary amount of days worked\n");
                int so = scan.nextInt();

                while(so>3||so<1)
                {
                    System.out.println("Invalid option, try again:\n" +
                            "what would you like to do?\n" +
                            "1) add days worked\n" +
                            "2) view total days worked\n" +
                            "3) view salary amount of days worked\n");

                    so = scan.nextInt();
                }
                switch(so){
                    case 1:
                        System.out.println("How many days did you work?\n");
                        int days = scan.nextInt();
                        s1.addHours(days);
                        break;
                    case 2:
                        System.out.println("Days: " + (s1.getTime()/8));
                        break;
                    case 3:
                        System.out.println("Salary for " + (s1.getTime()/8) + " days: " + s1.salaryPay());
                }
            break;
            case 2:
                System.out.println("What is your name?:\n");
                String name2 = scan.next();
                System.out.println("What is your hourly rate?:\n");
                int rate = scan.nextInt();

                while(rate<1){
                    System.out.println("Not possible, try again.\nWhat is your annual salary?:\n");
                    rate = scan.nextInt();
                }
                Contract c1 = new Contract("S00" + count, name2,rate);
                c1.addHours(10);

                System.out.println("Hello " + name2 + ", what would you like to do?\n" +
                        "1) add hours worked\n" +
                        "2) view total hours worked\n" +
                        "3) view salary amount of hours worked\n");
                int co = scan.nextInt();

                while(co>3||co<1)
                {
                    System.out.println("Invalid option, try again:\n" +
                            "what would you like to do?\n" +
                            "1) add hours worked\n" +
                            "2) view total hours worked\n" +
                            "3) view salary amount of hours worked\n");

                    co = scan.nextInt();
                }
                switch(co){
                    case 1:
                        System.out.println("How many hours did you work?\n");
                        int hours = scan.nextInt();
                        c1.addHours(hours);
                        break;
                    case 2:
                        System.out.println("Hours: " + (c1.getTime()/8));
                        break;
                    case 3:
                        System.out.println("Salary for " + (c1.getTime()/8) + " hours: " + c1.salaryPay());
                }
                break;



        }







    }
}
