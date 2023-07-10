package com.example.spring_boot_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.util.List;

@RestController
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    private final DBDocumentRepository dbDocumentRepository;

    public UploadController(DBDocumentRepository dbDocumentRepository) {
        this.dbDocumentRepository = dbDocumentRepository;
    }
    @RequestMapping(value = "/file", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveFile(@RequestParam String name, @RequestParam String email,
                                           @RequestParam MultipartFile document) throws IOException, OperationNotSupportedException {
        if(document.isEmpty() || name.isEmpty() || email.isEmpty()){
            throw new RuntimeException( "Empty fields or file");
        }

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFile(document.getBytes());
        fileEntity.setName(name);
        fileEntity.setOriginalName(document.getOriginalFilename());
        fileEntity.setEmail(email);

        return dbDocumentRepository.add(fileEntity);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public FileEntity GetOneFile(@RequestParam String path) throws IOException {
        return dbDocumentRepository.query(path);
    }

    @GetMapping(value = "/files", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<FileEntity> getFiles() throws IOException {
        return dbDocumentRepository.queryAll();
    }
}
