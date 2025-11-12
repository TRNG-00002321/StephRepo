package com.revature.day03;

import java.util.Scanner;
public class SwitchCase
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int input;
        System.out.println("Enter a numer from 1-7:");
        try
        {
            input = Integer.parseInt(scan.next());
        }
        catch(NumberFormatException nfe)
        {
            System.out.println("This wasn't a number, default is 1.");
            input = 1;
        }
        switch(input)
        {
            case 1:
                System.out.println("This is a Sunday.");
                break;
            case 2:
                System.out.println("This is a Monday.");
                break;
            case 3:
                System.out.println("This is a Tuesday.");
                break;
            case 4:
                System.out.println("This is a Wednesday.");
                break;
            case 5:
                System.out.println("This is a Thursday.");
                break;
            case 6:
                System.out.println("This is a Friday.");
                break;
            case 7:
                System.out.println("This is a Saturday.");
                break;
        }
    }
}
