package com.example.spring_boot_project;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.List;

public class DBDocumentRepository implements DocumentRepository{
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
    public List<FileEntity> query() throws IOException {
        return null;
    }
}
