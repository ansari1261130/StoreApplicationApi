package com.springboot.StoreApplicationApi.dtos;

import lombok.Data;

@Data
public class RegisterUserRequest {
    String name;
    String email;
    String password;
}
