package com.okayo.facture.exception.badrequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientBadRequestException extends Exception {

    private static final Logger log = LogManager.getLogger();

    public ClientBadRequestException(String message) {
        super(message);
        log.error("[INCORRECT-INPUT] - {}", message);
    }
}
