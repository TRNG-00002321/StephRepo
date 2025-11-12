package com.revature.day03;

public class AsterickStairs
{
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);

        for(int i = 0; i < n;i++)
        {
            for(int j = 0; j < i + 1; j++)
            {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
