package com.kenzie.app.zipcode.data;

public class Address {
    //declare properties
    private String name;
    private String streetAddress;
    private String city;
    private String state; // enum for State
    private String zipcode; // 71290-5671

    //declare constructors
    public Address(String name, String streetAddress, String city, String state) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
    }

    public Address(String name, String streetAddress, String city, String state, String zipcode) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Address(){
        this("", "", "", "");
    }

    //declare methods
    //getters/setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
