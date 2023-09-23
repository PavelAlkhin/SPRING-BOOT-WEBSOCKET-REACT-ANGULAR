package com.cy.tiersec.websocket.locatestats.controller;

import com.cy.tiersec.websocket.locatestats.dto.TextMessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketTextController {
    private final SimpMessagingTemplate template;

    public WebSocketTextController(SimpMessagingTemplate template) {
        this.template = template;
    }

    /**
     * curl 'http://localhost:8080/send' -H 'Content-Type: application/json' -d '{"message": "hello"}'
     */

    @PostMapping("/addmany")
    public ResponseEntity<String> sendMessage(@RequestBody TextMessageDTO textMessageDTO) {
        System.out.println(textMessageDTO);
        template.convertAndSend("/topic/message", textMessageDTO);
        return ResponseEntity.ok(String.format("\nSENDED %s\n\n", textMessageDTO.getMessage()));
    }

    @MessageMapping("/sendMessage")
    public void receiveMessage(@Payload TextMessageDTO textMessageDTO) {
        // receive message from client
    }


    @SendTo("/topic/message")
    public TextMessageDTO broadcastMessage(@Payload TextMessageDTO textMessageDTO) {
        System.out.println("textMessageDTO = " + textMessageDTO);
        return textMessageDTO;
    }
}
