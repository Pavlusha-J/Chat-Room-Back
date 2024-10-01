package com.example.websocketssample;


public class ChatMessage {
    private String sender;
    private String content;
    private long sentAt;

    public ChatMessage() {}

    public ChatMessage(String sender, String content, long sentAt) {
        this.sender = sender;
        this.content = content;
        this.sentAt = sentAt;
    }

    // Getteriai ir setteriai
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSentAt() {
        return sentAt;
    }

    public void setSentAt(long sentAt) {
        this.sentAt = sentAt;
    }
}

