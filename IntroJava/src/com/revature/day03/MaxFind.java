package com.revature.day03;

public class MaxFind
{
    public static void main(String[] args)
    {
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int n3 = Integer.parseInt(args[2]);
        int maxResult = n1;

        if(n2 > n1 || n3 > n1)
        {
            maxResult = Math.max(n3, n2);
        }

        System.out.println("Max number is " + maxResult);
    }
}
