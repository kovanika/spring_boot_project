package com.example.spring_boot_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    @RequestMapping(value = "/files", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveFile(@RequestParam String name, @RequestParam String email,
                                           @RequestParam MultipartFile document) {

        File file = new File("C:\\Users\\user\\Desktop\\" + document.getOriginalFilename());
        try(OutputStream os = new FileOutputStream(file)){
            os.write(document.getBytes());
        }
        catch (IOException e){
            logger.error(e.getMessage(), e);
        }
        return "file.getFile()";
    }

    @GetMapping
    public FileEntity hello(){
        FileEntity file = new FileEntity();
        file.setName("name");
        file.setEmail("email");
        return file;
    }
}
