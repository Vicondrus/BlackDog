package com.vetshop.application;

import com.vetshop.notifications.NotificationWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

/**
 * The type Socket configurer.
 */
@Configuration
public class SocketConfigurer {

    private static final String WS_URI = "ws://localhost:8080/notifications";

    private final NotificationWebSocketHandler notificationWebSocketHandler;

    /**
     * Instantiates a new Socket configurer.
     *
     * @param notificationWebSocketHandler the notification web socket handler
     */
    public SocketConfigurer(NotificationWebSocketHandler notificationWebSocketHandler) {
        this.notificationWebSocketHandler = notificationWebSocketHandler;
    }

    /**
     * Connection manager web socket connection manager.
     *
     * @return the web socket connection manager
     */
    @Bean
    public WebSocketConnectionManager connectionManager() {
        WebSocketConnectionManager manager = new WebSocketConnectionManager(client(), notificationWebSocketHandler, WS_URI);
        manager.setAutoStartup(true);
        return manager;
    }

    /**
     * Client standard web socket client.
     *
     * @return the standard web socket client
     */
    @Bean
    public StandardWebSocketClient client() {
        return new StandardWebSocketClient();
    }
}
