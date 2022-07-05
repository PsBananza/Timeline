package com.dmitry.NewsClient.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;





@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${storage.dir}")
    private String uploadDir;
    private final Path root = Paths.get("src/main/resources/avatar");
    public static String avatar;
    public String fileUpt(MultipartFile multipartFile) throws IOException {
        String fileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        Path copyLocation = Paths.get(uploadDir + File.separator + fileName);
        avatar = "http://localhost:8080/file/" + fileName;
        Files.copy(multipartFile.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        return avatar;
    }

        public UrlResource getFile(final String fileName) throws IOException {
            Path file = root.resolve(fileName);
            return new UrlResource(file.toUri());
        }




}
