package com.springboot.StoreApplicationApi.dtos.user;


import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
}
