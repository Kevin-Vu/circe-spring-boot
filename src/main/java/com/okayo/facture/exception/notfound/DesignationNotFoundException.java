package com.okayo.facture.exception.notfound;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DesignationNotFoundException extends Exception {

    private static final Logger log = LogManager.getLogger();

    public DesignationNotFoundException(String message) {
        super(message);
        log.error("[BDD] - {}", message);
    }
}
