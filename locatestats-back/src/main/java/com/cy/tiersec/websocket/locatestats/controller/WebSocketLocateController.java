package com.cy.tiersec.websocket.locatestats.controller;

import com.cy.tiersec.websocket.locatestats.dto.addmany.request.AddManyRequestDto;
import com.cy.tiersec.websocket.locatestats.dto.addmany.response.AddManyResponseDto;
import com.cy.tiersec.websocket.locatestats.dto.websocket.CoordinatesDto;
import com.cy.tiersec.websocket.locatestats.service.WebsocketServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebSocketLocateController {

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

    @MessageMapping("/sendMessage")
    public void receiveMessage(@Payload CoordinatesDto dto) {
        // receive message from client. if we need it. but for now don`t need
    }


    @SendTo("/topic/message")
    public CoordinatesDto broadcastMessage(@Payload CoordinatesDto dto) {
        System.out.println("message to react = " + dto);
        return dto;
    }
}
