package com.okayo.facture.exception.notfound;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientNotFoundException extends Exception {

    private static final Logger log = LogManager.getLogger();

    public ClientNotFoundException(String message) {
        super(message);
        log.error("[BDD] - {}", message);
    }
}
