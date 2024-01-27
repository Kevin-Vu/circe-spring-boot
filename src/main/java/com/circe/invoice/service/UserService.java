package com.circe.invoice.service;

import com.circe.invoice.dto.user.CreateUserDto;
import com.circe.invoice.dto.user.UserDto;
import com.circe.invoice.exception.notfound.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  UserDto loadUserByCode(String userCode) throws UserNotFoundException;

  UserDto loadUserById(Integer id) throws UserNotFoundException;

  UserDto createUser(CreateUserDto clientDto);

  UserDto updateUser(UserDto userDto) throws UserNotFoundException;

  void deleteUser(Integer id);
}
