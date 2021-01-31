package com.circe.invoice.dto.mapper;

import com.circe.invoice.dto.user.CreateUserDto;
import com.circe.invoice.dto.user.UserDto;
import com.circe.invoice.entity.referential.AuthorityEntity;
import com.circe.invoice.entity.referential.UserEntity;
import com.circe.invoice.repository.referential.AuthorityRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

    @Mapping(source = "authority.name", target = "authority")
    UserDto convert(UserEntity userEntity);

    /* ************************************************************************************************************** */
    @Mapping(target = "authority", ignore = true)
    UserEntity convert(CreateUserDto createUserDto, @Context AuthorityRepository authorityRepository);

    @AfterMapping
    default void after(CreateUserDto createUserDto, @MappingTarget UserEntity userEntity, @Context AuthorityRepository authorityRepository){
        AuthorityEntity authorityEntity = authorityRepository.findByName(createUserDto.getAuthority());
        if(authorityEntity != null){
            userEntity.setAuthority(authorityEntity);
        }
    }
    /* ************************************************************************************************************** */

    /* ************************************************************************************************************** */
    @Mapping(target = "authority", ignore = true)
    UserEntity convert(UserDto userDto, @Context AuthorityRepository authorityRepository);

    @AfterMapping
    default void after(UserDto userDto, @MappingTarget UserEntity userEntity, @Context AuthorityRepository authorityRepository){
        AuthorityEntity authorityEntity = authorityRepository.findByName(userDto.getAuthority());
        if(authorityEntity != null){
            userEntity.setAuthority(authorityEntity);
        }
    }
    /* ************************************************************************************************************** */

    @Mapping(target = "authority", ignore = true)
    void updateEntityFromDto(UserDto userDto, @MappingTarget UserEntity userEntity);

}
