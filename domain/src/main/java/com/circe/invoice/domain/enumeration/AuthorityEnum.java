package com.circe.invoice.domain.enumeration;

import lombok.Getter;

public enum AuthorityEnum {
  ADMINISTRATOR("ADMINISTRATOR"),
  MANAGER("MANAGER"),
  USER("USER");

  @Getter private final String value;

  AuthorityEnum(String value) {
    this.value = value;
  }
}
