package com.springboot.StoreApplicationApi.mappers;

import com.springboot.StoreApplicationApi.dtos.user.RegisterUserRequest;
import com.springboot.StoreApplicationApi.dtos.user.UserDto;
import com.springboot.StoreApplicationApi.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T13:04:39+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }

    @Override
    public User toEntity(RegisterUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( request.getName() );
        user.email( request.getEmail() );
        user.password( request.getPassword() );

        return user.build();
    }

    @Override
    public void update(RegisterUserRequest request, User user) {
        if ( request == null ) {
            return;
        }

        user.setName( request.getName() );
        user.setEmail( request.getEmail() );
        user.setPassword( request.getPassword() );
    }
}
