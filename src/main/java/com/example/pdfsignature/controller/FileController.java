package com.example.pdfsignature.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.pdfsignature.dto.FileDto;
import com.example.pdfsignature.service.FileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/v1")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestParam MultipartFile[] files, Model model) throws IOException {
        model.addAttribute("files", fileService.upload(files));
        return "list";
    }

    @GetMapping("download")
    public ResponseEntity<Resource> download(@ModelAttribute FileDto fileDto) throws IOException {
        return fileService.download(fileDto);
    }

}
