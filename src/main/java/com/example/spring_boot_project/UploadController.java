package com.example.spring_boot_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    private DocumentRepository documentRepository = new FileSystemDocumentRepository();
    @RequestMapping(value = "/files", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveFile(@RequestParam String name, @RequestParam String email,
                                           @RequestParam MultipartFile document) throws IOException, OperationNotSupportedException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFile(document.getBytes());
        fileEntity.setName(name);
        fileEntity.setOriginalName(document.getOriginalFilename());
        fileEntity.setEmail(email);

        return documentRepository.add(fileEntity);
    }

    @GetMapping(value = "/files", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<FileEntity> getFiles() throws IOException {
        return documentRepository.query();
    }
}
