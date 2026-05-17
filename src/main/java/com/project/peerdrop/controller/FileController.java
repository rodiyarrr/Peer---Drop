package com.project.peerdrop.controller;

import com.project.peerdrop.dto.request.UploadRequestDTO;
import com.project.peerdrop.dto.response.AuthResponseDTO;
import com.project.peerdrop.dto.response.FileResponseDTO;
import com.project.peerdrop.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public FileResponseDTO uploadFile(@ModelAttribute UploadRequestDTO requestDTO) throws IOException {

        return fileService.uploadFile(requestDTO);
    }
}
