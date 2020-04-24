package com.vetshop.notifications;

import lombok.SneakyThrows;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Observable;
import java.util.Observer;

/**
 * The type Concrete observer.
 */
public class ConcreteObserver implements Observer {

    private final WebSocketSession session;

    /**
     * Instantiates a new Concrete observer.
     *
     * @param session the session
     */
    public ConcreteObserver(WebSocketSession session) {
        this.session = session;
    }

    @SneakyThrows
    @Override
    public void update(Observable o, Object arg) {
        TextMessage message = new TextMessage((String) arg);
        session.sendMessage(message);
    }
}
