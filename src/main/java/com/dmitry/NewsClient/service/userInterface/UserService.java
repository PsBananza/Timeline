package com.dmitry.NewsClient.service.userInterface;

import com.dmitry.NewsClient.dto.BaseSuccessResponse;
import com.dmitry.NewsClient.dto.PublicUserView;
import com.dmitry.NewsClient.dto.PutUserDto;
import com.dmitry.NewsClient.dto.PutUserDtoResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<PublicUserView> getUserAll();
    PublicUserView getUserId(UUID id);
    PublicUserView getUserInfo(UUID id);
    PutUserDtoResponse putUserDtoResponse(PutUserDto userDto, UUID id);
    BaseSuccessResponse deleteUser(UUID id);

}
