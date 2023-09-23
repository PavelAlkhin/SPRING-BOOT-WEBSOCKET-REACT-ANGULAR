package com.cy.tiersec.websocket.locatestats.dto.addmany.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class _2gParams{
    @JsonProperty("Arfcn") 
    public int arfcn;
    @JsonProperty("Ulsc") 
    public int ulsc;
    @JsonProperty("Dlsc") 
    public int dlsc;
}
