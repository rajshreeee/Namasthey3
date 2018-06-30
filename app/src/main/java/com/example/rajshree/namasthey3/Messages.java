package com.example.rajshree.namasthey3;

public class Messages {
    String message;
    long timestamp;
    String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Messages(String message, long timestamp, String from) {
        this.message = message;
        this.timestamp = timestamp;
        this.from = from;
    }

    public Messages() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
