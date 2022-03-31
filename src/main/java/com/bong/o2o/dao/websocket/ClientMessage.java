package com.bong.o2o.dao.websocket;

public class ClientMessage {
    private String content;

    public ClientMessage() {
    }

    public ClientMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
