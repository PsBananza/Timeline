package com.dmitry.NewsClient.controller;

import java.io.IOException;

import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@CrossOrigin
@RequestMapping("/file")
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    @Value("${server.fileDirectoryUrl}")
    public String fileDirUrl;

    @GetMapping("/{fileName}")
    @ResponseBody
    public ResponseEntity<Resource> getUser(@PathVariable(value = "fileName") final String fileName) throws CustomException, IOException {
        Resource file = fileService.getFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<CustomSuccessResponse<String>> registerUser(@RequestParam("file") MultipartFile file) throws CustomException, IOException {

        return new ResponseEntity(new CustomSuccessResponse(fileDirUrl + fileService.fileUpt(file)), HttpStatus.OK);
    }

}
