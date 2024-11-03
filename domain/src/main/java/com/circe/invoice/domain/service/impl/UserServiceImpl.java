package com.circe.invoice.domain.service.impl;

import com.circe.invoice.domain.dto.user.CreateUserDto;
import com.circe.invoice.domain.dto.user.UserDto;
import com.circe.invoice.domain.exception.BusinessException;
import com.circe.invoice.domain.repository.referential.UserRepository;
import com.circe.invoice.domain.repository.referential.AuthorityRepository;
import com.circe.invoice.domain.service.UserService;
import com.circe.invoice.domain.util.BCryptManagerUtil;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final AuthorityRepository authorityRepository;
  private final UserRepository userRepository;
  private final BCryptManagerUtil bCryptManagerUtil;

  /**
   * Implement a custom Principal for Spring Security, in CurrentUser we have the id of the user and
   * its email
   *
   * @param userCode : user code
   * @return : CurrentUser which extends Spring's User object
   */
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String userCode) {
    UserDto user = this.userRepository.findByUserCode(userCode)
        .orElseThrow(() -> new BusinessException(""));

    List<RightEntity> rights = userEntity.getAuthority().getRightEntities();
    List<GrantedAuthority> authorities =
        rights.stream()
            .map(r -> new SimpleGrantedAuthority(r.getName()))
            .collect(Collectors.toList());

    return new CurrentUser(
        userEntity.getUserCode(),
        userEntity.getPassword(),
        authorities,
        userEntity.getId(),
        userEntity.getEmail(),
        userEntity.getPwdExpirationDate(),
        userEntity.getPwdAccessStart(),
        userEntity.getPwdAccessEnd());
  }

  /**
   * Load a User by its user code
   *
   * @param userCode : user code
   * @return : UserEntity
   */
  @Override
  public UserDto loadUserByCode(String userCode) throws BusinessException {
    UserEntity userEntity = userRepository.findByUserCode(userCode);
    if (userEntity == null) throw new BusinessException("User doesn't exist : " + userCode);
    return userMapper.convert(userEntity);
  }

  /**
   * Load a user by its id
   *
   * @param id : id
   * @return : UserEntity
   */
  @Override
  public UserDto loadUserById(Integer id) throws BusinessException {
    UserEntity userEntity = userRepository.findById(id).orElse(null);
    if (userEntity == null)
      throw new BusinessException(String.format("User with id %d does not exist", id));
    return userMapper.convert(userEntity);
  }

  /**
   * Create a user
   *
   * @param createUserDto : CreateUserDto
   * @return : UserDto
   */
  @Override
  @Transactional
  public UserDto createUser(CreateUserDto createUserDto) {
    UserEntity userEntity = userMapper.convert(createUserDto, authorityRepository);
    userEntity.setPassword(
        bCryptManagerUtil.getPasswordEncoder().encode(createUserDto.getPassword()));
    userEntity = userRepository.save(userEntity);
    return userMapper.convert(userEntity);
  }

  /**
   * Update a user but not its password
   *
   * @param userDto : UserDto
   * @return : UserDto
   */
  @Override
  @Transactional
  public UserDto updateUser(UserDto userDto) throws BusinessException {
    UserEntity userEntity = this.userRepository.findById(userDto.getId()).orElse(null);
    if (userEntity == null)
      throw new BusinessException("User not found : user code : " + userDto.getUserCode());
    userMapper.updateEntityFromDto(userDto, userEntity);
    userEntity = userRepository.save(userEntity);
    return userMapper.convert(userEntity);
  }

  /**
   * Delete a user by its id
   *
   * @param id : user id
   */
  @Override
  @Transactional
  public void deleteUser(Integer id) {
    userRepository.deleteById(id);
  }
}
