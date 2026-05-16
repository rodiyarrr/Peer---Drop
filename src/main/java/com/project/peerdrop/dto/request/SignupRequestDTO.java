package com.project.peerdrop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
    private String userName;
    private String email;
    private String password;
}
