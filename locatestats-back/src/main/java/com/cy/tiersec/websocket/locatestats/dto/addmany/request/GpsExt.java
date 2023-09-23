package com.cy.tiersec.websocket.locatestats.dto.addmany.request; 
import com.fasterxml.jackson.annotation.JsonProperty; 
public class GpsExt{
    @JsonProperty("Status") 
    public int status;
    @JsonProperty("Lat") 
    public double lat;
    @JsonProperty("Lon") 
    public double lon;
    @JsonProperty("Alt") 
    public double alt;
    @JsonProperty("TS") 
    public int tS;
}
