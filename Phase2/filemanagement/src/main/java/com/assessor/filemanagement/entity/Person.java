package com.assessor.filemanagement.entity;

import com.assessor.filemanagement.repository.SearchCsv;

import java.util.List;
import java.util.Objects;

public final class Person implements SearchCsv {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final Farbe color;

    public Person(String firstName, String lastName, String address, Farbe color,int id) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.color = color;
    }

    public int getId(){
        return this.id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Farbe getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Person) obj;
        return Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, color);
    }

    @Override
    public String toString() {
        return "Person[" +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "address=" + address + ", " +
                "color=" + color + ']';
    }

    @Override
    public boolean findByColor(String color) {
        return (this.color.name().equalsIgnoreCase(color));


    }

}

