package com.example.rajshree.namasthey3;

public class profileinfofromdb {

    String name;//while giving var names, give the exact as in firebase db or it won't run
    String interests;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public profileinfofromdb() {
    }


}
