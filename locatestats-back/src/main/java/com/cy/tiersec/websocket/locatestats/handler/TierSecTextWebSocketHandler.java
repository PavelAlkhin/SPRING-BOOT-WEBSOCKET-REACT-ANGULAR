package com.cy.tiersec.websocket.locatestats.handler;

import com.cy.tiersec.websocket.locatestats.dto.websocket.CoordinatesDto;
import com.cy.tiersec.websocket.locatestats.service.TierSecSessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class TierSecTextWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TierSecSessionManager tierSecSessionManager;
    final Logger logger = LoggerFactory.getLogger(TierSecTextWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        tierSecSessionManager.addSession(session);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                // при первом подключении можно передавать на клиента ид сессии и запонмать там ее
                // сейчас для имитации передаются координаты. В самом простейшем виде.
                // Map.of("STATUS", "OK", "sessionId", session.getId())
                new CoordinatesDto(48.8588548, 2.2945, -28.0, 63.0)
        )));
        logger.info("Connection with id {} established with server!", session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        tierSecSessionManager.removeSession(session);
        logger.info("Session with id {} was removed!", session.getId());
    }
}
