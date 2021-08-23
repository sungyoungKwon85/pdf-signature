package com.example.pdfsignature.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.pdfsignature.dto.FileDto;
import com.example.pdfsignature.service.FileInfoService;
import com.example.pdfsignature.service.FileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FileController {

    private final FileService fileService;
    private final FileInfoService fileInfoService;

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestParam MultipartFile[] files, Model model) throws IOException {
        List<FileDto> fileDtoList = fileInfoService.saveFiles(fileService.upload(files));
        model.addAttribute("files", fileDtoList);
        return "redirect:/files";
    }

    @GetMapping("/files/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable("id") Long id) throws IOException {
        return fileService.download(fileInfoService.getFile(id));
    }

    @GetMapping("/files")
    public String files(Model model) {
        model.addAttribute("files", fileInfoService.getFiles());
        return "index";
    }

    @DeleteMapping("/files/{id}")
    public String delete(@PathVariable("id") Long id) throws IOException {
        fileService.deleteFile(fileInfoService.deleteAndGetFileDto(id));
        return "index";
    }

}
