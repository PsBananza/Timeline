package com.dmitry.NewsClient.service.fileInterface;

import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface FileService {
    String fileUpt(MultipartFile multipartFile) throws IOException;
    UrlResource getFile(final String fileName) throws MalformedURLException;
}
