package com.project.peerdrop.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileResponse {
    private String shareCode;
    private LocalDateTime expiryTime;
}
