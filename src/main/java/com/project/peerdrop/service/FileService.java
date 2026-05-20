package com.project.peerdrop.service;

import com.project.peerdrop.config.SecurityConfig;
import com.project.peerdrop.dto.request.UploadRequestDTO;
import com.project.peerdrop.dto.response.FileResponseDTO;
import com.project.peerdrop.exceptions.DownloadLimitExceededException;
import com.project.peerdrop.exceptions.FileExpiredException;
import com.project.peerdrop.exceptions.InvalidPasswordException;
import com.project.peerdrop.exceptions.NoPasswordEnteredException;
import com.project.peerdrop.model.SharedFile;
import com.project.peerdrop.repository.SharedFileRepository;
import com.project.peerdrop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class FileService {

    @Autowired
    private SharedFileRepository sharedFileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public FileResponseDTO uploadFile(UploadRequestDTO requestDTO) throws IOException {

        SharedFile sharedFile=new SharedFile();

        // Taking file as input
        MultipartFile file= requestDTO.getFile();

        //Unique File Name
        String storedFileName=System.currentTimeMillis()+"_"+file.getOriginalFilename();
        sharedFile.setStoredFileName(storedFileName);

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
        Long fileSize= file.getSize();
        String originalFileName= file.getOriginalFilename();
        sharedFile.setOriginalFileName(originalFileName);
        sharedFile.setMimeType(file.getContentType());
        sharedFile.setFileSize(fileSize);
        sharedFile.setUploadedAt(LocalDateTime.now());


        //CORE SHARING LOGIC
        sharedFile.setMaxDownloadLimit(requestDTO.getNoOfDownloadsAllowed());
        // sharedFile.setFilePasswordHash(requestDTO.getPassword());
        String password=requestDTO.getPassword();
        if(password!=null
        && !password.isBlank()){
            sharedFile.setFilePasswordHash(passwordEncoder.encode(password));            
        }
        sharedFile.setDownloadCount(0);

        //Expiry Logic
        Integer expiryValue= requestDTO.getExpiryValue();
        String expiryUnit=requestDTO.getExpiryUnit();
        LocalDateTime expiryTime;
        if(expiryUnit.equalsIgnoreCase("Minutes")){
            expiryTime=LocalDateTime.now().plusMinutes(expiryValue);
        }
        else{
            expiryTime=LocalDateTime.now().plusHours(expiryValue);
        }
        sharedFile.setExpiryTime(expiryTime);

        //SAVE TO DB
        sharedFileRepository.save(sharedFile);

        //Response sent to client after File Upload
        FileResponseDTO responseDTO=new FileResponseDTO();
        responseDTO.setExpiryTime(sharedFile.getExpiryTime());
        responseDTO.setFileName(sharedFile.getOriginalFileName());
        responseDTO.setShareCode(shareCode);

        return responseDTO;

    }

    public File downloadFile(String shareCode,String enteredPassword) throws FileNotFoundException{

        SharedFile sharedFile=sharedFileRepository.findByShareCode(shareCode);

        //Checking if file exists for the given share code
        if(sharedFile==null){
            throw new FileNotFoundException("Invalid Share Code");
        }
        if (sharedFile.getFilePasswordHash()!=null){
            if( enteredPassword==null){
                throw new NoPasswordEnteredException("File is Password Protected");
            }
            if (!passwordEncoder.matches(enteredPassword, sharedFile.getFilePasswordHash())){
                throw new InvalidPasswordException("Password Mismatch");
            }
        }
        if (sharedFile.getExpiryTime().isBefore(LocalDateTime.now())){
            throw new FileExpiredException("File has Expired");
        }

        if(sharedFile.getDownloadCount()
                >= sharedFile.getMaxDownloadLimit()){

            throw new DownloadLimitExceededException(
                    "Download limit exceeded"
            );
        }

        String uploadPath = System.getProperty("user.dir") + "/uploads/";
        File file=new File(uploadPath,sharedFile.getStoredFileName());

        if(!file.exists()){
            throw new FileNotFoundException(
                    "File does not exist on server"
            );
        }

        sharedFile.setDownloadCount(sharedFile.getDownloadCount()+1);
        sharedFileRepository.save(sharedFile);
        return file;
    }
}
