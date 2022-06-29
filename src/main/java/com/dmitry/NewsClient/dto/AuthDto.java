package com.dmitry.NewsClient.dto;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthDto {
    @Size(min = 3, max = 100)
    private String email;
    private String password;
}
