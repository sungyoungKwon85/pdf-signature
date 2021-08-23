package com.example.pdfsignature.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pdfsignature.dto.FileDto;
import com.example.pdfsignature.entity.FileInfo;
import com.example.pdfsignature.repository.FileInfoRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FileInfoService {

    private final FileInfoRepository fileInfoRepository;

    @Transactional
    public List<FileDto> saveFiles(List<FileDto> fileDtoList) {
        List<FileInfo> fileInfos = fileInfoRepository.saveAll(fileDtoList.stream()
            .map(dto -> FileInfo.builder()
                .prefix(dto.getPrefix())
                .fileName(dto.getFileName())
                .contentType(dto.getContentType())
                .build())
            .collect(Collectors.toList()));

        return fileInfos.stream()
            .map(fileInfo -> fileInfo.toFileDto())
            .collect(Collectors.toList());
    }

    public List<FileDto> getFiles() {
        return fileInfoRepository.findAll().stream()
            .map(fileInfo -> fileInfo.toFileDto())
            .collect(Collectors.toList());
    }

    public FileDto getFile(Long id) {
        return fileInfoRepository.findById(id).map(fileInfo -> fileInfo.toFileDto()).orElseGet(null);
    }

    @Transactional
    public FileDto deleteAndGetFileDto(Long id) {
        FileInfo fileInfo = fileInfoRepository.findById(id).orElseGet(null);
        if (fileInfo != null) {
            FileDto fileDto = fileInfo.toFileDto();
            fileInfoRepository.deleteById(id);
            return fileDto;
        }
        return null;
    }
}
