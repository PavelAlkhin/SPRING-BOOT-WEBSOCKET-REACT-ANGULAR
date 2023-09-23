package com.cy.tiersec.websocket.locatestats.dto.addmany.request; 
import com.fasterxml.jackson.annotation.JsonProperty; 
public class Angle{
    @JsonProperty("TSi") 
    public int tSi;
    @JsonProperty("TSf") 
    public double tSf;
    @JsonProperty("RSSI_m") 
    public double rSSI_m;
    @JsonProperty("RSSI_s") 
    public double rSSI_s;
    @JsonProperty("SNR_m") 
    public double sNR_m;
    @JsonProperty("SNR_s") 
    public double sNR_s;
    @JsonProperty("Master") 
    public int master;
    @JsonProperty("An") 
    public double an;
    @JsonProperty("Phase") 
    public double phase;
    @JsonProperty("Visible") 
    public int visible;
    @JsonProperty("Int_m") 
    public double int_m;
    @JsonProperty("Int_s") 
    public double int_s;
    @JsonProperty("Int") 
    public double _int;
    @JsonProperty("Ant") 
    public int ant;
    @JsonProperty("RxGain") 
    public int rxGain;
    @JsonProperty("V") 
    public int v;
}
