# Spares Module Sequence Diagrams

This document contains detailed sequence diagrams for all flows within the Spares Module of the ITLDIS system. The Spares module is integrated across Service, Inventory, Reports, and PI (Purchase Indent) modules.

## Table of Contents

1. [Add Spares to Job Card Estimate Flow](#1-add-spares-to-job-card-estimate-flow)
2. [Add Spares to Job Card Actual Flow](#2-add-spares-to-job-card-actual-flow)
3. [Get Part Number by AJAX Flow](#3-get-part-number-by-ajax-flow)
4. [Get Part Price by Part Number Flow (AJAX)](#4-get-part-price-by-part-number-flow-ajax)
5. [Get Part Description by AJAX Flow](#5-get-part-description-by-ajax-flow)
6. [Get Part Price by Part Description Flow (AJAX)](#6-get-part-price-by-part-description-flow-ajax)
7. [Check Part Number Flow (AJAX)](#7-check-part-number-flow-ajax)
8. [View Inventory Flow](#8-view-inventory-flow)
9. [Counter Sale Flow](#9-counter-sale-flow)
10. [Spares & Lubes Report Flow](#10-spares--lubes-report-flow)
11. [Create PI for Spares Flow](#11-create-pi-for-spares-flow)

---

## 1. Add Spares to Job Card Estimate Flow

This diagram shows the process of adding spare parts to a job card estimate.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant JobcardSparesCharges as JobcardSparesCharges
    participant Jobcarddetails as Jobcarddetails
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Fill job card estimate form with spare parts (partNo, partDesc, quantity, unitPrice, billCode, warranty, finalAmount)
    User->>Browser: Click Save Estimate button
    Browser->>StrutsFramework: POST /serviceProcess.do?option=addEstimate
    StrutsFramework->>ServiceAction: addEstimate(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>ServiceAction: String userid
    
    ServiceAction->>ServiceDAO: addEstimate(sf, userid)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session hrbsession
    
    ServiceDAO->>ServiceDAO: hrbsession.beginTransaction()
    
    ServiceDAO->>Database: DELETE FROM Estimateothercharges WHERE jobCardNo=?
    ServiceDAO->>Database: DELETE FROM Estimatelabourcharges WHERE jobCardNo=?
    ServiceDAO->>Database: DELETE FROM JobcardSparesCharges WHERE jobCardNo=?
    
    ServiceDAO->>Database: Load Jobcarddetails WHERE jobCardNo=?
    Database-->>ServiceDAO: Jobcarddetails jd
    
    alt Spare parts array is not null
        loop For each spare part
            alt Part details are valid (partNo, partDesc, unitPrice, quantity, amount, billCode, warranty, finalAmount not empty)
                ServiceDAO->>JobcardSparesCharges: new JobcardSparesCharges()
                ServiceDAO->>JobcardSparesCharges: Set jobCardNo, jobSpareID (jobCardNo-partNo), partNo, partDesc, unitPrice, qty, partCategory="SPARES", amount, billID, discInPerc, finalAmount
                ServiceDAO->>Database: hrbsession.save(jsc)
                Database-->>ServiceDAO: Save result
            end
        end
    end
    
    alt Lubes array is not null
        loop For each lube part
            alt Lube details are valid
                ServiceDAO->>JobcardSparesCharges: new JobcardSparesCharges()
                ServiceDAO->>JobcardSparesCharges: Set jobSpareID, partNo, jobCardNo, qty, partDesc, unitPrice, partCategory="LUBES", amount, billID, discInPerc, finalAmount
                ServiceDAO->>Database: hrbsession.save(jsc)
                Database-->>ServiceDAO: Save result
            end
        end
    end
    
    ServiceDAO->>Jobcarddetails: Set ttlEstimatePartsAmt, ttlEstimateLubesAmt, ttlEstimateLabourChargesAmt, ttlEstimateOtherChargesAmt
    ServiceDAO->>Database: hrbsession.saveOrUpdate(jd)
    Database-->>ServiceDAO: Update result
    
    ServiceDAO->>ServiceDAO: hrbsession.getTransaction().commit()
    ServiceDAO-->>ServiceAction: "Success@@estimateSuccess"
    
    ServiceAction->>Browser: Set request attributes (result="SUCCESS", message)
    ServiceAction->>StrutsFramework: Forward to success
    StrutsFramework->>Browser: Render success page
    Browser-->>User: Display "Estimate Information has been Successfully Added"
```

---

## 2. Add Spares to Job Card Actual Flow

This diagram shows the process of adding actual spare parts used to a job card.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant JobcardSparesActualcharges as JobcardSparesActualcharges
    participant Jobcarddetails as Jobcarddetails
    participant Billmaster as Billmaster
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Fill job card actual form with spare parts used (partNo, partDesc, quantity, unitPrice, billCode, warranty, modifiedPartsUsed, vendorName, complaintCode, causalCode)
    User->>Browser: Click Save Actual button
    Browser->>StrutsFramework: POST /serviceProcess.do?option=addActual
    StrutsFramework->>ServiceAction: addActual(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>ServiceAction: String userid
    
    ServiceAction->>ServiceDAO: addActual(sf, userid)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session hrbsession
    
    ServiceDAO->>ServiceDAO: hrbsession.beginTransaction()
    
    ServiceDAO->>Database: Query Billmaster WHERE warrantyType='Yes'
    Database-->>ServiceDAO: List of warranty bill IDs
    ServiceDAO->>ServiceDAO: Build billcomparelist
    
    ServiceDAO->>Database: Load Jobcarddetails WHERE jobCardNo=?
    Database-->>ServiceDAO: Jobcarddetails jd
    
    ServiceDAO->>ServiceDAO: Check if actuals entry exists (isExistActual)
    
    alt Actuals entry exists
        ServiceDAO->>Database: DELETE FROM Actualsothercharges WHERE jobCardNo=?
        ServiceDAO->>Database: DELETE FROM Actualslabourcharges WHERE jobCardNo=?
        ServiceDAO->>Database: DELETE FROM JobcardSparesActualcharges WHERE jobCardNo=?
    end
    
    alt Spare parts array is not null
        loop For each spare part
            alt Part details are valid
                ServiceDAO->>JobcardSparesActualcharges: new JobcardSparesActualcharges()
                ServiceDAO->>JobcardSparesActualcharges: Set jobCardNo, jobSpareID, partNo, partDesc, unitPrice, qty, partCategory="SPARES", amount, billID, modifiedPartsUsed, vendorName, discInPerc, finalAmount, cmpNo, causalOrConseq
                
                alt BillID is warranty type
                    ServiceDAO->>Jobcarddetails: Set wtyClaimStatus="REQUIRED", iswarrantyreq='Y'
                end
                
                ServiceDAO->>Database: hrbsession.save(jsc)
                Database-->>ServiceDAO: Save result
            end
        end
    end
    
    alt Lubes array is not null
        loop For each lube part
            alt Lube details are valid
                ServiceDAO->>JobcardSparesActualcharges: new JobcardSparesActualcharges()
                ServiceDAO->>JobcardSparesActualcharges: Set all lube fields, partCategory="LUBES"
                ServiceDAO->>Database: hrbsession.save(jsc)
                Database-->>ServiceDAO: Save result
            end
        end
    end
    
    ServiceDAO->>Jobcarddetails: Update job card with actual amounts and warranty status
    ServiceDAO->>Database: hrbsession.saveOrUpdate(jd)
    Database-->>ServiceDAO: Update result
    
    ServiceDAO->>ServiceDAO: hrbsession.getTransaction().commit()
    ServiceDAO-->>ServiceAction: "Success@@actualSuccess"
    
    ServiceAction->>Browser: Set request attributes (result="SUCCESS", message)
    ServiceAction->>StrutsFramework: Forward to success
    StrutsFramework->>Browser: Render success page
    Browser-->>User: Display "Actual Information has been Successfully Added"
```

---

## 3. Get Part Number by AJAX Flow

This diagram shows the AJAX flow for retrieving part numbers based on partial input.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"

    User->>Browser: Type part number in form field
    Browser->>Browser: Trigger AJAX call on input/change
    Browser->>StrutsFramework: GET /serviceProcess.do?option=getPartNumberAjax&partno=PART123&comptype=SPARES
    StrutsFramework->>ServiceAction: getPartNumberAjax(serviceForm, request, response)
    
    ServiceAction->>ServiceAction: Extract partno, comptype from request parameters
    
    ServiceAction->>ServiceDAO: getComponentList(partno, comptype, "partNo", "Partmaster", "partType")
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    
    ServiceDAO->>Database: Query Partmaster WHERE partNo LIKE ? AND partType=?
    Database-->>ServiceDAO: ResultSet matching part numbers
    
    ServiceDAO->>ServiceDAO: Build JSON/XML string with part numbers and descriptions
    ServiceDAO-->>ServiceAction: String result (formatted part list)
    
    ServiceAction->>Browser: Write result to response PrintWriter
    Browser->>Browser: Parse result and populate dropdown/autocomplete
    Browser-->>User: Display matching part numbers in dropdown
```

---

## 4. Get Part Price by Part Number Flow (AJAX)

This diagram shows the AJAX flow for retrieving part price based on part number.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Select part number from dropdown
    Browser->>Browser: Trigger AJAX call
    Browser->>StrutsFramework: GET /serviceProcess.do?option=getPartPriceBypartNo&partno=PART123
    StrutsFramework->>ServiceAction: getPartPriceBypartNo(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getAttribute("priceListCode")
    HTTPSession-->>ServiceAction: String priceListCode
    
    ServiceAction->>ServiceAction: Extract partno from request parameter
    
    ServiceAction->>ServiceDAO: getPriceByPartNo(partno, "p1", "SpPriceMaster", "CatPart", "price", "partNo", priceListCode)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    
    ServiceDAO->>Database: Query SpPriceMaster JOIN CatPart WHERE CatPart.partNo=? AND SpPriceMaster.priceListCode=? AND SpPriceMaster.partNo=CatPart.partNo
    Database-->>ServiceDAO: ResultSet with price information
    
    ServiceDAO->>ServiceDAO: Extract price and format result
    ServiceDAO-->>ServiceAction: String result (price value)
    
    ServiceAction->>Browser: Write result to response PrintWriter
    Browser->>Browser: Parse result and update Unit Price field
    Browser-->>User: Display part price in form
```

---

## 5. Get Part Description by AJAX Flow

This diagram shows the AJAX flow for retrieving part descriptions based on partial input.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"

    User->>Browser: Type part description in form field
    Browser->>Browser: Trigger AJAX call on input/change
    Browser->>StrutsFramework: GET /serviceProcess.do?option=getPartDescAjax&partDesc=ENGINE&comptype=SPARES
    StrutsFramework->>ServiceAction: getPartDescAjax(serviceForm, request, response)
    
    ServiceAction->>ServiceAction: Extract partDesc, comptype from request parameters
    
    ServiceAction->>ServiceDAO: getComponentList(partDesc, comptype, "p1", "CatPart", "partType")
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    
    ServiceDAO->>Database: Query CatPart WHERE p1 LIKE ? AND partType=?
    Database-->>ServiceDAO: ResultSet matching part descriptions
    
    ServiceDAO->>ServiceDAO: Build JSON/XML string with part descriptions and numbers
    ServiceDAO-->>ServiceAction: String result (formatted part list)
    
    ServiceAction->>Browser: Write result to response PrintWriter
    Browser->>Browser: Parse result and populate dropdown/autocomplete
    Browser-->>User: Display matching part descriptions in dropdown
```

---

## 6. Get Part Price by Part Description Flow (AJAX)

This diagram shows the AJAX flow for retrieving part price based on part description.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Select part description from dropdown
    Browser->>Browser: Trigger AJAX call
    Browser->>StrutsFramework: GET /serviceProcess.do?option=getPartPriceBypartDesc&partDesc=ENGINE OIL FILTER
    StrutsFramework->>ServiceAction: getPartPriceBypartDesc(serviceForm, request, response)
    
    ServiceAction->>HTTPSession: request.getSession()
    HTTPSession-->>ServiceAction: HttpSession session
    
    ServiceAction->>HTTPSession: session.getAttribute("priceListCode")
    HTTPSession-->>ServiceAction: String priceListCode
    
    ServiceAction->>ServiceAction: Extract partDesc from request parameter
    
    ServiceAction->>ServiceDAO: getPart_in_db(partDesc)
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    ServiceDAO->>Database: Query CatPart WHERE p1=? LIMIT 1
    Database-->>ServiceDAO: Part number
    ServiceDAO-->>ServiceAction: String partno
    
    ServiceAction->>ServiceDAO: getPriceByPartNo(partno, "partNo", "SpPriceMaster", "CatPart", "price", "partNo", priceListCode)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    
    ServiceDAO->>Database: Query SpPriceMaster JOIN CatPart WHERE CatPart.partNo=? AND SpPriceMaster.priceListCode=?
    Database-->>ServiceDAO: ResultSet with price information
    
    ServiceDAO->>ServiceDAO: Extract price and format result
    ServiceDAO-->>ServiceAction: String result (price value)
    
    ServiceAction->>Browser: Write result to response PrintWriter
    Browser->>Browser: Parse result and update Unit Price field
    Browser-->>User: Display part price in form
```

---

## 7. Check Part Number Flow (AJAX)

This diagram shows the AJAX flow for validating if a part number exists in the system.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ServiceAction as serviceAction
    participant ServiceDAO as serviceDAO
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"

    User->>Browser: Enter part number in form field
    Browser->>Browser: Trigger AJAX call on blur/change
    Browser->>StrutsFramework: GET /serviceProcess.do?option=getPartCheck&partno=PART123
    StrutsFramework->>ServiceAction: getPartCheck(serviceForm, request, response)
    
    ServiceAction->>ServiceAction: Extract partno from request parameter
    
    ServiceAction->>ServiceDAO: getPartNoCheck(partno)
    
    ServiceDAO->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ServiceDAO: Session session
    
    ServiceDAO->>Database: Query CatPart WHERE partNo=?
    Database-->>ServiceDAO: ResultSet part check
    
    alt Part exists
        ServiceDAO->>ServiceDAO: Set result = "exists" or part details
    else Part does not exist
        ServiceDAO->>ServiceDAO: Set result = "notexists"
    end
    
    ServiceDAO-->>ServiceAction: String result
    
    ServiceAction->>Browser: Write result to response PrintWriter
    Browser->>Browser: Parse result
    alt Part exists
        Browser-->>User: Enable form fields or show success indicator
    else Part does not exist
        Browser-->>User: Show error message "Part number not found"
    end
```

---

## 8. View Inventory Flow

This diagram shows the process of viewing spare parts inventory.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant InvtoryAction as InvtoryAction
    participant InventoryDAO as inventoryDAO
    participant MethodUtility as MethodUtility
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click View Inventory Tile or submit search
    Browser->>StrutsFramework: GET /inventoryAction.do?option=viewInventory (with optional parameters: dealerCode, etype)
    StrutsFramework->>InvtoryAction: viewInventory(inventoryForm, request, response)
    
    InvtoryAction->>HTTPSession: request.getSession()
    HTTPSession-->>InvtoryAction: HttpSession session
    
    InvtoryAction->>HTTPSession: session.getAttribute("priceListCode"), session.getAttribute("userFun"), session.getAttribute("user_id")
    HTTPSession-->>InvtoryAction: String priceListCode, Vector userFunctionalities, String user_id
    
    InvtoryAction->>MethodUtility: getDealersDetailsUnderUser(user_id)
    MethodUtility->>Database: Query dealer details based on user permissions
    Database-->>MethodUtility: Dealer list
    MethodUtility-->>InvtoryAction: List dealerList
    
    alt User has functionality 101 (Dealer)
        InvtoryAction->>HTTPSession: session.getAttribute("dealerCode")
        HTTPSession-->>InvtoryAction: String dealerCode
        InvtoryAction->>InventoryDAO: getInventoryList(invForm, priceListCode)
        InventoryDAO->>Database: Query inventory with dealer code filter
        Database-->>InventoryDAO: Inventory list with spare parts
        InventoryDAO-->>InvtoryAction: ArrayList inventoryList
        
        alt etype equals export
            InvtoryAction->>Browser: Set request attribute inventoryList
            InvtoryAction->>StrutsFramework: Forward to exportinventory
            StrutsFramework->>Browser: Render export inventory page
            Browser-->>User: Display export page
        else etype is null
            InvtoryAction->>Browser: Set request attribute inventoryList
            InvtoryAction->>StrutsFramework: Forward to viewinventory
            StrutsFramework->>Browser: Render view inventory page
            Browser-->>User: Display inventory list with spare parts
        end
    else User has other functionalities (HO/Regional)
        InvtoryAction->>Browser: Set request attribute dealerList
        
        alt getDataFlag equals true
            InvtoryAction->>InventoryDAO: getInventoryList(invForm, priceListCode)
            InventoryDAO->>Database: Query inventory with dealer code filter
            Database-->>InventoryDAO: Inventory list
            InventoryDAO-->>InvtoryAction: ArrayList inventoryList
            InvtoryAction->>Browser: Set request attribute inventoryList
        end
        
        alt etype equals export
            InvtoryAction->>StrutsFramework: Forward to exportinventory
            StrutsFramework->>Browser: Render export inventory page
            Browser-->>User: Display export page
        else etype is null
            InvtoryAction->>StrutsFramework: Forward to viewinventory4HO
            StrutsFramework->>Browser: Render view inventory for HO page
            Browser-->>User: Display inventory list with dealer filter
        end
    end
```

---

## 9. Counter Sale Flow

This diagram shows the process of performing a counter sale of spare parts.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant InvtoryAction as InvtoryAction
    participant InventoryDAO as inventoryDAO
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Counter Sale Tile
    Browser->>StrutsFramework: GET /inventoryAction.do?option=initCounterSale
    StrutsFramework->>InvtoryAction: initCounterSale(inventoryForm, request, response)
    
    InvtoryAction->>InventoryDAO: getCategoryList()
    InventoryDAO->>Database: Query customer categories
    Database-->>InventoryDAO: Category list
    InventoryDAO-->>InvtoryAction: LinkedHashSet categoryList
    
    InvtoryAction->>Browser: Set request attribute categoryList
    InvtoryAction->>StrutsFramework: Forward to initCounterSale
    StrutsFramework->>Browser: Render counter sale form
    Browser-->>User: Display counter sale form with category dropdown
    
    User->>Browser: Select customer category
    Browser->>StrutsFramework: GET /inventoryAction.do?option=getcustNameByCustcategoryID&custcategoryID=CAT001
    StrutsFramework->>InvtoryAction: getcustNameByCustcategoryID(inventoryForm, request, response)
    
    InvtoryAction->>HTTPSession: session.getAttribute("dealerCode")
    HTTPSession-->>InvtoryAction: String dealerCode
    
    InvtoryAction->>InventoryDAO: getcustNameByCustcategoryID(invForm)
    InventoryDAO->>Database: Query customers by category and dealer
    Database-->>InventoryDAO: Customer list
    InventoryDAO-->>InvtoryAction: String result (customer list)
    
    InvtoryAction->>Browser: Write result to response
    Browser->>Browser: Populate customer dropdown
    Browser-->>User: Display customer list
    
    User->>Browser: Select customer and add spare parts to cart, fill form
    User->>Browser: Click Save Counter Sale button
    Browser->>StrutsFramework: POST /inventoryAction.do?option=counterSale
    StrutsFramework->>InvtoryAction: counterSale(inventoryForm, request, response)
    
    InvtoryAction->>HTTPSession: session.getAttribute("dealerCode"), session.getAttribute("user_id"), session.getAttribute("sellingPercentage")
    HTTPSession-->>InvtoryAction: String dealerCode, String user_id, Double sellingPercentage
    
    InvtoryAction->>InventoryDAO: getcustNameById(invForm)
    InventoryDAO->>Database: Query customer details
    Database-->>InventoryDAO: Customer information
    
    InvtoryAction->>InventoryDAO: getTaxSaleTypeList()
    InventoryDAO->>Database: Query tax sale types
    Database-->>InventoryDAO: Tax type list
    
    InvtoryAction->>Browser: Set request attributes (billedList, saleTaxTypeList)
    InvtoryAction->>StrutsFramework: Forward to counterSale
    StrutsFramework->>Browser: Render counter sale entry form
    Browser-->>User: Display counter sale entry form
    
    User->>Browser: Fill spare parts details and submit
    Browser->>StrutsFramework: POST /inventoryAction.do?option=addCounterSale
    StrutsFramework->>InvtoryAction: addCounterSale(inventoryForm, request, response)
    
    InvtoryAction->>InventoryDAO: saveCounterSale(invForm, user_id)
    InventoryDAO->>Database: Insert SpInventSaleMaster, SpInventSaleDetail records
    Database-->>InventoryDAO: Save result
    
    alt Result is success
        InvtoryAction->>Browser: Set request attributes (result="SUCCESS", show_message=invNo, printInvoiceLink, addMoreLink)
        InvtoryAction->>StrutsFramework: Forward to message
        StrutsFramework->>Browser: Render success page
        Browser-->>User: Display "Counter Sale has been Successfully Added" with invoice number
    else Result is failure
        InvtoryAction->>Browser: Set request attributes (result="FAILURE", show_message="")
        InvtoryAction->>StrutsFramework: Forward to message
        StrutsFramework->>Browser: Render error page
        Browser-->>User: Display error message
    end
```

---

## 10. Spares & Lubes Report Flow

This diagram shows the process of generating Spares & Lubes reports.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ReportAction as ReportAction
    participant ReportDao as ReportDao
    participant JasperReports as JasperReports
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Spares & Lubes Report Tile
    Browser->>StrutsFramework: GET /reportAction.do?option=getListSpareLubeReport&flag=flagValue
    StrutsFramework->>ReportAction: getListSpareLubeReport(serviceForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>ReportAction: String user_id
    
    ReportAction->>ReportAction: Extract flag parameter
    
    ReportAction->>ReportDao: getHierarchyList(user_id, flag)
    ReportDao->>Database: Query hierarchy (country, zone, state, CCM, CCE, dealer) based on user permissions
    Database-->>ReportDao: Hierarchy list
    ReportDao-->>ReportAction: ArrayList country (hierarchy list)
    
    ReportAction->>Browser: Set request attribute country
    ReportAction->>StrutsFramework: Forward to viewSpareLube
    StrutsFramework->>Browser: Render sparesAndLubesReport.jsp
    Browser-->>User: Display report criteria form (country, zone, state, CCM, CCE, dealer, financial year, month, week)
    
    User->>Browser: Select report criteria and click Generate Report
    Browser->>StrutsFramework: POST /reportAction.do?option=spareAndLubeReport&country=IND&zone=Z1&state=MH&ccm=CCM1&cce=CCE1&dealer=DLR001&finYear=2023-24&month=12&week=1
    StrutsFramework->>ReportAction: spareAndLubeReport(serviceForm, request, response)
    
    ReportAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>ReportAction: String user_id
    
    ReportAction->>ReportAction: Extract report parameters (country, zone, state, ccm, cce, dealer, finYear, month, week)
    ReportAction->>ReportAction: Calculate weekName and monthDisplay
    
    ReportAction->>ReportAction: Build parameterMap with all report parameters
    
    ReportAction->>Database: Get connection
    Database-->>ReportAction: Connection conn
    
    ReportAction->>JasperReports: JasperFillManager.fillReport("spareAndLube.jasper", parameterMap, conn)
    JasperReports->>Database: Execute stored procedure/queries for spares and lubes data
    Database-->>JasperReports: Report data
    JasperReports-->>ReportAction: JasperPrint jasperPrint
    
    ReportAction->>Browser: Set request attributes (jasperPrint, reportType="xls", reportName="Spares & Lubes Review Report")
    ReportAction->>StrutsFramework: Forward to downloadReport
    StrutsFramework->>Browser: Render download report page
    Browser-->>User: Download Spares & Lubes Review Report (Excel format)
```

---

## 11. Create PI for Spares Flow

This diagram shows the process of creating a Purchase Indent (PI) for spare parts.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant CreatePIAction as CreatePIAction
    participant CreatePIDao as CreatePIDao
    participant MethodUtility as MethodUtility
    participant PriceDetails as PriceDetails
    participant ConnectionHolder as ConnectionHolder
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Create PI Tile
    Browser->>StrutsFramework: GET /createPIAction.do?option=getorderListForCreatePI
    StrutsFramework->>CreatePIAction: getorderListForCreatePI(PIFormBean, request, response)
    
    CreatePIAction->>HTTPSession: request.getSession()
    HTTPSession-->>CreatePIAction: HttpSession session
    
    CreatePIAction->>HTTPSession: session.getAttribute("userFun"), session.getAttribute("user_id")
    HTTPSession-->>CreatePIAction: Vector userFunctionalities, String user_id
    
    CreatePIAction->>MethodUtility: getDealersDetailsUnderUser(user_id)
    MethodUtility->>Database: Query dealer details
    Database-->>MethodUtility: Dealer list
    MethodUtility-->>CreatePIAction: List dealerList
    
    alt User has functionality 101
        CreatePIAction->>HTTPSession: session.getAttribute("dealerCode")
        HTTPSession-->>CreatePIAction: String dealerCode
        CreatePIAction->>CreatePIAction: Set dealerCode from session
    end
    
    CreatePIAction->>CreatePIDao: getPOListExpoDealer(piForm)
    CreatePIDao->>Database: Query purchase orders for dealer
    Database-->>CreatePIDao: Purchase order list
    CreatePIDao-->>CreatePIAction: List orderList
    
    CreatePIAction->>Browser: Set request attributes (dealerList, orderList, buyer)
    CreatePIAction->>StrutsFramework: Forward to initcreatePIforExpoOrder
    StrutsFramework->>Browser: Render create PI form
    Browser-->>User: Display purchase order list with checkboxes
    
    User->>Browser: Select purchase orders and click Get Order Details
    Browser->>StrutsFramework: POST /createPIAction.do?option=getorderDetailList&checkedPOList=PO001,PO002
    StrutsFramework->>CreatePIAction: getorderDetailList(PIFormBean, request, response)
    
    CreatePIAction->>HTTPSession: session.getAttribute("servletapp.connection")
    HTTPSession-->>CreatePIAction: ConnectionHolder holder
    
    CreatePIAction->>ConnectionHolder: holder.getConnection()
    ConnectionHolder-->>CreatePIAction: Connection conn
    
    CreatePIAction->>PriceDetails: new PriceDetails(conn)
    PriceDetails-->>CreatePIAction: PriceDetails object
    
    CreatePIAction->>CreatePIDao: getPOListDetails(piForm, pObj)
    CreatePIDao->>Database: Query purchase order details with spare parts
    Database-->>CreatePIDao: Order detail list with spare parts
    CreatePIDao-->>CreatePIAction: List orderDtlList
    
    CreatePIAction->>CreatePIDao: getOrderDetail(piForm, cus_po_no)
    CreatePIDao->>Database: Query order header details
    Database-->>CreatePIDao: Order header information
    
    CreatePIAction->>MethodUtility: getCommonLabelValueHiber("MSPPaymentTermsEXPMaster", ...)
    MethodUtility->>Database: Query payment terms
    Database-->>MethodUtility: Payment terms list
    MethodUtility-->>CreatePIAction: Payment terms list
    
    CreatePIAction->>CreatePIDao: getOtherChargeList()
    CreatePIDao->>Database: Query other charges
    Database-->>CreatePIDao: Other charge list
    
    CreatePIAction->>Browser: Set request attributes (orderList, paymentTermsList, otherChargeList)
    CreatePIAction->>StrutsFramework: Forward to createPIExpoOrder
    StrutsFramework->>Browser: Render create PI detail form
    Browser-->>User: Display PI form with spare parts from purchase orders
    
    User->>Browser: Fill PI details and click Save PI
    Browser->>StrutsFramework: POST /createPIAction.do?option=savePIDetails
    StrutsFramework->>CreatePIAction: savePIDetails(PIFormBean, request, response)
    
    CreatePIAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>CreatePIAction: String userId
    
    CreatePIAction->>CreatePIAction: Validate token
    
    alt Token is valid
        CreatePIAction->>CreatePIDao: savePIDetails(piForm, userId)
        CreatePIDao->>Database: Insert PI master and detail records with spare parts
        Database-->>CreatePIDao: Save result
        CreatePIDao-->>CreatePIAction: String result ("success" or "failure")
        
        CreatePIAction->>CreatePIAction: Reset token
        
        alt Result is success
            CreatePIAction->>Browser: Set request attributes (result="SUCCESS", show_message="PI created successfully: [PI_NO]", addMoreLink)
            CreatePIAction->>StrutsFramework: Forward to message
            StrutsFramework->>Browser: Render success page
            Browser-->>User: Display "PI has been Successfully Created for PI No: [PI_NO]"
        else Result is failure
            CreatePIAction->>Browser: Set request attributes (result="FAILURE", show_message="")
            CreatePIAction->>StrutsFramework: Forward to message
            StrutsFramework->>Browser: Render error page
            Browser-->>User: Display "PI Creation Failure"
        end
    else Token is invalid
        CreatePIAction->>Browser: Set error message
        Browser-->>User: Display error message
    end
```

---

## Summary

The Spares Module handles:

1. **Job Card Integration**: Adding spare parts to job card estimates and actuals with warranty tracking
2. **Part Lookup**: AJAX-based part number and description lookup with price retrieval
3. **Inventory Management**: Viewing and managing spare parts inventory across dealers
4. **Counter Sales**: Selling spare parts directly to customers with invoice generation
5. **Reporting**: Generating comprehensive Spares & Lubes reports with hierarchy filters
6. **Purchase Indents**: Creating PIs for spare parts procurement from purchase orders
7. **Price Management**: Retrieving part prices based on price list codes and dealer settings
8. **Warranty Tracking**: Identifying warranty-eligible spare parts and updating job card warranty status

All flows integrate with the SQL Server database through Hibernate ORM and use stored procedures for complex queries. The module supports dealer-level and HO/Regional-level operations with appropriate access controls.

