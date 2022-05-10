package com.bong.o2o.service;

import com.bong.o2o.dao.order.OrderSheet;
import com.bong.o2o.dao.websocket.NotificationMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    public void sendMessage(OrderSheet order) {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try{
            this.brokerMessagingTemplate.convertAndSend("/topic/notification", new NotificationMessage(order.getId().toString()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
