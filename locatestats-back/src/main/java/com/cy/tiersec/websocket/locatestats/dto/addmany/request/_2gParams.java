package com.cy.tiersec.websocket.locatestats.dto.addmany.request; 
import com.fasterxml.jackson.annotation.JsonProperty; 
public class _2gParams{
    @JsonProperty("Arfcn") 
    public int arfcn;
    @JsonProperty("Ulsc") 
    public int ulsc;
    @JsonProperty("Dlsc") 
    public int dlsc;
}
