package com.dmitry.NewsClient.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomResponseEntityExeption {

    private String timestamp;
    private String status;
    private String error;

}
