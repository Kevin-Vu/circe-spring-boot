package com.circe.invoice.domain.util;

import com.circe.invoice.domain.dto.user.CreateUserDto;
import com.circe.invoice.domain.dto.user.UserDto;
import com.circe.invoice.application.security.CurrentUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class UserUtil {

  private UserUtil() {}

  /**
   * Check the content of a CreateUserDto
   *
   * @param createUserDto : CreateUserDto
   * @return : true if input is valid
   */
  public static boolean checkCreateUserInput(CreateUserDto createUserDto) {
    if (createUserDto == null) return false;
    return StringUtils.isNoneBlank(
            createUserDto.getFirstname(),
            createUserDto.getLastname(),
            createUserDto.getPassword(),
            createUserDto.getEmail(),
            createUserDto.getLangCode(),
            createUserDto.getAuthority())
        && EmailValidator.getInstance().isValid(createUserDto.getEmail());
  }

  /**
   * Check the content of a UserDto
   *
   * @param userDto : UserDto
   * @return : true if input is valid
   */
  public static boolean checkUserInput(UserDto userDto) {
    if (userDto == null || userDto.getId() == null || userDto.getId() < 0) return false;
    return StringUtils.isNoneBlank(
            userDto.getFirstname(),
            userDto.getLastname(),
            userDto.getEmail(),
            userDto.getUserCode(),
            userDto.getLangCode(),
            userDto.getAuthority())
        && EmailValidator.getInstance().isValid(userDto.getEmail());
  }

  /**
   * Check if the CurrentUser is admin
   *
   * @param user : CurrentUser
   * @return : true = is admin
   */
  public static boolean isAdmin(CurrentUser user) {
    return user.getAuthorities().stream()
        .anyMatch(a -> StringUtils.compare("RIGHT_ADMIN", a.getAuthority()) == 0);
  }
}
