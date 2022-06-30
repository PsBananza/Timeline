package com.dmitry.NewsClient.controller;

import com.dmitry.NewsClient.dto.AuthDto;
import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.dto.LoginUserDto;
import com.dmitry.NewsClient.dto.RegisterUserDto;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<CustomSuccessResponse<LoginUserDto>> registerUser(@RequestBody @Validated RegisterUserDto userDto) throws CustomException {
        return new ResponseEntity(new CustomSuccessResponse(userService.registerUser(userDto)), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomSuccessResponse<LoginUserDto>> authUser(@RequestBody @Validated AuthDto authDto) throws CustomException {
        return new ResponseEntity(new CustomSuccessResponse(userService.authUser(authDto)), HttpStatus.OK);
    }

}
