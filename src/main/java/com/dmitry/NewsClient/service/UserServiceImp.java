package com.dmitry.NewsClient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dmitry.NewsClient.dto.BaseSuccessResponse;
import com.dmitry.NewsClient.dto.PublicUserView;
import com.dmitry.NewsClient.dto.PutUserDto;
import com.dmitry.NewsClient.dto.PutUserDtoResponse;
import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.mapstruct.UserViewMapper;
import com.dmitry.NewsClient.repository.RepositoryUser;
import com.dmitry.NewsClient.service.userInterface.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserViewMapper userViewMapper;

    private final RepositoryUser userRepo;

    @Override
    public List<PublicUserView> getUserAll() {
        List<UserEntity> userEntity = userRepo.findAll();
        PublicUserView userView = new PublicUserView();
        List<PublicUserView> publicUserViews = new ArrayList<>();
        for (UserEntity p: userEntity) {
            userViewMapper.userView(p);
            publicUserViews.add(userView);
        }
        return publicUserViews;
    }

    @Override
    public PublicUserView getUserId(UUID id) {
        UserEntity entity = userRepo.findById(id);
        PublicUserView userView1 = userViewMapper.userView(entity);
        return userView1;
    }

    @Override
    public PublicUserView getUserInfo(UUID id) {
        UserEntity entity = userRepo.findById(id);
        PublicUserView userView1 = userViewMapper.userView(entity);
        return userView1;
    }

    @Override
    public PutUserDtoResponse putUserDtoResponse(PutUserDto userDto, UUID id) {
        UserEntity entity = userRepo.findById(id);
        entity.setAvatar(userDto.getAvatar())
                .setEmail(userDto.getEmail())
                .setName(userDto.getName())
                .setAvatar(FileServiceImp.avatar)
                .setRole(userDto.getRole());
        userRepo.save(entity);
        return userViewMapper.userDtoResp(entity);
    }

    @Override
    public BaseSuccessResponse deleteUser(UUID id) {
        UserEntity entity = userRepo.findById(id);
        userRepo.delete(entity);
        return new BaseSuccessResponse();
    }




}
