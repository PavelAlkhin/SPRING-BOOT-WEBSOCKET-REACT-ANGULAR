package com.cy.tiersec.websocket.locatestats.service;

import com.cy.tiersec.websocket.locatestats.dto.addmany.request.AddManyRequestDto;
import com.cy.tiersec.websocket.locatestats.dto.addmany.response.AddManyResponseDto;
import com.cy.tiersec.websocket.locatestats.dto.websocket.CoordinatesDto;

public interface WebsocketServiceClient {
    AddManyResponseDto getResponseDto(AddManyRequestDto dto);
    CoordinatesDto getResponseToSocket(AddManyRequestDto dto);
}
