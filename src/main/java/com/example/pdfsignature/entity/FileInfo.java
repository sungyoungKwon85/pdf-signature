package com.example.pdfsignature.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.pdfsignature.dto.FileDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "tb_file")
public class FileInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prefix;

    private String fileName;

    private String contentType;

    @Builder
    public FileInfo(String prefix, String fileName, String contentType) {
        this.prefix = prefix;
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public FileDto toFileDto() {
        return FileDto.builder()
            .id(id)
            .prefix(prefix)
            .fileName(fileName)
            .contentType(contentType)
            .lastModifiedDate(getLastModifiedDate())
            .build();
    }
}
