package com.i4o.dms.itldis.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface  StorageService {

    void init();

    void store(MultipartFile file, String fileName);
    
    void store(String base64Image, String filePath, String fileName);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    void deleteExistingFile(String filePath);

}