package com.circe.invoice.domain.service;

import com.circe.invoice.domain.dto.user.CreateUserDto;
import com.circe.invoice.domain.dto.user.UserDto;
import com.circe.invoice.domain.exception.BusinessException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  UserDto loadUserByCode(String userCode) throws BusinessException;

  UserDto loadUserById(Integer id) throws BusinessException;

  UserDto createUser(CreateUserDto clientDto);

  UserDto updateUser(UserDto userDto) throws BusinessException;

  void deleteUser(Integer id);
}
