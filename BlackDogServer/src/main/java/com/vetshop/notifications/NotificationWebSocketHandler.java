package com.vetshop.notifications;

import com.vetshop.services.exceptions.AlreadyExistingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class NotificationWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private NotificationService notificationService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws AlreadyExistingException {
        String[] payload = message.getPayload().split("####");;
        System.out.println("RECEIVED from " + session.getId() + ": " + message.getPayload());

        if(payload[0].equals("START"))
            notificationService.addTopic(session, payload[1]);
        else if (payload[0].equals("STOP"))
            notificationService.removeSubjectAndTopic(payload[1]);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        notificationService.removeSession(session.getId());
    }

}
