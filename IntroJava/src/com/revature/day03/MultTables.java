package com.revature.day03;

public class MultTables
{
    public static void main(String[] args)
    {
        int input = Integer.parseInt(args[0]);
        int count = input;
        while(count <= input*10)
        {
            System.out.print(count + " ");
            count += input;
        }
    }
}
