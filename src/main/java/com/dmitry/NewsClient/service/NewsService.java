package com.dmitry.NewsClient.service;

import com.dmitry.NewsClient.dto.*;
import com.dmitry.NewsClient.entity.NewsEntity;
import com.dmitry.NewsClient.entity.Tag;
import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.repository.NewsRep;
import com.dmitry.NewsClient.repository.RepositoryUser;
import com.dmitry.NewsClient.repository.TagRep;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final RepositoryUser userRepo;
    private final TagRep tagRepo;
    private final NewsRep newsRepo;

    public CreateNewsSuccessResponse createNews (NewsDto newsDto){
        NewsEntity newsEntity = new NewsEntity();
        UserEntity entity = userRepo.findById(newsDto.getUser_id());
        List<Tag> list = new ArrayList<>();

        for (String tags: newsDto.getTags()) {
            Tag tag = new Tag();
            tag.setTitle(tags);
            list.add(tag);
        }
        newsEntity.setDescription(newsDto.getDescription())
                .setImage(FileService.avatar)
                .setTitle(newsDto.getTitle())
                .setTags(list)
                .setUser(entity);
        newsRepo.save(newsEntity);

        for (Tag tags: list) {
            tags.setEntity(newsEntity);
            tagRepo.save(tags);
        }
        CreateNewsSuccessResponse response = new CreateNewsSuccessResponse();
        response.setId(newsEntity.getId());
        return new CreateNewsSuccessResponse();
    }

    public CustomSuccessResponse getNews (int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<NewsEntity> pagedResult = newsRepo.findAll(pageable);
        List<GetNewsOutDto> getNewsOutDto = new ArrayList<>();
        PageableResponse<GetNewsOutDto> response = new PageableResponse<>();
        for (NewsEntity entity : pagedResult ) {
            GetNewsOutDto newsEntity = new GetNewsOutDto();
            newsEntity.setId(entity.getId());
            newsEntity.setDescription(entity.getDescription());
            newsEntity.setImage(entity.getImage());
            newsEntity.setTags(entity.getTags());
            newsEntity.setTitle(entity.getTitle());
            newsEntity.setUserId(entity.getUser().getId());
            newsEntity.setUsername(entity.getUser().getName());
            getNewsOutDto.add(newsEntity);
        }
        response.setContent(getNewsOutDto);
        response.setNumberOfElements(Long.valueOf(getNewsOutDto.size()));
        return new CustomSuccessResponse(response);
    }

}
