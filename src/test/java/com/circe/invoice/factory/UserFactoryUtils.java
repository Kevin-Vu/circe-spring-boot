package com.circe.invoice.factory;

import com.circe.invoice.dto.user.CreateUserDto;
import com.circe.invoice.dto.user.UserDto;
import org.apache.commons.lang3.RandomStringUtils;

public class UserFactoryUtils {

  private UserFactoryUtils() {}

  public static UserDto generateClientDto() {
    return UserDto.builder()
        .firstname(RandomStringUtils.randomAlphabetic(20))
        .lastname(RandomStringUtils.randomAlphabetic(20))
        .userCode(RandomStringUtils.randomAlphabetic(10))
        .langCode("FR")
        .email(
            RandomStringUtils.randomAlphabetic(10).toLowerCase()
                + "@"
                + RandomStringUtils.randomAlphabetic(5).toLowerCase()
                + ".fr")
        .build();
  }

  public static CreateUserDto generateCreateClientDto() {
    return CreateUserDto.builder()
        .firstname(RandomStringUtils.randomAlphabetic(30))
        .lastname(RandomStringUtils.randomAlphabetic(30))
        .email(
            RandomStringUtils.randomAlphabetic(10).toLowerCase()
                + "@"
                + RandomStringUtils.randomAlphabetic(5).toLowerCase()
                + ".fr")
        .userCode(RandomStringUtils.randomAlphabetic(10))
        .langCode("FR")
        .password(RandomStringUtils.randomAlphanumeric(10))
        .build();
  }
}
