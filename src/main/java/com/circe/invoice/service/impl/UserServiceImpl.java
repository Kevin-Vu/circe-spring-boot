package com.circe.invoice.service.impl;

import com.circe.invoice.dto.mapper.UserMapper;
import com.circe.invoice.dto.user.CreateUserDto;
import com.circe.invoice.dto.user.UserDto;
import com.circe.invoice.entity.referential.RightEntity;
import com.circe.invoice.entity.referential.UserEntity;
import com.circe.invoice.exception.notfound.UserNotFoundException;
import com.circe.invoice.repository.referential.AuthorityRepository;
import com.circe.invoice.repository.referential.UserRepository;
import com.circe.invoice.security.CurrentUser;
import com.circe.invoice.service.UserService;
import com.circe.invoice.util.BCryptManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptManagerUtil bCryptManagerUtil;

    @Autowired
    private UserMapper userMapper;

    /**
     * Implement a custom Principal for Spring Security, in CurrentUser we have the id of the user and its email
     *
     * @param userCode : user code
     *
     * @return : CurrentUser which extends Spring's User object
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userCode){
        UserEntity userEntity = this.userRepository.findByUserCode(userCode);
        if(userEntity == null)
            throw new UsernameNotFoundException("User not found : user code : " + userCode);

        List<RightEntity> rights = userEntity.getAuthority()
                .getRightEntities();
        List<GrantedAuthority> authorities = rights.stream()
                                                .map(r -> new SimpleGrantedAuthority(r.getName()))
                                                .collect(Collectors.toList());

        return new CurrentUser(userEntity.getUserCode(), userEntity.getPassword(), authorities, userEntity.getId(), userEntity.getEmail(),
                    userEntity.getPwdExpirationDate(), userEntity.getPwdAccessStart(), userEntity.getPwdAccessEnd());
    }

    /**
     * Load a User by its user code
     *
     * @param userCode : user code
     *
     * @return : UserEntity
     *
     * @throws UserNotFoundException
     */
    @Override
    public UserDto loadUserByCode(String userCode) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findByUserCode(userCode);
        if(userEntity == null)
            throw new UserNotFoundException("User doesn't exist : " + userCode);
        return userMapper.convert(userEntity);
    }

    /**
     * Load a user by its id
     *
     * @param id : id
     *
     * @return : UserEntity
     *
     * @throws UserNotFoundException
     */
    @Override
    public UserDto loadUserById(Integer id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if(userEntity == null)
            throw new UserNotFoundException(String.format("User with id %d does not exist", id));
        return userMapper.convert(userEntity);
    }

    /**
     * Create an user
     *
     * @param createUserDto : CreateUserDto
     *
     * @return : UserDto
     */
    @Override
    @Transactional
    public UserDto createUser(CreateUserDto createUserDto){
        UserEntity userEntity = userMapper.convert(createUserDto, authorityRepository);
        userEntity.setPassword(bCryptManagerUtil.getPasswordEncoder().encode(createUserDto.getPassword()));
        userEntity = userRepository.save(userEntity);
        return userMapper.convert(userEntity);
    }

    /**
     * Update a user but not its password
     *
     * @param userDto : UserDto
     *
     * @return : UserDto
     */
    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) throws UserNotFoundException {
        UserEntity userEntity = this.userRepository.findById(userDto.getId()).orElse(null);
        if(userEntity == null)
            throw new UserNotFoundException("User not found : user code : " + userDto.getUserCode());
        userMapper.updateEntityFromDto(userDto, userEntity);
        userEntity = userRepository.save(userEntity);
        return userMapper.convert(userEntity);
    }

    /**
     * Delete an user by its id
     *
     * @param id : user id
     */
    @Override
    @Transactional
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
}
