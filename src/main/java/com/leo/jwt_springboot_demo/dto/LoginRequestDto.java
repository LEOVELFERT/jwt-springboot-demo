package com.leo.jwt_springboot_demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginRequestDto {
    private String userName;
    private String password;
}
