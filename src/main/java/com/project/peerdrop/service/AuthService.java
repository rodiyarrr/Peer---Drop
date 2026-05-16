package com.project.peerdrop.service;

import com.project.peerdrop.dto.request.LoginRequestDTO;
import com.project.peerdrop.dto.request.SignupRequestDTO;
import com.project.peerdrop.dto.response.AuthResponse;
import com.project.peerdrop.model.User;
import com.project.peerdrop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    public AuthResponse login(LoginRequestDTO loginRequestDTO){
        return null;
    }

    public AuthResponse signup(SignupRequestDTO signupRequestDTO){
        User user=new User();

        user.setUserName(signupRequestDTO.getUserName());
        user.setUserEmail(signupRequestDTO.getEmail());
        user.setPasswordHash(signupRequestDTO.getPassword());

        userRepository.save(user);

        AuthResponse authResponseDTO=new AuthResponse();
        authResponseDTO.setMessage("Signup Successful!");
        authResponseDTO.setUserName(user.getUserName());
        authResponseDTO.setEmail(user.getUserEmail());

        return authResponseDTO;
    }
}
