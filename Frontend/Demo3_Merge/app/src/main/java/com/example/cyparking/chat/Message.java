package com.example.cyparking.chat;

import com.example.cyparking.UserSchema;

public class Message {
    String message;
    ChatUser sender;
    long createdAt;

    public Message(ChatUser sender, String message, long createdAt) {
        this.message = message;
        this.sender = sender;
        this.createdAt = createdAt;
    }

    public ChatUser getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public long getCreatedAt() {return createdAt;}
}

