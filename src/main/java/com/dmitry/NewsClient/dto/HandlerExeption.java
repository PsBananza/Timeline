package com.dmitry.NewsClient.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class HandlerExeption extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CustomExeption.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public final ResponseEntity<CustomResponseEntityExeption> handleAccessDeniedException(CustomExeption customExeption) {
        var errorDetails = new CustomResponseEntityExeption();
        errorDetails.setTimestamp(LocalDateTime
                        .now()
                        .toString())
                .setStatus(String.valueOf(customExeption.getCode()))
                .setError(customExeption.getMessage());

        return ResponseEntity.badRequest().body(errorDetails);
    }

}
