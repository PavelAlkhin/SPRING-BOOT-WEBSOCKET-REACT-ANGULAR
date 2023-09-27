package com.cy.tiersec.websocket.locatestats.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TierSecSessionManager {
    final Logger logger = LoggerFactory.getLogger(TierSecSessionManager.class);
    private final List<WebSocketSession> sessions;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TierSecSessionManager() {
        this.sessions = new CopyOnWriteArrayList<>();
    }
    public void sendBroadCastMessage(Object msg)  {

        String toSocket = null;
        try {
            toSocket = objectMapper.writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return;
        }

        try{
            for(var session : sessions){
                session.sendMessage(new TextMessage(toSocket));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void addSession(WebSocketSession session){
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session){
        sessions.remove(session);
    }
}
