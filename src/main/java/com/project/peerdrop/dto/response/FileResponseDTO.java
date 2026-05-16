package com.project.peerdrop.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileResponseDTO {
    private String fileName;
    private String shareCode;
    private LocalDateTime expiryTime;
}
