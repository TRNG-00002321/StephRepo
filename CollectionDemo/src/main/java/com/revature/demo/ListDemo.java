package com.revature.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListDemo
{
    public static void main(String[] args)
    {
        List<String> myList = new ArrayList();
        myList.add("Why");
        myList.add("Meowdy");
        myList.add("Pardner");

        System.out.println(myList.get(2));

        Iterator iter = myList.iterator();
        while(iter.hasNext())
        {
            System.out.println(iter.next());
        }

        for(int i = 0; i < myList.size();i++)
        {
            System.out.println(myList.get(i));
        }

        myList.remove(0);

        for(String i : myList)
        {
            System.out.println(i);
        }

    }
}
