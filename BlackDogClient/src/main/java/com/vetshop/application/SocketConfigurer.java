package com.vetshop.application;

import com.vetshop.notifications.NotificationWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class SocketConfigurer {

    private static final String WS_URI = "ws://localhost:8080/notifications";

    @Autowired
    private StandardWebSocketClient standardWebSocketClient;

    @Autowired
    private NotificationWebSocketHandler notificationWebSocketHandler;

    @Bean
    public WebSocketConnectionManager connectionManager() {
        WebSocketConnectionManager manager = new WebSocketConnectionManager(client(), notificationWebSocketHandler, WS_URI);
        manager.setAutoStartup(true);
        return manager;
    }

    @Bean
    public StandardWebSocketClient client() {
        return new StandardWebSocketClient();
    }
}
