package com.project.peerdrop.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {

    @NotBlank(message="Username Required")
    private String userName;

    @Email
    @NotBlank(message="Email Required")
    private String email;

    @NotBlank(message = "Password Required")
    private String password;
}
