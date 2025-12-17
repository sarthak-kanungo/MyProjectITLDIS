## Exception Module - Detailed Sequence Diagrams

This document describes the **exception handling flows** implemented in the `com.i4o.dms.kubota.exception` module:

- **Global Exception Handling** (Generic exceptions, HTTP method errors, validation errors, and custom API exceptions).
- **Error Response Construction** (ApiErrorResponse and ApiResponse structures).
- **Validation Error Processing** (Field errors, object errors, and constraint violations).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the Exception module.

---

## 1. Generic Exception Handling Flow

This flow shows how **unexpected exceptions** are caught and handled by the GlobalExceptionHandler.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as Controller/Service Layer
    participant GExHandler as GlobalExceptionHandler
    participant Logger as Logger (SLF4J)
    participant ApiResponse as ApiResponse
    participant ResponseEntity as ResponseEntity

    %% Generic Exception Flow
    Note over Client,ResponseEntity: Generic Exception Handling
    
    Client->>Controller: HTTP Request
    Controller->>Controller: Business Logic Execution
    
    alt Exception Occurs
        Controller-->>GExHandler: Exception thrown
        activate GExHandler
        
        GExHandler->>GExHandler: @ExceptionHandler(Exception.class)<br/>handleGlobalException(Exception ex, WebRequest request)
        
        GExHandler->>ApiResponse: new ApiResponse<>()
        activate ApiResponse
        ApiResponse-->>GExHandler: apiResponse instance
        deactivate ApiResponse
        
        GExHandler->>Logger: logger.error("An error occurred: ", ex)
        activate Logger
        Logger->>Logger: Log exception with stack trace
        Logger-->>GExHandler: Logged
        deactivate Logger
        
        GExHandler->>ApiResponse: setMessage("An unexpected error occurred: " + ex.getMessage())
        GExHandler->>ApiResponse: setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
        
        GExHandler->>ResponseEntity: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse)
        activate ResponseEntity
        ResponseEntity-->>GExHandler: ResponseEntity with status 500
        deactivate ResponseEntity
        
        GExHandler-->>Client: HTTP 500 Response<br/>{status: 500, message: "An unexpected error occurred: ..."}
        deactivate GExHandler
    end
```

---

## 2. HTTP Method Not Supported Exception Flow

This flow shows how **HTTP method not allowed exceptions** are handled when an unsupported HTTP method is used.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant DispatcherServlet as Spring DispatcherServlet
    participant GExHandler as GlobalExceptionHandler
    participant ApiResponse as ApiResponse
    participant ResponseEntity as ResponseEntity

    %% HTTP Method Not Supported Flow
    Note over Client,ResponseEntity: HTTP Method Not Supported Exception Handling
    
    Client->>DispatcherServlet: HTTP Request<br/>(Unsupported Method, e.g., PUT on GET-only endpoint)
    
    DispatcherServlet->>DispatcherServlet: Process Request Mapping
    
    alt Method Not Supported
        DispatcherServlet-->>GExHandler: HttpRequestMethodNotSupportedException thrown
        activate GExHandler
        
        GExHandler->>GExHandler: @ExceptionHandler(HttpRequestMethodNotSupportedException.class)<br/>handleMethodNotAllowedException(ex, request)
        
        GExHandler->>ApiResponse: new ApiResponse<>()
        activate ApiResponse
        ApiResponse-->>GExHandler: apiResponse instance
        deactivate ApiResponse
        
        GExHandler->>ApiResponse: setMessage("HTTP method not allowed: " + ex.getMethod())
        GExHandler->>ApiResponse: setStatus(HttpStatus.METHOD_NOT_ALLOWED.value())
        
        GExHandler->>ResponseEntity: ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiResponse)
        activate ResponseEntity
        ResponseEntity-->>GExHandler: ResponseEntity with status 405
        deactivate ResponseEntity
        
        GExHandler-->>Client: HTTP 405 Response<br/>{status: 405, message: "HTTP method not allowed: PUT"}
        deactivate GExHandler
    end
```

---

## 3. Validation Exception Handling Flow

This flow shows how **method argument validation errors** are processed and returned to the client.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as Controller
    participant Validator as Spring Validator
    participant GExHandler as GlobalExceptionHandler
    participant ApiResponse as ApiResponse
    participant HashMap as HashMap<String, String>
    participant ResponseEntity as ResponseEntity

    %% Validation Exception Flow
    Note over Client,ResponseEntity: Method Argument Validation Exception Handling
    
    Client->>Controller: POST/PUT Request<br/>(Request Body with @Valid annotation)
    
    Controller->>Validator: Validate Request Body
    activate Validator
    
    alt Validation Fails
        Validator-->>Controller: MethodArgumentNotValidException thrown
        deactivate Validator
        Controller-->>GExHandler: Exception propagated
        activate GExHandler
        
        GExHandler->>GExHandler: @ExceptionHandler(MethodArgumentNotValidException.class)<br/>handleValidationExceptions(ex)
        
        GExHandler->>ApiResponse: new ApiResponse<>()
        activate ApiResponse
        ApiResponse-->>GExHandler: apiResponse instance
        deactivate ApiResponse
        
        GExHandler->>HashMap: new HashMap<String, String>()
        activate HashMap
        HashMap-->>GExHandler: errors map instance
        deactivate HashMap
        
        GExHandler->>GExHandler: ex.getBindingResult().getAllErrors()
        GExHandler->>GExHandler: forEach((error) -> {<br/>  fieldName = ((FieldError) error).getField()<br/>  errorMessage = error.getDefaultMessage()<br/>  errors.put(fieldName, errorMessage)<br/>})
        
        loop For Each Field Error
            GExHandler->>HashMap: put(fieldName, errorMessage)
            Note right of HashMap: e.g., "email": "must be a valid email",<br/>"age": "must be greater than 0"
        end
        
        GExHandler->>ApiResponse: setMessage("Validation Failed" + ex.getMessage())
        GExHandler->>ApiResponse: setStatus(HttpStatus.BAD_REQUEST.value())
        
        GExHandler->>ResponseEntity: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse)
        activate ResponseEntity
        ResponseEntity-->>GExHandler: ResponseEntity with status 400
        deactivate ResponseEntity
        
        GExHandler-->>Client: HTTP 400 Response<br/>{status: 400, message: "Validation Failed ..."}
        deactivate GExHandler
    else Validation Passes
        Validator-->>Controller: Validation successful
        deactivate Validator
        Controller->>Controller: Process request normally
    end
```

---

## 4. Custom API Request Exception Handling Flow

This flow shows how **custom ApiRequestException** instances are handled and converted to ApiException responses.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Service as Service Layer
    participant GExHandler as GlobalExceptionHandler
    participant ApiException as ApiException
    participant Date as Date
    participant WebRequest as WebRequest
    participant ResponseEntity as ResponseEntity

    %% Custom API Request Exception Flow
    Note over Client,ResponseEntity: Custom API Request Exception Handling
    
    Client->>Service: Business Logic Call
    
    alt Business Rule Violation
        Service->>Service: throw new ApiRequestException("Custom error message")
        Service-->>GExHandler: ApiRequestException thrown
        activate GExHandler
        
        GExHandler->>GExHandler: @ExceptionHandler(ApiRequestException.class)<br/>handleApiRequestException(e, request, httpStatus)
        
        GExHandler->>Date: new Date()
        activate Date
        Date-->>GExHandler: current timestamp
        deactivate Date
        
        GExHandler->>WebRequest: request.getDescription(false)
        activate WebRequest
        WebRequest-->>GExHandler: request description (URI)
        deactivate WebRequest
        
        GExHandler->>ApiException: new ApiException(timestamp, e.getMessage(), requestDescription)
        activate ApiException
        ApiException->>ApiException: Constructor sets:<br/>- timestamp<br/>- message<br/>- details
        ApiException-->>GExHandler: apiException instance
        deactivate ApiException
        
        GExHandler->>ResponseEntity: new ResponseEntity<>(apiException, httpStatus)
        activate ResponseEntity
        Note right of ResponseEntity: httpStatus parameter determines<br/>the HTTP status code
        ResponseEntity-->>GExHandler: ResponseEntity with ApiException body
        deactivate ResponseEntity
        
        GExHandler-->>Client: HTTP Response (status from httpStatus parameter)<br/>{timestamp: Date, message: "...", details: "..."}
        deactivate GExHandler
    end
```

---

## 5. ApiErrorResponse Construction Flow (Alternative Error Response Structure)

This flow shows how **ApiErrorResponse** objects are constructed with detailed error information, including sub-errors for validation failures. Note: This class exists but is not currently used in GlobalExceptionHandler (which uses ApiResponse instead).

```mermaid
sequenceDiagram
    %% Participants
    participant Handler as Exception Handler
    participant ApiErrorResponse as ApiErrorResponse
    participant HttpStatus as HttpStatus
    participant LocalDateTime as LocalDateTime
    participant ApiSubError as ApiSubError
    participant ApiValidationError as ApiValidationError
    participant ArrayList as ArrayList

    %% ApiErrorResponse Construction Flow
    Note over Handler,ArrayList: ApiErrorResponse Construction (Alternative Structure)
    
    Handler->>ApiErrorResponse: new ApiErrorResponse(HttpStatus status)
    activate ApiErrorResponse
    
    ApiErrorResponse->>LocalDateTime: LocalDateTime.now()
    activate LocalDateTime
    LocalDateTime-->>ApiErrorResponse: current timestamp
    deactivate LocalDateTime
    
    ApiErrorResponse->>ApiErrorResponse: setStatus(status)
    ApiErrorResponse->>ApiErrorResponse: setTimestamp(timestamp)
    
    alt Exception with Throwable
        Handler->>ApiErrorResponse: new ApiErrorResponse(status, ex)
        ApiErrorResponse->>ApiErrorResponse: setMessage("Unexpected error")
        ApiErrorResponse->>ApiErrorResponse: setDebugMessage(ex.getLocalizedMessage())
    else Exception with Custom Message
        Handler->>ApiErrorResponse: new ApiErrorResponse(status, message, ex)
        ApiErrorResponse->>ApiErrorResponse: setMessage(message)
        ApiErrorResponse->>ApiErrorResponse: setDebugMessage(ex.getLocalizedMessage())
    end
    
    alt Adding Validation Errors
        Handler->>ApiErrorResponse: addValidationErrors(List<FieldError>)
        
        loop For Each FieldError
            ApiErrorResponse->>ApiErrorResponse: addValidationError(FieldError)
            ApiErrorResponse->>ApiValidationError: new ApiValidationError(object, field, rejectedValue, message)
            activate ApiValidationError
            ApiValidationError-->>ApiErrorResponse: validationError instance
            deactivate ApiValidationError
            
            ApiErrorResponse->>ApiErrorResponse: addSubError(validationError)
            
            alt SubErrors List is Null
                ApiErrorResponse->>ArrayList: new ArrayList<>()
                activate ArrayList
                ArrayList-->>ApiErrorResponse: subErrors list
                deactivate ArrayList
                ApiErrorResponse->>ApiErrorResponse: setSubErrors(subErrors)
            end
            
            ApiErrorResponse->>ApiErrorResponse: subErrors.add(validationError)
        end
    else Adding Constraint Violations
        Handler->>ApiErrorResponse: addValidationErrors(Set<ConstraintViolation>)
        
        loop For Each ConstraintViolation
            ApiErrorResponse->>ApiErrorResponse: addValidationError(ConstraintViolation)
            ApiErrorResponse->>ApiErrorResponse: Extract:<br/>- rootBeanClass name<br/>- property path<br/>- invalid value<br/>- message
            ApiErrorResponse->>ApiValidationError: new ApiValidationError(object, field, rejectedValue, message)
            activate ApiValidationError
            ApiValidationError-->>ApiErrorResponse: validationError instance
            deactivate ApiValidationError
            ApiErrorResponse->>ApiErrorResponse: addSubError(validationError)
        end
    end
    
    ApiErrorResponse-->>Handler: Complete ApiErrorResponse object<br/>{status, timestamp, message, debugMessage, subErrors}
    deactivate ApiErrorResponse
```

---

## 6. Complete Exception Handling Flow with Multiple Exception Types

This comprehensive flow shows how different exception types are routed to appropriate handlers in the GlobalExceptionHandler.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as Controller
    participant Service as Service Layer
    participant Validator as Spring Validator
    participant GExHandler as GlobalExceptionHandler
    participant ApiResponse as ApiResponse
    participant ApiException as ApiException
    participant Logger as Logger

    %% Complete Exception Flow
    Note over Client,Logger: Complete Exception Handling Flow
    
    Client->>Controller: HTTP Request
    
    Controller->>Controller: Process Request
    
    alt Exception Type Detection
        alt MethodArgumentNotValidException
            Controller->>Validator: Validate Request
            Validator-->>Controller: MethodArgumentNotValidException
            Controller-->>GExHandler: Exception propagated
            activate GExHandler
            GExHandler->>GExHandler: handleValidationExceptions()
            GExHandler->>ApiResponse: Create ApiResponse with validation errors
            GExHandler-->>Client: HTTP 400 Bad Request
            deactivate GExHandler
            
        else HttpRequestMethodNotSupportedException
            Controller-->>GExHandler: HttpRequestMethodNotSupportedException
            activate GExHandler
            GExHandler->>GExHandler: handleMethodNotAllowedException()
            GExHandler->>ApiResponse: Create ApiResponse with method error
            GExHandler-->>Client: HTTP 405 Method Not Allowed
            deactivate GExHandler
            
        else ApiRequestException
            Controller->>Service: Business Logic
            Service-->>Controller: throw ApiRequestException
            Controller-->>GExHandler: ApiRequestException propagated
            activate GExHandler
            GExHandler->>GExHandler: handleApiRequestException()
            GExHandler->>ApiException: Create ApiException with timestamp and details
            GExHandler-->>Client: HTTP Response (custom status)
            deactivate GExHandler
            
        else Generic Exception
            Controller->>Service: Business Logic
            Service-->>Controller: Unexpected Exception
            Controller-->>GExHandler: Exception propagated
            activate GExHandler
            GExHandler->>GExHandler: handleGlobalException()
            GExHandler->>Logger: logger.error("An error occurred: ", ex)
            GExHandler->>ApiResponse: Create ApiResponse with error message
            GExHandler-->>Client: HTTP 500 Internal Server Error
            deactivate GExHandler
        end
    end
```

---

## Class Structure Overview

### GlobalExceptionHandler
- **Purpose**: Central exception handler using Spring's `@ControllerAdvice`
- **Exception Handlers**:
  1. `handleGlobalException()` - Catches all unhandled exceptions
  2. `handleMethodNotAllowedException()` - Handles HTTP method errors
  3. `handleValidationExceptions()` - Handles validation errors
  4. `handleApiRequestException()` - Handles custom API exceptions

### ApiErrorResponse
- **Purpose**: Structured error response with detailed error information
- **Features**:
  - HTTP status code
  - Timestamp (formatted as "dd-MM-yyyy hh:mm:ss")
  - Error message and debug message
  - Sub-errors for validation failures
  - Support for FieldError, ObjectError, and ConstraintViolation

### ApiResponse
- **Purpose**: Standard API response wrapper used by GlobalExceptionHandler
- **Fields**: status, message, result, count, id, token

### ApiException
- **Purpose**: Exception response structure for ApiRequestException
- **Fields**: timestamp (Date), message, details

### ApiRequestException
- **Purpose**: Custom runtime exception for API-specific errors
- **Extends**: RuntimeException

---

## Notes

1. **Current Implementation**: The `GlobalExceptionHandler` uses `ApiResponse` for most exceptions, while `ApiErrorResponse` exists but is not currently integrated into the handler methods.

2. **Exception Priority**: Spring's exception handling follows a priority order - more specific exception handlers are matched before generic ones.

3. **Logging**: Generic exceptions are logged with full stack traces using SLF4J Logger.

4. **Validation Errors**: The validation exception handler extracts field-level errors from Spring's BindingResult and maps them to a HashMap structure.

5. **Custom Exceptions**: `ApiRequestException` allows custom error messages and can be thrown with different HTTP status codes via the handler method parameter.

