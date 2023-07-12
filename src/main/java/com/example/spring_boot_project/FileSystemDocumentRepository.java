package com.example.spring_boot_project;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@ConditionalOnProperty(prefix = "repository", name = "type", havingValue = "file", matchIfMissing = false)
public class FileSystemDocumentRepository implements DocumentRepository{

    private final DirectoryProperties directoryProperties;

    public FileSystemDocumentRepository( DirectoryProperties directoryProperties) {
        this.directoryProperties = directoryProperties;
    }

    @Override
    public String add(FileEntity fileEntity) throws IOException {
        File file = new File(directoryProperties.path() + fileEntity.getOriginalName());
        return Files.write(Paths.get(file.getAbsolutePath()), fileEntity.getFile()).toString();
    }

    @Override
    public void update(FileEntity file) {

    }

    @Override
    public void remove(FileEntity file) {

    }

    @Override
    public List<FileEntity> queryAll() throws IOException {
        List<String> paths;
        List<byte[]> byteArrays = new ArrayList<byte[]>();
        try(Stream<Path> stream = Files.walk(Paths.get(directoryProperties.path()))){
            paths = stream.filter(Files::isRegularFile).map(Path::toString).collect(Collectors.toList());
        }
        for(int i = 0; i < paths.size(); i++){
            byteArrays.add(Files.readAllBytes(Paths.get(paths.get(i))));
        }
        List<FileEntity> fileEntityList = new ArrayList<FileEntity>();
        for(int i = 0; i < paths.size(); i++){
            FileEntity fileEntity = new FileEntity();
            fileEntity.setOriginalName(paths.get(i));
            fileEntity.setFile(byteArrays.get(i));
            fileEntityList.add(fileEntity);
        }
        return fileEntityList;
    }

    @Override
    public FileEntity query(String path) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setOriginalName(path);
        fileEntity.setFile(Files.readAllBytes(Paths.get(path)));

        return fileEntity;
    }
    @Override
    public  FileEntity get(String url) throws IOException{
        return null;
    }
}