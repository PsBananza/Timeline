package com.dmitry.NewsClient.service;

import com.dmitry.NewsClient.dto.AuthDto;
import com.dmitry.NewsClient.dto.LoginUserDto;

public interface AuthService {
    public LoginUserDto authUser(AuthDto authDto);
}
