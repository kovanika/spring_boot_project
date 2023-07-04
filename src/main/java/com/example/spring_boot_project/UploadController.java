package com.example.spring_boot_project;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class UploadController {
    @RequestMapping(value = "/files", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveFile(@RequestParam String name, @RequestParam String email,
                                           @RequestParam MultipartFile document) throws IOException {

        File file = new File("C:\\Users\\user\\Desktop\\" + document.getOriginalFilename());
        try(OutputStream os = new FileOutputStream(file)){
            os.write(document.getBytes());
        }
        catch (Exception e){
            return "Exception!";
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
