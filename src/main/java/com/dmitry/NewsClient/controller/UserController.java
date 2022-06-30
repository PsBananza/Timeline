package com.dmitry.NewsClient.controller;

import java.util.List;
import java.util.UUID;

import com.dmitry.NewsClient.config.CustomUserDetails;
import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.dto.PublicUserView;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<CustomSuccessResponse<List<PublicUserView>>> allUser() throws CustomException {
        return new ResponseEntity(new CustomSuccessResponse(userService.getUserAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomSuccessResponse<PublicUserView>> idUser(@PathVariable UUID id) throws CustomException {
        return new ResponseEntity(new CustomSuccessResponse(userService.getUserId(id)), HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<CustomSuccessResponse<PublicUserView>> infoUser(Authentication authentication) throws CustomException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return new ResponseEntity(new CustomSuccessResponse(userService.getUserInfo(userDetails.getUsername())), HttpStatus.OK);
    }


}
