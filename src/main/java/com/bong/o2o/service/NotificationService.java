package com.bong.o2o.service;

import com.bong.o2o.dao.websocket.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    public void sendMessage(String message) {
        this.brokerMessagingTemplate.convertAndSend("/topic/notification", new NotificationMessage(message));
    }

}
