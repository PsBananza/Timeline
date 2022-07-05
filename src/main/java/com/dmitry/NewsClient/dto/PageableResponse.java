package com.dmitry.NewsClient.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageableResponse<T> {

    List<GetNewsOutDto> content;
    Long numberOfElements;

}
