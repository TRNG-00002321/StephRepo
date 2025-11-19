package com.revature.demo;

public class FunInterfaces
{
    static void main(String[] args) {
        //Hellos hi = ()->{System.out.println("Hello");};
        //Hellos hi = (word)->{System.out.println("Hello " + word.toUpperCase());};
        Hellos hi = (fname,lname)->{System.out.println("Hello, " + fname + " " + lname);};


        hi.meowdy("Meowdy","Pardner");


    }
}
