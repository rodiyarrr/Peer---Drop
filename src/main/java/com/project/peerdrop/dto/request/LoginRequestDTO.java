package com.project.peerdrop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank(message = "Email Required")
    private String email;

    @NotBlank(message = "Password Required")
    private String password;
}
