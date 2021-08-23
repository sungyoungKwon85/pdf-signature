package com.example.pdfsignature.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.pdfsignature.dto.FileDto;
import com.example.pdfsignature.service.FileInfoService;
import com.example.pdfsignature.service.FileService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(FileController.class)
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;
    @MockBean
    private FileInfoService fileInfoService;


    @Test
    public void index() throws Exception {
        List<FileDto> fileDtoList = new ArrayList<>();
        FileDto dto1 = FileDto.builder()
            .id(1L)
            .prefix("111")
            .fileName("filename1")
            .contentType("pdf")
            .lastModifiedDate(LocalDateTime.now().minusDays(5))
            .build();
        FileDto dto2 = FileDto.builder()
            .id(2L)
            .prefix("222")
            .fileName("filename2")
            .contentType("pdf")
            .lastModifiedDate(LocalDateTime.now().minusDays(10))
            .build();
        fileDtoList.add(dto1);
        fileDtoList.add(dto2);

        when(fileInfoService.getFiles()).thenReturn(fileDtoList);

        mockMvc.perform(
            MockMvcRequestBuilders.get("/files")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(model().attributeExists("files"))
            .andExpect(model().attribute("files", fileDtoList))
            .andDo(print());
    }

}