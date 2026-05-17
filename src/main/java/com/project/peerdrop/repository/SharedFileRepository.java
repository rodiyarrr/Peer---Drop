package com.project.peerdrop.repository;

import com.project.peerdrop.model.SharedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SharedFileRepository extends JpaRepository<SharedFile, UUID> {

    SharedFile findByShareCode(String shareCode);
}
