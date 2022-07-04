package com.dmitry.NewsClient.controller;

import com.dmitry.NewsClient.dto.*;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/news")
@RestController
@RequiredArgsConstructor
public class NewsController {
    private final NewsService service;

    @PostMapping
    public CreateNewsSuccessResponse createNews(@RequestBody NewsDto newsDto) throws CustomException {
        return service.createNews(newsDto);
    }

    @GetMapping
    public CustomSuccessResponse<PageableResponse<List<GetNewsOutDto>>> getNews(@RequestParam int page, @RequestParam int perPage) throws CustomException {

        return service.getNews(page, perPage);
    }

}
