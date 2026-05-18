package com.project.peerdrop.exceptions;

public class NoPasswordEnteredException extends RuntimeException{
    public NoPasswordEnteredException(String message) {
        super(message);
    }
}
