package com.cy.tiersec.websocket.locatestats.dto.addmany.request; 
import com.fasterxml.jackson.annotation.JsonProperty; 
public class Compass{
    @JsonProperty("Hdg") 
    public double hdg;
    @JsonProperty("FitErr") 
    public String fitErr;
}
