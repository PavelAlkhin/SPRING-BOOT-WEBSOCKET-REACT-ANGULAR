package com.cy.tiersec.websocket.locatestats.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageCastException extends RuntimeException {
    final Logger logger = LoggerFactory.getLogger(MessageCastException.class);

    public MessageCastException(String errorMessage, Object message) {
        super(errorMessage);
        logger.error(errorMessage);
    }
}
