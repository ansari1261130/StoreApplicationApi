package com.springboot.StoreApplicationApi.dtos;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    String oldPassword;
    String newPassword;
}
