package com.dmitry.NewsClient.service;

import com.dmitry.NewsClient.dto.LoginUserDto;
import com.dmitry.NewsClient.dto.RegisterUserDto;
import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.repository.RepositoryUser;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  implements RegistrationService {

    private final RepositoryUser userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginUserDto registerUser(RegisterUserDto userDto) {
        if (userRepo.findByEmail(userDto.getEmail()) != null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        UserEntity entity = new UserEntity();
        entity.setAvatar(userDto.getAvatar());
        entity.setEmail(userDto.getEmail());
        entity.setName(userDto.getName());
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        entity.setRole(userDto.getRole());
        userRepo.save(entity);
        LoginUserDto loginUserDto = new LoginUserDto()
        .setAvatar(userDto.getAvatar())
        .setEmail(userDto.getEmail())
                .setId(entity.getId())
                .setName(userDto.getName())
                .setRole(userDto.getRole())
                .setToken("rgdfg");


        return loginUserDto;
    }
}
