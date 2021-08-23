package com.example.pdfsignature.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileDto {
    private String prefix;
    private String fileName;
    private String contentType;

    @Builder
    public FileDto(String prefix, String fileName, String contentType) {
        this.prefix = prefix;
        this.fileName = fileName;
        this.contentType = contentType;
    }
}
