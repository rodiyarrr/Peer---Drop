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

    public AuthResponse login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUserEmail(loginRequestDTO.getEmail());
        if (user == null) {
            AuthResponse userInvalid = new AuthResponse();
            userInvalid.setMessage("Login Failed \nUser does not exist.");
            return userInvalid;
        }
        String requestPassword = loginRequestDTO.getPassword();

        if (requestPassword.equals(user.getPasswordHash())) {
            AuthResponse loginSuccessful = new AuthResponse();

            loginSuccessful.setMessage("Login Succesful");
            loginSuccessful.setUserName(user.getUserName());
            loginSuccessful.setEmail(user.getUserEmail());

            return loginSuccessful;
        }

        else {
            AuthResponse invalidPassword = new AuthResponse();

            invalidPassword.setMessage("Login Failed \nPassword Mismatch");

            return invalidPassword;
        }
    }

    public AuthResponse signup(SignupRequestDTO signupRequestDTO) {
        User userCheck = userRepository.findByUserEmail(signupRequestDTO.getEmail());
        if (userCheck != null) {

            AuthResponse emailAlreadyExists = new AuthResponse();

            emailAlreadyExists.setMessage("Signup Failed \nEmail already exists");
            emailAlreadyExists.setEmail(signupRequestDTO.getEmail());

            return emailAlreadyExists;

        }
        User user = new User();

        user.setUserName(signupRequestDTO.getUserName());
        user.setUserEmail(signupRequestDTO.getEmail());
        user.setPasswordHash(signupRequestDTO.getPassword());

        userRepository.save(user);

        AuthResponse signupSuccessful = new AuthResponse();
        signupSuccessful.setMessage("Signup Successful!");
        signupSuccessful.setUserName(user.getUserName());
        signupSuccessful.setEmail(user.getUserEmail());

        return signupSuccessful;
    }
}


