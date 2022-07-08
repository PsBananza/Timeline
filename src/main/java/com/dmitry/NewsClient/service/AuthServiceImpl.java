package com.dmitry.NewsClient.service;

import com.dmitry.NewsClient.dto.AuthDto;
import com.dmitry.NewsClient.dto.LoginUserDto;
import com.dmitry.NewsClient.dto.RegisterUserDto;
import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.exeption.ErrorCodes;
import com.dmitry.NewsClient.mapstruct.LoginUserDtoMapper;
import com.dmitry.NewsClient.repository.RepositoryUser;
import com.dmitry.NewsClient.service.authInterface.AuthService;
import com.dmitry.NewsClient.service.authInterface.RegistrationService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements RegistrationService, AuthService {

    private final RepositoryUser userRepo;
    private final PasswordEncoder passwordEncoder;
    private final LoginUserDtoMapper loginUserDtoMapper;

    @Override
    public LoginUserDto registerUser(RegisterUserDto userDto) {
        if (userRepo.findByEmail(userDto.getEmail()) != null) {
            throw new CustomException(ErrorCodes.USER_WITH_THIS_EMAIL_ALREADY_EXIST);
        }
        UserEntity entity = new UserEntity()
                .setEmail(userDto.getEmail())
                .setName(userDto.getName())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setRole(userDto.getRole())
                .setAvatar(FileServiceImp.avatar);
        userRepo.save(entity);
        LoginUserDto loginUserDto = loginUserDtoMapper.loginUserDto(entity);
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
        LoginUserDto loginUserDto = loginUserDtoMapper.loginUserDto(entity);

        return loginUserDto;
    }

}
