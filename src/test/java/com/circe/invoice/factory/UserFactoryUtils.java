package com.circe.invoice.factory;

import com.circe.invoice.dto.user.CreateUserDto;
import com.circe.invoice.dto.user.UserDto;
import org.apache.commons.lang3.RandomStringUtils;

public class UserFactoryUtils {

  private UserFactoryUtils() {}

  public static UserDto generateClientDto() {
    return UserDto.builder()
        .firstname(RandomStringUtils.insecure().nextAlphabetic(20))
        .lastname(RandomStringUtils.insecure().nextAlphabetic(20))
        .userCode(RandomStringUtils.insecure().nextAlphabetic(10))
        .langCode("FR")
        .email(
            RandomStringUtils.insecure().nextAlphabetic(10).toLowerCase()
                + "@"
                + RandomStringUtils.insecure().nextAlphabetic(5).toLowerCase()
                + ".fr")
        .build();
  }

  public static CreateUserDto generateCreateClientDto() {
    return CreateUserDto.builder()
        .firstname(RandomStringUtils.insecure().nextAlphabetic(30))
        .lastname(RandomStringUtils.insecure().nextAlphabetic(30))
        .email(
            RandomStringUtils.insecure().nextAlphabetic(10).toLowerCase()
                + "@"
                + RandomStringUtils.insecure().nextAlphabetic(5).toLowerCase()
                + ".fr")
        .userCode(RandomStringUtils.insecure().nextAlphabetic(10))
        .langCode("FR")
        .password(RandomStringUtils.insecure().nextAlphanumeric(10))
        .build();
  }
}
