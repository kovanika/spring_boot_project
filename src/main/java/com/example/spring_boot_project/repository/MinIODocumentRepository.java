package com.example.spring_boot_project.repository;

import com.example.spring_boot_project.FileEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.List;

@Repository
@ConditionalOnProperty(
        value="repository.type",
        havingValue = "minio",
        matchIfMissing = false)
public class MinIODocumentRepository implements  DocumentRepository{
    @Override
    public String add(FileEntity file) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    public void update(FileEntity file) {

    }

    @Override
    public void remove(FileEntity file) {

    }

    @Override
    public List<FileEntity> queryAll() throws IOException {
        return null;
    }

    @Override
    public FileEntity query(FileEntity fileEntity) throws IOException {
        return null;
    }

    @Override
    public FileEntity get(String url) {
        return null;
    }
}
