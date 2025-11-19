package com.revature.demo;

public class Citizen
{
    private String name;
    private String phonenum;
    private Address address;

    public Citizen(String name, String phonenum)
    {
        this.name = name;
        this.phonenum = phonenum;
    }

    public Citizen(String name, String phonenum, Address address) {
        this.name = name;
        this.phonenum = phonenum;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "name='" + name + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", address=" + address +
                '}';
    }
}
