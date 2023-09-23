package com.cy.tiersec.websocket.locatestats.dto.addmany.request; 
import com.fasterxml.jackson.annotation.JsonProperty; 
public class State{
    @JsonProperty("DlMode") 
    public int dlMode;
    @JsonProperty("Tune") 
    public String tune;
    @JsonProperty("SignalFound") 
    public int signalFound;
    @JsonProperty("Hsdpa") 
    public int hsdpa;
    @JsonProperty("Standard") 
    public String standard;
    @JsonProperty("Usrp") 
    public int usrp;
    @JsonProperty("Capabilities") 
    public int capabilities;
    @JsonProperty("Expiration") 
    public long expiration;
    @JsonProperty("Compression") 
    public int compression;
    @JsonProperty("Error") 
    public int error;
    @JsonProperty("Remote") 
    public int remote;
}
