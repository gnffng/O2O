package com.bong.o2o.dao.websocket;

public class NotificationMessage {
    private String content;

    public NotificationMessage() {
    }

    public NotificationMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
