package com.bprocare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.bprocare.websockets.MessageHandler;

@Configuration
@EnableWebSocket
public class WebSocketEventListener implements  WebSocketConfigurer  {

    @Autowired
    MessageHandler messageHandler;

	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
                   registry.addHandler(messageHandler, "/topic/public").withSockJS();
    }


	
}
