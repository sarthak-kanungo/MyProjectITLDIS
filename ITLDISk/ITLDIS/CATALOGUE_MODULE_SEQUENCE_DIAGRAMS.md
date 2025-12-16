# Catalogue Module Sequence Diagrams

This document contains detailed sequence diagrams for all flows within the Catalogue Module of the ITLDIS system. The Catalogue module manages Part Master data, including parts and lubes cataloging, pricing, and standard job part assignments.

## Table of Contents

1. [Initialize Part Master Flow](#1-initialize-part-master-flow)
2. [Get Part Number by AJAX Flow](#2-get-part-number-by-ajax-flow)
3. [Get Part Description by AJAX Flow](#3-get-part-description-by-ajax-flow)
4. [Get Part Price by Part Number Flow (AJAX)](#4-get-part-price-by-part-number-flow-ajax)
5. [Get Part Price by Part Description Flow (AJAX)](#5-get-part-price-by-part-description-flow-ajax)
6. [Check Part Number Flow (AJAX)](#6-check-part-number-flow-ajax)
7. [Manage Part/Lube Master Flow](#7-manage-partlube-master-flow)
8. [Download Part List Master Flow](#8-download-part-list-master-flow)
9. [Upload SAP Part Master Flow](#9-upload-sap-part-master-flow)

---

## 1. Initialize Part Master Flow

This diagram shows the process of initializing the Part Master page with job types and part lists.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant MasterAction as masterAction
    participant MasterDAO as masterDAO
    participant Database as "SQL Server DB"

    User->>Browser: Click Catalogue/Part Master Tile
    Browser->>StrutsFramework: GET /masterAction.do?option=initPartMaster
    StrutsFramework->>MasterAction: initPartMaster(masterForm, request, response)
    
    MasterAction->>Database: Get connection
    Database-->>MasterAction: Connection conn
    
    MasterAction->>MasterDAO: new masterDAO(conn)
    MasterDAO-->>MasterAction: masterDAO object
    
    MasterAction->>MasterDAO: getCommonLabelValues("Jobtypemaster", "jobTypeID", "jobTypeDesc", "jobTypeDesc", "")
    MasterDAO->>Database: Query Jobtypemaster table
    Database-->>MasterDAO: Job type list
    MasterDAO-->>MasterAction: LinkedHashSet jobTypeList
    
    alt Job type and model are provided
        MasterAction->>MasterDAO: GetPartDetails4jobtype(jobType, modelNo)
        MasterDAO->>Database: Query CatPart JOIN Standardjobpartmaster WHERE jobTypeId=? AND modelcode=?
        Database-->>MasterDAO: ResultSet part details (partNo, partType, p1, qty)
        MasterDAO->>MasterDAO: Map results to masterForm objects
        MasterDAO-->>MasterAction: ArrayList partList
        MasterAction->>MasterAction: Set name with job type and model
    end
    
    MasterAction->>Browser: Set request attributes (partList, masterform, jobTypeList)
    MasterAction->>StrutsFramework: Forward to initPartMaster
    StrutsFramework->>Browser: Render Part Master page
    Browser-->>User: Display Part Master form with job type dropdown and part list (if job type selected)
```

---

## 2. Get Part Number by AJAX Flow

This diagram shows the AJAX flow for retrieving part numbers based on partial input.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant MasterAction as masterAction
    participant MasterDAO as masterDAO
    participant Database as "SQL Server DB"

    User->>Browser: Type part number in form field
    Browser->>Browser: Trigger AJAX call on input/change
    Browser->>StrutsFramework: GET /masterAction.do?option=getPartNumberAjax&partno=PART123&comptype=SPARES
    StrutsFramework->>MasterAction: getPartNumberAjax(masterForm, request, response)
    
    MasterAction->>Database: Get connection
    Database-->>MasterAction: Connection conn
    
    MasterAction->>MasterAction: Extract partno, comptype from request parameters
    
    MasterAction->>MasterDAO: new masterDAO(conn)
    MasterDAO-->>MasterAction: masterDAO object
    
    MasterAction->>MasterDAO: getComponentList(partno, comptype, "partNo", "CatPart", "partType")
    
    MasterDAO->>Database: Query CatPart WHERE partType LIKE ? AND partNo LIKE ? ORDER BY partNo
    Database-->>MasterDAO: ResultSet matching part numbers
    
    MasterDAO->>MasterDAO: Build XML string with part numbers
    MasterDAO-->>MasterAction: String result (formatted part list)
    
    MasterAction->>Browser: Write result to response PrintWriter
    Browser->>Browser: Parse result and populate dropdown/autocomplete
    Browser-->>User: Display matching part numbers in dropdown
```

---

## 3. Get Part Description by AJAX Flow

This diagram shows the AJAX flow for retrieving part descriptions based on partial input.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant MasterAction as masterAction
    participant MasterDAO as masterDAO
    participant Database as "SQL Server DB"

    User->>Browser: Type part description in form field
    Browser->>Browser: Trigger AJAX call on input/change
    Browser->>StrutsFramework: GET /masterAction.do?option=getPartDescAjax&partDesc=ENGINE&comptype=SPARES
    StrutsFramework->>MasterAction: getPartDescAjax(masterForm, request, response)
    
    MasterAction->>MasterAction: Extract partDesc, comptype from request parameters
    
    MasterAction->>MasterDAO: new masterDAO()
    MasterDAO-->>MasterAction: masterDAO object
    
    MasterAction->>MasterDAO: getComponentList(partDesc, comptype, "p1", "CatPart", "partType")
    
    MasterDAO->>Database: Query CatPart WHERE partType LIKE ? AND p1 LIKE ? ORDER BY p1
    Database-->>MasterDAO: ResultSet matching part descriptions
    
    MasterDAO->>MasterDAO: Build XML string with part descriptions
    MasterDAO-->>MasterAction: String result (formatted part list)
    
    MasterAction->>Browser: Write result to response PrintWriter
    Browser->>Browser: Parse result and populate dropdown/autocomplete
    Browser-->>User: Display matching part descriptions in dropdown
```

---

## 4. Get Part Price by Part Number Flow (AJAX)

This diagram shows the AJAX flow for retrieving part price based on part number.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant MasterAction as masterAction
    participant MasterDAO as masterDAO
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Select part number from dropdown
    Browser->>Browser: Trigger AJAX call
    Browser->>StrutsFramework: GET /masterAction.do?option=getPartPriceBypartNo&partno=PART123
    StrutsFramework->>MasterAction: getPartPriceBypartNo(masterForm, request, response)
    
    MasterAction->>Database: Get connection
    Database-->>MasterAction: Connection conn
    
    MasterAction->>HTTPSession: request.getSession()
    HTTPSession-->>MasterAction: HttpSession session
    
    MasterAction->>HTTPSession: session.getAttribute("priceListCode")
    HTTPSession-->>MasterAction: String priceListCode
    
    MasterAction->>MasterAction: Extract partno from request parameter
    
    MasterAction->>MasterDAO: new masterDAO(conn)
    MasterDAO-->>MasterAction: masterDAO object
    
    MasterAction->>MasterDAO: getPriceByPartNo(partno, priceListCode)
    
    MasterDAO->>Database: Query CatPart LEFT JOIN SpPriceMaster WHERE partNo=? AND (pricelistCode IS NULL OR pricelistCode=?)
    Database-->>MasterDAO: ResultSet with price information (p1, partType, price)
    
    MasterDAO->>MasterDAO: Format result string
    MasterDAO-->>MasterAction: String result (price value with description)
    
    MasterAction->>Browser: Write result to response PrintWriter
    Browser->>Browser: Parse result and update Unit Price field
    Browser-->>User: Display part price in form
```

---

## 5. Get Part Price by Part Description Flow (AJAX)

This diagram shows the AJAX flow for retrieving part price based on part description.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant MasterAction as masterAction
    participant MasterDAO as masterDAO
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Select part description from dropdown
    Browser->>Browser: Trigger AJAX call
    Browser->>StrutsFramework: GET /masterAction.do?option=getPartPriceBypartDesc&partDesc=ENGINE OIL FILTER
    StrutsFramework->>MasterAction: getPartPriceBypartDesc(masterForm, request, response)
    
    MasterAction->>Database: Get connection
    Database-->>MasterAction: Connection conn
    
    MasterAction->>HTTPSession: request.getSession()
    HTTPSession-->>MasterAction: HttpSession session
    
    MasterAction->>HTTPSession: session.getAttribute("priceListCode")
    HTTPSession-->>MasterAction: String priceListCode
    
    MasterAction->>MasterAction: Extract partDesc from request parameter
    
    MasterAction->>MasterDAO: new masterDAO(conn)
    MasterDAO-->>MasterAction: masterDAO object
    
    MasterAction->>MasterDAO: getPart_in_db(partDesc)
    MasterDAO->>Database: Query CatPart WHERE p1=? ORDER BY p1 LIMIT 1
    Database-->>MasterDAO: Part number
    MasterDAO-->>MasterAction: String partno
    
    MasterAction->>MasterDAO: getPriceByPartNo(partno, priceListCode)
    
    MasterDAO->>Database: Query CatPart LEFT JOIN SpPriceMaster WHERE partNo=? AND (pricelistCode IS NULL OR pricelistCode=?)
    Database-->>MasterDAO: ResultSet with price information
    MasterDAO->>MasterDAO: Format result string
    MasterDAO-->>MasterAction: String result (price value)
    
    MasterAction->>Browser: Write result to response PrintWriter
    Browser->>Browser: Parse result and update Unit Price field
    Browser-->>User: Display part price in form
```

---

## 6. Check Part Number Flow (AJAX)

This diagram shows the AJAX flow for validating if a part number exists in the system.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant MasterAction as masterAction
    participant MasterDAO as masterDAO
    participant Database as "SQL Server DB"

    User->>Browser: Enter part number in form field
    Browser->>Browser: Trigger AJAX call on blur/change
    Browser->>StrutsFramework: GET /masterAction.do?option=getPartCheck&partno=PART123
    StrutsFramework->>MasterAction: getPartCheck(masterForm, request, response)
    
    MasterAction->>Database: Get connection
    Database-->>MasterAction: Connection conn
    
    MasterAction->>MasterAction: Extract partno from request parameter
    
    MasterAction->>MasterDAO: new masterDAO(conn)
    MasterDAO-->>MasterAction: masterDAO object
    
    MasterAction->>MasterDAO: getPartNoCheck(partno)
    
    MasterDAO->>Database: Query CatPart WHERE partNo=?
    Database-->>MasterDAO: ResultSet part check
    
    alt Part exists
        MasterDAO->>MasterDAO: Set result = "exists"
    else Part does not exist
        MasterDAO->>MasterDAO: Set result = "notexists"
    end
    
    MasterDAO-->>MasterAction: String result
    
    MasterAction->>Browser: Write result to response PrintWriter
    Browser->>Browser: Parse result
    alt Part exists
        Browser-->>User: Enable form fields or show success indicator
    else Part does not exist
        Browser-->>User: Show error message "Part number not found"
    end
```

---

## 7. Manage Part/Lube Master Flow

This diagram shows the process of adding or updating parts/lubes for a job type and model.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant MasterAction as masterAction
    participant MasterDAO as masterDAO
    participant HibernateUtil as HibernateUtil
    participant Standardjobpartmaster as Standardjobpartmaster
    participant Database as "SQL Server DB"

    User->>Browser: Select job type and model, add parts/lubes with quantities
    User->>Browser: Click Save button
    Browser->>StrutsFramework: POST /masterAction.do?option=managePartLubeMaster
    StrutsFramework->>MasterAction: managePartLubeMaster(masterForm, request, response)
    
    MasterAction->>Database: Get connection
    Database-->>MasterAction: Connection conn
    
    MasterAction->>MasterDAO: new masterDAO(conn)
    MasterDAO-->>MasterAction: masterDAO object
    
    MasterAction->>MasterDAO: addPartLubes4Master(mf)
    
    MasterDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>MasterDAO: Session hrbsession
    
    MasterDAO->>MasterDAO: Extract jobTypeId and modelCode from masterForm
    MasterDAO->>MasterDAO: check_in_Db(jobTypeId, "Standardjobpartmaster", "jobTypeId", " and modelcode =?")
    
    MasterDAO->>Database: Query Standardjobpartmaster WHERE jobTypeId=? AND modelcode=?
    Database-->>MasterDAO: Check result
    
    MasterDAO->>MasterDAO: hrbsession.beginTransaction()
    
    alt Entry exists for job type and model
        MasterDAO->>Database: DELETE FROM Standardjobpartmaster WHERE jobTypeId=? AND modelcode=?
        Database-->>MasterDAO: Delete result
    end
    
    loop For each part in part array
        alt Part number is not empty
            MasterDAO->>Standardjobpartmaster: new Standardjobpartmaster()
            MasterDAO->>Standardjobpartmaster: Set jobTypeId, modelcode, partNo, lastUpdatedBy, lastUpdatedOn, qty
            MasterDAO->>Database: hrbsession.save(sm)
            Database-->>MasterDAO: Save result
        end
    end
    
    MasterDAO->>MasterDAO: hrbsession.getTransaction().commit()
    MasterDAO-->>MasterAction: String result ("SUCCESS@@message" or "FAILURE@@message")
    
    MasterAction->>MasterAction: Split result by "@@"
    MasterAction->>Browser: Set request attributes (message, show_message, result, addMoreLink, addMoreLinkURL)
    
    alt Result is SUCCESS
        MasterAction->>StrutsFramework: Forward to message
        StrutsFramework->>Browser: Render success page
        Browser-->>User: Display "Part/Lubes for Jobtype [JobType] and Model Code [Model] has been Successfully Added"
    else Result is FAILURE
        MasterAction->>StrutsFramework: Forward to message
        StrutsFramework->>Browser: Render error page
        Browser-->>User: Display error message
    end
```

---

## 8. Download Part List Master Flow

This diagram shows the process of downloading part list to Excel.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant MasterAction as masterAction
    participant MasterDAO as masterDAO
    participant Database as "SQL Server DB"

    User->>Browser: Click Download Part List Tile
    Browser->>StrutsFramework: GET /masterAction.do?option=initDownloadPartlistMaster
    StrutsFramework->>MasterAction: initDownloadPartlistMaster(masterForm, request, response)
    
    MasterAction->>StrutsFramework: Forward to initDownloadPartlist
    StrutsFramework->>Browser: Render download part list form
    Browser-->>User: Display download form with part number search field
    
    User->>Browser: Enter part number (optional) and click Export to Excel
    Browser->>StrutsFramework: POST /masterAction.do?option=exportToExcelPartlistMaster&partNo=PART123
    StrutsFramework->>MasterAction: exportToExcelPartlistMaster(masterForm, request, response)
    
    MasterAction->>MasterAction: Extract partNo from request parameter
    
    MasterAction->>MasterDAO: new masterDAO()
    MasterDAO-->>MasterAction: masterDAO object
    
    MasterAction->>MasterDAO: getpartArrayList(partNo)
    
    MasterDAO->>Database: Query CatPart WHERE partNo LIKE ? ORDER BY partNo
    Database-->>MasterDAO: ResultSet part list
    MasterDAO->>MasterDAO: Map results to masterForm objects
    MasterDAO-->>MasterAction: ArrayList partArrayList
    
    MasterAction->>Browser: Set request attribute partArrayList
    MasterAction->>StrutsFramework: Forward to downloadPartlistExcel
    StrutsFramework->>Browser: Render Excel export page
    Browser-->>User: Download Part List Excel file with part details
```

---

## 9. Upload SAP Part Master Flow

This diagram shows the process of initializing the SAP Part Master upload page.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant MasterAction as masterAction

    User->>Browser: Click Upload SAP Part Master Tile
    Browser->>StrutsFramework: GET /masterAction.do?option=initUploadSAPPartMaster
    StrutsFramework->>MasterAction: initUploadSAPPartMaster(masterForm, request, response)
    
    MasterAction->>StrutsFramework: Forward to uploadSAPPart
    StrutsFramework->>Browser: Render upload SAP part master page
    Browser-->>User: Display upload form for SAP part master Excel file
```

---

## Summary

The Catalogue Module handles:

1. **Part Master Management**: Viewing and managing parts/lubes catalog with job type and model associations
2. **Part Lookup**: AJAX-based part number and description lookup with price retrieval
3. **Standard Job Part Assignment**: Associating parts/lubes with specific job types and models with quantities
4. **Part Price Management**: Retrieving part prices based on price list codes
5. **Part Validation**: Checking if part numbers exist in the catalog
6. **Part List Export**: Downloading part lists to Excel format
7. **SAP Integration**: Uploading part master data from SAP systems

All flows integrate with the SQL Server database through Hibernate ORM and JDBC connections. The module uses the `CatPart` table for part catalog data, `Standardjobpartmaster` for job type-part associations, and `SpPriceMaster` for pricing information. The module supports dealer-level operations with appropriate access controls based on user permissions.

