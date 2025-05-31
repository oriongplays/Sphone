package com.dev.sphone.mod.common.phone;

import java.util.List;

public class Conversation {

    private List<Message> messages;
    private Contact sender;

    public Conversation() {
    }

    public Conversation(List<Message> messages, Contact sender) {
        this.messages = messages;
        this.sender = sender;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Contact getSender() {
        return sender;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setSender(Contact sender) {
        this.sender = sender;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Message getLastMessage() {
        if (messages == null || messages.isEmpty()) return null;
        return messages.get(messages.size() - 1);
    }
}
