package com.project.peerdrop.controller;

import com.project.peerdrop.dto.request.SignupRequestDTO;
import com.project.peerdrop.dto.response.AuthResponse;
import com.project.peerdrop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AuthResponse signupController(@RequestBody SignupRequestDTO signupRequestDTO){
        return authService.signup(signupRequestDTO);
    }
}
