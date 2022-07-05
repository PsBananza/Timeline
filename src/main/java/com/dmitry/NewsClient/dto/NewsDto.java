package com.dmitry.NewsClient.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
public class NewsDto {

    @Size(min = 3, max = 160)
    private String description;
    private List<String> tags;
    @Size(min = 3, max = 25)
    private String image;
    @Size(min = 3, max = 160)
    private String title;
    private UUID user_id;

}
