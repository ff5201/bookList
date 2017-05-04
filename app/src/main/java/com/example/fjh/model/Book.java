package com.example.fjh.model;

public class Book {
    private String ID;
    private String name;
    private String price;

    public Book(String ID, String name, String price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return this.ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return this.price;
    }

    public String toString() {
        return this.ID + " " + this.name + " " + this.price;
    }
}
