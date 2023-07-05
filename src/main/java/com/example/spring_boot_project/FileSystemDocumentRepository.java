package com.example.spring_boot_project;

import java.io.File;
import java.util.List;

public class FileSystemDocumentRepository implements DocumentRepository{
    @Override
    public void add(FileEntity fileEntity) {
        File file = new File("C:\\Users\\user\\Desktop\\files\\" + fileEntity.getOriginalName());
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
