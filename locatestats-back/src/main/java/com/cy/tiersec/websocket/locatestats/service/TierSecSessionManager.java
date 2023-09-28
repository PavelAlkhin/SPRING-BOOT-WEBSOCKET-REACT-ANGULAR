package com.cy.tiersec.websocket.locatestats.service;

import com.cy.tiersec.websocket.locatestats.exception.MessageCastException;
import com.cy.tiersec.websocket.locatestats.exception.SendMessageException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TierSecSessionManager {
    private final List<WebSocketSession> sessions;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TierSecSessionManager() {
        this.sessions = new CopyOnWriteArrayList<>();
    }
    public void sendBroadCastMessage(Object msg)  {

        try {
            String toSocket = objectMapper.writeValueAsString(msg);
            for(var session : sessions){
                session.sendMessage(new TextMessage(toSocket));
            }

        } catch (JsonProcessingException e) {
            throw new MessageCastException(e.getMessage(), msg);
        } catch (IOException e) {
            throw new SendMessageException(e.getMessage(), msg);
        }
    }

    public void addSession(WebSocketSession session){
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session){
        sessions.remove(session);
    }
}
