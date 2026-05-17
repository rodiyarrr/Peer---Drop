package com.project.peerdrop.controller;


import com.project.peerdrop.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    public FileService fileService;

    @GetMapping("/{shareCode}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String shareCode) throws FileNotFoundException {

        File file=fileService.downloadFile(shareCode);

        Resource resource= new FileSystemResource(file);

        return ResponseEntity.ok().body(resource);
    }
}
