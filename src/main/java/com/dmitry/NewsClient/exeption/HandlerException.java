package com.dmitry.NewsClient.exeption;

import java.time.LocalDateTime;

import com.dmitry.NewsClient.dto.CustomResponseEntityException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {
    @ExceptionHandler({CustomException.class})
    public final ResponseEntity<CustomResponseEntityException> handleAccessDeniedException(CustomException customExeption) {
        var errorDetails = new CustomResponseEntityException();
        errorDetails.setTimestamp(LocalDateTime
                        .now()
                        .toString())
                .setStatus(String.valueOf(customExeption.getCode()))
                .setError(customExeption.getMessage());

        return ResponseEntity.badRequest().body(errorDetails);
    }

}
