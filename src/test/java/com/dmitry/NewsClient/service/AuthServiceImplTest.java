package com.dmitry.NewsClient.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dmitry.NewsClient.dto.AuthDto;
import com.dmitry.NewsClient.dto.LoginUserDto;
import com.dmitry.NewsClient.dto.RegisterUserDto;
import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.exeption.ErrorCodes;
import com.dmitry.NewsClient.mapstruct.LoginUserDtoMapper;
import com.dmitry.NewsClient.repository.RepositoryUser;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuthServiceImplTest {
    @Autowired
    private AuthServiceImpl authServiceImpl;

    @MockBean
    private LoginUserDtoMapper loginUserDtoMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RepositoryUser repositoryUser;

    @Test
    void testRegisterUser() {
        UserEntity userEntity = new UserEntity()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setId(UUID.randomUUID())
                .setName("Name")
                .setNews(new ArrayList<>())
                .setPassword("banana")
                .setRole("Role");

        UserEntity userEntity1 = new UserEntity()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setId(UUID.randomUUID())
                .setName("Name")
                .setNews(new ArrayList<>())
                .setPassword("banana")
                .setRole("Role");
        when(repositoryUser.findByEmail((String) any())).thenReturn(userEntity);
        when(repositoryUser.save((UserEntity) any())).thenReturn(userEntity1);
        when(repositoryUser.save(any())).thenReturn(userEntity1);

        RegisterUserDto registerUserDto = new RegisterUserDto()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setName("Name")
                .setPassword("banana")
                .setRole("Role");
        assertThrows(CustomException.class, () -> authServiceImpl.registerUser(registerUserDto));
        verify(repositoryUser).findByEmail((String) any());
    }

    @Test
    void testRegisterUser2() {
        when(repositoryUser.findByEmail((String) any())).thenThrow(new CustomException(ErrorCodes.UNKNOWN));
        when(repositoryUser.save((UserEntity) any())).thenThrow(new CustomException(ErrorCodes.UNKNOWN));

        RegisterUserDto registerUserDto = new RegisterUserDto()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setName("Name")
                .setPassword("banana")
                .setRole("Role");
        assertThrows(CustomException.class, () -> authServiceImpl.registerUser(registerUserDto));
        verify(repositoryUser).findByEmail((String) any());
    }

    @Test
    void testAuthUser() {
        UserEntity userEntity = new UserEntity()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setId(UUID.randomUUID())
                .setName("Name")
                .setNews(new ArrayList<>())
                .setPassword("banana")
                .setRole("Role");
        when(repositoryUser.findByEmail((String) any())).thenReturn(userEntity);

        LoginUserDto loginUserDto = new LoginUserDto()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setId(UUID.randomUUID())
                .setName("Name")
                .setRole("Role")
                .setToken("ABC123");
        when(loginUserDtoMapper.loginUserDto((UserEntity) any())).thenReturn(loginUserDto);
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(true);

        AuthDto authDto = new AuthDto();
        authDto.setEmail("Bananza@yandex.ru");
        authDto.setPassword("banana");
        assertSame(loginUserDto, authServiceImpl.authUser(authDto));
        verify(repositoryUser).findByEmail((String) any());
        verify(loginUserDtoMapper).loginUserDto((UserEntity) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
    }

    @Test
    void testAuthUser2() {
        UserEntity userEntity = new UserEntity()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setId(UUID.randomUUID())
                .setName("Name")
                .setNews(new ArrayList<>())
                .setPassword("banana")
                .setRole("Role");
        when(repositoryUser.findByEmail((String) any())).thenReturn(userEntity);
        when(loginUserDtoMapper.loginUserDto((UserEntity) any())).thenThrow(new CustomException(ErrorCodes.UNKNOWN));
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(true);

        AuthDto authDto = new AuthDto();
        authDto.setEmail("Bananza@yandex.ru");
        authDto.setPassword("banana");
        assertThrows(CustomException.class, () -> authServiceImpl.authUser(authDto));
        verify(repositoryUser).findByEmail((String) any());
        verify(loginUserDtoMapper).loginUserDto((UserEntity) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
    }

    @Test
    void testAuthUser3() {
        UserEntity userEntity = new UserEntity()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setId(UUID.randomUUID())
                .setName("Name")
                .setNews(new ArrayList<>())
                .setPassword("banana")
                .setRole("Role");
        when(repositoryUser.findByEmail((String) any())).thenReturn(userEntity);

        LoginUserDto loginUserDto = new LoginUserDto()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setId(UUID.randomUUID())
                .setName("Name")
                .setRole("Role")
                .setToken("ABC123");
        when(loginUserDtoMapper.loginUserDto((UserEntity) any())).thenReturn(loginUserDto);
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(false);

        AuthDto authDto = new AuthDto();
        authDto.setEmail("Bananza@yandex.ru");
        authDto.setPassword("banana");
        assertThrows(CustomException.class, () -> authServiceImpl.authUser(authDto));
        verify(repositoryUser).findByEmail((String) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
    }
}

