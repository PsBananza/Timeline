package com.dmitry.NewsClient.dto;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class LoginUserDto {
    private String avatar;
    @Email
    @Size(min = 3, max = 100)
    private String email;
    UUID id;
    @Size(min = 3, max = 25)
    private String name;
    @Size(min = 3, max = 25)
    private String role;
    private String token;

}
