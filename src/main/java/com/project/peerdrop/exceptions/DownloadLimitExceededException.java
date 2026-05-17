package com.project.peerdrop.exceptions;

public class DownloadLimitExceededException extends RuntimeException{

    public DownloadLimitExceededException(String message){
        super(message);
    }
}
