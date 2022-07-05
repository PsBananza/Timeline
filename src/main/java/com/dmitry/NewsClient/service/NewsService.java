package com.dmitry.NewsClient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dmitry.NewsClient.dto.BaseSuccessResponse;
import com.dmitry.NewsClient.dto.CreateNewsSuccessResponse;
import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.dto.GetNewsOutDto;
import com.dmitry.NewsClient.dto.NewsDto;
import com.dmitry.NewsClient.dto.PageableResponse;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final RepositoryUser userRepo;
    private final TagRep tagRepo;
    private final NewsRep newsRepo;
    private final EntityManager entityManager;

    public CreateNewsSuccessResponse createNews(NewsDto newsDto, UUID id) {
        NewsEntity newsEntity = new NewsEntity();
        UserEntity entity = userRepo.findById(newsDto.getUser_id());
        List<Tag> list = new ArrayList<>();

        for (String tags: newsDto.getTags()) {
            Tag tag = tagRepo.findByTitle(tags);
            if (tag == null) {
                tag = new Tag();
                tag.setTitle(tags);
            }
            list.add(tag);
        }
        newsEntity.setDescription(newsDto.getDescription())
                .setImage(FileService.avatar)
                .setTitle(newsDto.getTitle())
                .setTags(list)
                .setUser(entity)
                .setUsername(userRepo.findById(id).getName());
        newsRepo.save(newsEntity);

        for (Tag tags: list) {
            tagRepo.save(tags);
        }
        CreateNewsSuccessResponse response = new CreateNewsSuccessResponse();
        response.setId(newsEntity.getId());
        return new CreateNewsSuccessResponse();
    }

    public CustomSuccessResponse getNews(int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage, Sort.by("id").descending());
        Page<NewsEntity> pagedResult = newsRepo.findAll(pageable);
        List<GetNewsOutDto> getNewsOutDto = new ArrayList<>();
        PageableResponse<GetNewsOutDto> response = new PageableResponse<>();
        for (NewsEntity entity : pagedResult) {
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
        response.setNumberOfElements(pagedResult.getTotalElements());
        return new CustomSuccessResponse(response);
    }

    public PageableResponse findNews(int page, int perPage, String author, String keywords, String tags) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> criteriaQuery = criteriaBuilder.createQuery(NewsEntity.class);
        Root<NewsEntity> newsRoot = criteriaQuery.from(NewsEntity.class);

        List<Predicate> predicateList = new ArrayList<>();
        List<Predicate> tagsPredicateList = new ArrayList<>();

        if (author != null) {
            predicateList.add(criteriaBuilder.equal(newsRoot.get("username"), author));
        }
        if (keywords != null) {
            predicateList.add(criteriaBuilder.like(newsRoot.get("title"), keywords));
        }
        if (tags != null) {
            for (String tag : tags.split(",")) {
                Tag tagEntity = tagRepo.findByTitle(tag);
                tagsPredicateList.add(criteriaBuilder.isMember(tagEntity, newsRoot.get("tags")));
            }
            Predicate[] tagsPredicate = new Predicate[tagsPredicateList.size()];
            tagsPredicateList.toArray(tagsPredicate);
            predicateList.add(criteriaBuilder.or(tagsPredicate));
        }
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        criteriaQuery.select(newsRoot).where(criteriaBuilder.and(predicates));

        TypedQuery<NewsEntity> query = entityManager.createQuery(criteriaQuery);

        query.setFirstResult((page - 1) * perPage);
        query.setMaxResults(perPage);

        List<NewsEntity> newsEntityList = query.getResultList();
        List<GetNewsOutDto> getNewsOutDtos = new ArrayList<>();

        for (NewsEntity entity : newsEntityList) {
            GetNewsOutDto newsEntity = new GetNewsOutDto();
            newsEntity.setId(entity.getId())
                    .setDescription(entity.getDescription())
                    .setImage(entity.getImage())
                    .setTags(entity.getTags())
                    .setTitle(entity.getTitle())
                    .setUserId(entity.getUser().getId())
                    .setUsername(entity.getUser().getName());
            getNewsOutDtos.add(newsEntity);
        }
        PageableResponse pageableResponse = new PageableResponse();
        pageableResponse.setContent(getNewsOutDtos)
                .setNumberOfElements(Long.valueOf(getNewsOutDtos.size()));

        return pageableResponse;
    }

    public CustomSuccessResponse getUserNews(int page, int perPage, UUID id) {
        Pageable pageable = PageRequest.of(page - 1, perPage, Sort.by("id").descending());
        UserEntity user = userRepo.findById(id);
        Page<NewsEntity> pagedResult = newsRepo.findByUser(pageable, user);
        List<GetNewsOutDto> getNewsOutDto = new ArrayList<>();
        PageableResponse<GetNewsOutDto> response = new PageableResponse<>();
        for (NewsEntity entity : pagedResult) {
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
        response.setNumberOfElements(pagedResult.getTotalElements());
        return new CustomSuccessResponse(response);
    }

    public BaseSuccessResponse deleteNews(long id) {
        NewsEntity entity = newsRepo.findById(id);
        List<Tag> listTagDeleteNews = entity.getTags();
        List<Tag> list = List.of();
        entity.setTags(list);
        entity.setUser(new UserEntity());
        newsRepo.delete(entity);
        for (Tag s: listTagDeleteNews) {
            if (newsRepo.findByTags(s) == null) {
                tagRepo.delete(s);
            }
        }
        return new BaseSuccessResponse();
    }





}