package com.dmitry.NewsClient.dto;

import com.dmitry.NewsClient.exeption.ErrorCodes;
import lombok.Getter;

@Getter
public class CustomExeption extends RuntimeException {

    private int code;

    public CustomExeption(ErrorCodes errorCodes) {
        super(errorCodes.getMessage());
        this.code = errorCodes.getCode();
    }

}
