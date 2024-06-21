package com.example.teachandlearn.Student.SelectClass;

public class Message {
    public static final String SENT_BY_ME = "sent_by_me";
    public static final String SENT_BY_BOT = "sent_by_bot";

    private String message;
    private String sentBy;

    public Message(String message, String sentBy) {
        this.message = message;
        this.sentBy = sentBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }
}
