package com.example.pdfsignature.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.pdfsignature.entity.FileInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class FileInfoRepositoryTest {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Test
    public void saveAndFindById() {
        FileInfo fileInfo = FileInfo.builder()
            .prefix("123")
            .fileName("filename")
            .contentType("pdf")
            .build();

        FileInfo savedFile = fileInfoRepository.save(fileInfo);
        FileInfo foundFile = fileInfoRepository.findById(savedFile.getId()).orElseGet(null);

        assertNotNull(foundFile);
        assertEquals(foundFile.getFileName(), fileInfo.getFileName());
    }

}