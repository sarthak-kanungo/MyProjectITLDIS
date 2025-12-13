package com.i4o.dms.itldis.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.common.service.ImageResizer;
import com.i4o.dms.itldis.storage.exception.FileStorageException;
import com.i4o.dms.itldis.storage.exception.MyFileNotFoundException;

@Service
class FileStorageService implements StorageService {

    private final Path rootLocation;
    private final String rootLocationStr;


    @Autowired
    public FileStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.rootLocationStr = properties.getLocation();
    }

 /*   @Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new FileStorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new FileStorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new FileStorageException("Failed to store file " + filename, e);
        }
    }*/

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new FileStorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new MyFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new MyFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void deleteExistingFile(String filePath) {

        if (load(filePath).toString().contains(filePath)) {
            try {
                Files.deleteIfExists(load(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new FileStorageException("Could not initialize storage", e);
        }
    }

    @Override
    public void store(MultipartFile file, String filePath) {
        try {
            if (file.isEmpty()) {
                throw new FileStorageException("Failed to store empty file " + filePath);
            }
//            if (filePath.contains("..")) {
//// This is a security check
//                throw new FileStorageException(
//                        "Cannot store file with relative path outside current directory "
//                                + filePath);
//            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filePath),
                        StandardCopyOption.REPLACE_EXISTING);
                
//	            File f = this.rootLocation.resolve(filePath).toFile();
//	            
//	            String regex = "[^\\s]+(.*?)\\.(jpg|jpeg|png|JPG|JPEG|PNG)$";
//	            
//	            String filename = f.getName();
//	            if(filename.matches(regex)){	
//	                byte[] bytes = ImageResizer.resize(f);
//	            	if(bytes!=null){
//		            	FileOutputStream fos = new FileOutputStream(f);
//		            	fos.write(bytes);
//		            	fos.flush();
//		            	fos.close();
//	            	}
//	            }
            }
        } catch (IOException e) {
            throw new FileStorageException("Failed to store file " + filePath, e);
        }
    }
    
    public void store(String base64Image, String filePath, String fileName){
    	this.rootLocation.resolve(filePath);
    	
    	try (FileOutputStream imageOutFile = new FileOutputStream(rootLocationStr+"/"+filePath)) {
	        byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
	        imageOutFile.write(imageByteArray);
	    } catch (FileNotFoundException e) {
	        System.out.println("Image not found" + e);
	    } catch (IOException ioe) {
	        System.out.println("Exception while reading the Image " + ioe);
	    }
    }
}
