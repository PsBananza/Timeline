package com.dmitry.NewsClient.service;

import com.dmitry.NewsClient.config.jwt.JwtProvider;
import com.dmitry.NewsClient.dto.AuthDto;
import com.dmitry.NewsClient.dto.LoginUserDto;
import com.dmitry.NewsClient.dto.RegisterUserDto;
import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.exeption.ErrorCodes;
import com.dmitry.NewsClient.repository.RepositoryUser;
import com.dmitry.NewsClient.service.interfaceService.AuthServiceInt;
import com.dmitry.NewsClient.service.interfaceService.RegistrationService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements RegistrationService, AuthServiceInt {

    private final RepositoryUser userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginUserDto registerUser(RegisterUserDto userDto) {
        if (userRepo.findByEmail(userDto.getEmail()) != null) {
            throw new CustomException(ErrorCodes.USER_WITH_THIS_EMAIL_ALREADY_EXIST);
        }
        UserEntity entity = new UserEntity();
        entity.setEmail(userDto.getEmail());
        entity.setName(userDto.getName());
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        entity.setRole(userDto.getRole());
        entity.setAvatar(FileService.avatar);
        JwtProvider jwtProvider = new JwtProvider();
        userRepo.save(entity);
        LoginUserDto loginUserDto = new LoginUserDto()
        .setAvatar(entity.getAvatar())
        .setEmail(userDto.getEmail())
                .setId(entity.getId())
                .setName(userDto.getName())
                .setRole(userDto.getRole())
                .setToken(jwtProvider.generateToken(entity.getId()));


        return loginUserDto;
    }
    @Override
    public LoginUserDto authUser(AuthDto authDto) {
        UserEntity entity;
        entity = userRepo.findByEmail(authDto.getEmail());
        if (entity == null) {
            throw new CustomException(ErrorCodes.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(authDto.getPassword(), entity.getPassword())) {
            throw new CustomException(ErrorCodes.PASSWORD_NOT_VALID);
        }
        JwtProvider jwtProvider = new JwtProvider();
        LoginUserDto loginUserDto = new LoginUserDto()
                .setAvatar(entity.getAvatar())
                .setEmail(entity.getEmail())
                .setId(entity.getId())
                .setName(entity.getName())
                .setRole(entity.getRole())
                .setToken(jwtProvider.generateToken(entity.getId()));

        return loginUserDto;
    }

}
