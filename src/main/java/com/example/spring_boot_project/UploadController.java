package com.example.spring_boot_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private DocumentRepository documentRepository = new DBDocumentRepository();
    @RequestMapping(value = "/files", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveFile(@RequestParam String name, @RequestParam String email,
                                           @RequestParam MultipartFile document) throws IOException, OperationNotSupportedException, SQLException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFile(document.getBytes());
        fileEntity.setName(name);
        fileEntity.setOriginalName(document.getOriginalFilename());
        fileEntity.setEmail(email);

        return documentRepository.add(fileEntity);
    }

    @PostMapping(value = "/find/file")
    public FileEntity GetOneFile(@RequestBody FileEntity fileEntity) throws IOException, SQLException {
        return documentRepository.query(fileEntity);
    }

    @PostMapping(value = "/add/file")
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
