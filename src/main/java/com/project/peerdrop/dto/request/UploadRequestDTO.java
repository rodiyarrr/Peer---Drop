package com.project.peerdrop.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UploadRequestDTO {
    private String originalFileName;
    private Integer noOfDownloadsAllowed;
    private String password;
    private LocalDateTime expiryTime;
}
