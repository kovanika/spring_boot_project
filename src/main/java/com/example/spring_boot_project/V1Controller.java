package com.example.spring_boot_project;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class V1Controller {


    /**
     * http://localhost:9002/v1/tmp?fileName=File1&fileExtension=txt
     * @param fileName
     * @param fileExtension
     * @param dir
     * @return
     */
    @PostMapping(value = "{dir}")
    public String getFile(@RequestParam String fileName, @RequestParam String fileExtension, @PathVariable String dir) {
        return null;
    }

}

