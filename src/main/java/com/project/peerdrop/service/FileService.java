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

        // Taking file as input
        MultipartFile file= requestDTO.getFile();
        Long fileSize= file.getSize();

        //File transfer logic - /uploads
        String uploadPath = System.getProperty("user.dir") + "/uploads/";
        File uploadDirectory=new File(uploadPath);

        if (!uploadDirectory.exists()){
            uploadDirectory.mkdirs();
        }
        File destinationFile=new File(uploadDirectory,
                sharedFile.getStoredFileName());

        file.transferTo(destinationFile);


        //Share Code generation
        String shareCode =String.valueOf((int) (Math.random()*900000)+100000);
        sharedFile.setShareCode(shareCode);

        //File-Metadata
        String originalFileName= file.getOriginalFilename();
        sharedFile.setOriginalFileName(originalFileName);
        sharedFile.setMimeType(file.getContentType());
        sharedFile.setFileSize(fileSize);
        sharedFile.setUploadedAt(LocalDateTime.now());

        //Unique File Name
        String storedFileName=System.currentTimeMillis()+"_"+originalFileName;
        sharedFile.setStoredFileName(storedFileName);

        //CORE SHARING LOGIC
        sharedFile.setMaxDownloadLimit(requestDTO.getNoOfDownloadsAllowed());
        sharedFile.setFilePasswordHash(requestDTO.getPassword());
        sharedFile.setExpiryTime(requestDTO.getExpiryTime());
        sharedFile.setDownloadCount(0);


        //SAVE TO DB
        sharedFileRepository.save(sharedFile);

        //Response sent to client after File Upload
        FileResponseDTO responseDTO=new FileResponseDTO();
        responseDTO.setExpiryTime(sharedFile.getExpiryTime());
        responseDTO.setFileName(sharedFile.getOriginalFileName());
        responseDTO.setShareCode(shareCode);

        return responseDTO;

    }
}
