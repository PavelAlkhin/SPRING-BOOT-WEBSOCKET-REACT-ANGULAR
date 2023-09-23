package com.cy.tiersec.websocket.locatestats.dto.addmany.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class UsrpCfg{
    @JsonProperty("RxGain") 
    public int rxGain;
    @JsonProperty("Network") 
    public int network;
    @JsonProperty("2g_params") 
    public _2gParams _2g_params;
    @JsonProperty("3g_params") 
    public _3gParams _3g_params;
    @JsonProperty("4g_params") 
    public _4gParams _4g_params;
    @JsonProperty("Alpha") 
    public double alpha;
    @JsonProperty("Slot") 
    public String slot;
    @JsonProperty("Celltrack") 
    public int celltrack;
    @JsonProperty("Watcher") 
    public int watcher;
    @JsonProperty("Mode") 
    public int mode;
    @JsonProperty("Antenna")
    public int antenna;
    @JsonProperty("GpsSrc") 
    public int gpsSrc;
    @JsonProperty("Version") 
    public int version;
    @JsonProperty("App") 
    public int app;
    @JsonProperty("ScanUARFCN") 
    public String scanUARFCN;
    @JsonProperty("ScanPSC") 
    public String scanPSC;
    @JsonProperty("UlScStart") 
    public int ulScStart;
    @JsonProperty("UlScEnd") 
    public int ulScEnd;
    @JsonProperty("ScanULSC") 
    public String scanULSC;
    @JsonProperty("ScanTimeouts") 
    public String scanTimeouts;

    @Override
    public String toString() {
        return "UsrpCfg{" +
                "rxGain=" + rxGain +
                ", network=" + network +
                ", _2g_params=" + _2g_params +
                ", _3g_params=" + _3g_params +
                ", _4g_params=" + _4g_params +
                ", alpha=" + alpha +
                ", slot='" + slot + '\'' +
                ", celltrack=" + celltrack +
                ", watcher=" + watcher +
                ", mode=" + mode +
                ", antenna=" + antenna +
                ", gpsSrc=" + gpsSrc +
                ", version=" + version +
                ", app=" + app +
                ", scanUARFCN='" + scanUARFCN + '\'' +
                ", scanPSC='" + scanPSC + '\'' +
                ", ulScStart=" + ulScStart +
                ", ulScEnd=" + ulScEnd +
                ", scanULSC='" + scanULSC + '\'' +
                ", scanTimeouts='" + scanTimeouts + '\'' +
                '}';
    }
}
