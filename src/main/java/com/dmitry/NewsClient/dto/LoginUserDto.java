package com.dmitry.NewsClient.dto;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.dmitry.NewsClient.exeption.ValidationConstants;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class LoginUserDto {
    private String avatar;

    @NotBlank(message = ValidationConstants.USER_EMAIL_NOT_NULL)
    @Email(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = ValidationConstants.USER_EMAIL_MIN_SIZE,
            max = ValidationConstants.USER_EMAIL_MAX_SIZE, message = ValidationConstants.USER_EMAIL_NOT_VALID)
    private String email;

    UUID id;

    @Size(min = ValidationConstants.USER_NAME_MIN_SIZE, max = ValidationConstants.USER_NAME_MAX_SIZE,
            message = ValidationConstants.USERNAME_SIZE_NOT_VALID)
    private String name;
    private String role;
    private String token;

}
