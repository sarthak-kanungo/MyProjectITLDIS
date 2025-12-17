# Storage Module Sequence Diagrams

This document contains detailed sequence diagrams for the Storage Module of the KUBOTA Backend application.

## Table of Contents
1. [Module Initialization Flow](#1-module-initialization-flow)
2. [File Upload Flow (MultipartFile)](#2-file-upload-flow-multipartfile)
3. [Base64 Image Upload Flow](#3-base64-image-upload-flow)
4. [File Retrieval Flow](#4-file-retrieval-flow)
5. [File Listing Flow](#5-file-listing-flow)
6. [File Deletion Flow](#6-file-deletion-flow)
7. [Error Handling Flow](#7-error-handling-flow)

---

## 1. Module Initialization Flow

This diagram shows how the Storage Module is initialized when the application starts.

```mermaid
sequenceDiagram
    participant SpringBoot as Spring Boot Application
    participant KubotaApp as KubotaApplication
    participant CommandRunner as CommandLineRunner
    participant StorageService as StorageService Interface
    participant FileStorageService as FileStorageService
    participant StorageProps as StorageProperties
    participant FileSystem as File System

    SpringBoot->>KubotaApp: Application Startup
    KubotaApp->>StorageProps: Load @ConfigurationProperties("storage")
    StorageProps-->>KubotaApp: StorageProperties instance<br/>(location = "upload-dir")
    
    SpringBoot->>FileStorageService: @Autowired Constructor
    FileStorageService->>StorageProps: getLocation()
    StorageProps-->>FileStorageService: "upload-dir"
    FileStorageService->>FileStorageService: Paths.get("upload-dir")
    FileStorageService-->>SpringBoot: FileStorageService instance created
    
    SpringBoot->>KubotaApp: init(StorageService) CommandLineRunner
    KubotaApp->>CommandRunner: Create CommandLineRunner
    CommandRunner->>StorageService: init()
    StorageService->>FileStorageService: init()
    FileStorageService->>FileSystem: Files.createDirectories(rootLocation)
    alt Directory Creation Success
        FileSystem-->>FileStorageService: Directory created
        FileStorageService-->>StorageService: Success
        StorageService-->>CommandRunner: Initialization complete
    else Directory Creation Failure
        FileSystem-->>FileStorageService: IOException
        FileStorageService->>FileStorageService: throw FileStorageException<br/>("Could not initialize storage")
        FileStorageService-->>StorageService: Exception thrown
        StorageService-->>CommandRunner: Initialization failed
    end
```

---

## 2. File Upload Flow (MultipartFile)

This diagram shows the complete flow when uploading a file via MultipartFile.

```mermaid
sequenceDiagram
    participant Client as Client/API Consumer
    participant FileUploadController as FileUploadController
    participant StorageService as StorageService Interface
    participant FileStorageService as FileStorageService
    participant FileSystem as File System
    participant MultipartFile as MultipartFile

    Client->>FileUploadController: POST /api/saveFile<br/>(MultipartFile file)
    FileUploadController->>FileUploadController: Extract file.getOriginalFilename()
    FileUploadController->>StorageService: store(file, fileName)
    StorageService->>FileStorageService: store(MultipartFile, String)
    
    FileStorageService->>MultipartFile: isEmpty()
    MultipartFile-->>FileStorageService: false (file has content)
    
    FileStorageService->>MultipartFile: getInputStream()
    MultipartFile-->>FileStorageService: InputStream
    
    FileStorageService->>FileStorageService: rootLocation.resolve(filePath)
    FileStorageService->>FileSystem: Files.copy(inputStream,<br/>rootLocation.resolve(filePath),<br/>REPLACE_EXISTING)
    
    alt File Copy Success
        FileSystem-->>FileStorageService: File copied successfully
        FileStorageService-->>StorageService: Success
        StorageService-->>FileUploadController: File stored
        FileUploadController->>FileUploadController: Add success message to<br/>redirectAttributes
        FileUploadController-->>Client: redirect:/ (with success message)
    else File Copy Failure
        FileSystem-->>FileStorageService: IOException
        FileStorageService->>FileStorageService: throw FileStorageException<br/>("Failed to store file " + filePath)
        FileStorageService-->>StorageService: Exception thrown
        StorageService-->>FileUploadController: FileStorageException
        FileUploadController-->>Client: Error response
    else Empty File
        MultipartFile-->>FileStorageService: isEmpty() = true
        FileStorageService->>FileStorageService: throw FileStorageException<br/>("Failed to store empty file")
        FileStorageService-->>StorageService: Exception thrown
        StorageService-->>FileUploadController: FileStorageException
        FileUploadController-->>Client: Error response
    end
```

---

## 3. Base64 Image Upload Flow

This diagram shows the flow when uploading a Base64 encoded image.

```mermaid
sequenceDiagram
    participant Client as Client/API Consumer
    participant Service as Service Layer<br/>(Various Controllers)
    participant StorageService as StorageService Interface
    participant FileStorageService as FileStorageService
    participant Base64Decoder as Base64 Decoder
    participant FileSystem as File System

    Client->>Service: Request with Base64 image<br/>(String base64Image, String filePath, String fileName)
    Service->>StorageService: store(base64Image, filePath, fileName)
    StorageService->>FileStorageService: store(String, String, String)
    
    FileStorageService->>FileStorageService: rootLocation.resolve(filePath)
    FileStorageService->>FileStorageService: rootLocationStr + "/" + filePath<br/>(construct full path)
    
    FileStorageService->>Base64Decoder: Base64.getDecoder().decode(base64Image)
    Base64Decoder-->>FileStorageService: byte[] imageByteArray
    
    FileStorageService->>FileSystem: new FileOutputStream(rootLocationStr + "/" + filePath)
    
    alt File Output Stream Creation Success
        FileSystem-->>FileStorageService: FileOutputStream instance
        FileStorageService->>FileSystem: imageOutFile.write(imageByteArray)
        FileSystem-->>FileStorageService: Bytes written successfully
        FileStorageService-->>StorageService: Success
        StorageService-->>Service: File stored
        Service-->>Client: Success response
    else File Not Found
        FileSystem-->>FileStorageService: FileNotFoundException
        FileStorageService->>FileStorageService: System.out.println<br/>("Image not found" + e)
        FileStorageService-->>StorageService: Error (logged)
        StorageService-->>Service: Error
        Service-->>Client: Error response
    else IO Exception
        FileSystem-->>FileStorageService: IOException
        FileStorageService->>FileStorageService: System.out.println<br/>("Exception while reading the Image" + ioe)
        FileStorageService-->>StorageService: Error (logged)
        StorageService-->>Service: Error
        Service-->>Client: Error response
    end
```

---

## 4. File Retrieval Flow

This diagram shows how files are retrieved and served to clients.

```mermaid
sequenceDiagram
    participant Client as Client/API Consumer
    participant FileUploadController as FileUploadController
    participant StorageService as StorageService Interface
    participant FileStorageService as FileStorageService
    participant FileSystem as File System
    participant UrlResource as UrlResource

    Client->>FileUploadController: GET /api/files/{filename}
    FileUploadController->>FileUploadController: Extract filename from path
    FileUploadController->>StorageService: loadAsResource(filename)
    StorageService->>FileStorageService: loadAsResource(String)
    
    FileStorageService->>FileStorageService: load(filename)
    FileStorageService->>FileStorageService: rootLocation.resolve(filename)
    FileStorageService-->>FileStorageService: Path file
    
    FileStorageService->>FileSystem: file.toUri()
    FileSystem-->>FileStorageService: URI
    FileStorageService->>UrlResource: new UrlResource(file.toUri())
    UrlResource-->>FileStorageService: Resource instance
    
    FileStorageService->>UrlResource: resource.exists()
    UrlResource->>FileSystem: Check if file exists
    FileSystem-->>UrlResource: true/false
    
    alt File Exists and Readable
        UrlResource-->>FileStorageService: exists() = true<br/>isReadable() = true
        FileStorageService-->>StorageService: Resource
        StorageService-->>FileUploadController: Resource
        FileUploadController->>FileUploadController: Set HttpHeaders.CONTENT_DISPOSITION<br/>("attachment; filename=\"" + file.getFilename() + "\"")
        FileUploadController-->>Client: ResponseEntity.ok()<br/>with Resource body
    else File Not Found
        UrlResource-->>FileStorageService: exists() = false<br/>OR isReadable() = false
        FileStorageService->>FileStorageService: throw MyFileNotFoundException<br/>("Could not read file: " + filename)
        FileStorageService-->>StorageService: Exception thrown
        StorageService-->>FileUploadController: MyFileNotFoundException
        FileUploadController->>FileUploadController: @ExceptionHandler<br/>handleStorageFileNotFound()
        FileUploadController-->>Client: ResponseEntity.notFound()
    else Malformed URL
        FileSystem-->>FileStorageService: MalformedURLException
        FileStorageService->>FileStorageService: throw MyFileNotFoundException<br/>("Could not read file: " + filename, e)
        FileStorageService-->>StorageService: Exception thrown
        StorageService-->>FileUploadController: MyFileNotFoundException
        FileUploadController->>FileUploadController: @ExceptionHandler<br/>handleStorageFileNotFound()
        FileUploadController-->>Client: ResponseEntity.notFound()
    end
```

---

## 5. File Listing Flow

This diagram shows how all stored files are listed.

```mermaid
sequenceDiagram
    participant Client as Client/API Consumer
    participant FileUploadController as FileUploadController
    participant StorageService as StorageService Interface
    participant FileStorageService as FileStorageService
    participant FileSystem as File System
    participant Stream as Stream<Path>

    Client->>FileUploadController: GET /api/newUrl
    FileUploadController->>StorageService: loadAll()
    StorageService->>FileStorageService: loadAll()
    
    FileStorageService->>FileSystem: Files.walk(rootLocation, 1)
    FileSystem-->>FileStorageService: Stream<Path>
    
    FileStorageService->>Stream: filter(path -> !path.equals(rootLocation))
    Stream-->>FileStorageService: Filtered Stream<Path>
    
    FileStorageService->>Stream: map(rootLocation::relativize)
    Stream-->>FileStorageService: Stream<Path> (relative paths)
    
    alt Success
        FileStorageService-->>StorageService: Stream<Path>
        StorageService-->>FileUploadController: Stream<Path>
        FileUploadController->>FileUploadController: map(path -><br/>MvcUriComponentsBuilder<br/>.fromMethodName(...)<br/>.build().toString())
        FileUploadController->>FileUploadController: collect(Collectors.toList())
        FileUploadController->>FileUploadController: model.addAttribute("files", fileList)
        FileUploadController-->>Client: "uploadForm" view<br/>with file list
    else IO Exception
        FileSystem-->>FileStorageService: IOException
        FileStorageService->>FileStorageService: throw FileStorageException<br/>("Failed to read stored files")
        FileStorageService-->>StorageService: Exception thrown
        StorageService-->>FileUploadController: FileStorageException
        FileUploadController-->>Client: Error response
    end
```

---

## 6. File Deletion Flow

This diagram shows how files are deleted from storage.

```mermaid
sequenceDiagram
    participant Client as Client/API Consumer
    participant Service as Service Layer<br/>(Various Controllers)
    participant StorageService as StorageService Interface
    participant FileStorageService as FileStorageService
    participant FileSystem as File System

    Note over Client,FileSystem: Single File Deletion
    Client->>Service: Request to delete file<br/>(String filePath)
    Service->>StorageService: deleteExistingFile(filePath)
    StorageService->>FileStorageService: deleteExistingFile(String)
    
    FileStorageService->>FileStorageService: load(filePath)
    FileStorageService->>FileStorageService: rootLocation.resolve(filePath)
    FileStorageService-->>FileStorageService: Path
    
    FileStorageService->>FileStorageService: Check if path contains filePath
    alt Path Contains File Path
        FileStorageService->>FileSystem: Files.deleteIfExists(load(filePath))
        alt File Deleted Successfully
            FileSystem-->>FileStorageService: true (file deleted)
            FileStorageService-->>StorageService: Success
            StorageService-->>Service: File deleted
            Service-->>Client: Success response
        else File Not Found
            FileSystem-->>FileStorageService: false (file didn't exist)
            FileStorageService-->>StorageService: Success (no-op)
            StorageService-->>Service: Success
            Service-->>Client: Success response
        else IO Exception
            FileSystem-->>FileStorageService: IOException
            FileStorageService->>FileStorageService: e.printStackTrace()
            FileStorageService-->>StorageService: Error (logged)
            StorageService-->>Service: Error
            Service-->>Client: Error response
        end
    else Path Doesn't Match
        FileStorageService-->>StorageService: No operation (skip deletion)
        StorageService-->>Service: No action taken
        Service-->>Client: Response
    end
    
    Note over Client,FileSystem: Delete All Files
    Client->>Service: Request to delete all files
    Service->>StorageService: deleteAll()
    StorageService->>FileStorageService: deleteAll()
    FileStorageService->>FileSystem: FileSystemUtils.deleteRecursively<br/>(rootLocation.toFile())
    FileSystem-->>FileStorageService: All files deleted
    FileStorageService-->>StorageService: Success
    StorageService-->>Service: All files deleted
    Service-->>Client: Success response
```

---

## 7. Error Handling Flow

This diagram shows how errors are handled throughout the storage module.

```mermaid
sequenceDiagram
    participant Client as Client/API Consumer
    participant Controller as Controller Layer
    participant StorageService as StorageService Interface
    participant FileStorageService as FileStorageService
    participant FileSystem as File System
    participant ExceptionHandler as Exception Handler

    Note over Client,ExceptionHandler: FileStorageException Flow
    Client->>Controller: Request (upload/delete/load)
    Controller->>StorageService: Operation call
    StorageService->>FileStorageService: Operation execution
    
    alt FileSystem Error
        FileStorageService->>FileSystem: File operation
        FileSystem-->>FileStorageService: IOException
        FileStorageService->>FileStorageService: throw FileStorageException<br/>(message, cause)
        FileStorageService-->>StorageService: FileStorageException
        StorageService-->>Controller: FileStorageException
        Controller->>ExceptionHandler: Unhandled exception
        ExceptionHandler-->>Client: 500 Internal Server Error
    end
    
    Note over Client,ExceptionHandler: MyFileNotFoundException Flow
    Client->>Controller: GET /api/files/{filename}
    Controller->>StorageService: loadAsResource(filename)
    StorageService->>FileStorageService: loadAsResource()
    
    alt File Not Found
        FileStorageService->>FileSystem: Check file existence
        FileSystem-->>FileStorageService: File doesn't exist
        FileStorageService->>FileStorageService: throw MyFileNotFoundException<br/>("Could not read file: " + filename)
        FileStorageService-->>StorageService: MyFileNotFoundException
        StorageService-->>Controller: MyFileNotFoundException
        Controller->>Controller: @ExceptionHandler<br/>handleStorageFileNotFound()
        Controller-->>Client: ResponseEntity.notFound()<br/>(404 Not Found)
    end
    
    Note over Client,ExceptionHandler: Empty File Error
    Client->>Controller: POST /api/saveFile (empty file)
    Controller->>StorageService: store(emptyFile, fileName)
    StorageService->>FileStorageService: store()
    FileStorageService->>FileStorageService: Check file.isEmpty()
    FileStorageService->>FileStorageService: throw FileStorageException<br/>("Failed to store empty file")
    FileStorageService-->>StorageService: FileStorageException
    StorageService-->>Controller: FileStorageException
    Controller->>ExceptionHandler: Unhandled exception
    ExceptionHandler-->>Client: 500 Internal Server Error
```

---

## Module Components Overview

### Classes and Interfaces

1. **StorageService** (Interface)
   - Defines the contract for storage operations
   - Methods: `init()`, `store()`, `loadAll()`, `load()`, `loadAsResource()`, `deleteAll()`, `deleteExistingFile()`

2. **FileStorageService** (Implementation)
   - Implements `StorageService` interface
   - Handles actual file system operations
   - Uses `StorageProperties` for configuration

3. **StorageProperties**
   - Configuration properties class
   - Annotated with `@ConfigurationProperties("storage")`
   - Default location: "upload-dir"

4. **FileStorageException**
   - Runtime exception for storage-related errors
   - Used for general storage failures

5. **MyFileNotFoundException**
   - Specialized exception for file not found scenarios
   - Annotated with `@ResponseStatus(HttpStatus.NOT_FOUND)`
   - Returns 404 status code

### Key Features

- **File Upload**: Supports both MultipartFile and Base64 image uploads
- **File Retrieval**: Load files as Spring Resource for serving
- **File Listing**: List all stored files with relative paths
- **File Deletion**: Delete single files or all files
- **Error Handling**: Comprehensive exception handling with appropriate HTTP status codes
- **Initialization**: Automatic directory creation on application startup

### Integration Points

The Storage Module is used by various controllers across the application:
- `FileUploadController` - Direct file upload/download operations
- Various service implementations for module-specific file operations (Warranty, Service, Sales, CRM, etc.)

