package com.dmitry.NewsClient.controller;

import java.io.IOException;

import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@CrossOrigin
@RequestMapping("/file")
@RestController
@RequiredArgsConstructor
@Log
public class FileController {

    private final FileService fileService;

    @GetMapping("/{fileName}")
    public ResponseEntity<UrlResource> getUser(@PathVariable String fileName) throws CustomException, IOException {

        return new ResponseEntity(fileService.getFile(fileName), HttpStatus.OK);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<CustomSuccessResponse<String>> registerUser(MultipartFile file) throws CustomException, IOException {

        return new ResponseEntity(new CustomSuccessResponse(fileService.fileUpt(file)), HttpStatus.OK);
    }

}
