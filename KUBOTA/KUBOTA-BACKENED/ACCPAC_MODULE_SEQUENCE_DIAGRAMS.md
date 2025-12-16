# ACCPAC Module Sequence Diagrams

This document contains detailed sequence diagrams for all flows within the ACCPAC Module of the KUBOTA-BACKENED system. The ACCPAC module handles integration with ACCPAC accounting system, including dealer master management, purchase order details, and channel finance invoice management.

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

This diagram shows the flow for uploading dealer master data from an Excel file.

```mermaid
sequenceDiagram
    participant Client as "Client Application"
    participant AccPacController as AccPacController
    participant Poiji as Poiji Library
    participant DealerMasterRepo as AccPacDealerMasterRepository
    participant Database as "SQL Server DB"

    Client->>AccPacController: POST /api/accpac/uploadDealerMasterExcel<br/>(multipartFile: Excel file)
    
    AccPacController->>AccPacController: Extract MultipartFile from request
    
    AccPacController->>AccPacController: Get InputStream from multipartFile
    
    AccPacController->>Poiji: Poiji.fromExcel(in, PoijiExcelType.XLS,<br/>AccPacDealerMaster.class, options)
    
    Poiji->>Poiji: Parse Excel file using annotations<br/>(@ExcelCell, @ExcelCellName)
    
    Poiji->>Poiji: Map Excel rows to AccPacDealerMaster objects
    
    Poiji-->>AccPacController: List AccPacDealerMaster objects
    
    AccPacController->>DealerMasterRepo: saveAll(accPacDealerMasters)
    
    DealerMasterRepo->>Database: INSERT INTO AccPacDealerMaster<br/>(dealerCode, dealerName, status,<br/>dealerFirmType, gstNo, panNo,<br/>emailId, address1-4, pinCode,<br/>tehsil, city, state, country,<br/>dealerType, dealerCreditLimit, syncDate)
    
    Database-->>DealerMasterRepo: Save result
    
    DealerMasterRepo-->>AccPacController: Saved entities
    
    AccPacController->>AccPacController: Create ApiResponse<br/>(message: "Dealer Master Uploaded Successfully",<br/>status: 200)
    
    AccPacController-->>Client: ResponseEntity ApiResponse (200 OK)
    
    Client->>Client: Display success message
```

---

## 2. Dealer Code Autocomplete Flow

This diagram shows the flow for dealer code autocomplete functionality.

```mermaid
sequenceDiagram
    participant Client as "Client Application"
    participant AccPacController as AccPacController
    participant DealerMasterRepo as AccPacDealerMasterRepository
    participant Database as "SQL Server DB"
    participant StoredProcedure as "sp_accpac_dealer_master_dealer_code_auto"

    Client->>AccPacController: GET /api/accpac/dealerCodeAuto<br/>?dealerCode=DLR
    
    AccPacController->>AccPacController: Extract dealerCode parameter
    
    AccPacController->>DealerMasterRepo: findByDealerCodeContaining(dealerCode)
    
    DealerMasterRepo->>Database: CALL sp_accpac_dealer_master_dealer_code_auto(:dealerCode)
    
    Database->>StoredProcedure: Execute stored procedure with dealerCode parameter
    
    StoredProcedure->>StoredProcedure: Query AccPacDealerMaster table<br/>WHERE dealerCode LIKE %dealerCode%
    
    StoredProcedure->>Database: Return matching dealer codes
    
    Database-->>DealerMasterRepo: List Map String Object (dealer codes)
    
    DealerMasterRepo-->>AccPacController: List Map String Object
    
    AccPacController->>AccPacController: Create ApiResponse<br/>(message: "dealer code get successfully",<br/>status: 200,<br/>result: dealer codes list)
    
    AccPacController-->>Client: ResponseEntity ApiResponse (200 OK)
    
    Client->>Client: Display autocomplete suggestions
```

---

## 3. Get Details by Dealer Code Flow

This diagram shows the flow for retrieving dealer details by dealer code.

```mermaid
sequenceDiagram
    participant Client as "Client Application"
    participant AccPacController as AccPacController
    participant DealerMasterRepo as AccPacDealerMasterRepository
    participant Database as "SQL Server DB"
    participant StoredProcedure as "sp_accpac_dealer_get_details_by_dealer_code"

    Client->>AccPacController: GET /api/accpac/getDetailsByDealerCode<br/>?dealerCode=DLR001
    
    AccPacController->>AccPacController: Extract dealerCode parameter
    
    AccPacController->>DealerMasterRepo: getDetailsByDealerCode(dealerCode)
    
    DealerMasterRepo->>Database: CALL sp_accpac_dealer_get_details_by_dealer_code(:dealerCode)
    
    Database->>StoredProcedure: Execute stored procedure with dealerCode parameter
    
    StoredProcedure->>StoredProcedure: Query AccPacDealerMaster table<br/>WHERE dealerCode = dealerCode
    
    StoredProcedure->>Database: Return dealer details<br/>(dealerCode, dealerName, status,<br/>dealerFirmType, gstNo, panNo,<br/>emailId, address, pinCode,<br/>tehsil, city, state, country,<br/>dealerType, dealerCreditLimit)
    
    Database-->>DealerMasterRepo: List Map String Object (dealer details)
    
    DealerMasterRepo-->>AccPacController: List Map String Object
    
    AccPacController->>AccPacController: Create ApiResponse<br/>(message: "Details Get Successfully",<br/>status: 200,<br/>result: dealer details)
    
    AccPacController-->>Client: ResponseEntity ApiResponse (200 OK)
    
    Client->>Client: Display dealer details
```

---

## 4. Get Dealer Type List Flow

This diagram shows the flow for retrieving the list of dealer types.

```mermaid
sequenceDiagram
    participant Client as "Client Application"
    participant AccPacController as AccPacController
    participant DealerMasterRepo as AccPacDealerMasterRepository
    participant Database as "SQL Server DB"
    participant StoredProcedure as "sp_acc_pac_dealer_dealer_type_list"

    Client->>AccPacController: GET /api/accpac/dealerTypeList
    
    AccPacController->>DealerMasterRepo: getDealerType()
    
    DealerMasterRepo->>Database: CALL sp_acc_pac_dealer_dealer_type_list()
    
    Database->>StoredProcedure: Execute stored procedure (no parameters)
    
    StoredProcedure->>StoredProcedure: Query AccPacDealerMaster table<br/>SELECT DISTINCT dealerType
    
    StoredProcedure->>Database: Return distinct dealer types
    
    Database-->>DealerMasterRepo: List Map String Object (dealer types)
    
    DealerMasterRepo-->>AccPacController: List Map String Object
    
    AccPacController->>AccPacController: Create ApiResponse<br/>(message: "Dealer Type Get Successfully",<br/>status: 200,<br/>result: dealer types list)
    
    AccPacController-->>Client: ResponseEntity ApiResponse (200 OK)
    
    Client->>Client: Display dealer type dropdown/list
```

---

## 5. Save ACCPAC PO Details Flow

This diagram shows the flow for saving ACCPAC purchase order details.

```mermaid
sequenceDiagram
    participant Client as "Client Application"
    participant AccpacPoDetailsController as AccpacPoDetailsController
    participant AccpacPoDetailsRepo as AccpacPoDetailsRepo
    participant Database as "SQL Server DB"
    participant AccpacPoDetails as AccpacPoDetails Entity

    Client->>AccpacPoDetailsController: POST /api/accpac/saveaccpacpodetails<br/>(JSON: AccpacPoDetails)
    
    AccpacPoDetailsController->>AccpacPoDetailsController: Extract AccpacPoDetails from request body<br/>(@RequestBody)
    
    AccpacPoDetailsController->>AccpacPoDetailsController: Validate AccpacPoDetails entity<br/>(dealerCode, dealer_id,<br/>totalCreditLimit, availableLimit,<br/>totalOs, currentOs,<br/>os0To30Days, os31To60Days,<br/>os61To90Days, os90Days,<br/>orderUnderProcess, pendingOrder,<br/>pendingPayment, syncDate)
    
    AccpacPoDetailsController->>AccpacPoDetailsRepo: save(accpacPoDetails)
    
    AccpacPoDetailsRepo->>Database: INSERT INTO accpac_dealer_outstanding<br/>(dealerCode, dealer_id,<br/>totalCreditLimit, availableLimit,<br/>totalOs, currentOs,<br/>os0To30Days, os31To60Days,<br/>os61To90Days, os90Days,<br/>orderUnderProcess, pendingOrder,<br/>pendingPayment, syncDate)
    
    Database-->>AccpacPoDetailsRepo: Saved entity with generated ID
    
    AccpacPoDetailsRepo-->>AccpacPoDetailsController: AccpacPoDetails entity (saved)
    
    AccpacPoDetailsController->>AccpacPoDetailsController: Create ApiResponse<br/>(message: "accpac po details successfully saved.",<br/>status: 200)
    
    AccpacPoDetailsController-->>Client: ResponseEntity ApiResponse (200 OK)
    
    Client->>Client: Display success message
```

---

## 6. Get Outstanding Status Flow

This diagram shows the flow for retrieving outstanding status for a dealer or purchase order.

```mermaid
sequenceDiagram
    participant Client as "Client Application"
    participant AccpacPoDetailsController as AccpacPoDetailsController
    participant AccpacPoDetailsRepo as AccpacPoDetailsRepo
    participant Database as "SQL Server DB"
    participant StoredProcedure as "sp_accpac_get_dealer_os"

    Client->>AccpacPoDetailsController: GET /api/accpac/getOsStatus<br/>?dealerCode=DLR001&poid=12345
    
    AccpacPoDetailsController->>AccpacPoDetailsController: Extract dealerCode and poid parameters
    
    AccpacPoDetailsController->>AccpacPoDetailsController: Validate and normalize parameters<br/>(if null or empty or "null", set to null)
    
    alt poid is not null
        AccpacPoDetailsController->>AccpacPoDetailsController: Convert poid string to Long
    end
    
    AccpacPoDetailsController->>AccpacPoDetailsRepo: getOsStatus(dealerCode, poid)
    
    AccpacPoDetailsRepo->>Database: CALL sp_accpac_get_dealer_os(:dealerCode, :poid)
    
    Database->>StoredProcedure: Execute stored procedure with parameters
    
    StoredProcedure->>StoredProcedure: Query accpac_dealer_outstanding table<br/>WHERE dealerCode = dealerCode<br/>AND (poid = poid OR poid IS NULL)
    
    StoredProcedure->>StoredProcedure: Calculate outstanding amounts<br/>(totalOs, currentOs,<br/>os0To30Days, os31To60Days,<br/>os61To90Days, os90Days,<br/>availableLimit, totalCreditLimit)
    
    StoredProcedure->>Database: Return outstanding status map
    
    Database-->>AccpacPoDetailsRepo: Map String Object (outstanding status)
    
    AccpacPoDetailsRepo-->>AccpacPoDetailsController: Map String Object
    
    AccpacPoDetailsController->>AccpacPoDetailsController: Create ApiResponse<br/>(message: "outstanding details get successfully.",<br/>status: 200,<br/>result: outstanding status map)
    
    AccpacPoDetailsController-->>Client: ResponseEntity ApiResponse (200 OK)
    
    Client->>Client: Display outstanding status
```

---

## 7. Save Invoice Details Flow

This diagram shows the flow for saving channel finance invoice details.

```mermaid
sequenceDiagram
    participant Client as "Client Application"
    participant AccpacChannelFinanceController as AccpacChannelFinanceController
    participant InvoiceDetailsRepo as InvoiceDetailsRepo
    participant Database as "SQL Server DB"
    participant AccpacChannelFinanceInvoice as AccpacChannelFinanceInvoice Entity

    Client->>AccpacChannelFinanceController: POST /api/accpac/invoiceDetails/saveInvoiceDetails<br/>(JSON: AccpacChannelFinanceInvoice)
    
    AccpacChannelFinanceController->>AccpacChannelFinanceController: Extract AccpacChannelFinanceInvoice from request body<br/>(@RequestBody)
    
    AccpacChannelFinanceController->>AccpacChannelFinanceController: Validate AccpacChannelFinanceInvoice entity<br/>(dealerCode, invoiceDate,<br/>invoiceAmount, totalUtilisedAmount,<br/>invoiceNumber, status, syncDate)
    
    AccpacChannelFinanceController->>InvoiceDetailsRepo: save(accpacChannelFinanceInvoice)
    
    InvoiceDetailsRepo->>Database: INSERT INTO accpac_channel_finance_invoice<br/>(dealerCode, invoiceDate,<br/>invoiceAmount, totalUtilisedAmount,<br/>invoiceNumber, status, syncDate)
    
    Database-->>InvoiceDetailsRepo: Saved entity with generated ID
    
    InvoiceDetailsRepo-->>AccpacChannelFinanceController: AccpacChannelFinanceInvoice entity (saved)
    
    AccpacChannelFinanceController->>AccpacChannelFinanceController: Create ApiResponse<br/>(message: "invoice details successfully saved.",<br/>status: 200)
    
    AccpacChannelFinanceController-->>Client: ResponseEntity ApiResponse (200 OK)
    
    Client->>Client: Display success message
```

---

## Summary

The ACCPAC Module handles:

1. **Dealer Master Management**:
   - Upload dealer master data from Excel files
   - Autocomplete dealer codes
   - Retrieve dealer details by code
   - Get dealer type list

2. **Purchase Order Details Management**:
   - Save ACCPAC PO details (outstanding information)
   - Retrieve outstanding status for dealers or purchase orders

3. **Channel Finance Invoice Management**:
   - Save channel finance invoice details

All flows integrate with SQL Server database through JPA repositories and stored procedures. The module uses Spring Boot REST controllers with JSON request/response handling. Excel file processing is handled by the Poiji library for dealer master uploads. The module follows RESTful API patterns and returns standardized ApiResponse objects with status codes and messages.

