package com.cy.tiersec.websocket.locatestats.dto.addmany.request; 
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.ArrayList;

public class AddManyRequestDto {
    @JsonProperty("DftrxId") 
    public String dftrxId;
    @JsonProperty("Config") 
    public Config config;
    @JsonProperty("State") 
    public State state;
    @JsonProperty("GpsExt") 
    public GpsExt gpsExt;
    @JsonProperty("Compass") 
    public Compass compass;
    @JsonProperty("Timestamp") 
    public int timestamp;
    @JsonProperty("Points") 
    public ArrayList<Point> points;
}
