package com.dmitry.NewsClient.exeption;

import lombok.Getter;

@Getter
public class CustomExeption extends RuntimeException {

    private int code;

    public CustomExeption(ErrorCodes errorCodes) {
        super(errorCodes.getMessage());
        this.code = errorCodes.getCode();
    }

}
