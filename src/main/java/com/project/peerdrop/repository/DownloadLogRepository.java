package com.project.peerdrop.repository;

import com.project.peerdrop.model.DownloadLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DownloadLogRepository extends JpaRepository<DownloadLog, UUID> {
}
