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

    private DocumentRepository documentRepository = new FileSystemDocumentRepository();
    @RequestMapping(value = "/files", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveFile(@RequestParam String name, @RequestParam String email,
                                           @RequestParam MultipartFile document) {



        try{
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFile(document.getBytes());
            fileEntity.setName(name);
            fileEntity.setOriginalName(document.getOriginalFilename());
            fileEntity.setEmail(email);

            documentRepository.add(fileEntity);
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
