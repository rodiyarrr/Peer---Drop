package com.project.peerdrop.service;

import com.project.peerdrop.dto.request.LoginRequestDTO;
import com.project.peerdrop.dto.request.SignupRequestDTO;
import com.project.peerdrop.dto.response.AuthResponseDTO;
import com.project.peerdrop.exceptions.InvalidCredentialsException;
import com.project.peerdrop.exceptions.UserAlreadyExistsException;
import com.project.peerdrop.exceptions.UserNotFoundException;
import com.project.peerdrop.model.User;
import com.project.peerdrop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUserEmail(loginRequestDTO.getEmail());
        if (user == null) {
            throw new UserNotFoundException(
                    "No such email found in directory");
        }
        String requestPassword = loginRequestDTO.getPassword();

        if (requestPassword.equals(user.getPasswordHash())) {
            AuthResponseDTO loginSuccessful = new AuthResponseDTO();

            loginSuccessful.setMessage("Login Succesful");
            loginSuccessful.setUserName(user.getUserName());
            loginSuccessful.setEmail(user.getUserEmail());

            return loginSuccessful;
        }

        else {
            throw new InvalidCredentialsException("Password incorrect");
        }
    }

    public AuthResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        User userCheck = userRepository.findByUserEmail(signupRequestDTO.getEmail());
        if (userCheck != null) {

            throw new UserAlreadyExistsException("Email already exists");

        }
        User user = new User();
        user.setUserName(signupRequestDTO.getUserName());
        user.setUserEmail(signupRequestDTO.getEmail());
        user.setPasswordHash(signupRequestDTO.getPassword());

        userRepository.save(user);

        AuthResponseDTO signupSuccessful = new AuthResponseDTO();
        signupSuccessful.setMessage("Signup Successful!");
        signupSuccessful.setUserName(user.getUserName());
        signupSuccessful.setEmail(user.getUserEmail());

        return signupSuccessful;
    }
}


