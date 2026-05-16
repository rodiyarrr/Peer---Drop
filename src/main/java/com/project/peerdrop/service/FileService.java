package com.project.peerdrop.service;

import com.project.peerdrop.dto.request.UploadRequestDTO;
import com.project.peerdrop.dto.response.FileResponseDTO;
import com.project.peerdrop.model.SharedFile;
import com.project.peerdrop.repository.SharedFileRepository;
import com.project.peerdrop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class FileService {
    @Autowired
    private SharedFileRepository sharedFileRepository;

    @Autowired
    private UserRepository userRepository;

    public FileResponseDTO uploadFile(UploadRequestDTO requestDTO) throws IOException {

        SharedFile sharedFile=new SharedFile();

        MultipartFile file= requestDTO.getFile();
        Long fileSize= file.getSize();

        String originalFileName= file.getOriginalFilename();
        sharedFile.setOriginalFileName(originalFileName);

        String storedFileName=System.currentTimeMillis()+"_"+originalFileName;
        sharedFile.setStoredFileName(storedFileName);

        sharedFile.setFileSize(fileSize);
        sharedFile.setMaxDownloadLimit(requestDTO.getNoOfDownloadsAllowed());
        //ye aage jake BCrypt use krke hash krna hai pw before storing
        sharedFile.setFilePasswordHash(requestDTO.getPassword());
        sharedFile.setExpiryTime(requestDTO.getExpiryTime());
        sharedFile.setUploadedAt(LocalDateTime.now());
        sharedFile.setDownloadCount(0);

        String uploadPath="uploads/";
        File destinationFile=new File(uploadPath,sharedFile.getStoredFileName());
        file.transferTo(destinationFile);

        sharedFileRepository.save(sharedFile);

        FileResponseDTO responseDTO=new FileResponseDTO();
        responseDTO.setExpiryTime(sharedFile.getExpiryTime());
        responseDTO.setFileName(sharedFile.getOriginalFileName());
        return responseDTO;

    }
}
