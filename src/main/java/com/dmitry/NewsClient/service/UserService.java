package com.dmitry.NewsClient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dmitry.NewsClient.dto.PublicUserView;
import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.repository.RepositoryUser;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RepositoryUser userRepo;

    public List<PublicUserView> getUserAll() {
        List<UserEntity> userEntity = userRepo.findAll();
        PublicUserView userView = new PublicUserView();
        List<PublicUserView> publicUserViews = new ArrayList<>();
        for (UserEntity p: userEntity) {
            userView.setAvatar(p.getAvatar())
                    .setName(p.getName())
                    .setRole(p.getRole())
                    .setEmail(p.getEmail())
                    .setId(p.getId());
            publicUserViews.add(userView);
        }
        return publicUserViews;
    }

    public PublicUserView getUserId(UUID id) {
        UserEntity entity = userRepo.findById(id);
        PublicUserView userView = new PublicUserView();
        userView.setId(entity.getId())
                .setRole(entity.getRole())
                .setAvatar(entity.getAvatar())
                .setName(entity.getName())
                .setEmail(entity.getEmail());

        return userView;
    }

    public PublicUserView getUserInfo(String email) {
        UserEntity entity = userRepo.findByEmail(email);
        PublicUserView userView = new PublicUserView();
        userView.setId(entity.getId())
                .setRole(entity.getRole())
                .setAvatar(entity.getAvatar())
                .setName(entity.getName())
                .setEmail(entity.getEmail());

        return userView;
    }




}
