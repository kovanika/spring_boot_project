package com.example.spring_boot_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @ExceptionHandler(value = {Exception.class})
    protected String defaultHandleConflict(Exception e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected String defaultHandleConflict(RuntimeException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }
}
