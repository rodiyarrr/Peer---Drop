package com.project.peerdrop.exceptions;

public class FileExpiredException extends RuntimeException{
    public FileExpiredException(String message) {
        super(message);
    }
}
