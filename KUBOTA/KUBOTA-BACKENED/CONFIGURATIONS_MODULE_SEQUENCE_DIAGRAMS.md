## Configurations Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.configurations` module:

- **Web Configuration** (multipart file upload resolver + image compression interceptor).
- **Swagger API Documentation** (Swagger/OpenAPI configuration with authentication).
- **Global CORS Configuration** (Cross-Origin Resource Sharing filter).
- **Jackson JSON Configuration** (ObjectMapper timezone configuration).
- **Excel Import Manager** (Excel import utility bean configuration).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the configurations module.

---

## 1. Web Configuration - File Upload with Image Compression Interceptor Flow

This flow shows how **multipart file uploads** are handled and how the **ImageCompressNLimiterInterceptor** intercepts image uploads to validate size limits and compress images before they reach the controller.

```mermaid
sequenceDiagram
    %% Participants
    participant Spring as Spring Application Context
    participant WebConfig as WebConfiguration
    participant MultipartResolver as StandardServletMultipartResolver
    participant Interceptor as ImageCompressNLimiterInterceptor
    participant Client as Client Application
    participant Controller as Controller
    participant ImageIO as ImageIO Library

    %% Application Startup - Configuration
    Note over Spring,WebConfig: Application Startup - Bean Configuration
    Spring->>WebConfig: Initialize WebConfiguration
    WebConfig->>WebConfig: Read maxFileSize from application.properties
    WebConfig->>WebConfig: Read maxRequestSize from application.properties
    
    WebConfig->>MultipartResolver: Create StandardServletMultipartResolver bean
    MultipartResolver-->>WebConfig: MultipartResolver instance
    
    WebConfig->>Interceptor: Create ImageCompressNLimiterInterceptor bean
    Interceptor->>Interceptor: Read image.maxFileSize from properties
    Interceptor->>Interceptor: Read image.compressLimitSize from properties
    Interceptor->>Interceptor: Read image.compressionQualityVal from properties
    Interceptor-->>WebConfig: Interceptor instance
    
    WebConfig->>WebConfig: addInterceptors(InterceptorRegistry)
    WebConfig->>WebConfig: Register interceptor in registry
    WebConfig-->>Spring: Configuration complete

    %% File Upload Request Flow
    Note over Client,ImageIO: File Upload Request Processing
    Client->>Controller: POST /api/.../upload (multipart/form-data)
    
    Spring->>MultipartResolver: resolveMultipart(request)
    MultipartResolver->>MultipartResolver: Parse multipart request
    MultipartResolver->>MultipartResolver: Extract MultipartFile objects
    MultipartResolver-->>Spring: MultipartHttpServletRequest

    Spring->>Interceptor: preHandle(request, response, handler)
    Interceptor->>Interceptor: Check if request instanceof MultipartHttpServletRequest
    
    alt Multipart request detected
        Interceptor->>Interceptor: validateImage(request, response)
        Interceptor->>Interceptor: Get MultiValueMap from request
        
        loop For each file parameter in request
            Interceptor->>Interceptor: Get List of MultipartFile
            
            loop For each MultipartFile
                Interceptor->>Interceptor: Check file.getContentType()
                
                alt File is image type
                    Interceptor->>Interceptor: isImageFile(file) returns true
                    
                    Interceptor->>Interceptor: Check file.getSize() > maxFileSize
                    
                    alt File size exceeds limit
                        Interceptor->>Interceptor: isImgageUnderSize() returns true
                        Interceptor->>Controller: Return false (block request)
                        Interceptor->>Client: response.sendError(403, "Image file size exceeds limit")
                        Note over Client: Request rejected
                    else File size within limit
                        Interceptor->>Interceptor: Check file.getSize() > compressLimitSize
                        
                        alt File eligible for compression
                            Interceptor->>Interceptor: isEligibleForCompression() returns true
                            
                            Interceptor->>Interceptor: createCompressedMultipartFile(file)
                            Interceptor->>Interceptor: compressImage(file.getBytes())
                            
                            Interceptor->>ImageIO: ImageIO.read(ByteArrayInputStream)
                            ImageIO-->>Interceptor: BufferedImage originalImage
                            
                            Interceptor->>ImageIO: Get ImageWriter for "jpg"
                            ImageIO-->>Interceptor: ImageWriter instance
                            
                            Interceptor->>Interceptor: Set compression quality (compressionQualityVal)
                            Interceptor->>Interceptor: Create ImageWriteParam with MODE_EXPLICIT
                            Interceptor->>ImageIO: writer.write(IIOImage, writeParam)
                            ImageIO-->>Interceptor: Compressed image bytes
                            
                            Interceptor->>Interceptor: Create CustomMultipartFile with compressed bytes
                            Interceptor->>Interceptor: Replace file in request: multiFileMap.set(i, compressedImage)
                            
                            Note over Interceptor: Compressed file injected into request
                        end
                    end
                else File is not image type
                    Interceptor->>Interceptor: Skip processing (non-image file)
                end
            end
        end
        
        Interceptor-->>Spring: Return true (allow request to proceed)
    else Non-multipart request
        Interceptor-->>Spring: Return true (no processing needed)
    end

    Spring->>Controller: Process request with (possibly compressed) files
    Controller->>Controller: Handle business logic
    Controller-->>Client: Response (success/error)
```

---

## 2. Swagger API Documentation - Initialization and Access Flow

This flow shows how **Swagger/OpenAPI documentation** is configured during application startup and how clients access the API documentation with authentication.

```mermaid
sequenceDiagram
    %% Participants
    participant Spring as Spring Application Context
    participant SwaggerConfig as SwaggerConfig
    participant Docket as Docket Bean
    participant SwaggerUI as Swagger UI
    participant Client as Client Browser
    participant API as REST API Endpoints

    %% Application Startup - Swagger Configuration
    Note over Spring,SwaggerConfig: Application Startup - Swagger Configuration
    Spring->>SwaggerConfig: Initialize SwaggerConfig (@Configuration)
    SwaggerConfig->>SwaggerConfig: @EnableSwagger2 annotation processed
    
    Spring->>SwaggerConfig: swaggerApi() bean creation
    SwaggerConfig->>Docket: new Docket(DocumentationType.SWAGGER_2)
    Docket-->>SwaggerConfig: Docket instance
    
    SwaggerConfig->>Docket: select().apis(RequestHandlerSelectors.any())
    SwaggerConfig->>Docket: paths(regex("/api.*"))
    SwaggerConfig->>Docket: build()
    
    SwaggerConfig->>SwaggerConfig: apiKey() - Create ApiKey
    SwaggerConfig->>SwaggerConfig: Return ApiKey("access_token", "Authorization", "header")
    SwaggerConfig->>Docket: securitySchemes(Lists.newArrayList(apiKey()))
    
    SwaggerConfig->>Docket: pathMapping("/")
    SwaggerConfig->>Docket: genericModelSubstitutes(ResponseEntity.class)
    SwaggerConfig->>Docket: useDefaultResponseMessages(false)
    SwaggerConfig->>Docket: forCodeGeneration(true)
    
    Docket-->>SwaggerConfig: Configured Docket bean
    SwaggerConfig-->>Spring: Docket bean registered
    
    Spring->>SwaggerUI: Initialize Swagger UI with Docket configuration
    SwaggerUI->>API: Scan all controllers matching /api.* pattern
    API-->>SwaggerUI: Controller metadata (endpoints, parameters, responses)
    SwaggerUI->>SwaggerUI: Generate OpenAPI/Swagger JSON specification
    SwaggerUI-->>Spring: Swagger UI ready at /swagger-ui.html

    %% Client Access Flow
    Note over Client,API: Client Accessing API Documentation
    Client->>SwaggerUI: GET /swagger-ui.html
    SwaggerUI-->>Client: Swagger UI HTML page
    
    Client->>SwaggerUI: GET /v2/api-docs (Swagger JSON)
    SwaggerUI->>Docket: Generate API documentation JSON
    Docket->>API: Collect endpoint metadata
    API-->>Docket: Endpoint information
    Docket-->>SwaggerUI: Swagger JSON specification
    SwaggerUI-->>Client: JSON response with API documentation
    
    Note over Client: Client views API endpoints in Swagger UI
    
    %% Authenticated API Call from Swagger UI
    Client->>SwaggerUI: Enter Authorization token in Swagger UI
    SwaggerUI->>SwaggerUI: Store token for API calls
    
    Client->>SwaggerUI: Click "Try it out" on an endpoint
    SwaggerUI->>API: API Request with Authorization header
    Note over SwaggerUI,API: Header: Authorization: Bearer <token>
    
    API->>API: Validate Authorization header
    alt Token valid
        API->>API: Process request
        API-->>SwaggerUI: Response (200 OK with data)
        SwaggerUI-->>Client: Display response in Swagger UI
    else Token invalid
        API-->>SwaggerUI: Response (401 Unauthorized)
        SwaggerUI-->>Client: Display error in Swagger UI
    end
```

---

## 3. Global CORS Configuration - Cross-Origin Request Handling Flow

This flow shows how **CORS (Cross-Origin Resource Sharing)** is configured globally and how the CORS filter intercepts requests to validate origins, headers, and methods.

```mermaid
sequenceDiagram
    %% Participants
    participant Spring as Spring Application Context
    participant CORSConfig as GlobalCORSConfig
    participant CorsConfigSource as CorsConfigurationSource
    participant CorsFilter as CorsFilter
    participant Client as Client Browser (Different Origin)
    participant Server as Spring MVC DispatcherServlet
    participant Controller as Controller

    %% Application Startup - CORS Configuration
    Note over Spring,CORSConfig: Application Startup - CORS Configuration
    Spring->>CORSConfig: Initialize GlobalCORSConfig (@Configuration)
    
    Spring->>CORSConfig: corsConfigurationSource() bean creation
    CORSConfig->>CORSConfig: Create new CorsConfiguration
    CORSConfig->>CORSConfig: Read Constants.ALLOWED_ORIGINS list
    
    CORSConfig->>CorsConfigSource: setAllowedOrigins(ALLOWED_ORIGINS)
    Note over CORSConfig: Origins: localhost:9000-9008,<br/>180.179.248.142:7000-7007,<br/>dmskai.in:9000-9008
    
    CORSConfig->>CorsConfigSource: setAllowCredentials(true)
    CORSConfig->>CorsConfigSource: setAllowedMethods(["GET", "POST"])
    CORSConfig->>CorsConfigSource: setAllowedHeaders([Accept, Origin, Content-Type,<br/>Authorization, X-Requested-With, ...])
    
    CORSConfig->>CorsConfigSource: Create UrlBasedCorsConfigurationSource
    CORSConfig->>CorsConfigSource: registerCorsConfiguration("/**", configuration)
    CorsConfigSource-->>CORSConfig: CorsConfigurationSource bean
    
    Spring->>CORSConfig: corsFilter() bean creation
    CORSConfig->>CorsFilter: new CorsFilter(corsConfigurationSource)
    CorsFilter-->>CORSConfig: CorsFilter bean
    CORSConfig-->>Spring: CORS configuration complete

    %% CORS Request Flow
    Note over Client,Controller: Cross-Origin Request Processing
    Client->>Server: HTTP Request (e.g., POST /api/kubotauser/login)<br/>Origin: http://localhost:9000
    
    Server->>CorsFilter: Filter request (doFilter)
    CorsFilter->>CorsConfigSource: Get CORS configuration for "/**"
    CorsConfigSource-->>CorsFilter: CorsConfiguration
    
    CorsFilter->>CorsFilter: Check request Origin header
    CorsFilter->>CorsFilter: Validate origin against ALLOWED_ORIGINS
    
    alt Origin is allowed
        CorsFilter->>CorsFilter: Check request method (GET/POST)
        
        alt Method is allowed
            CorsFilter->>CorsFilter: Check request headers
            
            alt Headers are allowed
                CorsFilter->>CorsFilter: Set CORS response headers
                CorsFilter->>CorsFilter: Access-Control-Allow-Origin: <origin>
                CorsFilter->>CorsFilter: Access-Control-Allow-Credentials: true
                CorsFilter->>CorsFilter: Access-Control-Allow-Methods: GET, POST
                CorsFilter->>CorsFilter: Access-Control-Allow-Headers: <allowed headers>
                
                alt Preflight request (OPTIONS)
                    CorsFilter->>Client: Response 200 OK with CORS headers
                    Note over Client: Browser sends actual request
                else Actual request
                    CorsFilter->>Server: Continue filter chain
                    Server->>Controller: Process request
                    Controller->>Controller: Execute business logic
                    Controller-->>Server: Response
                    Server->>CorsFilter: Response with CORS headers added
                    CorsFilter-->>Client: HTTP Response with CORS headers
                end
            else Headers not allowed
                CorsFilter->>Client: Response 403 Forbidden
                Note over Client: Request rejected - invalid headers
            end
        else Method not allowed
            CorsFilter->>Client: Response 405 Method Not Allowed
            Note over Client: Request rejected - invalid method
        end
    else Origin not allowed
        CorsFilter->>Client: Response 403 Forbidden
        Note over Client: Request rejected - origin not in allowed list
    end
```

---

## 4. Jackson JSON Configuration - ObjectMapper Timezone Configuration Flow

This flow shows how **Jackson ObjectMapper** is configured with timezone settings during application startup and how it affects JSON serialization/deserialization.

```mermaid
sequenceDiagram
    %% Participants
    participant Spring as Spring Application Context
    participant JacksonConfig as JaksonConfiguration
    participant ObjectMapper as ObjectMapper Bean
    participant Controller as Controller
    participant Entity as Entity/Model Object
    participant Client as Client Application

    %% Application Startup - Jackson Configuration
    Note over Spring,JacksonConfig: Application Startup - Jackson Configuration
    Spring->>JacksonConfig: Initialize JaksonConfiguration (@Configuration)
    Spring->>ObjectMapper: Create default ObjectMapper bean
    
    Spring->>JacksonConfig: JacksonConfiguration(ObjectMapper) - Autowired
    JacksonConfig->>ObjectMapper: setTimeZone(TimeZone.getDefault())
    ObjectMapper->>ObjectMapper: Get system default timezone
    ObjectMapper->>ObjectMapper: Configure timezone for date/time serialization
    ObjectMapper-->>JacksonConfig: ObjectMapper configured with default timezone
    JacksonConfig-->>Spring: Configuration complete

    %% JSON Serialization Flow (Entity to JSON)
    Note over Controller,Client: JSON Serialization - Entity to JSON
    Controller->>Controller: Process request and create Entity object
    Controller->>Entity: Set date/time fields (e.g., new Date())
    Entity->>Entity: Store date/time in memory
    
    Controller->>ObjectMapper: writeValueAsString(entity) or<br/>@ResponseBody annotation
    ObjectMapper->>ObjectMapper: Serialize Entity to JSON
    ObjectMapper->>ObjectMapper: Apply timezone from configuration
    ObjectMapper->>ObjectMapper: Convert date/time fields using configured timezone
    ObjectMapper->>ObjectMapper: Format dates according to timezone
    ObjectMapper-->>Controller: JSON string with timezone-adjusted dates
    
    Controller->>Client: HTTP Response (JSON body)
    Note over Client: JSON contains dates formatted<br/>according to system timezone

    %% JSON Deserialization Flow (JSON to Entity)
    Note over Client,Entity: JSON Deserialization - JSON to Entity
    Client->>Controller: POST /api/... (JSON body with dates)
    Controller->>ObjectMapper: readValue(jsonString, Entity.class) or<br/>@RequestBody annotation
    
    ObjectMapper->>ObjectMapper: Parse JSON string
    ObjectMapper->>ObjectMapper: Deserialize JSON to Entity
    ObjectMapper->>ObjectMapper: Apply timezone from configuration
    ObjectMapper->>ObjectMapper: Parse date/time fields using configured timezone
    ObjectMapper->>ObjectMapper: Convert to appropriate Java Date/Time objects
    ObjectMapper-->>Controller: Entity object with parsed dates
    
    Controller->>Controller: Process Entity with timezone-adjusted dates
    Controller->>Entity: Save to database or process
    Entity-->>Controller: Entity processed
    Controller-->>Client: Response
```

---

## 5. Excel Import Manager - Bean Configuration and Usage Flow

This flow shows how the **ExcelImportManager** bean is configured during application startup and how it's used by service classes to import Excel files.

```mermaid
sequenceDiagram
    %% Participants
    participant Spring as Spring Application Context
    participant ExcelConfig as ManageExcelImport
    participant ExcelManager as ExcelImportManager Bean
    participant Service as Service Class (e.g., SparePurchaseOrderImpl)
    participant Controller as Controller
    participant Client as Client Application
    participant ExcelFile as Excel File (MultipartFile)

    %% Application Startup - Excel Import Manager Configuration
    Note over Spring,ExcelConfig: Application Startup - Bean Configuration
    Spring->>ExcelConfig: Initialize ManageExcelImport (@Configuration)
    
    Spring->>ExcelConfig: excelImportManager() bean creation
    ExcelConfig->>ExcelManager: new ExcelImportManager()
    ExcelManager->>ExcelManager: Initialize ExcelImportManager instance
    ExcelManager-->>ExcelConfig: ExcelImportManager bean
    ExcelConfig-->>Spring: Bean registered in application context

    %% Excel Import Usage Flow
    Note over Client,ExcelFile: Excel File Import Request
    Client->>Controller: POST /api/.../uploadExcel<br/>(multipart/form-data with Excel file)
    
    Controller->>Controller: Receive MultipartFile (Excel file)
    Controller->>Service: importExcelData(MultipartFile file)
    
    Service->>Spring: @Autowired ExcelImportManager
    Spring-->>Service: Inject ExcelImportManager bean instance
    
    Service->>ExcelManager: Process Excel file (various methods)
    Note over Service,ExcelManager: Methods like:<br/>- readExcelFile()<br/>- validateExcelData()<br/>- parseExcelRows()<br/>- convertToEntity()
    
    ExcelManager->>ExcelFile: Read file bytes
    ExcelFile-->>ExcelManager: File bytes
    
    ExcelManager->>ExcelManager: Parse Excel file (Apache POI or similar)
    ExcelManager->>ExcelManager: Read rows and columns
    ExcelManager->>ExcelManager: Validate data format
    ExcelManager->>ExcelManager: Convert rows to entity objects
    
    ExcelManager-->>Service: List of Entity objects or validation results
    
    Service->>Service: Process imported data
    alt Data valid
        Service->>Service: Save entities to database
        Service-->>Controller: Success response with imported count
    else Data invalid
        Service-->>Controller: Error response with validation messages
    end
    
    Controller-->>Client: HTTP Response (success/error)
```

---

## Summary

The **configurations** module provides **cross-cutting infrastructure configuration** for the KUBOTA DMS:

- **Web Configuration**:
  - Configures `StandardServletMultipartResolver` for handling multipart file uploads.
  - Registers `ImageCompressNLimiterInterceptor` to intercept image uploads, validate file sizes, and automatically compress images that exceed the compression threshold before they reach controllers.

- **Swagger API Documentation**:
  - Configures Swagger/OpenAPI documentation with API key authentication.
  - Scans all endpoints matching `/api.*` pattern and generates interactive API documentation accessible at `/swagger-ui.html`.
  - Supports Authorization header-based authentication for testing APIs.

- **Global CORS Configuration**:
  - Configures Cross-Origin Resource Sharing (CORS) filter globally.
  - Validates request origins against an allowed list (localhost ports, production IPs, and domains).
  - Sets appropriate CORS headers for allowed GET/POST requests with specific headers.

- **Jackson JSON Configuration**:
  - Configures Jackson `ObjectMapper` with system default timezone.
  - Ensures consistent date/time serialization and deserialization across all JSON operations.

- **Excel Import Manager**:
  - Provides a centralized `ExcelImportManager` bean for Excel file processing.
  - Used by multiple service classes (spares, warranty, inventory management) for importing Excel data.

These configuration classes ensure **consistent behavior** across the entire application for file uploads, API documentation, cross-origin requests, JSON processing, and Excel imports.

