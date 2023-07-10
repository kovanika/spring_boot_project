package com.example.spring_boot_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    AppProperties appProperties;

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        System.out.println("HELLO");
        System.out.println(appProperties.toString());
    }


    private final DocumentRepository documentRepository;

    public UploadController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    @RequestMapping(value = "add/file", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveFile(@RequestParam String name,
                                           @RequestParam MultipartFile document) throws IOException, OperationNotSupportedException, SQLException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFile(document.getBytes());
        fileEntity.setName(name);
        fileEntity.setOriginalName(document.getOriginalFilename());

        return documentRepository.add(fileEntity);
    }

    @PostMapping(value = "/find/file")
    public FileEntity GetOneFile(@RequestBody FileEntity fileEntity) throws IOException, SQLException {
        return documentRepository.query(fileEntity);
    }

    @PostMapping(value = "/add/json-file")
    public String AddOneFile(@RequestBody FileEntity fileEntity) throws IOException, SQLException, OperationNotSupportedException {
        return documentRepository.add(fileEntity);
    }

    @PostMapping(value = "update/file")
    public void UpdateOneFile(@RequestBody FileEntity fileEntity) throws SQLException, IOException {
        documentRepository.update(fileEntity);
    }

    @PostMapping(value = "/remove/file")
    public void DeleteOneFile(@RequestBody FileEntity fileEntity) throws IOException, SQLException, OperationNotSupportedException {
        documentRepository.remove(fileEntity);
    }

    @GetMapping(value = "/files", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<FileEntity> getFiles() throws IOException, SQLException {
        return documentRepository.queryAll();
    }
}
