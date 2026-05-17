package com.project.peerdrop.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException exception){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException exception){

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(FileExpiredException.class)
    public ResponseEntity<String> handleFileExpiredException(FileExpiredException exception){

        return ResponseEntity.status(HttpStatus.GONE)
                .body(exception.getMessage());
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Invalid Share Code");
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException exception){

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(exception.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPtrException(NullPointerException exception){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Null Pointer Exception");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleFileUploadException(IOException fileUploadException){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(fileUploadException.getMessage());
    }
}
