package com.springboot.StoreApplicationApi.dtos.user;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    String oldPassword;
    String newPassword;
}
