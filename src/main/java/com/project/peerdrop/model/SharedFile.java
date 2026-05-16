package com.project.peerdrop.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shared_files")
public class SharedFile {

    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID fileId;

    private String originalFileName;
    private String storedFileName;

    // FILE META DATA
    private Long fileSize;
    private LocalDateTime uploadedAt;
    private String mimeType;

    // DOWNLOAD LOGIC
    private String shareCode;
    private String filePasswordHash;
    private LocalDateTime expiryTime;
    private Integer maxDownloadLimit;
    private Integer downloadCount;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;

}
