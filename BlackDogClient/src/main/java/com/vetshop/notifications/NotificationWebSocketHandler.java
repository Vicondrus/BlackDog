package com.vetshop.notifications;

import com.vetshop.application.JavaFXApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * The type Notification web socket handler.
 */
@Service
public class NotificationWebSocketHandler extends TextWebSocketHandler {

    private WebSocketSession session;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.session = session;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JavaFXApplication.showAlert(message.getPayload());
    }

    /**
     * Gets session.
     *
     * @return the session
     */
    public WebSocketSession getSession() {
        return session;
    }

    /**
     * Send message.
     *
     * @param message the message
     * @throws IOException the io exception
     */
    public void sendMessage(String message) throws IOException {
        session.sendMessage(new TextMessage(message));
    }

}
