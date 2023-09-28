package com.cy.tiersec.websocket.locatestats.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMessageException extends RuntimeException {
    final Logger logger = LoggerFactory.getLogger(SendMessageException.class);

    public SendMessageException(String errorMessage, Object message) {
        super(errorMessage);
        logger.error("Error {} with sending message {}", errorMessage, message.toString());
    }
}
