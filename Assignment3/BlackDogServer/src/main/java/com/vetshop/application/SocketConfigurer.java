package com.vetshop.application;

import com.vetshop.notifications.NotificationWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * The type Socket configurer.
 */
@Configuration
@EnableWebSocket
public class SocketConfigurer implements WebSocketConfigurer {

    private final NotificationWebSocketHandler notificationWebSocketHandler;

    /**
     * Instantiates a new Socket configurer.
     *
     * @param notificationWebSocketHandler the notification web socket handler
     */
    public SocketConfigurer(NotificationWebSocketHandler notificationWebSocketHandler) {
        this.notificationWebSocketHandler = notificationWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(notificationWebSocketHandler, "/notifications");
    }
}
