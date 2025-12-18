## Constant Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.constant` module:

- **Constant Definition and Loading** (DmsConstants interface with constant values).
- **Usage in Warranty Hotline Report Service** (Status constants for photo submission and answering).
- **Usage in Marketing Activity Status Controller** (Lookup type code constant for system lookups).
- **Constant Access Pattern** (How constants are accessed across the application).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the constant module.

---

## 1. Constant Definition and Loading - DmsConstants Interface

This flow shows how **DmsConstants** interface defines constant values and how they are loaded and accessed by classes at compile-time and runtime.

```mermaid
sequenceDiagram
    %% Participants
    participant JVM as Java Virtual Machine
    participant ClassLoader as Class Loader
    participant DmsConstants as DmsConstants Interface
    participant Service as Service/Controller Class
    participant Memory as JVM Memory

    JVM->>ClassLoader: Load DmsConstants class
    ClassLoader->>ClassLoader: Locate DmsConstants.class file
    ClassLoader->>DmsConstants: Load class bytecode
    
    DmsConstants->>Memory: Initialize constant values in constant pool
    Memory->>Memory: Store SM_ACTIVITY_STATUS string literal
    Memory->>Memory: Store Submitted string literal
    Memory->>Memory: Store Answered string literal
    
    DmsConstants-->>ClassLoader: Class loaded with constants
    ClassLoader-->>JVM: DmsConstants class ready

    Service->>Service: Import DmsConstants interface
    Service->>DmsConstants: Access constant DmsConstants.PHOTO_SUBMIT
    
    DmsConstants->>Memory: Lookup constant value in constant pool
    Memory-->>DmsConstants: Return string literal Submitted
    DmsConstants-->>Service: Return constant value
    
    Service->>Service: Service uses constant value for comparison or lookup
```

---

## 2. Usage in Warranty Hotline Report Service - Photo Status Constants

This flow shows how **DmsConstants** constants are used in **WarrantyHotlineReportServiceImpl** to determine photo attachment status when submitting warranty hotline reports.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as Warranty Hotline Report Controller
    participant Service as WarrantyHotlineReportServiceImpl
    participant DmsConstants as DmsConstants
    participant StorageService as StorageService
    participant AttachmentRepo as WarrantyHotlineReportAttachmentRepo
    participant DB as Database

    %% Warranty Hotline Report Submission Flow
    Note over Client,DB: Warranty Hotline Report Submission with Attachments
    
    Client->>Controller: POST /api/warranty/hotlineReport/submit<br/>(with report data and attachments)
    
    Controller->>Service: submitHotlineReport(hotlineReport, multipartFileList)
    
    Service->>Service: Set createdBy, createdDate, htlrDate<br/>(if new report)
    Service->>Service: Set lastModifiedBy, lastModifiedDate<br/>(if existing report)
    Service->>Service: Save report to database
    Service->>DB: INSERT/UPDATE warranty_hotline_report
    DB-->>Service: Report saved
    
    Service->>Service: Check if attachments exist<br/>(multipartFileList.size() <= 5)
    
    loop For each attachment file
        Service->>Service: Create WarrantyHotlineReportAttachment object
        Service->>Service: Get report status: hotlineReport.getStatus()
        
        Service->>DmsConstants: Access DmsConstants.PHOTO_SUBMIT
        DmsConstants-->>Service: Return "Submitted"
        Service->>Service: Compare status.equals(DmsConstants.PHOTO_SUBMIT)
        
        alt Status equals PHOTO_SUBMIT
            Service->>Service: Build photoName = "wa_hotline_rpt_" +<br/>DmsConstants.PHOTO_SUBMIT +<br/>System.currentTimeMillis() + "_" + filename
            Note over Service: photoName = "wa_hotline_rpt_Submitted1234567890_photo.jpg"
            
            Service->>StorageService: store(file, photoName)
            StorageService->>StorageService: Save file to storage location
            StorageService-->>Service: File stored successfully
            
            Service->>Service: Set attachment fileName = photoName
            Service->>DmsConstants: Access DmsConstants.PHOTO_SUBMIT
            DmsConstants-->>Service: Return "Submitted"
            Service->>Service: Set attachStatus = DmsConstants.PHOTO_SUBMIT
            
        else Status equals PHOTO_ANSWERED
            Service->>DmsConstants: Access DmsConstants.PHOTO_ANSWERED
            DmsConstants-->>Service: Return "Answered"
            Service->>Service: Compare status.equals(DmsConstants.PHOTO_ANSWERED)
            
            Service->>Service: Build photoName = "wa_hotline_rpt_" +<br/>DmsConstants.PHOTO_ANSWERED +<br/>System.currentTimeMillis() + "_" + filename
            Note over Service: photoName = "wa_hotline_rpt_Answered1234567890_photo.jpg"
            
            Service->>StorageService: store(file, photoName)
            StorageService->>StorageService: Save file to storage location
            StorageService-->>Service: File stored successfully
            
            Service->>Service: Set attachment fileName = photoName
            Service->>DmsConstants: Access DmsConstants.PHOTO_ANSWERED
            DmsConstants-->>Service: Return "Answered"
            Service->>Service: Set attachStatus = DmsConstants.PHOTO_ANSWERED
        end
        
        Service->>Service: Set attachment reportId = warrantyHotlineReport.getId()
        Service->>AttachmentRepo: save(hotlineReportAttachment)
        AttachmentRepo->>DB: INSERT INTO warranty_hotline_report_attachment<br/>(fileName, attachStatus, reportId)
        DB-->>AttachmentRepo: Attachment saved
        AttachmentRepo-->>Service: Attachment entity saved
    end
    
    Service-->>Controller: Return ApiResponse with success message
    Controller-->>Client: HTTP Response (200 OK)
```

---

## 3. Usage in Marketing Activity Status Controller - System Lookup Constant

This flow shows how **DmsConstants.SM_ACTIVITY_STATUS** is used in **MarketingActivityStatusController** to query system lookup values for marketing activity statuses.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as MarketingActivityStatusController
    participant DmsConstants as DmsConstants
    participant SysLookupRepo as SysLookupRepo
    participant DB as Database
    participant ApiResponse as ApiResponse

    %% Get Marketing Activity Status Flow
    Note over Client,ApiResponse: Get Marketing Activity Status List
    
    Client->>Controller: GET /api/salesandpresales/marketingActivityStatus
    
    Controller->>Controller: Create new ApiResponse object
    
    Controller->>DmsConstants: Access DmsConstants.SM_ACTIVITY_STATUS
    DmsConstants-->>Controller: Return "SM_ACTIVITY_STATUS"
    
    Controller->>SysLookupRepo: findByLookuptypecode(DmsConstants.SM_ACTIVITY_STATUS)
    Note over Controller: Pass constant value "SM_ACTIVITY_STATUS"<br/>to repository method
    
    SysLookupRepo->>DB: SELECT * FROM system_lookup<br/>WHERE lookuptypecode = 'SM_ACTIVITY_STATUS'
    DB->>DB: Execute query
    DB-->>SysLookupRepo: List of SystemLookUpEntity records
    Note over DB: Returns all lookup values<br/>with type code "SM_ACTIVITY_STATUS"
    
    SysLookupRepo-->>Controller: List of SystemLookUpEntity
    
    Controller->>ApiResponse: setResult(lookupList)
    Controller->>ApiResponse: setMessage("status get successfully.")
    Controller->>ApiResponse: setStatus(HttpStatus.OK.value())
    
    Controller-->>Client: ResponseEntity(ApiResponse)<br/>{ status: 200, message: "status get successfully.",<br/>result: [SystemLookUpEntity...] }
    
    Client->>Client: Display marketing activity statuses<br/>in dropdown or list

    %% Save Marketing Activity Status Flow
    Note over Client,ApiResponse: Save Marketing Activity Status
    
    Client->>Controller: POST /api/salesandpresales/marketingActivityStatus/saveMarketingActivityStatus<br/>(with SystemLookUpEntity in body)
    
    Controller->>Controller: Extract SystemLookUpEntity from request body
    Controller->>Controller: Note: Entity should have lookuptypecode = "SM_ACTIVITY_STATUS"
    
    Controller->>SysLookupRepo: save(systemLookUpEntity)
    SysLookupRepo->>DB: INSERT/UPDATE system_lookup<br/>(lookuptypecode, lookupcode, lookupvalue, ...)
    DB-->>SysLookupRepo: Entity saved
    SysLookupRepo-->>Controller: Saved SystemLookUpEntity
    
    Controller->>ApiResponse: setMessage("marketing activity status successfully saved.")
    Controller->>ApiResponse: setStatus(HttpStatus.OK.value())
    
    Controller-->>Client: ResponseEntity(ApiResponse)<br/>{ status: 200, message: "marketing activity status successfully saved." }
```

---

## 4. Constant Access Pattern - Compile-Time vs Runtime Access

This flow shows the **different patterns** of accessing constants and how they behave at compile-time vs runtime.

```mermaid
sequenceDiagram
    %% Participants
    participant Developer as Developer
    participant Compiler as Java Compiler
    participant SourceCode as Source Code
    participant Bytecode as Compiled Bytecode
    participant JVM as Java Virtual Machine
    participant ConstantPool as Constant Pool
    participant Service as Service Class

    %% Compile-Time Constant Resolution
    Note over Developer,Bytecode: Compile-Time Constant Resolution
    
    Developer->>SourceCode: Write code using DmsConstants.PHOTO_SUBMIT
    SourceCode->>Compiler: Compile source code
    
    Compiler->>Compiler: Resolve constant reference
    Compiler->>Compiler: Lookup DmsConstants.PHOTO_SUBMIT value
    Compiler->>Compiler: Find constant value = "Submitted"
    Compiler->>Compiler: Replace constant reference with literal value
    
    Compiler->>Bytecode: Generate bytecode with inline literal
    Note over Bytecode: Bytecode contains:<br/>LDC "Submitted"<br/>(instead of field reference)
    
    Bytecode-->>Compiler: Compilation complete
    Compiler-->>Developer: .class file generated

    %% Runtime Constant Access
    Note over JVM,Service: Runtime Constant Access
    
    JVM->>Bytecode: Load compiled class
    Bytecode->>ConstantPool: Store string literals in constant pool
    ConstantPool->>ConstantPool: Store "SM_ACTIVITY_STATUS"
    ConstantPool->>ConstantPool: Store "Submitted"
    ConstantPool->>ConstantPool: Store "Answered"
    
    Service->>Service: Execute code that uses constant
    Service->>ConstantPool: Access constant value
    ConstantPool-->>Service: Return string literal from pool
    
    Note over Service: Constant value retrieved<br/>from constant pool (fast access)

    %% Comparison with Variable
    Note over Service: Constant vs Variable Comparison
    
    Service->>Service: String status = hotlineReport.getStatus()
    Service->>ConstantPool: Access DmsConstants.PHOTO_SUBMIT
    ConstantPool-->>Service: Return "Submitted"
    Service->>Service: Compare: status.equals(DmsConstants.PHOTO_SUBMIT)
    Service->>Service: Result: true/false
    
    Note over Service: Constant provides type safety<br/>and prevents typos in string literals
```

---

## 5. Complete Flow - Warranty Hotline Report with Constant-Based Status Management

This flow shows the **complete end-to-end flow** of how constants are used throughout the warranty hotline report submission and status management process.

```mermaid
sequenceDiagram
    %% Participants
    participant User as End User
    participant UI as Frontend UI
    participant Controller as Warranty Hotline Report Controller
    participant Service as WarrantyHotlineReportServiceImpl
    participant DmsConstants as DmsConstants
    participant StorageService as StorageService
    participant AttachmentRepo as WarrantyHotlineReportAttachmentRepo
    participant ReportRepo as WarrantyHotlineReportRepo
    participant DB as Database

    %% Complete Warranty Hotline Report Flow
    Note over User,DB: Complete Warranty Hotline Report Submission Flow
    
    User->>UI: Fill warranty hotline report form<br/>Select status: "Submitted" or "Answered"<br/>Upload photo attachments
    
    UI->>Controller: POST /api/warranty/hotlineReport/submit<br/>{ reportData, status: "Submitted", files: [...] }
    
    Controller->>Service: submitHotlineReport(hotlineReport, multipartFileList)
    
    Service->>Service: Validate report data
    Service->>Service: Set audit fields (createdBy, createdDate)
    
    Service->>ReportRepo: save(hotlineReport)
    ReportRepo->>DB: INSERT INTO warranty_hotline_report
    DB-->>ReportRepo: Report saved with ID
    ReportRepo-->>Service: Saved WarrantyHotlineReport
    
    Service->>Service: Check if attachments exist and count <= 5
    
    loop For each attachment file
        Service->>Service: Create WarrantyHotlineReportAttachment
        
        Service->>Service: Get status from report: hotlineReport.getStatus()
        Note over Service: status = "Submitted" or "Answered"
        
        Service->>DmsConstants: Access DmsConstants.PHOTO_SUBMIT
        DmsConstants-->>Service: Return "Submitted"
        
        Service->>Service: if (status.equals(DmsConstants.PHOTO_SUBMIT))
        
        alt Status is "Submitted"
            Service->>DmsConstants: Access DmsConstants.PHOTO_SUBMIT
            DmsConstants-->>Service: Return "Submitted"
            
            Service->>Service: Build filename with constant:<br/>"wa_hotline_rpt_" + DmsConstants.PHOTO_SUBMIT + timestamp
            Service->>StorageService: store(file, filename)
            StorageService-->>Service: File stored
            
            Service->>Service: Set attachment properties:<br/>fileName = filename<br/>attachStatus = DmsConstants.PHOTO_SUBMIT
            
        else Status is "Answered"
            Service->>DmsConstants: Access DmsConstants.PHOTO_ANSWERED
            DmsConstants-->>Service: Return "Answered"
            
            Service->>Service: Build filename with constant:<br/>"wa_hotline_rpt_" + DmsConstants.PHOTO_ANSWERED + timestamp
            Service->>StorageService: store(file, filename)
            StorageService-->>Service: File stored
            
            Service->>Service: Set attachment properties:<br/>fileName = filename<br/>attachStatus = DmsConstants.PHOTO_ANSWERED
        end
        
        Service->>AttachmentRepo: save(attachment)
        AttachmentRepo->>DB: INSERT INTO warranty_hotline_report_attachment
        DB-->>AttachmentRepo: Attachment saved
        AttachmentRepo-->>Service: Saved attachment
    end
    
    Service->>Service: Create ApiResponse with success message
    Service-->>Controller: Return ApiResponse
    
    Controller-->>UI: HTTP 200 OK with success message
    UI->>User: Display success notification
    
    Note over User: User can view report with attachments<br/>Status determines attachment naming and status
```

---

## Summary

The **constant** module provides **centralized constant definitions** for the KUBOTA DMS:

- **DmsConstants Interface**:
  - Defines constant string values used across the application
  - `SM_ACTIVITY_STATUS`: Lookup type code for marketing activity statuses
  - `PHOTO_SUBMIT`: Status value for submitted photos ("Submitted")
  - `PHOTO_ANSWERED`: Status value for answered photos ("Answered")

- **Usage Patterns**:
  - **Status Comparison**: Used in `WarrantyHotlineReportServiceImpl` to determine photo attachment status and file naming
  - **System Lookup**: Used in `MarketingActivityStatusController` to query system lookup values by type code
  - **Type Safety**: Provides compile-time constant resolution and prevents typos in string literals

- **Benefits**:
  - **Centralized Management**: All constants defined in one place for easy maintenance
  - **Type Safety**: Compile-time checking prevents errors
  - **Performance**: Constants are stored in JVM constant pool for fast access
  - **Maintainability**: Changes to constant values only need to be made in one location

- **Constant Access**:
  - Constants are resolved at compile-time and stored in JVM constant pool
  - Access is fast and efficient (no field lookup overhead)
  - Values are inlined in bytecode for optimal performance

This module ensures **consistent use of string constants** across the application, reducing errors from typos and making it easier to maintain and update constant values throughout the codebase.

