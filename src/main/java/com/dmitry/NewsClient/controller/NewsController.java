package com.dmitry.NewsClient.controller;

import com.dmitry.NewsClient.config.jwt.JwtProvider;
import com.dmitry.NewsClient.dto.*;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequestMapping("/news")
@RestController
@RequiredArgsConstructor
public class NewsController {
    private final NewsService service;
    private final JwtProvider provider;

    @PostMapping
    public CreateNewsSuccessResponse createNews(@RequestHeader(name = "Authorization") String token,
                                                @RequestBody NewsDto newsDto) throws CustomException {
        return service.createNews(newsDto, UUID.fromString(provider.getLoginFromToken(token)));
    }

    @GetMapping
    public CustomSuccessResponse<PageableResponse<List<GetNewsOutDto>>> getNews(@RequestParam int page,
                                                                                @RequestParam int perPage) throws CustomException {
        return service.getNews(page, perPage);
    }

    @GetMapping("/find")
    public PageableResponse<List<GetNewsOutDto>> findNews(@RequestParam int page,
                                                          @RequestParam int perPage,
                                                          @RequestParam(required = false) String author,
                                                          @RequestParam(required = false) String tags,
                                                          @RequestParam(required = false) String keywords) throws CustomException {
        return service.findNews(page, perPage, author, keywords, tags);
    }

    @GetMapping("/user/{userId}")
    public CustomSuccessResponse<PageableResponse<List<GetNewsOutDto>>> getUserNews(@RequestParam int page,
                                                                                    @RequestParam int perPage,
                                                                                    @PathVariable UUID userId) throws CustomException {
        return service.getUserNews(page, perPage, userId);
    }


}
