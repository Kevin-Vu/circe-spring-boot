package com.circe.invoice.controller;

import com.circe.invoice.dto.user.CreateUserDto;
import com.circe.invoice.dto.user.UserDto;
import com.circe.invoice.exception.badrequest.UserBadRequestException;
import com.circe.invoice.exception.notfound.UserNotFoundException;
import com.circe.invoice.security.CurrentUser;
import com.circe.invoice.service.UserService;
import com.circe.invoice.util.UserUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  @Autowired private UserService userService;

  /**
   * Create an UserEntity in database from a CreateUserDto
   *
   * @param createUserDto : CreateUserDto
   * @return : UserDto
   */
  @Operation(summary = "Create a new user")
  @PostMapping(value = "/api/auth/user")
  @PreAuthorize("hasAnyAuthority('RIGHT_ADMIN')")
  public ResponseEntity<UserDto> createUser(
      CurrentUser user, @RequestBody CreateUserDto createUserDto) throws UserBadRequestException {
    if (!UserUtil.checkCreateUserInput(createUserDto))
      throw new UserBadRequestException("CreateUserDto contains bad input");
    return new ResponseEntity<>(userService.createUser(createUserDto), HttpStatus.OK);
  }

  @GetMapping(value = "/api/user")
  public ResponseEntity<UserDto> createddUser() throws UserBadRequestException {
    CreateUserDto createUserDto =
        CreateUserDto.builder()
            .userCode(RandomStringUtils.randomAlphabetic(10))
            .password("password")
            .authority("admin")
            .email(RandomStringUtils.randomAlphabetic(10) + "kevin.vu@gotde.fr")
            .firstname("ferf")
            .lastname("eef")
            .langCode("FR")
            .build();
    return new ResponseEntity<>(userService.createUser(createUserDto), HttpStatus.OK);
  }

  /**
   * Get an User from its id
   *
   * @param id : user id
   * @return : UserDto
   */
  @Operation(summary = "Get a user by its id")
  @GetMapping(value = "/api/auth/user")
  public ResponseEntity<UserDto> getUser(CurrentUser user, @RequestParam Integer id)
      throws UserBadRequestException, UserNotFoundException {
    if (id == null || id < 0) throw new UserBadRequestException("Bad id");
    if (UserUtil.isAdmin(user) || user.getId().equals(id))
      return new ResponseEntity<>(userService.loadUserById(id), HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }

  /**
   * Delete an user by its id
   *
   * @param user : user id
   * @return : HttpStatus
   */
  @Operation(summary = "Delete an user")
  @DeleteMapping(value = "/api/auth/user")
  @PreAuthorize("hasAnyAuthority('RIGHT_ADMIN')")
  public ResponseEntity<HttpStatus> deleteUser(CurrentUser user, @RequestParam Integer id)
      throws UserBadRequestException {
    if (id == null || id < 0) throw new UserBadRequestException("Bad id");
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Update an UserEntity from an UserDto
   *
   * @param userDto : UserDto
   * @return : UserDto
   */
  @Operation(summary = "Update an user")
  @PutMapping(value = "/api/auth/user")
  public ResponseEntity<UserDto> updateUser(CurrentUser user, @RequestBody UserDto userDto)
      throws UserBadRequestException, UserNotFoundException {
    if (!UserUtil.checkUserInput(userDto))
      throw new UserBadRequestException("UserDto contains bad input");
    if (UserUtil.isAdmin(user) || user.getId().equals(userDto.getId()))
      return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }
}
