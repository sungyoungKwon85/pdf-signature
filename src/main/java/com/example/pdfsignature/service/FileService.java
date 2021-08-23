package com.example.pdfsignature.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.pdfsignature.dto.FileDto;


@Service
public class FileService {

    @Value("${spring.servlet.multipart.location}")
    private String filePath;
    private static final String SEPARATOR = "_";

    public List<FileDto> upload(MultipartFile[] files) throws IOException {
        List<FileDto> list = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                FileDto fileDto = FileDto.builder().prefix(String.valueOf(System.currentTimeMillis()))
                    .fileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .build();

                list.add(fileDto);

                File newFile = new File(fileDto.getPrefix() + SEPARATOR + fileDto.getFileName());
                file.transferTo(newFile);
            }
        }
        return list;
    }

    public ResponseEntity<Resource> download(FileDto fileDto) throws IOException {
        Path path = Paths.get(filePath + "/" + fileDto.getPrefix() + SEPARATOR + fileDto.getFileName());
        String contentType = Files.probeContentType(path);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(ContentDisposition.builder("attachment")
            .filename(fileDto.getFileName(), StandardCharsets.UTF_8)
            .build());
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, contentType);
        Resource inputStreamResource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(inputStreamResource, httpHeaders, HttpStatus.OK);
    }
}
