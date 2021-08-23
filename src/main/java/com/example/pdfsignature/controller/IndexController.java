package com.example.pdfsignature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.pdfsignature.service.FileInfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final FileInfoService fileInfoService;

    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/files";
    }
}
