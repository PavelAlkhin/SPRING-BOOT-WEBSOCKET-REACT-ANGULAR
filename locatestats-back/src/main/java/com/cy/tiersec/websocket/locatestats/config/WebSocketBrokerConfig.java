package com.cy.tiersec.websocket.locatestats.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@ConfigurationProperties(prefix = "url")
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

//    @Value("${react}")
    private String URL_ANGULAR;
//    @Value("${angular}")
    private String URL_REACT;

    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // with sockjs for REACT
        registry.addEndpoint("/ws")
//                .setAllowedOrigins(URL_REACT)
//                .setAllowedOrigins(URL_ANGULAR)
                .setAllowedOriginPatterns("*")
                .withSockJS();

        // without sockjs for ANGULAR
        registry.addEndpoint("/ws-message")
                .setAllowedOriginPatterns("*");
    }

//    @Bean
//    WebMvcConfigurer corsConfig() {
//        return new WebMvcConfigurer() {
//
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/ws/**")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*")
//                        .allowedOrigins(URL);
//            }
//        };
//    }
}
