package com.dmitry.NewsClient.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors
public class PageableResponse<T> {

    private List<GetNewsOutDto> content;
    private Long numberOfElements;

}
