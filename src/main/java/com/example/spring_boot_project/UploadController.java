package com.example.spring_boot_project;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@RestController
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean emailValidator(String email)
    {
        if (email == null) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    private final DocumentRepository dbDocumentRepository;

    public UploadController(DocumentRepository dbDocumentRepository) {
        this.dbDocumentRepository = dbDocumentRepository;
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveFile(@RequestParam String name, @NotNull @Email @RequestParam String email,
                                           @RequestParam MultipartFile document) throws IOException, OperationNotSupportedException {

        if (!emailValidator(email)) {

            throw new RuntimeException("The email address in invalid");

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