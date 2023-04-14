package com.anywrgroup.usermanager.mapper;

import com.anywrgroup.usermanager.dto.UserDTO;
import com.anywrgroup.usermanager.entity.User;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    UserDTO toDto(User user);

    @AfterMapping
    default void setPassword(@MappingTarget UserDTO userDTO){
        userDTO.setPassword(null);
    }
}
