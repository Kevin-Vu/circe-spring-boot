package com.circe.invoice.domain.mapper;

import com.circe.invoice.domain.dto.user.CreateUserDto;
import com.circe.invoice.domain.dto.user.UserDto;
import com.circe.invoice.infrastructure.repository.referential.authority.AuthorityEntity;
import com.circe.invoice.infrastructure.repository.referential.user.UserEntity;
import com.circe.invoice.infrastructure.repository.referential.authority.JpaAuthorityRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

  @Mapping(source = "authority.name", target = "authority")
  UserDto convert(UserEntity userEntity);

  /* ************************************************************************************************************** */
  @Mapping(target = "authority", ignore = true)
  UserEntity convert(CreateUserDto createUserDto, @Context JpaAuthorityRepository authorityRepository);

  @AfterMapping
  default void after(
      CreateUserDto createUserDto,
      @MappingTarget UserEntity userEntity,
      @Context JpaAuthorityRepository authorityRepository) {
    AuthorityEntity authorityEntity = authorityRepository.findByName(createUserDto.getAuthority());
    if (authorityEntity != null) {
      userEntity.setAuthority(authorityEntity);
    }
  }

  /* ************************************************************************************************************** */

  /* ************************************************************************************************************** */
  @Mapping(target = "authority", ignore = true)
  UserEntity convert(UserDto userDto, @Context JpaAuthorityRepository authorityRepository);

  @AfterMapping
  default void after(
      UserDto userDto,
      @MappingTarget UserEntity userEntity,
      @Context JpaAuthorityRepository authorityRepository) {
    AuthorityEntity authorityEntity = authorityRepository.findByName(userDto.getAuthority());
    if (authorityEntity != null) {
      userEntity.setAuthority(authorityEntity);
    }
  }

  /* ************************************************************************************************************** */

  @Mapping(target = "authority", ignore = true)
  void updateEntityFromDto(UserDto userDto, @MappingTarget UserEntity userEntity);
}
