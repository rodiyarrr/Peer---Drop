    package com.project.peerdrop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="DownloadLogs")
@Getter
@Setter
@NoArgsConstructor
public class DownloadLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID downloadLogId;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private SharedFile file;

    private LocalDateTime downloadedAt;

    @ManyToOne
    @JoinColumn(name="downloaded_by")
    private User downloadedBy;

}
