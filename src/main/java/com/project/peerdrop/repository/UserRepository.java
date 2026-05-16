package com.project.peerdrop.repository;

import com.project.peerdrop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{
    public User findByUserEmail(String userEmail) ;
}
