package com.revature.day04;

public class UseStrings
{
    public static void main(String[] args)
    {
        String item = " Hello World! ";
        System.out.println(item);
        System.out.println(item.toUpperCase() + " " + item.toLowerCase());
        System.out.println(item.charAt(7));
        System.out.println(item.concat("by Me"));
        System.out.println(item.equals("Hello World!"));
        System.out.println(item.equalsIgnoreCase(" hello world! "));
        System.out.println(item.length());
        System.out.println(item.replace("o","a"));
        System.out.println(item.trim());

        StringBuilder sb = new StringBuilder();
        StringBuffer sb2 = new StringBuffer();

        sb.append("This is a string.");
        sb2.append("this is also a string");

        sb.insert(4,"s");
        sb2.insert(6,"sssssss");

        sb.deleteCharAt(13);
        sb2.deleteCharAt(17);

        sb.setCharAt(4,'t');
        sb2.setCharAt(5, 't');

        System.out.println(sb.substring(3,10));
        System.out.println(sb2.substring(6));
    }
}
