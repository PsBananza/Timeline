package com.dmitry.NewsClient.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterUserDto {

    private String avatar;
    @Email
    @Size(min = 3, max = 100)
    private String email;
    @Size(min = 3, max = 25)
    private String name;
    private String password;
    @Size(min = 3, max = 25)
    private String role;

}
