package com.example.pdfsignature.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileDto {

    private Long id;
    private String prefix;
    private String fileName;
    private String contentType;
    private LocalDateTime lastModifiedDate;

    @Builder
    public FileDto(Long id, String prefix, String fileName, String contentType, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.prefix = prefix;
        this.fileName = fileName;
        this.contentType = contentType;
        this.lastModifiedDate = lastModifiedDate;
    }

    @Builder
    public FileDto(String prefix, String fileName, String contentType, LocalDateTime lastModifiedDate) {
        this.prefix = prefix;
        this.fileName = fileName;
        this.contentType = contentType;
        this.lastModifiedDate = lastModifiedDate;
    }
}
