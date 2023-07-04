package com.example.spring_boot_project;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @RequestMapping(path = "/files", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public File saveFile(@RequestParam String name, @RequestParam String email,
                                           @RequestPart MultipartFile document) {
        File file = new File();
        file.setName(name);
        file.setEmail(email);
        file.setFile(document);

        return file;
    }
}
