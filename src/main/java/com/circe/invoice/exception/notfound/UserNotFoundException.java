package com.circe.invoice.exception.notfound;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserNotFoundException extends Exception {

  private static final Logger log = LogManager.getLogger();

  public UserNotFoundException(String message) {
    super(message);
    log.error("[BDD] - {}", message);
  }
}
