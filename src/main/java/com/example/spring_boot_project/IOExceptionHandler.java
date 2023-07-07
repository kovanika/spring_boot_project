package com.example.spring_boot_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class IOExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @ExceptionHandler(value = {IOException.class})
    protected String handleConflict(IOException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    protected ResponseEntity<String> fileExceptionHandler(FileNotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = FileIsAlreadyExistException.class)
    protected ResponseEntity<String> fileIsAlreadyExistExceptionHandler(FileIsAlreadyExistException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    protected String defaultHandleConflict(Exception e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }
}
