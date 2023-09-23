package com.cy.tiersec.websocket.locatestats.dto.addmany.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class AddManyResponseDto {
    @JsonProperty("UsrpCfg") 
    public UsrpCfg usrpCfg;
    @JsonProperty("Command") 
    public int command;

    @Override
    public String toString() {
        return "AddManyResponseDto{" +
                "usrpCfg=" + usrpCfg +
                ", command=" + command +
                '}';
    }
}
