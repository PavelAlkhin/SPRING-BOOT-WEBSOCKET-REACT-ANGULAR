package com.cy.tiersec.websocket.locatestats.controller;

import com.cy.tiersec.websocket.locatestats.dto.addmany.request.AddManyRequestDto;
import com.cy.tiersec.websocket.locatestats.dto.addmany.response.AddManyResponseDto;
import com.cy.tiersec.websocket.locatestats.dto.websocket.CoordinatesDto;
import com.cy.tiersec.websocket.locatestats.service.TierSecSessionManager;
import com.cy.tiersec.websocket.locatestats.service.WebsocketServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
public class WebSocketLocateController {
    private final SimpMessagingTemplate template;

    private final WebsocketServiceClient websocketServiceClient;

    private final TierSecSessionManager tierSecSessionManager;

    public WebSocketLocateController(
            SimpMessagingTemplate template,
            WebsocketServiceClient websocketServiceClient,
            TierSecSessionManager tierSecSessionManager
    ) {
        this.template = template;
        this.websocketServiceClient = websocketServiceClient;
        this.tierSecSessionManager = tierSecSessionManager;
    }

    /**
     * curl 'http://localhost:8080/send' -H 'Content-Type: application/json' -d '{}'
     */

    //Здесь пришлось извратиться, так как для StockJS и Stomp, которые в React, нужно совсем другое чем
    //для angular, в котором используется Websocket....
    @PostMapping("/addmany")
    public ResponseEntity<AddManyResponseDto> sendMessage(@RequestBody AddManyRequestDto dto) {

        CoordinatesDto responseToSocket = websocketServiceClient.getResponseToSocket(dto);

        // отправляем по http для React-app
        template.convertAndSend("/topic/message", responseToSocket);

        // отправляем по ws для Angular
        tierSecSessionManager.sendBroadCastMessage(responseToSocket);

        return ResponseEntity.ok(websocketServiceClient.getResponseDto(dto));
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