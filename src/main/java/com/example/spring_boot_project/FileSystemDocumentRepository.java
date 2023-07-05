package com.example.spring_boot_project;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public List<FileEntity> query() throws IOException {
        List<String> paths;
        List<byte[]> byteArrays = new ArrayList<byte[]>();
        try(Stream<Path> stream = Files.walk(Paths.get("C:\\Users\\user\\Desktop\\files\\"))){
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
}
