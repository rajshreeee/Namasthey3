package com.example.rajshree.namasthey3;

public class Conversation {

    public long timestamp;

    public Conversation(long timestamp) {
        this.timestamp = timestamp;
    }

    public Conversation() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
