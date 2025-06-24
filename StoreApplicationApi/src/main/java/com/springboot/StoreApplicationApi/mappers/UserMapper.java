package com.springboot.StoreApplicationApi.mappers;

import com.springboot.StoreApplicationApi.dtos.user.RegisterUserRequest;
import com.springboot.StoreApplicationApi.dtos.user.UserDto;
import com.springboot.StoreApplicationApi.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);

    void update(RegisterUserRequest request,@MappingTarget User user);
}
