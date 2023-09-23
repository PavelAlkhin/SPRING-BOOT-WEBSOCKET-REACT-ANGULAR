package com.cy.tiersec.websocket.locatestats.dto.addmany.request; 
import com.fasterxml.jackson.annotation.JsonProperty; 
import java.util.List; 
public class Point{
    @JsonProperty("Id") 
    public int id;
    @JsonProperty("SettingsVersion") 
    public int settingsVersion;
    @JsonProperty("Channel") 
    public int channel;
    @JsonProperty("SNR") 
    public double sNR;
    @JsonProperty("Angles") 
    public ArrayList<Angle> angles;
    @JsonProperty("RSSI") 
    public double rSSI;
    @JsonProperty("Angle2a") 
    public double angle2a;
    @JsonProperty("Antenna2a") 
    public int antenna2a;
    @JsonProperty("Compression") 
    public int compression;
    @JsonProperty("Antenna") 
    public int antenna;
    @JsonProperty("TSi") 
    public int tSi;
    @JsonProperty("TSf") 
    public double tSf;
    @JsonProperty("Clocks") 
    public ArrayList<Object> clocks;
}
