package com.dmitry.NewsClient.controller;

import javax.validation.Valid;

import com.dmitry.NewsClient.dto.AuthDto;
import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.dto.LoginUserDto;
import com.dmitry.NewsClient.dto.RegisterUserDto;
import com.dmitry.NewsClient.service.authInterface.AuthService;
import com.dmitry.NewsClient.service.authInterface.RegistrationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService userService;
    private final RegistrationService registerService;

    @PostMapping("/register")
    public ResponseEntity<CustomSuccessResponse<LoginUserDto>> registerUser(@RequestBody @Valid RegisterUserDto userDto) {
        return new ResponseEntity(new CustomSuccessResponse(registerService.registerUser(userDto)), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomSuccessResponse<LoginUserDto>> authUser(@RequestBody @Valid AuthDto authDto) {
        return new ResponseEntity(new CustomSuccessResponse(userService.authUser(authDto)), HttpStatus.OK);
    }

}
