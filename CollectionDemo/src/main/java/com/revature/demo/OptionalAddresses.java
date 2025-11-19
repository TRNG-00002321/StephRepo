package com.revature.demo;

import java.util.Optional;

public class OptionalAddresses
{
    public static void main(String[] args)
    {
        Address ady = new Address("1234 Read Line","Plano",45612);
        Citizen c1 = new Citizen("Bob","123-456-7890",ady);
        Citizen c2 = new Citizen("Bob","123-456-7890");

        Optional<Address> checkAdy = Optional.ofNullable(c2.getAddress());
        if(checkAdy.isPresent())
            System.out.println(c2.getAddress());
        else System.out.println("They have no address.");

        System.out.println(c1.getAddress());
    }
}
