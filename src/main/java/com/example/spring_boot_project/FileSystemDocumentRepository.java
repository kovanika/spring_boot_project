package com.example.spring_boot_project;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileSystemDocumentRepository implements DocumentRepository{
    @Override
    public void add(FileEntity fileEntity) throws IOException {
        File file = new File("C:\\Users\\user\\Desktop\\files\\" + fileEntity.getOriginalName());
        Files.write(Paths.get(file.getAbsolutePath()), fileEntity.getFile());
    }

    @Override
    public void update(FileEntity file) {

    }

    @Override
    public void remove(FileEntity file) {

    }

    @Override
    public List<FileEntity> query(FileSpecification fileSpecification) {
        return null;
    }
}
