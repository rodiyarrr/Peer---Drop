package com.project.peerdrop.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class UploadRequestDTO {

    @NotNull
    private MultipartFile file;

    @Min(1)
    @Max(5)
    private Integer noOfDownloadsAllowed;

    private String password;

    @NotNull
    private Integer expiryValue; //ex- 30

    @NotBlank
    private String expiryUnit; // Minutes or Hours

}
