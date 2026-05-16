package com.project.peerdrop.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String  message) {
        super(message);
    }
}
