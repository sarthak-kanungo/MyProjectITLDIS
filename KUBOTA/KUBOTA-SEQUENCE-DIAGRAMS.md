# KUBOTA DMS - Detailed Sequence Diagrams

This document contains comprehensive sequence diagrams for the KUBOTA Dealer Management System (DMS), illustrating the main business flows and system interactions.

## Table of Contents
1. [User Authentication & Authorization Flow](#1-user-authentication--authorization-flow)
2. [Warranty Claim Request (WCR) Flow](#2-warranty-claim-request-wcr-flow)
3. [Spare Parts Sales Order Flow](#3-spare-parts-sales-order-flow)
4. [Service Job Card Creation Flow](#4-service-job-card-creation-flow)
5. [File Upload & Storage Flow](#5-file-upload--storage-flow)
6. [Approval Workflow Flow](#6-approval-workflow-flow)

---

## 1. User Authentication & Authorization Flow

```mermaid
sequenceDiagram
    participant User as User/Browser
    participant Frontend as Angular Frontend
    participant Controller as UserTokenController
    participant AuthService as UserAuthentication
    participant TokenService as UserTokenService
    participant UserService as UserDetailsServiceImpl
    participant JWT as JWT Service
    participant DB as Database

    User->>Frontend: Login Request (username, password)
    Frontend->>Controller: POST /api/security/login
    Controller->>UserService: loadUserByUsername(username)
    UserService->>DB: Query User Details
    DB-->>UserService: User Entity
    UserService->>DB: Query User Roles & Permissions
    DB-->>UserService: Roles & Permissions
    UserService-->>Controller: UserDetails Object
    
    Controller->>JWT: Generate Token(userDetails)
    JWT-->>Controller: JWT Token
    
    Controller->>TokenService: Save Token to Session
    TokenService->>DB: Store Token & Session Info
    DB-->>TokenService: Token Saved
    
    Controller->>AuthService: Set Authentication Context
    AuthService->>DB: Store User Context (DealerId, BranchId, etc.)
    DB-->>AuthService: Context Saved
    
    Controller-->>Frontend: Response (Token, User Details)
    Frontend-->>User: Display Dashboard
    
    Note over User,DB: Subsequent API Calls
    User->>Frontend: API Request
    Frontend->>Controller: Request with JWT Token
    Controller->>TokenService: Validate Token
    TokenService->>DB: Verify Token & Session
    DB-->>TokenService: Token Valid
    TokenService->>AuthService: Get User Context
    AuthService->>DB: Retrieve User Context
    DB-->>AuthService: User Context
    AuthService-->>Controller: Authenticated User
    Controller->>Controller: Process Request
```

---

## 2. Warranty Claim Request (WCR) Flow

```mermaid
sequenceDiagram
    participant User as Dealer User
    participant Frontend as Angular Frontend
    participant WCRController as WarrantyWcrController
    participant AuthService as UserAuthentication
    participant PCRRepo as WarrantyPcrRepo
    participant WCRRepo as WarrantyWcrRepo
    participant WCRApprovalRepo as WarrantyWcrApprovalRepo
    participant SparePartRepo as SparePartRequisitionItemRepo
    participant StorageService as StorageService
    participant DB as Database

    User->>Frontend: Create WCR from PCR/Goodwill
    Frontend->>WCRController: GET /api/warranty/Wcr/pcrWarrantyClaim?pcrNo=X&id=Y
    
    WCRController->>AuthService: Get User Context
    AuthService-->>WCRController: User Context (DealerId, BranchId)
    
    WCRController->>PCRRepo: findByPcrNoAndId(pcrNo, id)
    PCRRepo->>DB: Query Warranty PCR Details
    DB-->>PCRRepo: PCR Entity with JobCard
    PCRRepo->>DB: getDealerDetails(branchId)
    DB-->>PCRRepo: Dealer Information
    PCRRepo-->>WCRController: PcrWarrantyClaimDto
    
    WCRController->>WCRRepo: getJobCardPartWarrantyInfo(pcrNo, "PCR")
    WCRRepo->>DB: Query Warranty Parts
    DB-->>WCRRepo: Warranty Parts List
    
    WCRController->>WCRRepo: getLabourCharge(pcrNo, "PCR")
    WCRRepo->>DB: Query Labour Charges
    DB-->>WCRRepo: Labour Charges
    
    WCRController->>WCRRepo: getOutSideLabourCharge(pcrNo, "PCR")
    WCRRepo->>DB: Query Outside Labour Charges
    DB-->>WCRRepo: Outside Labour Charges
    
    WCRController-->>Frontend: WcrWarrantyDto (Complete WCR Data)
    Frontend-->>User: Display WCR Form
    
    User->>Frontend: Submit WCR with Invoice Files
    Frontend->>WCRController: POST /api/warranty/Wcr/saveWcr (WarrantyWcr + Files)
    
    WCRController->>AuthService: Get User Context
    AuthService-->>WCRController: User Context
    
    WCRController->>WCRController: Set WCR Metadata (branchId, createdBy, wcrDate, status)
    
    WCRController->>StorageService: Store Invoice Files
    StorageService->>DB: Save File Metadata
    DB-->>StorageService: Files Saved
    StorageService-->>WCRController: File URLs
    
    WCRController->>WCRRepo: save(warrantyWcr)
    WCRRepo->>DB: Insert WarrantyWcr
    DB-->>WCRRepo: Saved WCR with ID
    WCRRepo-->>WCRController: WarrantyWcr Entity
    
    alt WCR Type is PCR
        WCRController->>SparePartRepo: updateWarrantyClaimPartPcr(wcrId, amounts, quantities, itemIds)
        SparePartRepo->>DB: Update Part Requisition Items
        DB-->>SparePartRepo: Updated
        
        WCRController->>SparePartRepo: updateWarrantyClaimLabourPcr(wcrId, amounts, itemIds)
        SparePartRepo->>DB: Update Labour Charges
        DB-->>SparePartRepo: Updated
        
        WCRController->>SparePartRepo: updateWarrantyClaimOutsideChargePcr(wcrId, amounts, itemIds)
        SparePartRepo->>DB: Update Outside Charges
        DB-->>SparePartRepo: Updated
    else WCR Type is GOODWILL
        WCRController->>SparePartRepo: updateWarrantyClaimPartGoodwill(wcrId, amounts, quantities, itemIds)
        SparePartRepo->>DB: Update Part Requisition Items
        DB-->>SparePartRepo: Updated
        
        WCRController->>SparePartRepo: updateWarrantyClaimLabourGoodwill(wcrId, amounts, itemIds)
        SparePartRepo->>DB: Update Labour Charges
        DB-->>SparePartRepo: Updated
        
        WCRController->>SparePartRepo: updateWarrantyClaimOutsideChargeGoodwill(wcrId, amounts, itemIds)
        SparePartRepo->>DB: Update Outside Charges
        DB-->>SparePartRepo: Updated
    end
    
    WCRController->>WCRApprovalRepo: getWarrantyWcrApprovalHierarchyLevel(dealerId)
    WCRApprovalRepo->>DB: Query Approval Hierarchy
    DB-->>WCRApprovalRepo: Approval Hierarchy Levels
    
    WCRController->>WCRController: Create WarrantyWcrApproval Objects for Each Level
    WCRController->>WCRApprovalRepo: saveAll(warrantyWcrApprovals)
    WCRApprovalRepo->>DB: Insert Approval Records
    DB-->>WCRApprovalRepo: Approvals Created
    
    WCRController-->>Frontend: Success Response
    Frontend-->>User: WCR Submitted Successfully
```

---

## 3. Spare Parts Sales Order Flow

```mermaid
sequenceDiagram
    participant User as Dealer User
    participant Frontend as Angular Frontend
    participant SOController as SpareSalesOrderController
    participant AuthService as UserAuthentication
    participant SOService as SpareSalesOrderService
    participant SORepo as SpareSalesOrderRepository
    participant QuotationRepo as SpareQuotationRepository
    participant InventoryRepo as Inventory Repository
    participant DB as Database

    User->>Frontend: Create Spare Sales Order
    Frontend->>SOController: GET /api/spares/salesOrder/getQuotationNumberAutocomplete?quotationNumber=X
    
    SOController->>AuthService: Get User Context
    AuthService-->>SOController: BranchId
    
    SOController->>SORepo: getQuotationNumberAutocomplete(quotationNumber, branchId)
    SORepo->>DB: Query Quotations
    DB-->>SORepo: Matching Quotations
    SORepo-->>SOController: Quotation List
    
    SOController-->>Frontend: Quotation Options
    Frontend-->>User: Display Quotation Dropdown
    
    User->>Frontend: Select Quotation & Items
    Frontend->>SOController: GET /api/spares/salesOrder/getItemDetailsByItemNumber?itemNumber=X
    
    SOController->>SORepo: getItemDetails(itemNumber)
    SORepo->>DB: Query Item Master
    DB-->>SORepo: Item Details
    SORepo-->>SOController: Item Information
    
    SOController->>SORepo: getAvailableStockByItemNumber(itemNumber)
    SORepo->>InventoryRepo: Query Current Stock
    InventoryRepo->>DB: Query Stock Levels
    DB-->>InventoryRepo: Stock Quantity
    InventoryRepo-->>SORepo: Available Stock
    SORepo-->>SOController: Stock Information
    
    SOController-->>Frontend: Item Details + Stock
    Frontend-->>User: Display Item with Stock
    
    User->>Frontend: Submit Sales Order
    Frontend->>SOController: POST /api/spares/salesOrder/saveSpareSalesOrder (SpareSalesOrder)
    
    SOController->>AuthService: Get User Context
    AuthService-->>SOController: User Context
    
    SOController->>SOController: Set Order Metadata (createdDate, status)
    
    alt Draft Flag is True
        SOController->>SOController: Set Status = "Draft"
    else Draft Flag is False
        SOController->>SOController: Set Status = "Open"
        SOController->>SOController: Set BackQuantity = Quantity for each part
    end
    
    SOController->>SOService: saveSpareSaleOrder(salesOrder)
    SOService->>SORepo: save(salesOrder)
    SORepo->>DB: Insert/Update SpareSalesOrder
    DB-->>SORepo: Saved Order with ID
    
    SOService->>SORepo: saveAll(salesOrder.getSpareSalesOrderPartDetailList())
    SORepo->>DB: Insert Order Part Details
    DB-->>SORepo: Part Details Saved
    
    alt Status is "Open"
        SOService->>InventoryRepo: Reserve Stock for Items
        InventoryRepo->>DB: Update Stock Reservations
        DB-->>InventoryRepo: Stock Reserved
    end
    
    SOService-->>SOController: Saved Sales Order
    SOController-->>Frontend: Success Response
    Frontend-->>User: Sales Order Saved/Submitted
```

---

## 4. Service Job Card Creation Flow

```mermaid
sequenceDiagram
    participant User as Service Technician
    participant Frontend as Angular Frontend
    participant JCController as ServiceJobCardController
    participant AuthService as UserAuthentication
    participant JCService as ServiceJobcardImpl
    participant JCRepo as ServiceJobcardRepo
    participant CustomerRepo as Customer Repository
    participant MachineRepo as Machine Repository
    participant StorageService as StorageService
    participant DB as Database

    User->>Frontend: Create Service Job Card
    Frontend->>JCController: GET /api/service/jobcard/getCustomerDetails?customerId=X
    
    JCController->>AuthService: Get User Context
    AuthService-->>JCController: User Context
    
    JCController->>CustomerRepo: findById(customerId)
    CustomerRepo->>DB: Query Customer
    DB-->>CustomerRepo: Customer Entity
    CustomerRepo-->>JCController: Customer Details
    
    JCController->>MachineRepo: findByCustomerId(customerId)
    MachineRepo->>DB: Query Customer Machines
    DB-->>MachineRepo: Machine List
    MachineRepo-->>JCController: Machines
    
    JCController-->>Frontend: Customer + Machines Data
    Frontend-->>User: Display Job Card Form
    
    User->>Frontend: Fill Job Card & Upload Photos
    Frontend->>JCController: POST /api/service/jobcard/saveJobCard (JobCard + Photos)
    
    JCController->>AuthService: Get User Context
    AuthService-->>JCController: User Context
    
    JCController->>JCController: Set Job Card Metadata (createdBy, createdDate, status)
    
    JCController->>StorageService: Store Service Photos
    StorageService->>DB: Save Photo Metadata
    DB-->>StorageService: Photos Saved
    StorageService-->>JCController: Photo URLs
    
    JCController->>JCService: saveJobCard(jobCard)
    JCService->>JCRepo: save(jobCard)
    JCRepo->>DB: Insert ServiceJobCard
    DB-->>JCRepo: Saved JobCard with ID
    
    JCService->>JCRepo: saveAll(jobCard.getSparePartRequisitionItem())
    JCRepo->>DB: Insert Part Requisition Items
    DB-->>JCRepo: Parts Saved
    
    JCService->>JCRepo: saveAll(jobCard.getLabourCharge())
    JCRepo->>DB: Insert Labour Charges
    DB-->>JCRepo: Labour Charges Saved
    
    JCService->>JCRepo: saveAll(jobCard.getOutsideJobCharge())
    JCRepo->>DB: Insert Outside Job Charges
    DB-->>JCRepo: Outside Charges Saved
    
    JCService->>JCRepo: saveAll(jobCard.getJobCardPhotos())
    JCRepo->>DB: Insert Job Card Photos
    DB-->>JCRepo: Photos Saved
    
    JCService-->>JCController: Saved Job Card
    JCController-->>Frontend: Success Response
    Frontend-->>User: Job Card Created Successfully
```

---

## 5. File Upload & Storage Flow

```mermaid
sequenceDiagram
    participant User as User
    participant Frontend as Angular Frontend
    participant FileController as FileUploadController
    participant StorageService as StorageService
    participant FileSystem as File System
    participant DB as Database

    User->>Frontend: Upload File (Invoice, Photo, Document)
    Frontend->>FileController: POST /api/saveFile (MultipartFile)
    
    FileController->>StorageService: store(file, filename)
    StorageService->>StorageService: Validate File (size, type)
    
    alt File Valid
        StorageService->>FileSystem: Write File to Storage Directory
        FileSystem-->>StorageService: File Written
        
        StorageService->>DB: Save File Metadata (filename, path, size, type)
        DB-->>StorageService: Metadata Saved
        
        StorageService-->>FileController: File Stored Successfully
        FileController-->>Frontend: Success Response with File URL
        Frontend-->>User: File Uploaded Successfully
    else File Invalid
        StorageService-->>FileController: FileStorageException
        FileController-->>Frontend: Error Response
        Frontend-->>User: Upload Failed
    end
    
    Note over User,DB: File Retrieval
    User->>Frontend: Request File Download
    Frontend->>FileController: GET /api/files/{filename}
    
    FileController->>StorageService: loadAsResource(filename)
    StorageService->>FileSystem: Read File
    FileSystem-->>StorageService: File Content
    StorageService->>DB: Get File Metadata
    DB-->>StorageService: File Metadata
    StorageService-->>FileController: Resource Object
    
    FileController->>FileController: Set Content-Disposition Header
    FileController-->>Frontend: File Resource with Headers
    Frontend-->>User: Download File
```

---

## 6. Approval Workflow Flow

```mermaid
sequenceDiagram
    participant User as Approver
    participant Frontend as Angular Frontend
    participant Controller as Approval Controller
    participant AuthService as UserAuthentication
    participant ApprovalRepo as Approval Repository
    participant HierarchyRepo as Hierarchy Repository
    participant NotificationService as Notification Service
    participant DB as Database

    User->>Frontend: View Pending Approvals
    Frontend->>Controller: GET /api/{module}/approvals/pending
    
    Controller->>AuthService: Get User Context
    AuthService-->>Controller: User Context (DesignationLevelId)
    
    Controller->>ApprovalRepo: findPendingApprovals(designationLevelId, userId)
    ApprovalRepo->>DB: Query Pending Approvals
    DB-->>ApprovalRepo: Approval List
    ApprovalRepo-->>Controller: Pending Approvals
    
    Controller-->>Frontend: Approval List
    Frontend-->>User: Display Pending Approvals
    
    User->>Frontend: Approve/Reject Request
    Frontend->>Controller: POST /api/{module}/approve (ApprovalDto)
    
    Controller->>AuthService: Get User Context
    AuthService-->>Controller: User Context
    
    Controller->>ApprovalRepo: findById(approvalId)
    ApprovalRepo->>DB: Query Approval Record
    DB-->>ApprovalRepo: Approval Entity
    ApprovalRepo-->>Controller: Approval Details
    
    Controller->>Controller: Validate Approval Authority
    
    alt User Has Authority
        Controller->>ApprovalRepo: updateApprovalStatus(approvalId, status, comments)
        ApprovalRepo->>DB: Update Approval Status
        DB-->>ApprovalRepo: Updated
        
        alt Approval Status is "Approved"
            Controller->>HierarchyRepo: getNextApprovalLevel(currentLevel)
            HierarchyRepo->>DB: Query Next Level
            DB-->>HierarchyRepo: Next Level Details
            
            alt Next Level Exists
                Controller->>ApprovalRepo: createNextLevelApproval(nextLevel, requestId)
                ApprovalRepo->>DB: Insert Next Level Approval
                DB-->>ApprovalRepo: Next Approval Created
                
                Controller->>NotificationService: Notify Next Approver
                NotificationService->>DB: Create Notification
                DB-->>NotificationService: Notification Created
            else Is Final Approval
                Controller->>Controller: Mark Request as Final Approved
                Controller->>DB: Update Request Final Status
                DB-->>Controller: Status Updated
                
                Controller->>NotificationService: Notify Requestor
                NotificationService->>DB: Create Notification
                DB-->>NotificationService: Notification Created
            end
        else Approval Status is "Rejected"
            Controller->>Controller: Mark Request as Rejected
            Controller->>DB: Update Request Status
            DB-->>Controller: Status Updated
            
            Controller->>NotificationService: Notify Requestor
            NotificationService->>DB: Create Notification
            DB-->>NotificationService: Notification Created
        end
        
        Controller-->>Frontend: Approval Processed Successfully
        Frontend-->>User: Approval Saved
    else User Lacks Authority
        Controller-->>Frontend: Unauthorized Error
        Frontend-->>User: Access Denied
    end
```

---

## System Architecture Overview

```mermaid
sequenceDiagram
    participant Client as Client Application
    participant Gateway as API Gateway
    participant Auth as Authentication Layer
    participant Controller as REST Controllers
    participant Service as Service Layer
    participant Repository as Repository Layer
    participant DB as Database
    participant External as External Systems

    Client->>Gateway: HTTP Request
    Gateway->>Auth: Validate Token
    Auth->>DB: Verify Session
    DB-->>Auth: Session Valid
    Auth-->>Gateway: Authenticated
    
    Gateway->>Controller: Route Request
    Controller->>Service: Business Logic Call
    Service->>Repository: Data Access
    Repository->>DB: SQL Query
    DB-->>Repository: Result Set
    Repository-->>Service: Domain Objects
    Service->>Service: Business Logic Processing
    
    alt External Integration Required
        Service->>External: API Call (SAP, AccPac, etc.)
        External-->>Service: Response
    end
    
    Service-->>Controller: DTO/Response
    Controller->>Controller: Format Response
    Controller-->>Gateway: JSON Response
    Gateway-->>Client: HTTP Response
```

---

## Notes

### Key Components:
- **Controllers**: Handle HTTP requests/responses, input validation
- **Services**: Business logic, orchestration, transaction management
- **Repositories**: Data access layer, database operations
- **Authentication**: JWT-based authentication, user context management
- **Storage Service**: File upload/download management
- **Approval System**: Multi-level approval workflow

### Common Patterns:
1. All requests go through authentication/authorization
2. User context (DealerId, BranchId) is injected via UserAuthentication
3. File uploads are handled by StorageService
4. Approvals follow hierarchical workflow
5. Database operations use JPA/Hibernate repositories

### Error Handling:
- Global exception handler catches all exceptions
- ApiResponse wrapper for consistent response format
- Validation errors returned with appropriate HTTP status codes

---

*Last Updated: December 2025*
