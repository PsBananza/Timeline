package com.dmitry.NewsClient.config;

import java.util.UUID;

import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.repository.RepositoryUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private RepositoryUser userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findById(UUID.fromString(username));
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }
}
