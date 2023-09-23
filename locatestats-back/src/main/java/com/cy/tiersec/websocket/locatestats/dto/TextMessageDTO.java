package com.cy.tiersec.websocket.locatestats.dto;

public class TextMessageDTO {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TextMessageDTO{" +
                "message='" + message + '\'' +
                '}';
    }
}
