package com.dmitry.NewsClient.service.interfaceService;

import com.dmitry.NewsClient.dto.AuthDto;
import com.dmitry.NewsClient.dto.LoginUserDto;

public interface AuthServiceInt {
    public LoginUserDto authUser(AuthDto authDto);
}
