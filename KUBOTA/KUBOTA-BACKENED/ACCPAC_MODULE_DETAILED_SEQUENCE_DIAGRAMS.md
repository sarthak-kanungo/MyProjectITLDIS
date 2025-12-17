# ACCPAC Module - Detailed Sequence Diagrams

This document contains comprehensive and detailed sequence diagrams for all flows within the ACCPAC Module of the KUBOTA-BACKENED system. The ACCPAC module handles integration with ACCPAC accounting system, including dealer master management, purchase order details, and channel finance invoice management.

## Module Overview

The ACCPAC module consists of:

- **3 Controllers**: 
  - `AccPacController` - Dealer Master operations
  - `AccpacPoDetailsController` - Purchase Order Details and Outstanding Status
  - `AccpacChannelFinanceController` - Channel Finance Invoice operations

- **3 Repositories**:
  - `AccPacDealerMasterRepository` - Dealer Master data access
  - `AccpacPoDetailsRepo` - PO Details and Outstanding data access
  - `InvoiceDetailsRepo` - Invoice Details data access

- **3 Main Domain Entities**:
  - `AccPacDealerMaster` - Dealer master information
  - `AccpacPoDetails` - Dealer outstanding and PO details
  - `AccpacChannelFinanceInvoice` - Channel finance invoice information

---

## Table of Contents

1. [Upload Dealer Master Excel Flow](#1-upload-dealer-master-excel-flow)
2. [Dealer Code Autocomplete Flow](#2-dealer-code-autocomplete-flow)
3. [Get Details by Dealer Code Flow](#3-get-details-by-dealer-code-flow)
4. [Get Dealer Type List Flow](#4-get-dealer-type-list-flow)
5. [Save ACCPAC PO Details Flow](#5-save-accpac-po-details-flow)
6. [Get Outstanding Status Flow](#6-get-outstanding-status-flow)
7. [Save Invoice Details Flow](#7-save-invoice-details-flow)

---

## 1. Upload Dealer Master Excel Flow

This diagram shows the complete flow for uploading dealer master data from an Excel file, including file parsing, data mapping, and database persistence.

```mermaid
sequenceDiagram
    participant Client as "Client Application<br/>(Frontend)"
    participant SpringSecurity as "Spring Security<br/>Filter Chain"
    participant AccPacController as "AccPacController<br/>@RestController<br/>@RequestMapping(/api/accpac)"
    participant Poiji as "Poiji Library<br/>(Excel Parser)"
    participant AccPacDealerMaster as "AccPacDealerMaster<br/>@Entity"
    participant DealerMasterRepo as "AccPacDealerMasterRepository<br/>JpaRepository"
    participant JPA as "JPA/Hibernate<br/>EntityManager"
    participant Database as "SQL Server Database<br/>(accpac_dealer_master table)"
    participant ApiResponse as "ApiResponse<br/>(Response Wrapper)"

    Client->>SpringSecurity: POST /api/accpac/uploadDealerMasterExcel<br/>Content-Type: multipart/form-data<br/>Authorization: Bearer token<br/>Body: multipartFile (Excel .xls)
    
    SpringSecurity->>SpringSecurity: Validate JWT token<br/>Check authentication & authorization
    
    alt Authentication Failed
        SpringSecurity-->>Client: 401 Unauthorized
    end
    
    SpringSecurity->>AccPacController: Forward request to controller
    
    AccPacController->>AccPacController: @PostMapping("/uploadDealerMasterExcel")<br/>@RequestParam MultipartFile multipartFile
    
    AccPacController->>AccPacController: Validate multipartFile<br/>- Check if file is not null<br/>- Check file extension (.xls)<br/>- Check file size
    
    alt File Validation Failed
        AccPacController->>ApiResponse: Create error response<br/>(status: 400, message: "Invalid file")
        AccPacController-->>Client: ResponseEntity 400 Bad Request
    end
    
    AccPacController->>AccPacController: multipartFile.getInputStream()<br/>Extract InputStream from MultipartFile
    
    AccPacController->>Poiji: Poiji.fromExcel(<br/>  InputStream in,<br/>  PoijiExcelType.XLS,<br/>  AccPacDealerMaster.class,<br/>  PoijiOptions with headerStart(0)<br/>)
    
    Poiji->>Poiji: Read Excel file stream<br/>Parse XLS format
    
    Poiji->>Poiji: Read header row (row 0)<br/>Map column names
    
    loop For each data row in Excel
        Poiji->>Poiji: Read row data
        
        Poiji->>AccPacDealerMaster: Create new AccPacDealerMaster instance
        
        Poiji->>AccPacDealerMaster: Map Excel columns using @ExcelCell annotations:<br/>- @ExcelCell(0) → dealerCode<br/>- @ExcelCell(1) → dealerName<br/>- @ExcelCell(2) → status<br/>- @ExcelCell(3) → dealerFirmType<br/>- @ExcelCell(4) → gstNo<br/>- @ExcelCell(5) → panNo<br/>- @ExcelCell(6) → emailId<br/>- @ExcelCell(7-10) → address1-4<br/>- @ExcelCell(11) → pinCode<br/>- @ExcelCell(12) → tehsil<br/>- @ExcelCell(13) → city<br/>- @ExcelCell(14) → state<br/>- @ExcelCell(15) → country<br/>- @ExcelCell(16) → dealerType<br/>- @ExcelCell(17) → dealerCreditLimit
        
        AccPacDealerMaster->>AccPacDealerMaster: Set syncDate = new Date()<br/>(current timestamp)
        
        AccPacDealerMaster-->>Poiji: Mapped entity object
    end
    
    Poiji-->>AccPacController: List<AccPacDealerMaster> objects<br/>(All parsed dealer master records)
    
    AccPacController->>DealerMasterRepo: saveAll(accPacDealerMasters)<br/>JpaRepository.saveAll()
    
    DealerMasterRepo->>JPA: EntityManager.persist()<br/>Batch insert operation
    
    JPA->>Database: BEGIN TRANSACTION
    
    loop For each AccPacDealerMaster entity
        JPA->>Database: INSERT INTO accpac_dealer_master<br/>(dealer_code, dealer_name, status,<br/>dealer_firm_type, gst_no, pan_no,<br/>email_id, address1, address2,<br/>address3, address4, pin_code,<br/>tehsil, city, state, country,<br/>dealer_type, dealer_credit_limit,<br/>sync_date)<br/>VALUES (?, ?, ?, ...)
        
        Database->>Database: Generate ID (IDENTITY column)<br/>Insert record
    end
    
    Database-->>JPA: INSERT results with generated IDs
    
    JPA->>Database: COMMIT TRANSACTION
    
    Database-->>JPA: Transaction committed
    
    JPA-->>DealerMasterRepo: List<AccPacDealerMaster> with IDs
    
    DealerMasterRepo-->>AccPacController: Saved entities with generated IDs
    
    AccPacController->>ApiResponse: Create ApiResponse<br/>apiResponse.setMessage("Dealer Master Uploaded Successfully")<br/>apiResponse.setStatus(HttpStatus.OK.value())<br/>apiResponse.setResult(null)
    
    AccPacController->>AccPacController: ResponseEntity.ok(apiResponse)
    
    AccPacController-->>Client: HTTP 200 OK<br/>Content-Type: application/json<br/>{<br/>  "status": 200,<br/>  "message": "Dealer Master Uploaded Successfully",<br/>  "result": null<br/>}
    
    Client->>Client: Display success message to user<br/>Refresh dealer master list
```

---

## 2. Dealer Code Autocomplete Flow

This diagram shows the flow for dealer code autocomplete functionality using stored procedures.

```mermaid
sequenceDiagram
    participant Client as "Client Application<br/>(Frontend)"
    participant SpringSecurity as "Spring Security<br/>Filter Chain"
    participant AccPacController as "AccPacController<br/>@RestController<br/>@RequestMapping(/api/accpac)"
    participant DealerMasterRepo as "AccPacDealerMasterRepository<br/>JpaRepository"
    participant JPA as "JPA/Hibernate<br/>EntityManager"
    participant Database as "SQL Server Database"
    participant StoredProcedure as "sp_accpac_dealer_master_dealer_code_auto<br/>(Stored Procedure)"
    participant ApiResponse as "ApiResponse<br/>(Response Wrapper)"

    Client->>SpringSecurity: GET /api/accpac/dealerCodeAuto?dealerCode=DLR<br/>Authorization: Bearer token
    
    SpringSecurity->>SpringSecurity: Validate JWT token<br/>Check authentication & authorization
    
    alt Authentication Failed
        SpringSecurity-->>Client: 401 Unauthorized
    end
    
    SpringSecurity->>AccPacController: Forward request to controller
    
    AccPacController->>AccPacController: @GetMapping("/dealerCodeAuto")<br/>@RequestParam("dealerCode") String dealerCode
    
    AccPacController->>AccPacController: Extract and validate dealerCode parameter<br/>- Check if not null<br/>- Trim whitespace
    
    AccPacController->>DealerMasterRepo: findByDealerCodeContaining(dealerCode)<br/>@Query with stored procedure call
    
    DealerMasterRepo->>JPA: Execute native query:<br/>CALL sp_accpac_dealer_master_dealer_code_auto(:dealerCode)
    
    JPA->>Database: Prepare stored procedure call<br/>Set parameter: dealerCode = "DLR"
    
    Database->>StoredProcedure: Execute sp_accpac_dealer_master_dealer_code_auto<br/>@dealerCode = 'DLR'
    
    StoredProcedure->>StoredProcedure: Query logic:<br/>SELECT dealer_code, dealer_name, ...<br/>FROM accpac_dealer_master<br/>WHERE dealer_code LIKE '%DLR%'<br/>ORDER BY dealer_code<br/>LIMIT (if applicable)
    
    StoredProcedure->>Database: Execute SELECT query
    
    Database->>Database: Search dealer_code column<br/>Find matching records with LIKE pattern
    
    Database-->>StoredProcedure: Result set:<br/>[{dealer_code: "DLR001", dealer_name: "..."},<br/> {dealer_code: "DLR002", dealer_name: "..."},<br/> ...]
    
    StoredProcedure-->>Database: Return result set as Map<String, Object>
    
    Database-->>JPA: List<Map<String, Object>><br/>(Matching dealer codes and details)
    
    JPA-->>DealerMasterRepo: List<Map<String, Object>>
    
    DealerMasterRepo-->>AccPacController: List<Map<String, Object>> code
    
    AccPacController->>ApiResponse: Create ApiResponse<br/>apiResponse.setMessage("dealer code get successfully")<br/>apiResponse.setStatus(HttpStatus.OK.value())<br/>apiResponse.setResult(code)
    
    AccPacController->>AccPacController: ResponseEntity.ok(apiResponse)
    
    AccPacController-->>Client: HTTP 200 OK<br/>Content-Type: application/json<br/>{<br/>  "status": 200,<br/>  "message": "dealer code get successfully",<br/>  "result": [<br/>    {<br/>      "dealer_code": "DLR001",<br/>      "dealer_name": "Dealer Name 1",<br/>      ...<br/>    },<br/>    ...<br/>  ]<br/>}
    
    Client->>Client: Display autocomplete dropdown<br/>Show matching dealer codes<br/>User selects from suggestions
```

---

## 3. Get Details by Dealer Code Flow

This diagram shows the flow for retrieving complete dealer details by dealer code.

```mermaid
sequenceDiagram
    participant Client as "Client Application<br/>(Frontend)"
    participant SpringSecurity as "Spring Security<br/>Filter Chain"
    participant AccPacController as "AccPacController<br/>@RestController<br/>@RequestMapping(/api/accpac)"
    participant DealerMasterRepo as "AccPacDealerMasterRepository<br/>JpaRepository"
    participant JPA as "JPA/Hibernate<br/>EntityManager"
    participant Database as "SQL Server Database"
    participant StoredProcedure as "sp_accpac_dealer_get_details_by_dealer_code<br/>(Stored Procedure)"
    participant ApiResponse as "ApiResponse<br/>(Response Wrapper)"

    Client->>SpringSecurity: GET /api/accpac/getDetailsByDealerCode?dealerCode=DLR001<br/>Authorization: Bearer token
    
    SpringSecurity->>SpringSecurity: Validate JWT token<br/>Check authentication & authorization
    
    alt Authentication Failed
        SpringSecurity-->>Client: 401 Unauthorized
    end
    
    SpringSecurity->>AccPacController: Forward request to controller
    
    AccPacController->>AccPacController: @GetMapping("/getDetailsByDealerCode")<br/>@RequestParam("dealerCode") String dealerCode
    
    AccPacController->>AccPacController: Extract and validate dealerCode parameter<br/>- Check if not null or empty<br/>- Trim whitespace
    
    alt dealerCode is null or empty
        AccPacController->>ApiResponse: Create error response<br/>(status: 400, message: "Dealer code required")
        AccPacController-->>Client: ResponseEntity 400 Bad Request
    end
    
    AccPacController->>DealerMasterRepo: getDetailsByDealerCode(dealerCode)<br/>@Query with stored procedure call
    
    DealerMasterRepo->>JPA: Execute native query:<br/>CALL sp_accpac_dealer_get_details_by_dealer_code(:dealerCode)
    
    JPA->>Database: Prepare stored procedure call<br/>Set parameter: dealerCode = "DLR001"
    
    Database->>StoredProcedure: Execute sp_accpac_dealer_get_details_by_dealer_code<br/>@dealerCode = 'DLR001'
    
    StoredProcedure->>StoredProcedure: Query logic:<br/>SELECT<br/>  dealer_code,<br/>  dealer_name,<br/>  status,<br/>  dealer_firm_type,<br/>  gst_no,<br/>  pan_no,<br/>  email_id,<br/>  address1, address2, address3, address4,<br/>  pin_code,<br/>  tehsil,<br/>  city,<br/>  state,<br/>  country,<br/>  dealer_type,<br/>  dealer_credit_limit,<br/>  sync_date<br/>FROM accpac_dealer_master<br/>WHERE dealer_code = 'DLR001'
    
    StoredProcedure->>Database: Execute SELECT query
    
    Database->>Database: Find exact match for dealer_code = 'DLR001'
    
    alt Dealer not found
        Database-->>StoredProcedure: Empty result set
        StoredProcedure-->>Database: Return empty list
        Database-->>JPA: Empty List<Map<String, Object>>
        JPA-->>DealerMasterRepo: Empty list
        DealerMasterRepo-->>AccPacController: Empty list
        AccPacController->>ApiResponse: Create ApiResponse<br/>(result: empty list)
    else Dealer found
        Database-->>StoredProcedure: Single row result set<br/>{<br/>  dealer_code: "DLR001",<br/>  dealer_name: "ABC Dealer",<br/>  status: "Active",<br/>  dealer_firm_type: "Partnership",<br/>  gst_no: "27AAAAA0000A1Z5",<br/>  pan_no: "AAAAA0000A",<br/>  email_id: "dealer@example.com",<br/>  address1: "123 Main St",<br/>  address2: "Building A",<br/>  address3: "Floor 2",<br/>  address4: "Suite 201",<br/>  pin_code: "400001",<br/>  tehsil: "Mumbai",<br/>  city: "Mumbai",<br/>  state: "Maharashtra",<br/>  country: "India",<br/>  dealer_type: "Authorized",<br/>  dealer_credit_limit: "5000000",<br/>  sync_date: "2024-01-15 10:30:00"<br/>}
        
        StoredProcedure-->>Database: Return result as Map<String, Object>
        
        Database-->>JPA: List<Map<String, Object>><br/>(Single dealer details)
        
        JPA-->>DealerMasterRepo: List<Map<String, Object>>
        
        DealerMasterRepo-->>AccPacController: List<Map<String, Object>> code
    end
    
    AccPacController->>ApiResponse: Create ApiResponse<br/>apiResponse.setMessage("Details Get Successfully")<br/>apiResponse.setStatus(HttpStatus.OK.value())<br/>apiResponse.setResult(code)
    
    AccPacController->>AccPacController: ResponseEntity.ok(apiResponse)
    
    AccPacController-->>Client: HTTP 200 OK<br/>Content-Type: application/json<br/>{<br/>  "status": 200,<br/>  "message": "Details Get Successfully",<br/>  "result": [<br/>    {<br/>      "dealer_code": "DLR001",<br/>      "dealer_name": "ABC Dealer",<br/>      "status": "Active",<br/>      ...<br/>    }<br/>  ]<br/>}
    
    Client->>Client: Display dealer details form<br/>Populate fields with dealer information
```

---

## 4. Get Dealer Type List Flow

This diagram shows the flow for retrieving the list of distinct dealer types.

```mermaid
sequenceDiagram
    participant Client as "Client Application<br/>(Frontend)"
    participant SpringSecurity as "Spring Security<br/>Filter Chain"
    participant AccPacController as "AccPacController<br/>@RestController<br/>@RequestMapping(/api/accpac)"
    participant DealerMasterRepo as "AccPacDealerMasterRepository<br/>JpaRepository"
    participant JPA as "JPA/Hibernate<br/>EntityManager"
    participant Database as "SQL Server Database"
    participant StoredProcedure as "sp_acc_pac_dealer_dealer_type_list<br/>(Stored Procedure)"
    participant ApiResponse as "ApiResponse<br/>(Response Wrapper)"

    Client->>SpringSecurity: GET /api/accpac/dealerTypeList<br/>Authorization: Bearer token
    
    SpringSecurity->>SpringSecurity: Validate JWT token<br/>Check authentication & authorization
    
    alt Authentication Failed
        SpringSecurity-->>Client: 401 Unauthorized
    end
    
    SpringSecurity->>AccPacController: Forward request to controller
    
    AccPacController->>AccPacController: @GetMapping("/dealerTypeList")<br/>(No parameters required)
    
    AccPacController->>DealerMasterRepo: getDealerType()<br/>@Query with stored procedure call
    
    DealerMasterRepo->>JPA: Execute native query:<br/>CALL sp_acc_pac_dealer_dealer_type_list()
    
    JPA->>Database: Prepare stored procedure call<br/>(No input parameters)
    
    Database->>StoredProcedure: Execute sp_acc_pac_dealer_dealer_type_list()
    
    StoredProcedure->>StoredProcedure: Query logic:<br/>SELECT DISTINCT dealer_type<br/>FROM accpac_dealer_master<br/>WHERE dealer_type IS NOT NULL<br/>ORDER BY dealer_type
    
    StoredProcedure->>Database: Execute SELECT DISTINCT query
    
    Database->>Database: Scan accpac_dealer_master table<br/>Extract distinct dealer_type values
    
    Database-->>StoredProcedure: Result set:<br/>[{dealer_type: "Authorized"},<br/> {dealer_type: "Sub-Dealer"},<br/> {dealer_type: "Service Center"},<br/> ...]
    
    StoredProcedure-->>Database: Return result set as Map<String, Object>
    
    Database-->>JPA: List<Map<String, Object>><br/>(Distinct dealer types)
    
    JPA-->>DealerMasterRepo: List<Map<String, Object>>
    
    DealerMasterRepo-->>AccPacController: List<Map<String, Object>> type
    
    AccPacController->>ApiResponse: Create ApiResponse<br/>apiResponse.setMessage("Dealer Type Get Successfully")<br/>apiResponse.setStatus(HttpStatus.OK.value())<br/>apiResponse.setResult(type)
    
    AccPacController->>AccPacController: ResponseEntity.ok(apiResponse)
    
    AccPacController-->>Client: HTTP 200 OK<br/>Content-Type: application/json<br/>{<br/>  "status": 200,<br/>  "message": "Dealer Type Get Successfully",<br/>  "result": [<br/>    {<br/>      "dealer_type": "Authorized"<br/>    },<br/>    {<br/>      "dealer_type": "Sub-Dealer"<br/>    },<br/>    {<br/>      "dealer_type": "Service Center"<br/>    }<br/>  ]<br/>}
    
    Client->>Client: Populate dealer type dropdown<br/>Display list of available dealer types
```

---

## 5. Save ACCPAC PO Details Flow

This diagram shows the complete flow for saving ACCPAC purchase order details (dealer outstanding information).

```mermaid
sequenceDiagram
    participant Client as "Client Application<br/>(Frontend)"
    participant SpringSecurity as "Spring Security<br/>Filter Chain"
    participant AccpacPoDetailsController as "AccpacPoDetailsController<br/>@RestController<br/>@RequestMapping(/api/accpac)"
    participant AccpacPoDetails as "AccpacPoDetails<br/>@Entity<br/>@Table(accpac_dealer_outstanding)"
    participant AccpacPoDetailsRepo as "AccpacPoDetailsRepo<br/>JpaRepository"
    participant JPA as "JPA/Hibernate<br/>EntityManager"
    participant Database as "SQL Server Database<br/>(accpac_dealer_outstanding table)"
    participant ApiResponse as "ApiResponse<br/>(Response Wrapper)"
    participant Validation as "Bean Validation<br/>(@NotNull, @NotBlank)"

    Client->>SpringSecurity: POST /api/accpac/saveaccpacpodetails<br/>Content-Type: application/json<br/>Authorization: Bearer token<br/>Body: {<br/>  "dealerCode": "DLR001",<br/>  "dealer_id": 123,<br/>  "totalCreditLimit": 5000000.00,<br/>  "availableLimit": 3000000.00,<br/>  "totalOs": 2000000.00,<br/>  "currentOs": 500000.00,<br/>  "os0To30Days": 300000.00,<br/>  "os31To60Days": 400000.00,<br/>  "os61To90Days": 500000.00,<br/>  "os90Days": 300000.00,<br/>  "orderUnderProcess": 100000.00,<br/>  "pendingOrder": 50000.00,<br/>  "pendingPayment": 25000.00<br/>}
    
    SpringSecurity->>SpringSecurity: Validate JWT token<br/>Check authentication & authorization
    
    alt Authentication Failed
        SpringSecurity-->>Client: 401 Unauthorized
    end
    
    SpringSecurity->>AccpacPoDetailsController: Forward request to controller
    
    AccpacPoDetailsController->>AccpacPoDetailsController: @PostMapping("/saveaccpacpodetails")<br/>@RequestBody AccpacPoDetails accpacPoDetails
    
    AccpacPoDetailsController->>AccpacPoDetailsController: Spring deserializes JSON to AccpacPoDetails object<br/>(Jackson ObjectMapper)
    
    AccpacPoDetailsController->>Validation: Validate AccpacPoDetails entity<br/>Check @NotNull constraints:<br/>- dealerCode (required)<br/>- totalCreditLimit (required)<br/>- availableLimit (required)<br/>- totalOs (required)<br/>- currentOs (required)<br/>- os0To30Days (required)<br/>- os31To60Days (required)<br/>- os61To90Days (required)<br/>- os90Days (required)
    
    alt Validation Failed
        Validation-->>AccpacPoDetailsController: ValidationException<br/>(List of constraint violations)
        AccpacPoDetailsController->>ApiResponse: Create error response<br/>(status: 400, message: "Validation failed")
        AccpacPoDetailsController-->>Client: ResponseEntity 400 Bad Request<br/>{<br/>  "status": 400,<br/>  "message": "Validation failed: dealerCode is required"<br/>}
    end
    
    Validation-->>AccpacPoDetailsController: Validation passed
    
    AccpacPoDetailsController->>AccpacPoDetails: Set syncDate = new Date()<br/>(if not already set)
    
    AccpacPoDetailsController->>AccpacPoDetailsRepo: save(accpacPoDetails)<br/>JpaRepository.save()
    
    AccpacPoDetailsRepo->>JPA: EntityManager.persist(accpacPoDetails)<br/>or EntityManager.merge() if ID exists
    
    JPA->>JPA: Check if entity has ID<br/>(id == null)
    
    alt New Entity (id == null)
        JPA->>Database: BEGIN TRANSACTION
        
        JPA->>Database: INSERT INTO accpac_dealer_outstanding<br/>(dealer_code, dealer_id,<br/>total_credit_limit, available_limit,<br/>total_os, current_os,<br/>os_0_to_30_days, os_31_to_60_days,<br/>os_61_to_90_days, os_90_days,<br/>order_under_process, pending_order,<br/>pending_payment, sync_date)<br/>VALUES<br/>(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        
        Database->>Database: Generate ID (IDENTITY column)<br/>Insert record<br/>Return generated ID
        
        Database-->>JPA: INSERT result with generated ID
        
        JPA->>Database: COMMIT TRANSACTION
    else Existing Entity (id != null)
        JPA->>Database: BEGIN TRANSACTION
        
        JPA->>Database: UPDATE accpac_dealer_outstanding<br/>SET dealer_code = ?,<br/>    dealer_id = ?,<br/>    total_credit_limit = ?,<br/>    available_limit = ?,<br/>    total_os = ?,<br/>    current_os = ?,<br/>    os_0_to_30_days = ?,<br/>    os_31_to_60_days = ?,<br/>    os_61_to_90_days = ?,<br/>    os_90_days = ?,<br/>    order_under_process = ?,<br/>    pending_order = ?,<br/>    pending_payment = ?,<br/>    sync_date = ?<br/>WHERE id = ?
        
        Database->>Database: Update record<br/>Return affected rows
        
        Database-->>JPA: UPDATE result
        
        JPA->>Database: COMMIT TRANSACTION
    end
    
    Database-->>JPA: Transaction committed
    
    JPA->>AccpacPoDetails: Set generated ID (if new entity)<br/>Refresh entity state
    
    JPA-->>AccpacPoDetailsRepo: AccpacPoDetails entity (saved/updated)
    
    AccpacPoDetailsRepo-->>AccpacPoDetailsController: AccpacPoDetails entity with ID
    
    AccpacPoDetailsController->>ApiResponse: Create ApiResponse<br/>apiResponse.setMessage("accpac po details successfully saved.")<br/>apiResponse.setStatus(HttpStatus.OK.value())<br/>apiResponse.setResult(null)
    
    AccpacPoDetailsController->>AccpacPoDetailsController: ResponseEntity.ok(apiResponse)
    
    AccpacPoDetailsController-->>Client: HTTP 200 OK<br/>Content-Type: application/json<br/>{<br/>  "status": 200,<br/>  "message": "accpac po details successfully saved.",<br/>  "result": null<br/>}
    
    Client->>Client: Display success message<br/>Refresh outstanding status display
```

---

## 6. Get Outstanding Status Flow

This diagram shows the flow for retrieving outstanding status for a dealer or purchase order using stored procedures.

```mermaid
sequenceDiagram
    participant Client as "Client Application<br/>(Frontend)"
    participant SpringSecurity as "Spring Security<br/>Filter Chain"
    participant AccpacPoDetailsController as "AccpacPoDetailsController<br/>@RestController<br/>@RequestMapping(/api/accpac)"
    participant AccpacPoDetailsRepo as "AccpacPoDetailsRepo<br/>JpaRepository"
    participant JPA as "JPA/Hibernate<br/>EntityManager"
    participant Database as "SQL Server Database"
    participant StoredProcedure as "sp_accpac_get_dealer_os<br/>(Stored Procedure)"
    participant ApiResponse as "ApiResponse<br/>(Response Wrapper)"

    Client->>SpringSecurity: GET /api/accpac/getOsStatus?dealerCode=DLR001&poid=12345<br/>Authorization: Bearer token
    
    SpringSecurity->>SpringSecurity: Validate JWT token<br/>Check authentication & authorization
    
    alt Authentication Failed
        SpringSecurity-->>Client: 401 Unauthorized
    end
    
    SpringSecurity->>AccpacPoDetailsController: Forward request to controller
    
    AccpacPoDetailsController->>AccpacPoDetailsController: @GetMapping("/getOsStatus")<br/>@RequestParam(required=false) String dealerCode<br/>@RequestParam(required=false) String poid
    
    AccpacPoDetailsController->>AccpacPoDetailsController: Extract parameters:<br/>dealerCode = "DLR001" (or null)<br/>poid = "12345" (or null)
    
    AccpacPoDetailsController->>AccpacPoDetailsController: Normalize parameters:<br/>if (dealerCode == null || dealerCode.equals("") || dealerCode.equals("null"))<br/>  dealerCode = null<br/>if (poid == null || poid.equals("") || poid.equals("null"))<br/>  poid = null
    
    AccpacPoDetailsController->>AccpacPoDetailsController: Convert poid to Long (if not null):<br/>Long poidLong = (poid == null ? null : Long.valueOf(poid))
    
    AccpacPoDetailsController->>AccpacPoDetailsRepo: getOsStatus(dealerCode, poidLong)<br/>@Query with stored procedure call
    
    AccpacPoDetailsRepo->>JPA: Execute native query:<br/>CALL sp_accpac_get_dealer_os(:dealerCode, :poid)
    
    JPA->>Database: Prepare stored procedure call<br/>Set parameters:<br/>@dealerCode = 'DLR001' (or NULL)<br/>@poid = 12345 (or NULL)
    
    Database->>StoredProcedure: Execute sp_accpac_get_dealer_os<br/>@dealerCode = 'DLR001'<br/>@poid = 12345
    
    StoredProcedure->>StoredProcedure: Query logic:<br/>SELECT<br/>  dealer_code,<br/>  dealer_id,<br/>  total_credit_limit,<br/>  available_limit,<br/>  total_os,<br/>  current_os,<br/>  os_0_to_30_days,<br/>  os_31_to_60_days,<br/>  os_61_to_90_days,<br/>  os_90_days,<br/>  order_under_process,<br/>  pending_order,<br/>  pending_payment,<br/>  sync_date<br/>FROM accpac_dealer_outstanding<br/>WHERE (dealer_code = @dealerCode OR @dealerCode IS NULL)<br/>  AND (poid = @poid OR @poid IS NULL)
    
    StoredProcedure->>Database: Execute SELECT query with conditional WHERE clause
    
    Database->>Database: Find matching records based on:<br/>- dealerCode match (if provided)<br/>- poid match (if provided)<br/>- Both conditions (if both provided)
    
    alt No records found
        Database-->>StoredProcedure: Empty result set
        StoredProcedure-->>Database: Return empty map or null
        Database-->>JPA: null or empty Map<String, Object>
        JPA-->>AccpacPoDetailsRepo: null or empty map
        AccpacPoDetailsRepo-->>AccpacPoDetailsController: null or empty map
    else Records found
        Database-->>StoredProcedure: Result set (single row expected):<br/>{<br/>  dealer_code: "DLR001",<br/>  dealer_id: 123,<br/>  total_credit_limit: 5000000.00,<br/>  available_limit: 3000000.00,<br/>  total_os: 2000000.00,<br/>  current_os: 500000.00,<br/>  os_0_to_30_days: 300000.00,<br/>  os_31_to_60_days: 400000.00,<br/>  os_61_to_90_days: 500000.00,<br/>  os_90_days: 300000.00,<br/>  order_under_process: 100000.00,<br/>  pending_order: 50000.00,<br/>  pending_payment: 25000.00,<br/>  sync_date: "2024-01-15 10:30:00"<br/>}
        
        StoredProcedure-->>Database: Return result as Map<String, Object>
        
        Database-->>JPA: Map<String, Object><br/>(Outstanding status details)
        
        JPA-->>AccpacPoDetailsRepo: Map<String, Object>
        
        AccpacPoDetailsRepo-->>AccpacPoDetailsController: Map<String, Object>
    end
    
    AccpacPoDetailsController->>ApiResponse: Create ApiResponse<br/>apiResponse.setMessage("outstanding details get successfully.")<br/>apiResponse.setStatus(HttpStatus.OK.value())<br/>apiResponse.setResult(outstandingStatusMap)
    
    AccpacPoDetailsController->>AccpacPoDetailsController: ResponseEntity.ok(apiResponse)
    
    AccpacPoDetailsController-->>Client: HTTP 200 OK<br/>Content-Type: application/json<br/>{<br/>  "status": 200,<br/>  "message": "outstanding details get successfully.",<br/>  "result": {<br/>    "dealer_code": "DLR001",<br/>    "dealer_id": 123,<br/>    "total_credit_limit": 5000000.00,<br/>    "available_limit": 3000000.00,<br/>    "total_os": 2000000.00,<br/>    "current_os": 500000.00,<br/>    "os_0_to_30_days": 300000.00,<br/>    "os_31_to_60_days": 400000.00,<br/>    "os_61_to_90_days": 500000.00,<br/>    "os_90_days": 300000.00,<br/>    "order_under_process": 100000.00,<br/>    "pending_order": 50000.00,<br/>    "pending_payment": 25000.00,<br/>    "sync_date": "2024-01-15 10:30:00"<br/>  }<br/>}
    
    Client->>Client: Display outstanding status<br/>Show credit limits, OS amounts<br/>Display aging buckets (0-30, 31-60, 61-90, 90+ days)
```

---

## 7. Save Invoice Details Flow

This diagram shows the complete flow for saving channel finance invoice details.

```mermaid
sequenceDiagram
    participant Client as "Client Application<br/>(Frontend)"
    participant SpringSecurity as "Spring Security<br/>Filter Chain"
    participant AccpacChannelFinanceController as "AccpacChannelFinanceController<br/>@RestController<br/>@RequestMapping(/api/accpac/invoiceDetails)"
    participant AccpacChannelFinanceInvoice as "AccpacChannelFinanceInvoice<br/>@Entity<br/>@Table(accpac_channel_finance_invoice)"
    participant InvoiceDetailsRepo as "InvoiceDetailsRepo<br/>JpaRepository"
    participant JPA as "JPA/Hibernate<br/>EntityManager"
    participant Database as "SQL Server Database<br/>(accpac_channel_finance_invoice table)"
    participant ApiResponse as "ApiResponse<br/>(Response Wrapper)"
    participant Validation as "Bean Validation<br/>(@NotNull)"

    Client->>SpringSecurity: POST /api/accpac/invoiceDetails/saveInvoiceDetails<br/>Content-Type: application/json<br/>Authorization: Bearer token<br/>Body: {<br/>  "dealerCode": "DLR001",<br/>  "invoiceDate": "15-01-2024",<br/>  "invoiceAmount": 500000.00,<br/>  "totalUtilisedAmount": 0.0,<br/>  "invoiceNumber": "INV-2024-001",<br/>  "status": "pending"<br/>}
    
    SpringSecurity->>SpringSecurity: Validate JWT token<br/>Check authentication & authorization
    
    alt Authentication Failed
        SpringSecurity-->>Client: 401 Unauthorized
    end
    
    SpringSecurity->>AccpacChannelFinanceController: Forward request to controller
    
    AccpacChannelFinanceController->>AccpacChannelFinanceController: @PostMapping("/saveInvoiceDetails")<br/>@RequestBody AccpacChannelFinanceInvoice accpacChannelFinanceInvoice
    
    AccpacChannelFinanceController->>AccpacChannelFinanceController: Spring deserializes JSON to AccpacChannelFinanceInvoice object<br/>(Jackson ObjectMapper)<br/>Parse invoiceDate from "dd-MM-yyyy" format
    
    AccpacChannelFinanceController->>Validation: Validate AccpacChannelFinanceInvoice entity<br/>Check @NotNull constraints:<br/>- dealerCode (required)<br/>- invoiceDate (optional but validated if present)<br/>- invoiceAmount (optional but validated if present)
    
    alt Validation Failed
        Validation-->>AccpacChannelFinanceController: ValidationException<br/>(List of constraint violations)
        AccpacChannelFinanceController->>ApiResponse: Create error response<br/>(status: 400, message: "Validation failed")
        AccpacChannelFinanceController-->>Client: ResponseEntity 400 Bad Request<br/>{<br/>  "status": 400,<br/>  "message": "Validation failed: dealerCode is required"<br/>}
    end
    
    Validation-->>AccpacChannelFinanceController: Validation passed
    
    AccpacChannelFinanceController->>AccpacChannelFinanceInvoice: Set default values if not provided:<br/>- totalUtilisedAmount = 0.0 (if null)<br/>- status = "pending" (if null or empty)<br/>- syncDate = new Date() (if not set)
    
    AccpacChannelFinanceController->>InvoiceDetailsRepo: save(accpacChannelFinanceInvoice)<br/>JpaRepository.save()
    
    InvoiceDetailsRepo->>JPA: EntityManager.persist(accpacChannelFinanceInvoice)<br/>or EntityManager.merge() if ID exists
    
    JPA->>JPA: Check if entity has ID<br/>(id == null)
    
    alt New Entity (id == null)
        JPA->>Database: BEGIN TRANSACTION
        
        JPA->>Database: INSERT INTO accpac_channel_finance_invoice<br/>(dealer_code, invoice_date,<br/>invoice_amount, total_utilised_amount,<br/>invoice_number, status, sync_date)<br/>VALUES<br/>(?, ?, ?, ?, ?, ?, ?)
        
        Database->>Database: Generate ID (IDENTITY column)<br/>Insert record<br/>Return generated ID
        
        Database-->>JPA: INSERT result with generated ID
        
        JPA->>Database: COMMIT TRANSACTION
    else Existing Entity (id != null)
        JPA->>Database: BEGIN TRANSACTION
        
        JPA->>Database: UPDATE accpac_channel_finance_invoice<br/>SET dealer_code = ?,<br/>    invoice_date = ?,<br/>    invoice_amount = ?,<br/>    total_utilised_amount = ?,<br/>    invoice_number = ?,<br/>    status = ?,<br/>    sync_date = ?<br/>WHERE id = ?
        
        Database->>Database: Update record<br/>Return affected rows
        
        Database-->>JPA: UPDATE result
        
        JPA->>Database: COMMIT TRANSACTION
    end
    
    Database-->>JPA: Transaction committed
    
    JPA->>AccpacChannelFinanceInvoice: Set generated ID (if new entity)<br/>Refresh entity state
    
    JPA-->>InvoiceDetailsRepo: AccpacChannelFinanceInvoice entity (saved/updated)
    
    InvoiceDetailsRepo-->>AccpacChannelFinanceController: AccpacChannelFinanceInvoice entity with ID
    
    AccpacChannelFinanceController->>ApiResponse: Create ApiResponse<br/>apiResponse.setMessage("invoice details successfully saved.")<br/>apiResponse.setStatus(HttpStatus.OK.value())<br/>apiResponse.setResult(null)
    
    AccpacChannelFinanceController->>AccpacChannelFinanceController: ResponseEntity.ok(apiResponse)
    
    AccpacChannelFinanceController-->>Client: HTTP 200 OK<br/>Content-Type: application/json<br/>{<br/>  "status": 200,<br/>  "message": "invoice details successfully saved.",<br/>  "result": null<br/>}
    
    Client->>Client: Display success message<br/>Refresh invoice list<br/>Update invoice status display
```

---

## Summary

The ACCPAC Module provides comprehensive integration with the ACCPAC accounting system through the following functionalities:

### 1. **Dealer Master Management** (`AccPacController`)
   - **Upload Dealer Master Excel**: Bulk import dealer master data from Excel files using Poiji library
   - **Dealer Code Autocomplete**: Search and autocomplete dealer codes using stored procedures
   - **Get Details by Dealer Code**: Retrieve complete dealer information by dealer code
   - **Get Dealer Type List**: Fetch distinct dealer types for dropdowns

### 2. **Purchase Order Details Management** (`AccpacPoDetailsController`)
   - **Save ACCPAC PO Details**: Persist dealer outstanding information including credit limits, OS amounts, and aging buckets
   - **Get Outstanding Status**: Retrieve outstanding status for dealers or purchase orders using stored procedures with optional filtering

### 3. **Channel Finance Invoice Management** (`AccpacChannelFinanceController`)
   - **Save Invoice Details**: Store channel finance invoice information with default status and utilization tracking

### Technical Architecture

- **Framework**: Spring Boot 2.3.3.RELEASE with Spring Data JPA
- **Database**: Microsoft SQL Server with stored procedures for complex queries
- **Security**: Spring Security with JWT token authentication
- **Data Access**: JPA/Hibernate with custom repositories and native queries
- **Excel Processing**: Poiji library for Excel file parsing
- **Validation**: Bean Validation (JSR-303) with @NotNull constraints
- **Response Format**: Standardized ApiResponse wrapper for all API responses
- **Date Handling**: Jackson JSON serialization with custom date formats

### Key Features

1. **Stored Procedures**: Used for complex queries and business logic (dealer code search, outstanding calculations)
2. **Batch Operations**: Bulk insert support for Excel uploads
3. **Flexible Filtering**: Optional parameters for outstanding status queries
4. **Default Values**: Automatic setting of syncDate and default status values
5. **Error Handling**: Validation and error responses for invalid inputs
6. **Transaction Management**: Automatic transaction handling by Spring Data JPA

### Database Tables

- `accpac_dealer_master`: Dealer master information
- `accpac_dealer_outstanding`: Dealer outstanding and PO details
- `accpac_channel_finance_invoice`: Channel finance invoice details

### Stored Procedures

- `sp_accpac_dealer_master_dealer_code_auto`: Dealer code autocomplete search
- `sp_accpac_dealer_get_details_by_dealer_code`: Get dealer details by code
- `sp_acc_pac_dealer_dealer_type_list`: Get distinct dealer types
- `sp_accpac_get_dealer_os`: Get outstanding status for dealer/PO

---

**Document Version**: 1.0  
**Last Updated**: 2024  
**Module Path**: `com.i4o.dms.kubota.accpac`

