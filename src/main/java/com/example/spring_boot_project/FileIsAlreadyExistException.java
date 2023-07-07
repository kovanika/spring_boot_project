package com.example.spring_boot_project;

public class FileIsAlreadyExistException extends RuntimeException{
    public FileIsAlreadyExistException(String message){
        super(message);
    }
}
