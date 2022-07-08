package com.dmitry.NewsClient.service.newsInterface;

import com.dmitry.NewsClient.dto.*;

import java.util.UUID;

public interface NewsService {
    CreateNewsSuccessResponse createNews(NewsDto newsDto, UUID id);
    CustomSuccessResponse getNews(int page, int perPage);
    PageableResponse findNews(int page, int perPage, String author, String keywords, String tags);
    CustomSuccessResponse getUserNews(int page, int perPage, UUID id);
    BaseSuccessResponse deleteNews(long id);
    BaseSuccessResponse putNews(long id, NewsDto newsDto);
}
