package com.bong.o2o.controller;

import com.bong.o2o.dao.websocket.ClientMessage;
import com.bong.o2o.dao.websocket.NotificationMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class NotificationsController {
    @MessageMapping("/message")
    @SendTo("/topic/notification")
    public NotificationMessage notify(ClientMessage clientMessage) {
        return new NotificationMessage(clientMessage.getContent());
    }
}