package com.cy.tiersec.websocket.locatestats.controller;

import com.cy.tiersec.websocket.locatestats.dto.addmany.request.AddManyRequestDto;
import com.cy.tiersec.websocket.locatestats.dto.addmany.response.AddManyResponseDto;
import com.cy.tiersec.websocket.locatestats.service.WebsocketServiceClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class WebSocketLocateController {

    final Logger logger = LoggerFactory.getLogger(WebSocketLocateController.class);

    private final SimpMessagingTemplate template;

    private final WebsocketServiceClient websocketServiceClient;

    /**
     * curl 'http://localhost:8080/send' -H 'Content-Type: application/json' -d '{}'
     */

    @PostMapping("/addmany")
    public ResponseEntity<AddManyResponseDto> sendMessage(@RequestBody AddManyRequestDto dto) {
        AddManyResponseDto responseDto = websocketServiceClient.getResponseDto(dto);
        System.out.println(responseDto);
        template.convertAndSend("/topic/message", websocketServiceClient.getResponseToSocket(dto));
        return ResponseEntity.ok(responseDto);
    }

    @MessageMapping("/test-connection")
    public ResponseEntity<String> testConnection(@Payload String str) {
        template.convertAndSend("/test-connection", "OK");
        return ResponseEntity.ok("OK");
    }


//    @SendTo("/topic/message")
//    public CoordinatesDto broadcastMessage(@Payload CoordinatesDto dto) {
//        System.out.println("message to react = " + dto);
//        return dto;
//    }
}