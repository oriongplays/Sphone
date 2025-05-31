package com.dev.sphone.mod.common.phone;

public class Message {

    private String message;
    private long date;
    private String sender;
    private String receiver;

    public Message() {
    }

    public Message(String message, long date, String sender, String receiver) {
        this.message = message;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public long getDate() {
        return date;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
