package com.project.peerdrop.controller;

import com.project.peerdrop.dto.request.LoginRequestDTO;
import com.project.peerdrop.dto.request.SignupRequestDTO;
import com.project.peerdrop.dto.response.AuthResponseDTO;
import com.project.peerdrop.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signupController(@Valid @RequestBody SignupRequestDTO signupRequestDTO){

        return ResponseEntity.ok(authService.signup(signupRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginController(@Valid @RequestBody LoginRequestDTO loginRequestDTO){

        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }
}
