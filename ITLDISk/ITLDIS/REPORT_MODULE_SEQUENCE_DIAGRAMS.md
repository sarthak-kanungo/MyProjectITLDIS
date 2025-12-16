# Report Module Sequence Diagrams

This document contains detailed sequence diagrams for all flows within the Report Module of the ITLDIS system.

## Table of Contents

1. [MIS Report (DMS Report) Flow](#1-mis-report-dms-report-flow)
2. [Spares & Lubes Report Flow](#2-spares--lubes-report-flow)
3. [Spares & Lubes Rolling Report Flow](#3-spares--lubes-rolling-report-flow)
4. [Spares & Lubes Report STK Flow](#4-spares--lubes-report-stk-flow)
5. [Spares & Lubes Rolling Report STK Flow](#5-spares--lubes-rolling-report-stk-flow)
6. [Sale Invoice Report Flow](#6-sale-invoice-report-flow)
7. [Pending PI Confirmation Report Flow](#7-pending-pi-confirmation-report-flow)
8. [Order Invoice Detail Report Flow](#8-order-invoice-detail-report-flow)
9. [GSTR1 Report Flow](#9-gstr1-report-flow)
10. [GSTR2 Report Flow](#10-gstr2-report-flow)

---

## 1. MIS Report (DMS Report) Flow

This diagram shows the complete MIS Report generation process including job types, installations, job cards, spares/lubes, and warranty claims.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ReportAction as ReportAction
    participant ReportDao as ReportDao
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant MethodUtility as MethodUtility
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click MIS Report Tile
    Browser->>StrutsFramework: GET /reportAction.do?option=initMisReport
    StrutsFramework->>ReportAction: initMisReport(reportForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HTTPSession: session.getAttribute("userFun"), session.getAttribute("user_id")
    HTTPSession-->>ReportAction: Vector userFunctionalities, String user_id
    
    ReportAction->>MethodUtility: getDealersDetailsUnderUser(user_id)
    MethodUtility->>Database: Query dealer details based on user permissions
    Database-->>MethodUtility: Dealer list
    MethodUtility-->>ReportAction: List dealerList
    
    alt User has functionality 101
        ReportAction->>HTTPSession: session.getAttribute("dealerCode")
        HTTPSession-->>ReportAction: String dealerCode
        ReportAction->>ReportAction: Set dealerCode from session
    else User has other functionalities
        ReportAction->>Browser: Set request attribute dealerList
    end
    
    ReportAction->>StrutsFramework: Forward to success
    StrutsFramework->>Browser: Render misReportDealer.jsp
    Browser-->>User: Display MIS Report search form
    
    User->>Browser: Select dealer code, from date, to date
    User->>Browser: Click Generate Report button
    Browser->>StrutsFramework: POST /reportAction.do?option=generateMisReport&dealerCode=DLR001&fromDate=01/01/2024&toDate=31/12/2024&etype=view
    StrutsFramework->>ReportAction: generateMisReport(reportForm, request, response)
    
    ReportAction->>ReportAction: Parse fromDate and toDate
    ReportAction->>ReportAction: Convert dates to yyyy-MM-dd format
    
    ReportAction->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ReportAction: Session hSession
    
    ReportAction->>ReportDao: getJobTypes(dealerCode, inputFromDate, inputToDate, rptForm, hSession)
    
    ReportDao->>Database: EXEC SP_DMIS_JobTypes :dealerCode, :fromDate, :toDate
    Database-->>ReportDao: ResultSet job types (jobTypeDesc, monthCount, yearCount)
    ReportDao->>ReportDao: Calculate MTD and YTM totals
    ReportDao->>ReportDao: Set jobTypeDesc, monthCount, yearCount arrays
    ReportDao->>ReportDao: getJobTypeInstallation(dealerCode, fromDate, toDate, rptForm, session)
    
    ReportDao->>Database: EXEC SP_DMIS_Installations :dealerCode, :fromDate, :toDate
    Database-->>ReportDao: ResultSet installations (monthDeliveryCnt, monthInsCnt, monthInvalidCnt, monthInvalidPer, monthPendingIns, monthPendingPer, yearDeliveryCnt, yearInsCnt, yearInvalidCnt, yearInvalidPer, yearPendingIns, yearPendingPer)
    ReportDao->>ReportDao: Set installation counts and percentages
    ReportDao->>ReportDao: getNoOfJobCardTractorAttendedAndLabourEarned(dealerCode, fromDate, toDate, rptForm, session)
    
    ReportDao->>Database: EXEC SP_DMIS_JobCards :dealerCode, :fromDate, :toDate
    Database-->>ReportDao: ResultSet job cards (jobCardColumnName, jobCardMonthCount, jobCardMonthLabour, jobCadYearCount, jobCardYearLabour)
    ReportDao->>ReportDao: Calculate totals for job cards and labour
    ReportDao->>ReportDao: Set job card arrays and totals
    ReportDao->>ReportDao: getSparesLubes(dealerCode, fromDate, toDate, rptForm, session)
    
    ReportDao->>Database: EXEC SP_DMIS_SPARELUBE :dealerCode, :fromDate, :toDate
    Database-->>ReportDao: ResultSet spares and lubes data
    ReportDao->>ReportDao: Set listOfLubes
    ReportDao->>ReportDao: getWarrantyClaimed(dealerCode, fromDate, toDate, rptForm, session)
    
    ReportDao->>Database: EXEC SP_DMIS_Claims :dealerCode, :fromDate, :toDate
    Database-->>ReportDao: ResultSet warranty claims (month and year data)
    ReportDao->>ReportDao: Set listOfWarrantyClaimMonth and listOfWarrantyClaimYear
    ReportDao->>ReportDao: getDealerName(dealerCode, rptForm, session)
    
    ReportDao->>Database: Query Dealervslocationcode WHERE dealerCode=?
    Database-->>ReportDao: Dealer name with code
    ReportDao->>ReportDao: Set dealerNameWithCode
    ReportDao-->>ReportAction: ReportForm with all MIS data
    
    alt etype equals export
        ReportAction->>Browser: Set request attributes (reportForm with all data)
        ReportAction->>StrutsFramework: Forward to exportviewMisDMSReport
        StrutsFramework->>Browser: Render exportviewMisReportDMS.jsp
        Browser-->>User: Display export page with MIS data
    else etype is null or view
        ReportAction->>Browser: Set request attributes (reportForm with all data)
        ReportAction->>StrutsFramework: Forward to viewMisDMSReport
        StrutsFramework->>Browser: Render viewMisReportDMS.jsp
        Browser-->>User: Display MIS Report with all sections (Job Types, Installations, Job Cards, Spares/Lubes, Warranty Claims)
    end
```

---

## 2. Spares & Lubes Report Flow

This diagram shows the Spares & Lubes Review Report generation with hierarchy filters.

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
    Browser->>StrutsFramework: GET /reportAction.do?option=getListSpareLubeReport&flag=DLR
    StrutsFramework->>ReportAction: getListSpareLubeReport(reportForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>ReportAction: String user_id
    
    ReportAction->>ReportAction: Extract flag parameter
    
    ReportAction->>ReportDao: getHierarchyList(user_id, flag)
    ReportDao->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ReportDao: Session hrbSession
    ReportDao->>Database: SELECT DISTINCT country FROM FN_GetHierrachyDetailsUnderUser(?) WHERE FLAG=?
    Database-->>ReportDao: Country list
    ReportDao-->>ReportAction: ArrayList country (hierarchy list)
    
    ReportAction->>Browser: Set request attribute country
    ReportAction->>StrutsFramework: Forward to viewSpareLube
    StrutsFramework->>Browser: Render sparesAndLubesReport.jsp
    Browser-->>User: Display report criteria form (country, zone, state, CCM, CCE, dealer, financial year, month, week)
    
    User->>Browser: Select hierarchy filters and click Generate Report
    Browser->>StrutsFramework: POST /reportAction.do?option=spareAndLubeReport&country=IND&zone=Z1&state=MH&ccm=CCM1&cce=CCE1&dealer=DLR001&finYear=2023-24&month=12&week=1&flag=DLR
    StrutsFramework->>ReportAction: spareAndLubeReport(reportForm, request, response)
    
    ReportAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>ReportAction: String user_id
    
    ReportAction->>ReportAction: Extract report parameters (country, zone, state, ccm, cce, dealer, finYear, month, week)
    ReportAction->>ReportAction: Calculate weekName based on week number
    ReportAction->>ReportAction: Get monthDisplay from DateFormatSymbols
    
    ReportAction->>ReportAction: Build parameterMap with all report parameters (loginUserID, country, zone, state, ccm, cce, dealer, finYear, month, week, monthDisplay, weekName)
    
    ReportAction->>Database: Get connection
    Database-->>ReportAction: Connection conn
    
    ReportAction->>JasperReports: JasperFillManager.fillReport("spareAndLube.jasper", parameterMap, conn)
    JasperReports->>Database: Execute stored procedure/queries for spares and lubes data based on hierarchy filters
    Database-->>JasperReports: Report data
    JasperReports-->>ReportAction: JasperPrint jasperPrint
    
    ReportAction->>Browser: Set request attributes (jasperPrint, reportType="xls", reportName="Spares & Lubes Review Report")
    ReportAction->>StrutsFramework: Forward to downloadReport
    StrutsFramework->>Browser: Render downloadReport.jsp
    Browser-->>User: Download Spares & Lubes Review Report (Excel format)
```

---

## 3. Spares & Lubes Rolling Report Flow

This diagram shows the Spares & Lubes Rolling Report generation.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ReportAction as ReportAction
    participant JasperReports as JasperReports
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Spares & Lubes Rolling Report Tile
    Browser->>StrutsFramework: GET /reportAction.do?option=getListSpareLubeRolling&flag=DLR
    StrutsFramework->>ReportAction: getListSpareLubeRolling(reportForm, request, response)
    
    ReportAction->>StrutsFramework: Forward to viewSpareLubeRolling
    StrutsFramework->>Browser: Render spareAndLubesRolling.jsp
    Browser-->>User: Display rolling report criteria form (financial year, month)
    
    User->>Browser: Select financial year and month, click Generate Report
    Browser->>StrutsFramework: POST /reportAction.do?option=getRollingSpareLubeReport&finYear=2023-24&month=12&flag=DLR
    StrutsFramework->>ReportAction: getRollingSpareLubeReport(reportForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HTTPSession: session.getAttribute("dealerCode"), session.getAttribute("user_id")
    HTTPSession-->>ReportAction: String dealerCode, String user_id
    
    ReportAction->>ReportAction: Extract finYear and month parameters
    ReportAction->>ReportAction: Split finYear and get finYearDisplay
    ReportAction->>ReportAction: Get monthDisplay from DateFormatSymbols
    
    ReportAction->>ReportAction: Build parameterMap (month, monthDisplay, finYearDisplay, finYear, loginUserId)
    
    ReportAction->>Database: Get connection
    Database-->>ReportAction: Connection conn
    
    ReportAction->>JasperReports: JasperFillManager.fillReport("spareAndLubeRolling.jasper", parameterMap, conn)
    JasperReports->>Database: Execute stored procedure/queries for rolling spares and lubes data
    Database-->>JasperReports: Rolling report data
    JasperReports-->>ReportAction: JasperPrint jasperPrint
    
    ReportAction->>Browser: Set request attributes (jasperPrint, reportType="xls", reportName="Spares & Lubes Rolling Report")
    ReportAction->>StrutsFramework: Forward to downloadReport
    StrutsFramework->>Browser: Render downloadReport.jsp
    Browser-->>User: Download Spares & Lubes Rolling Report (Excel format)
```

---

## 4. Spares & Lubes Report STK Flow

This diagram shows the Spares & Lubes Report STK generation with hierarchy filters.

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

    User->>Browser: Click Spares & Lubes Report STK Tile
    Browser->>StrutsFramework: GET /reportAction.do?option=getListSpareLubeReportSTK&flag=STK
    StrutsFramework->>ReportAction: getListSpareLubeReportSTK(reportForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>ReportAction: String user_id
    
    ReportAction->>ReportAction: Extract flag parameter
    
    ReportAction->>ReportDao: getHierarchyList(user_id, flag)
    ReportDao->>Database: Query hierarchy based on user permissions
    Database-->>ReportDao: Country list
    ReportDao-->>ReportAction: ArrayList country
    
    ReportAction->>Browser: Set request attribute country
    ReportAction->>StrutsFramework: Forward to viewSpareLubeSTK
    StrutsFramework->>Browser: Render sparesAndLubesReportSTK.jsp
    Browser-->>User: Display STK report criteria form (country, zone, state, CCM, CCE, dealer, financial year, month, week)
    
    User->>Browser: Select hierarchy filters and click Generate Report
    Browser->>StrutsFramework: POST /reportAction.do?option=spareAndLubeReportSTK&country=IND&zone=Z1&state=MH&ccm=CCM1&cce=CCE1&dealer=DLR001&finYear=2023-24&month=12&week=1&flag=STK
    StrutsFramework->>ReportAction: spareAndLubeReportSTK(reportForm, request, response)
    
    ReportAction->>HTTPSession: session.getAttribute("user_id")
    HTTPSession-->>ReportAction: String user_id
    
    ReportAction->>ReportAction: Extract report parameters and calculate weekName, monthDisplay
    ReportAction->>ReportAction: Build parameterMap with all report parameters
    
    ReportAction->>Database: Get connection
    Database-->>ReportAction: Connection conn
    
    ReportAction->>JasperReports: JasperFillManager.fillReport("sparesReviewSTK.jasper", parameterMap, conn)
    JasperReports->>Database: Execute stored procedure/queries for STK spares and lubes data
    Database-->>JasperReports: STK report data
    JasperReports-->>ReportAction: JasperPrint jasperPrint
    
    ReportAction->>Browser: Set request attributes (jasperPrint, reportType="xls", reportName="Review Report(STK)")
    ReportAction->>StrutsFramework: Forward to downloadReport
    StrutsFramework->>Browser: Render downloadReport.jsp
    Browser-->>User: Download Spares & Lubes Review Report STK (Excel format)
```

---

## 5. Spares & Lubes Rolling Report STK Flow

This diagram shows the Spares & Lubes Rolling Report STK generation.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ReportAction as ReportAction
    participant JasperReports as JasperReports
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Spares & Lubes Rolling Report STK Tile
    Browser->>StrutsFramework: GET /reportAction.do?option=getListSpareLubeRollingSTK&flag=STK
    StrutsFramework->>ReportAction: getListSpareLubeRollingSTK(reportForm, request, response)
    
    ReportAction->>StrutsFramework: Forward to viewSpareLubeRollingSTK
    StrutsFramework->>Browser: Render spareAndLubesRollingSTK.jsp
    Browser-->>User: Display STK rolling report criteria form (financial year, month)
    
    User->>Browser: Select financial year and month, click Generate Report
    Browser->>StrutsFramework: POST /reportAction.do?option=getRollingSpareLubeReportSTK&finYear=2023-24&month=12&flag=STK
    StrutsFramework->>ReportAction: getRollingSpareLubeReportSTK(reportForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HTTPSession: session.getAttribute("dealerCode"), session.getAttribute("user_id")
    HTTPSession-->>ReportAction: String dealerCode, String user_id
    
    ReportAction->>ReportAction: Extract finYear and month parameters
    ReportAction->>ReportAction: Calculate finYearDisplay and monthDisplay
    ReportAction->>ReportAction: Build parameterMap (month, monthDisplay, finYearDisplay, finYear, loginUserId)
    
    ReportAction->>Database: Get connection
    Database-->>ReportAction: Connection conn
    
    ReportAction->>JasperReports: JasperFillManager.fillReport("sparesRollingSTK.jasper", parameterMap, conn)
    JasperReports->>Database: Execute stored procedure/queries for STK rolling spares and lubes data
    Database-->>JasperReports: STK rolling report data
    JasperReports-->>ReportAction: JasperPrint jasperPrint
    
    ReportAction->>Browser: Set request attributes (jasperPrint, reportType="xls", reportName="Spares & Lubes Rolling Report(STK)")
    ReportAction->>StrutsFramework: Forward to downloadReport
    StrutsFramework->>Browser: Render downloadReport.jsp
    Browser-->>User: Download Spares & Lubes Rolling Report STK (Excel format)
```

---

## 6. Sale Invoice Report Flow

This diagram shows the Sale Invoice Report generation with Part Wise and Customer Wise options.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ReportAction as ReportAction
    participant ReportDao as ReportDao
    participant MethodUtility as MethodUtility
    participant JXL as JXL (Excel Library)
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Sale Invoice Report Tile
    Browser->>StrutsFramework: GET /reportAction.do?option=viewSaleInvoiceReport
    StrutsFramework->>ReportAction: viewSaleInvoiceReport(reportForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HTTPSession: session.getAttribute("userFun"), session.getAttribute("user_id")
    HTTPSession-->>ReportAction: Vector userFunctionalities, String user_id
    
    ReportAction->>ReportAction: Extract flag parameter and set default dates if null
    
    ReportAction->>MethodUtility: getDealersDetailsUnderUser(user_id)
    MethodUtility->>Database: Query dealer details
    Database-->>MethodUtility: Dealer list
    MethodUtility-->>ReportAction: List dealerList
    
    ReportAction->>MethodUtility: getDealersCountryUnderUser(user_id)
    MethodUtility->>Database: Query country details
    Database-->>MethodUtility: Country list
    MethodUtility-->>ReportAction: List countryList
    
    alt User has functionality 101
        ReportAction->>HTTPSession: session.getAttribute("dealerCode")
        HTTPSession-->>ReportAction: String dealerCode
        ReportAction->>ReportAction: Set dealerCode from session
    else User has other functionalities
        ReportAction->>Browser: Set request attribute dealerList
    end
    
    ReportAction->>Browser: Set request attributes (countryList, range)
    ReportAction->>StrutsFramework: Forward to viewSaleInvoice
    StrutsFramework->>Browser: Render viewSaleInvoiceList.jsp
    Browser-->>User: Display Sale Invoice Report search form (invoice no, PI no, part no, from date, to date, country, dealer, radio: Part Wise/Customer Wise)
    
    User->>Browser: Select search criteria and report type (Part Wise or Customer Wise), click Generate Report
    Browser->>StrutsFramework: POST /reportAction.do?option=createSaleInvoiceReport&radio=part Wise&invNo=&piNo=&partnum=&fromdate=01/01/2024&todate=31/12/2024&country=IND@@India&dealerCode=DLR001
    StrutsFramework->>ReportAction: createSaleInvoiceReport(reportForm, request, response)
    
    ReportAction->>HTTPSession: session.getValue("userCode"), session.getAttribute("user_id"), session.getValue("ecatPATH")
    HTTPSession-->>ReportAction: String userCode, String user_id, String ecatPath
    
    ReportAction->>ReportAction: Extract radio parameter (Part Wise or Customer Wise)
    ReportAction->>ReportAction: Extract all search parameters
    
    ReportAction->>ReportDao: getViewOrderInvoiceList(rptForm, userFunctionalities, radio)
    
    ReportDao->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ReportDao: Session session
    
    alt radio equals Part Wise
        ReportDao->>Database: EXEC PROC_PartWiseSalesSummary :COUNTRYCODE, :DELAERCODE, :INVOICEFROMDATE, :INVOICETODATE, :PARTNO, :INVOICENO, :PINO
        Database-->>ReportDao: ResultSet (DEALER_CODE, DEALERNAME, COUNTRY_NAME, SHIPPED_PART_NO, PART_DESC, QTY_SHIPPED)
        ReportDao->>ReportDao: Build ArrayList with 6 fields per record
    else radio equals Customer Wise
        ReportDao->>Database: EXEC PROC_CustomerWiseSalesSummary :COUNTRYCODE, :DELAERCODE, :INVOICEFROMDATE, :INVOICETODATE, :PARTNO, :INVOICENO, :PINO
        Database-->>ReportDao: ResultSet (DEALER_CODE, DEALERNAME, COUNTRY_NAME, INVOICE_NO, INVOICE_DATE, INV_CURRENCY, OTHCHARGES, TOTAL_INVOICE_AMOUNT, DISPATCH_MODE, AWB_BOL_NO, AWB_BOL_DATE)
        ReportDao->>ReportDao: Build ArrayList with 11 fields per record
    end
    
    ReportDao-->>ReportAction: ArrayList list (report data)
    
    ReportAction->>ReportAction: Create Excel folder and delete existing files
    ReportAction->>ReportAction: Create WritableWorkbook based on radio type
    
    alt radio equals Part Wise
        ReportAction->>JXL: Create sheet "Part Wise Sales Summary"
        ReportAction->>JXL: Set column headers (Customer Code, Customer Name, Country, Part Code, Part Description, Qty)
        ReportAction->>JXL: Write data rows (6 columns per row)
    else radio equals Customer Wise
        ReportAction->>JXL: Create sheet "Customer Wise Sales Summary"
        ReportAction->>JXL: Set column headers (Customer Code, Customer Name, Country, CI No., CI Date, Currency, Amount, Other Charges, Mode of Dispatch, Docket No, Docket Date)
        ReportAction->>JXL: Write data rows (11 columns per row)
    end
    
    ReportAction->>JXL: Write Excel file and close workbook
    
    ReportAction->>Browser: Set request attributes (dealerList, countryList, range, message="SUCCESS")
    ReportAction->>StrutsFramework: Forward to successReport
    StrutsFramework->>Browser: Render viewSaleInvoiceList.jsp with success message
    Browser-->>User: Display success message with download link for Excel file
```

---

## 7. Pending PI Confirmation Report Flow

This diagram shows the Pending PI Confirmation Report generation.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ReportAction as ReportAction
    participant ReportDao as ReportDao
    participant MethodUtility as MethodUtility
    participant JXL as JXL (Excel Library)
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Pending PI Confirmation Report Tile
    Browser->>StrutsFramework: GET /reportAction.do?option=viewPedningPIConfirmationReport
    StrutsFramework->>ReportAction: viewPedningPIConfirmationReport(reportForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HTTPSession: session.getAttribute("userFun"), session.getAttribute("user_id")
    HTTPSession-->>ReportAction: Vector userFunctionalities, String user_id
    
    ReportAction->>ReportAction: Extract flag parameter and set default dates if null
    
    ReportAction->>MethodUtility: getDealersDetailsUnderUser(user_id)
    MethodUtility->>Database: Query dealer details
    Database-->>MethodUtility: Dealer list
    MethodUtility-->>ReportAction: List dealerList
    
    ReportAction->>MethodUtility: getDealersCountryUnderUser(user_id)
    MethodUtility->>Database: Query country details
    Database-->>MethodUtility: Country list
    MethodUtility-->>ReportAction: List countryList
    
    alt User has functionality 101
        ReportAction->>HTTPSession: session.getAttribute("dealerCode")
        HTTPSession-->>ReportAction: String dealerCode
        ReportAction->>ReportAction: Set dealerCode from session
    else User has other functionalities
        ReportAction->>Browser: Set request attribute dealerList
    end
    
    ReportAction->>Browser: Set request attributes (countryList, range)
    ReportAction->>StrutsFramework: Forward to viewPedningPIConfirmationReport
    StrutsFramework->>Browser: Render viewPedningPIConfirmation.jsp
    Browser-->>User: Display Pending PI Confirmation Report search form (PI no, part no, from date, to date, country, dealer)
    
    User->>Browser: Select search criteria and click Generate Report
    Browser->>StrutsFramework: POST /reportAction.do?option=createPedningPIConfirmationReport&piNo=&partnum=&fromdate=01/01/2024&todate=31/12/2024&country=IND@@India&dealerCode=DLR001
    StrutsFramework->>ReportAction: createPedningPIConfirmationReport(reportForm, request, response)
    
    ReportAction->>HTTPSession: session.getValue("userCode"), session.getAttribute("user_id"), session.getValue("ecatPATH")
    HTTPSession-->>ReportAction: String userCode, String user_id, String ecatPath
    
    ReportAction->>ReportAction: Extract all search parameters
    
    ReportAction->>ReportDao: getPedningPIConfirmation(rptForm, userFunctionalities)
    
    ReportDao->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ReportDao: Session session
    
    ReportDao->>Database: EXEC PROC_PendingPIAtBuyerReport :COUNTRYCODE, :DELAERCODE, :PIFROMDATE, :PITODATE, :PARTNO, :PINO
    Database-->>ReportDao: ResultSet (DEALER_CODE, DEALERNAME, COUNTRY_NAME, CUST_PO_NO, PI_NO, PI_DATE, PART_NO, PARTDESC, LEAD_TIME, FINAL_PI_QTY, PI_CURRENCY)
    ReportDao->>ReportDao: Build ArrayList with 10 fields per record
    ReportDao-->>ReportAction: ArrayList list (pending PI data)
    
    ReportAction->>ReportAction: Create Excel folder and delete existing files
    ReportAction->>JXL: Create WritableWorkbook "PendingPIAtBuyerReportExcel.xls"
    ReportAction->>JXL: Create sheet "PENDING PI AT BUYER"
    ReportAction->>JXL: Set column headers (Customer Code, Customer Name, Country, PO No., PI No., PI Date, Part Code, Part Description, Lead Time, Qty)
    ReportAction->>JXL: Write data rows (10 columns per row)
    ReportAction->>JXL: Write Excel file and close workbook
    
    ReportAction->>Browser: Set request attributes (dealerList, countryList, range, message="SUCCESS")
    ReportAction->>StrutsFramework: Forward to successPIConfirmationReport
    StrutsFramework->>Browser: Render viewPedningPIConfirmation.jsp with success message
    Browser-->>User: Display success message with download link for Excel file
```

---

## 8. Order Invoice Detail Report Flow

This diagram shows the Order Invoice Detail Report generation.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ReportAction as ReportAction
    participant ReportDao as ReportDao
    participant MethodUtility as MethodUtility
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click Order Invoice Detail Report Tile
    Browser->>StrutsFramework: GET /reportAction.do?option=viewOrderInvDetReport
    StrutsFramework->>ReportAction: viewOrderInvDetReport(reportForm, request, response)
    
    ReportAction->>Database: Get connection
    Database-->>ReportAction: Connection conn
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HTTPSession: session.getAttribute("userFun"), session.getAttribute("user_id")
    HTTPSession-->>ReportAction: Vector userFunctionalities, String user_id
    
    ReportAction->>ReportAction: Extract flag, cce parameters and set default dates if null
    
    ReportAction->>MethodUtility: getDealersDetailsUnderUser(user_id)
    MethodUtility->>Database: Query dealer details
    Database-->>MethodUtility: Dealer list
    MethodUtility-->>ReportAction: List dealerList
    
    alt User has functionality 101
        ReportAction->>HTTPSession: session.getAttribute("dealerCode")
        HTTPSession-->>ReportAction: String dealerCode
        ReportAction->>ReportAction: Set dealerCode from session
    end
    
    ReportAction->>ReportAction: Set dealerCode = "ALL" if null
    
    ReportAction->>MethodUtility: getCommonLabelValueHiber("SpOrderTypeMaster", "codeRefNo", "orderType", "orderType", " ")
    MethodUtility->>Database: Query order types
    Database-->>MethodUtility: Order type list
    MethodUtility-->>ReportAction: Order type list
    
    ReportAction->>Browser: Set request attributes (dealerList, orderTypeList, range)
    
    alt eType equals export
        ReportAction->>ReportDao: getViewOrderList(rForm, userFunctionalities, conn)
        ReportDao->>Database: Call Sp_viewOrderInvDetailReport(?,?,?,?,?,?,?,?) stored procedure
        Database-->>ReportDao: ResultSet order/invoice details
        ReportDao->>ReportDao: Map results to ReportForm objects
        ReportDao-->>ReportAction: ArrayList orderList
        ReportAction->>Browser: Set request attribute viewOrderList
        ReportAction->>StrutsFramework: Forward to exportViewOrderInvDetReport
        StrutsFramework->>Browser: Render exportViewOrderInvDetReport.jsp
        Browser-->>User: Display export page with order/invoice details
    else eType is null or view
        ReportAction->>StrutsFramework: Forward to viewOrderInvDetReport
        StrutsFramework->>Browser: Render viewOrderInvDetReportList.jsp
        Browser-->>User: Display Order Invoice Detail Report search form (order type, status, range, from date, to date, dealer code, cce)
    end
```

---

## 9. GSTR1 Report Flow

This diagram shows the GSTR1 (GST Return) Report generation.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ReportAction as ReportAction
    participant ReportDao as ReportDao
    participant ApachePOI as Apache POI
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click GSTR1 Report Tile
    Browser->>StrutsFramework: GET /reportAction.do?option=initGstr1Report
    StrutsFramework->>ReportAction: initGstr1Report(reportForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HTTPSession: session.getAttribute("userFun"), session.getAttribute("user_id")
    HTTPSession-->>ReportAction: Vector userFunctionalities, String user_id
    
    ReportAction->>MethodUtility: getDealersDetailsUnderUser(user_id)
    MethodUtility->>Database: Query dealer details
    Database-->>MethodUtility: Dealer list
    MethodUtility-->>ReportAction: List dealerList
    
    alt User has functionality 101
        ReportAction->>HTTPSession: session.getAttribute("dealerCode")
        HTTPSession-->>ReportAction: String dealerCode
        ReportAction->>ReportAction: Set dealerCode from session
    else User has other functionalities
        ReportAction->>Browser: Set request attribute dealerList
    end
    
    ReportAction->>StrutsFramework: Forward to successGstr1
    StrutsFramework->>Browser: Render gstr1Report.jsp
    Browser-->>User: Display GSTR1 Report search form (dealer code, from date, to date)
    
    User->>Browser: Select dealer code, from date, to date and click Generate Report
    Browser->>StrutsFramework: POST /reportAction.do?option=generateGstr1Report&dealerCode=DLR001&fromDate=01/01/2024&toDate=31/01/2024
    StrutsFramework->>ReportAction: generateGstr1Report(reportForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ReportAction: Session hSession
    
    ReportAction->>ReportAction: Get template file path "GSTR-1_2018-19.xls"
    ReportAction->>ReportAction: Set orderType = "GSTR-1"
    
    ReportAction->>ReportDao: gstrDetails(rptForm)
    
    ReportDao->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ReportDao: Session session
    
    ReportDao->>Database: EXEC SP_GSTR_1_REPORT :dealerCode, :fromDate, :toDate, :type='B2B'
    Database-->>ReportDao: ResultSet B2B data (GSTIN/UIN, Invoice Number, Invoice date, Invoice Value, Place Of Supply, Reverse Charge, Tax Rate, Invoice Type, E-Commerce GSTIN, Rate, Taxable Value, Cess Amount, Dealer_Code)
    ReportDao->>ReportDao: Build List B2B data
    
    ReportDao->>Database: EXEC SP_GSTR_1_REPORT :dealerCode, :fromDate, :toDate, :type='HSN'
    Database-->>ReportDao: ResultSet HSN data (HSN, Description, UQC, Total Quantity, Total Value, Taxable Value, Integrated Tax Amount, Central Tax Amount, State Tax Amount, Cess Amount)
    ReportDao->>ReportDao: Build List HSN data
    
    ReportDao->>ReportDao: Build Map with keys "B2B" and "HSN"
    ReportDao-->>ReportAction: Map data (B2B and HSN lists)
    
    ReportAction->>ReportAction: Format fromDate for filename
    ReportAction->>ReportAction: Generate filename "GSTR_1_[MMM-yyyy].xls"
    
    ReportAction->>ReportDao: GSTR_1Report(response, request, data, filename, filePath)
    
    ReportDao->>ApachePOI: Load template Excel file using WorkbookFactory
    ApachePOI-->>ReportDao: Workbook template
    
    ReportDao->>ApachePOI: Get B2B sheet and populate B2B data
    ReportDao->>ApachePOI: Get HSN sheet and populate HSN data
    ReportDao->>ApachePOI: Write updated workbook to response output stream
    
    ReportAction->>Browser: Set response headers for Excel download
    Browser-->>User: Download GSTR1 Report Excel file
```

---

## 10. GSTR2 Report Flow

This diagram shows the GSTR2 (GST Return) Report generation.

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant StrutsFramework as "Struts Framework"
    participant ReportAction as ReportAction
    participant ReportDao as ReportDao
    participant ApachePOI as Apache POI
    participant HibernateUtil as HibernateUtil
    participant Database as "SQL Server DB"
    participant HTTPSession as "HTTP Session"

    User->>Browser: Click GSTR2 Report Tile
    Browser->>StrutsFramework: GET /reportAction.do?option=initGstr2Report
    StrutsFramework->>ReportAction: initGstr2Report(reportForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HTTPSession: session.getAttribute("userFun"), session.getAttribute("user_id")
    HTTPSession-->>ReportAction: Vector userFunctionalities, String user_id
    
    ReportAction->>MethodUtility: getDealersDetailsUnderUser(user_id)
    MethodUtility->>Database: Query dealer details
    Database-->>MethodUtility: Dealer list
    MethodUtility-->>ReportAction: List dealerList
    
    alt User has functionality 101
        ReportAction->>HTTPSession: session.getAttribute("dealerCode")
        HTTPSession-->>ReportAction: String dealerCode
        ReportAction->>ReportAction: Set dealerCode from session
    else User has other functionalities
        ReportAction->>Browser: Set request attribute dealerList
    end
    
    ReportAction->>StrutsFramework: Forward to successGstr2
    StrutsFramework->>Browser: Render gstr2Report.jsp
    Browser-->>User: Display GSTR2 Report search form (dealer code, from date, to date)
    
    User->>Browser: Select dealer code, from date, to date and click Generate Report
    Browser->>StrutsFramework: POST /reportAction.do?option=generateGstr2Report&dealerCode=DLR001&fromDate=01/01/2024&toDate=31/01/2024
    StrutsFramework->>ReportAction: generateGstr2Report(reportForm, request, response)
    
    ReportAction->>HTTPSession: request.getSession()
    HTTPSession-->>ReportAction: HttpSession session
    
    ReportAction->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ReportAction: Session hSession
    
    ReportAction->>ReportAction: Get template file path "GSTR-2_2018-19.xlsx"
    ReportAction->>ReportAction: Set orderType = "GSTR-2"
    
    ReportAction->>ReportDao: gstrDetails(rptForm)
    
    ReportDao->>HibernateUtil: getSessionFactory().openSession()
    HibernateUtil-->>ReportDao: Session session
    
    ReportDao->>Database: EXEC SP_GSTR_2_REPORT :dealerCode, :fromDate, :toDate, :type='B2B'
    Database-->>ReportDao: ResultSet B2B data (GSTIN/UIN, Invoice Number, Invoice date, Invoice Value, Place Of Supply, Reverse Charge, Tax Rate, Invoice Type, E-Commerce GSTIN, Rate, Taxable Value, Integrated Tax Paid, Central Tax Paid, State/UT Tax Paid, Cess Paid, Eligibility For ITC, Availed ITC Integrated Tax, Availed ITC Central Tax, Availed ITC State/UT Tax, Availed ITC Cess)
    ReportDao->>ReportDao: Build List B2B data
    
    ReportDao->>Database: EXEC SP_GSTR_2_REPORT :dealerCode, :fromDate, :toDate, :type='HSN'
    Database-->>ReportDao: ResultSet HSN data (HSN, Description, UQC, Total Quantity, Total Value, Taxable Value, Integrated Tax Amount, Central Tax Amount, State Tax Amount, Cess Amount)
    ReportDao->>ReportDao: Build List HSN data
    
    ReportDao->>ReportDao: Build Map with keys "B2B" and "HSN"
    ReportDao-->>ReportAction: Map data (B2B and HSN lists)
    
    ReportAction->>ReportAction: Format fromDate for filename
    ReportAction->>ReportAction: Generate filename "GSTR_2_[MMM-yyyy].xlsx"
    
    ReportAction->>ReportDao: GSTR_2Report(response, request, data, filename, filePath)
    
    ReportDao->>ApachePOI: Load template Excel file using WorkbookFactory
    ApachePOI-->>ReportDao: Workbook template
    
    ReportDao->>ApachePOI: Get B2B sheet and populate B2B data with ITC details
    ReportDao->>ApachePOI: Get HSN sheet and populate HSN data
    ReportDao->>ApachePOI: Write updated workbook to response output stream
    
    ReportAction->>Browser: Set response headers for Excel download
    Browser-->>User: Download GSTR2 Report Excel file
```

---

## Summary

The Report Module handles:

1. **MIS Report (DMS Report)**: Comprehensive dealer management information system report with job types, installations, job cards, spares/lubes, and warranty claims
2. **Spares & Lubes Reports**: Multiple variants (Review, Rolling, STK versions) with hierarchy-based filtering
3. **Sale Invoice Report**: Part-wise and Customer-wise sales summary reports with Excel export
4. **Pending PI Confirmation Report**: Reports on pending Purchase Indents at buyer level
5. **Order Invoice Detail Report**: Detailed order and invoice information reports
6. **GST Reports**: GSTR1 and GSTR2 reports for GST compliance with B2B and HSN data

All flows integrate with the SQL Server database through Hibernate ORM and use stored procedures for complex queries. Reports are generated using JasperReports for PDF/Excel output or JXL/Apache POI for Excel generation. The module supports dealer-level and HO/Regional-level operations with appropriate access controls based on user functionalities.

