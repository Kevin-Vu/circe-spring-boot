package com.circe.invoice.exception.badrequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserBadRequestException extends Exception {

  private static final Logger log = LogManager.getLogger();

  public UserBadRequestException(String message) {
    super(message);
    log.error("[INCORRECT-INPUT] - {}", message);
  }
}
