package com.project.peerdrop.controller;

import com.project.peerdrop.dto.request.UploadRequestDTO;
import com.project.peerdrop.dto.response.FileResponseDTO;
import com.project.peerdrop.service.FileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public FileResponseDTO uploadFile(@Valid @ModelAttribute UploadRequestDTO requestDTO) throws IOException {

        return fileService.uploadFile(requestDTO);
    }

}
