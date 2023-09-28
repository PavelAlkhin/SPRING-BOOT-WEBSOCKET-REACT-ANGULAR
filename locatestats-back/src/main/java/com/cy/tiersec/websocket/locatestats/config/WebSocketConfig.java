package com.cy.tiersec.websocket.locatestats.config;

import com.cy.tiersec.websocket.locatestats.handler.TierSecTextWebSocketHandler;
import com.cy.tiersec.websocket.locatestats.service.TierSecSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(messageWebSocketHandler(), "/topic/messages").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler messageWebSocketHandler() {
        return new TierSecTextWebSocketHandler(tierSecSessionManager());
    }

    @Bean
    public TierSecSessionManager tierSecSessionManager(){return new TierSecSessionManager();};

}
