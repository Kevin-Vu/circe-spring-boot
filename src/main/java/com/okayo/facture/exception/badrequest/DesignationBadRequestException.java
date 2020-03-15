package com.okayo.facture.exception.badrequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DesignationBadRequestException extends Exception {

    private static final Logger log = LogManager.getLogger();

    public DesignationBadRequestException(String message) {
        super(message);
        log.error("[INCORRECT-INPUT] - {}", message);
    }
}
