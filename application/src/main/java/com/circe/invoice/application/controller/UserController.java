package com.circe.invoice.application.controller;

import com.circe.invoice.domain.dto.user.CreateUserDto;
import com.circe.invoice.domain.dto.user.UserDto;
import com.circe.invoice.exception.badrequest.UserBadRequestException;
import com.circe.invoice.domain.exception.BusinessException;
import com.circe.invoice.application.security.CurrentUser;
import com.circe.invoice.domain.service.UserService;
import com.circe.invoice.domain.util.UserUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

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

  /**
   * Get an User from its id
   *
   * @param id : user id
   * @return : UserDto
   */
  @Operation(summary = "Get a user by its id")
  @GetMapping(value = "/api/auth/user")
  public ResponseEntity<UserDto> getUser(CurrentUser user, @RequestParam Integer id)
      throws UserBadRequestException, BusinessException {
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
      throws UserBadRequestException, BusinessException {
    if (!UserUtil.checkUserInput(userDto))
      throw new UserBadRequestException("UserDto contains bad input");
    if (UserUtil.isAdmin(user) || user.getId().equals(userDto.getId()))
      return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }
}
