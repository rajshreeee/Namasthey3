package com.example.rajshree.namasthey3;

public class Contact {

    String date;//remember var name should be same
String name;

    public Contact() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact(String date, String name) {
        this.date = date;
        this.name = name;
    }
}
