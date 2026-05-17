package com.project.peerdrop.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class UploadRequestDTO {

    private MultipartFile file;
    private Integer noOfDownloadsAllowed;
    private String password;
    private Integer expiryValue; //ex- 30
    private String expiryUnit; // Minutes or Hours

}
