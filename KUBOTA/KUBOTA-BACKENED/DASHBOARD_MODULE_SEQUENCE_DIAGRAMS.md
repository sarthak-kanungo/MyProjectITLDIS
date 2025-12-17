## Dashboard Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.dashboard` module:

- **Service Report Generation** (Job cards, installations, activities, complaints, presale services).
- **Sales Report Generation** (Enquiries, activities, complaints, stock, retail reports).
- **Marketing Report Generation** (Budget status, proposal status, enquiry status, claim status).
- **Warranty Report Generation** (PCR status, WCR status, settlement status, goodwill status).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the dashboard module.

---

## 1. Service Report Generation Flow

This flow shows how **service dashboard reports** are generated, including job card reports, installation reports, activity reports, complaint reports, and presale service reports.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as DashboardController
    participant UserAuth as UserAuthentication
    participant DashboardRepo as DashboardRepo
    participant StoredProc as Stored Procedures
    participant DB as Database

    %% Service Report Flow
    Note over Client,DB: Service Dashboard Report Generation
    
    Client->>Controller: POST /api/dashboard/serviceReport<br/>(DashboardSearchRequestModel)
    
    Controller->>Controller: Extract request parameters<br/>(fromDate, toDate, product, series, model,<br/>subModel, variant, dealerId, orgHierId, branchId)
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    %% Job Card Report
    Controller->>DashboardRepo: getDashboardJobCardReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_jobcard_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    StoredProc->>DB: Execute stored procedure with parameters
    DB->>DB: Query job card data with filters
    DB-->>StoredProc: Job card report results
    StoredProc-->>DashboardRepo: List of DashboardJobCardReportDto
    DashboardRepo-->>Controller: jobcardReport
    
    %% Installation Report
    Controller->>DashboardRepo: getDashboardInstallationReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_service_installation_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    StoredProc->>DB: Execute stored procedure with parameters
    DB->>DB: Query installation data with filters
    DB-->>StoredProc: Installation report results
    StoredProc-->>DashboardRepo: List of DashboardInstallationReportDto
    DashboardRepo-->>Controller: installationReport
    
    %% Activity Report
    Controller->>DashboardRepo: getDashboardServiceActivityReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_service_activity_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    StoredProc->>DB: Execute stored procedure with parameters
    DB->>DB: Query service activity data with filters
    DB-->>StoredProc: Activity report results
    StoredProc-->>DashboardRepo: List of DashboardActivityReportDto
    DashboardRepo-->>Controller: activityReport
    
    %% Complaint Report
    Controller->>DashboardRepo: getDashboardServiceComplaintReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_service_complaint_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    StoredProc->>DB: Execute stored procedure with parameters
    DB->>DB: Query service complaint data with filters
    DB-->>StoredProc: Complaint report results
    StoredProc-->>DashboardRepo: List of DashboardComplaintReportDto
    DashboardRepo-->>Controller: complaintReport
    
    %% Presale Service Report
    Controller->>DashboardRepo: getDashboardPresalesServiceReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_presales_service_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    StoredProc->>DB: Execute stored procedure with parameters
    DB->>DB: Query presale service data with filters
    DB-->>StoredProc: Presale service report results
    StoredProc-->>DashboardRepo: List of DashboardPresaleServiceReportDto
    DashboardRepo-->>Controller: presaleServiceReport
    
    %% Aggregate Results
    Controller->>Controller: Create Map with all reports<br/>result.put(jobcardReport, installationReport,<br/>activityReport, complaintReport, presaleServiceReport)
    
    Controller->>Controller: Create ApiResponse<br/>apiResponse.setResult(result)<br/>apiResponse.setMessage("Dashboard Service Report")
    
    Controller-->>Client: HTTP 200 OK<br/>(Map with all service reports)
```

---

## 2. Sales Report Generation Flow

This flow shows how **sales dashboard reports** are generated, including enquiry reports, activity reports (dealer own and Kubota supported), complaint reports, stock reports, and retail reports.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as DashboardController
    participant UserAuth as UserAuthentication
    participant DashboardRepo as DashboardRepo
    participant StoredProc as Stored Procedures
    participant DB as Database

    %% Sales Report Flow
    Note over Client,DB: Sales Dashboard Report Generation
    
    Client->>Controller: POST /api/dashboard/salesReport<br/>(DashboardSearchRequestModel with salesReportOption)
    
    Controller->>Controller: Extract request parameters<br/>(fromDate, toDate, product, series, model,<br/>subModel, variant, dealerId, orgHierId, branchId, salesReportOption)
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    %% Enquiry Report
    Controller->>DashboardRepo: getDashboardEnquiryReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_sales_enquiry_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    StoredProc->>DB: Execute stored procedure with parameters
    DB->>DB: Query sales enquiry data with filters
    DB-->>StoredProc: Enquiry report results
    StoredProc-->>DashboardRepo: List of DashboardSalesEnquiryReportDto
    DashboardRepo-->>Controller: enquiryReport
    
    %% Activity Dealer Own Report
    Controller->>DashboardRepo: getDashboardSalesActivityDealerOwnReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_sales_activity_dealer_own_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    StoredProc->>DB: Execute stored procedure with parameters
    DB->>DB: Query dealer own activity data with filters
    DB-->>StoredProc: Activity report results
    StoredProc-->>DashboardRepo: List of DashboardSalesActivityReportDto
    DashboardRepo-->>Controller: activityDealerOwnReport
    
    %% Activity Kubota Supported Report
    Controller->>DashboardRepo: getDashboardSalesActivityKubotaSupportReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_sales_activity_kubota_support_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    StoredProc->>DB: Execute stored procedure with parameters
    DB->>DB: Query Kubota supported activity data with filters
    DB-->>StoredProc: Activity report results
    StoredProc-->>DashboardRepo: List of DashboardSalesActivityReportDto
    DashboardRepo-->>Controller: activityKubotaSupportedReport
    
    %% Complaint Report
    Controller->>DashboardRepo: getDashboardSalesComplaintReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_sales_complaint_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    StoredProc->>DB: Execute stored procedure with parameters
    DB->>DB: Query sales complaint data with filters
    DB-->>StoredProc: Complaint report results
    StoredProc-->>DashboardRepo: List of DashboardSalesComplaintReportDto
    DashboardRepo-->>Controller: complaintReport
    
    %% Stock Report
    Controller->>DashboardRepo: getSalesStockReport(<br/>salesReportOption, username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_sales_stock_report<br/>(salesReportOption, userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    StoredProc->>DB: Execute stored procedure with parameters
    DB->>DB: Query stock data with filters and report option
    DB-->>StoredProc: Stock report results
    StoredProc-->>DashboardRepo: List of SalesStockReportDto
    DashboardRepo-->>Controller: stockReport
    
    %% Retail Report
    Controller->>DashboardRepo: getSalesRetailReport(<br/>salesReportOption, username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_sales_retail_report<br/>(salesReportOption, userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId)
    StoredProc->>DB: Execute stored procedure with parameters
    DB->>DB: Query retail data with filters and report option
    DB-->>StoredProc: Retail report results
    StoredProc-->>DashboardRepo: List of SalesRetailReportDto
    DashboardRepo-->>Controller: retailReport
    
    %% Aggregate Results
    Controller->>Controller: Create Map with all reports<br/>result.put(enquiryReport, activityDealerOwnReport,<br/>activityKubotaSupportedReport, complaintReport,<br/>stockReport, retailReport)
    
    Controller->>Controller: Create ApiResponse<br/>apiResponse.setResult(result)<br/>apiResponse.setMessage("Dashboard Sales Report")
    
    Controller-->>Client: HTTP 200 OK<br/>(Map with all sales reports)
```

---

## 3. Marketing Report Generation Flow

This flow shows how **marketing dashboard reports** are generated, including budget status, proposal status, enquiry status, and claim status reports.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as DashboardController
    participant UserAuth as UserAuthentication
    participant DashboardRepo as DashboardRepo
    participant StoredProc as Stored Procedures
    participant DB as Database

    %% Marketing Report Flow
    Note over Client,DB: Marketing Dashboard Report Generation
    
    Client->>Controller: POST /api/dashboard/marketingReport<br/>(DashboardSearchRequestModel with mReportType and budgetReportType)
    
    Controller->>Controller: Extract request parameters<br/>(fromDate, toDate, product, series, model,<br/>subModel, variant, dealerId, orgHierId, branchId,<br/>mReportType, budgetReportType)
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    %% Budget Status Report
    Controller->>DashboardRepo: getDashboardMarketingReport(<br/>budgetReportType, username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "BUDGET_STATUS")
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_marketing_report<br/>(ReportOption, userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "BUDGET_STATUS")
    StoredProc->>DB: Execute stored procedure with BUDGET_STATUS report type
    DB->>DB: Query budget status data with filters
    DB-->>StoredProc: Budget status report results
    StoredProc-->>DashboardRepo: List of Map with budget status data
    DashboardRepo-->>Controller: budgetStatusReport
    
    %% Proposal Status Report
    Controller->>DashboardRepo: getDashboardMarketingReport(<br/>mReportType, username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "PROPOSAL_STATUS")
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_marketing_report<br/>(ReportOption, userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "PROPOSAL_STATUS")
    StoredProc->>DB: Execute stored procedure with PROPOSAL_STATUS report type
    DB->>DB: Query proposal status data with filters
    DB-->>StoredProc: Proposal status report results
    StoredProc-->>DashboardRepo: List of Map with proposal status data
    DashboardRepo-->>Controller: proposalStatusReport
    
    %% Enquiry Status Report
    Controller->>DashboardRepo: getDashboardMarketingReport(<br/>mReportType, username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "ENQUIRY_STATUS")
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_marketing_report<br/>(ReportOption, userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "ENQUIRY_STATUS")
    StoredProc->>DB: Execute stored procedure with ENQUIRY_STATUS report type
    DB->>DB: Query enquiry status data with filters
    DB-->>StoredProc: Enquiry status report results
    StoredProc-->>DashboardRepo: List of Map with enquiry status data
    DashboardRepo-->>Controller: enquiryStatusReport
    
    %% Claim Status Report
    Controller->>DashboardRepo: getDashboardMarketingReport(<br/>mReportType, username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "CLAIM_STATUS")
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_marketing_report<br/>(ReportOption, userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "CLAIM_STATUS")
    StoredProc->>DB: Execute stored procedure with CLAIM_STATUS report type
    DB->>DB: Query claim status data with filters
    DB-->>StoredProc: Claim status report results
    StoredProc-->>DashboardRepo: List of Map with claim status data
    DashboardRepo-->>Controller: claimStatusReport
    
    %% Aggregate Results
    Controller->>Controller: Create Map with all reports<br/>result.put(budgetStatusReport, proposalStatusReport,<br/>enquiryStatusReport, claimStatusReport)
    
    Controller->>Controller: Create ApiResponse<br/>apiResponse.setResult(result)<br/>apiResponse.setMessage("Dashboard Sales Report")
    
    Controller-->>Client: HTTP 200 OK<br/>(Map with all marketing reports)
```

---

## 4. Warranty Report Generation Flow

This flow shows how **warranty dashboard reports** are generated, including PCR status, WCR status, settlement status, and goodwill status reports.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as DashboardController
    participant UserAuth as UserAuthentication
    participant DashboardRepo as DashboardRepo
    participant StoredProc as Stored Procedures
    participant DB as Database

    %% Warranty Report Flow
    Note over Client,DB: Warranty Dashboard Report Generation
    
    Client->>Controller: POST /api/dashboard/warrantyReport<br/>(DashboardSearchRequestModel)
    
    Controller->>Controller: Extract request parameters<br/>(fromDate, toDate, product, series, model,<br/>subModel, variant, dealerId, orgHierId, branchId)
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    %% PCR Status Report
    Controller->>DashboardRepo: getDashboardWarrantyReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "PCR_STATUS")
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_warranty_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "PCR_STATUS")
    StoredProc->>DB: Execute stored procedure with PCR_STATUS report type
    DB->>DB: Query PCR status data with filters
    DB-->>StoredProc: PCR status report results
    StoredProc-->>DashboardRepo: List of Map with PCR status data
    DashboardRepo-->>Controller: pcrStatusReport
    
    %% WCR Status Report
    Controller->>DashboardRepo: getDashboardWarrantyReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "WCR_STATUS")
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_warranty_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "WCR_STATUS")
    StoredProc->>DB: Execute stored procedure with WCR_STATUS report type
    DB->>DB: Query WCR status data with filters
    DB-->>StoredProc: WCR status report results
    StoredProc-->>DashboardRepo: List of Map with WCR status data
    DashboardRepo-->>Controller: wcrStatusReport
    
    %% Settlement Status Report
    Controller->>DashboardRepo: getDashboardWarrantyReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "SETTLEMENT_STATUS")
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_warranty_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "SETTLEMENT_STATUS")
    StoredProc->>DB: Execute stored procedure with SETTLEMENT_STATUS report type
    DB->>DB: Query settlement status data with filters
    DB-->>StoredProc: Settlement status report results
    StoredProc-->>DashboardRepo: List of Map with settlement status data
    DashboardRepo-->>Controller: settlementStatusReport
    
    %% Goodwill Status Report
    Controller->>DashboardRepo: getDashboardWarrantyReport(<br/>username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "GOODWILL_STATUS")
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_warranty_report<br/>(userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "GOODWILL_STATUS")
    StoredProc->>DB: Execute stored procedure with GOODWILL_STATUS report type
    DB->>DB: Query goodwill status data with filters
    DB-->>StoredProc: Goodwill status report results
    StoredProc-->>DashboardRepo: List of Map with goodwill status data
    DashboardRepo-->>Controller: goodwillStatusReport
    
    %% Aggregate Results
    Controller->>Controller: Create Map with all reports<br/>result.put(settlementStatusReport, wcrStatusReport,<br/>pcrStatusReport, goodwillStatusReport)
    
    Controller->>Controller: Create ApiResponse<br/>apiResponse.setResult(result)<br/>apiResponse.setMessage("Dashboard Sales Report")
    
    Controller-->>Client: HTTP 200 OK<br/>(Map with all warranty reports)
```

---

## 5. Enquiry Status Report Flow (Standalone)

This flow shows how a **standalone enquiry status report** is generated for marketing activities.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as DashboardController
    participant UserAuth as UserAuthentication
    participant DashboardRepo as DashboardRepo
    participant StoredProc as Stored Procedures
    participant DB as Database

    %% Enquiry Status Report Flow
    Note over Client,DB: Enquiry Status Report Generation
    
    Client->>Controller: POST /api/dashboard/enquiryStatusReport<br/>(DashboardSearchRequestModel with mReportType)
    
    Controller->>Controller: Extract request parameters<br/>(fromDate, toDate, product, series, model,<br/>subModel, variant, dealerId, orgHierId, branchId, mReportType)
    
    Controller->>UserAuth: getUsername()
    UserAuth-->>Controller: username
    
    Controller->>DashboardRepo: getDashboardMarketingReport(<br/>mReportType, username, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "ENQUIRY_STATUS")
    
    DashboardRepo->>StoredProc: CALL sp_dashboard_marketing_report<br/>(ReportOption, userCode, fromDate, toDate, product, series,<br/>model, subModel, variant, dealerId, orgHierId, branchId, "ENQUIRY_STATUS")
    StoredProc->>DB: Execute stored procedure with ENQUIRY_STATUS report type
    DB->>DB: Query enquiry status data with filters
    DB-->>StoredProc: Enquiry status report results
    StoredProc-->>DashboardRepo: List of Map with enquiry status data
    DashboardRepo-->>Controller: enquiryStatusReport
    
    Controller->>Controller: Create ApiResponse<br/>apiResponse.setResult(enquiryStatusReport)<br/>apiResponse.setMessage("Dashboard Sales Report")
    
    Controller-->>Client: HTTP 200 OK<br/>(List of enquiry status report data)
```

---

## Summary

The **dashboard** module provides comprehensive **reporting and analytics** functionality for the KUBOTA DMS:

- **Service Reports**:
  - Job card reports, installation reports, service activity reports
  - Service complaint reports, presale service reports
  - All reports use stored procedures with common filtering parameters

- **Sales Reports**:
  - Sales enquiry reports, sales activity reports (dealer own and Kubota supported)
  - Sales complaint reports, stock reports, retail reports
  - Stock and retail reports support different report options

- **Marketing Reports**:
  - Budget status reports, proposal status reports
  - Enquiry status reports, claim status reports
  - All use a single stored procedure with different report type parameters

- **Warranty Reports**:
  - PCR (Product Complaint Report) status reports
  - WCR (Warranty Claim Report) status reports
  - Settlement status reports, goodwill status reports
  - All use a single stored procedure with different report type parameters

- **Common Features**:
  - All reports support filtering by date range, product hierarchy (product, series, model, subModel, variant)
  - Dealer, organization hierarchy, and branch filtering
  - User-based access control through username parameter
  - Stored procedure-based data retrieval for performance
  - Aggregated results returned as Maps with multiple report types

The dashboard module integrates with multiple other modules (Service, Sales, Marketing, Warranty) to provide unified reporting capabilities, enabling dealers and headquarters to analyze performance, track activities, and monitor key metrics across all business areas.

