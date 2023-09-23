package com.cy.tiersec.websocket.locatestats.dto.websocket;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

public record CoordinatesDto(Double Lat, Double Lon, ArrayList<HashMap<String, Double>> Points){}
