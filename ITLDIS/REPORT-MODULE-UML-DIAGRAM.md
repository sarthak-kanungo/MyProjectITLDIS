# ITLDIS Report Module - UML Class Diagram

```mermaid
classDiagram
    %% ============================================
    %% CONTROLLER LAYER (1 Controller)
    %% ============================================
    
    class ReportController {
        -Logger logger
        -ReportService reportService
        +initMisReport(dealerCode)
        +generateMisReport(request)
        +getSparesLubesReport(request)
        +getSaleInvoiceReport(request)
        +getPendingPIReport(request)
        +generateGstr1Report(request)
        +generateGstr2Report(request)
    }
    
    %% ============================================
    %% SERVICE LAYER (1 Service Interface & Implementation)
    %% ============================================
    
    class ReportService {
        +generateMisReport(request) MisReportResponseDto
        +generateSparesLubesReport(request) MisReportResponseDto
        +generateSparesLubesRollingReport(request) MisReportResponseDto
        +generateSaleInvoiceReport(request) MisReportResponseDto
        +generatePendingPIReport(request) MisReportResponseDto
        +generateOrderInvoiceDetailReport(request) MisReportResponseDto
        +generateGstr1Report(request) MisReportResponseDto
        +generateGstr2Report(request) MisReportResponseDto
    }
    
    class ReportServiceImpl {
        -Logger logger
        -ReportRepository reportRepository
        +generateMisReport(request) MisReportResponseDto
        +generateSparesLubesReport(request) MisReportResponseDto
        +generateSparesLubesRollingReport(request) MisReportResponseDto
        +generateSaleInvoiceReport(request) MisReportResponseDto
        +generatePendingPIReport(request) MisReportResponseDto
        +generateOrderInvoiceDetailReport(request) MisReportResponseDto
        +generateGstr1Report(request) MisReportResponseDto
        +generateGstr2Report(request) MisReportResponseDto
    }
    
    %% ============================================
    %% REPOSITORY LAYER (1 Repository Interface & Implementation)
    %% ============================================
    
    class ReportRepository {
        +getJobTypes(dealerCode, fromDate, toDate) List~Object[]~
        +getJobTypeInstallations(dealerCode, fromDate, toDate) List~Object[]~
    }
    
    class ReportRepositoryImpl {
        -EntityManager entityManager
        +getJobTypes(dealerCode, fromDate, toDate) List~Object[]~
        +getJobTypeInstallations(dealerCode, fromDate, toDate) List~Object[]~
    }
    
    %% ============================================
    %% DTO LAYER (2 DTOs)
    %% ============================================
    
    class MisReportRequestDto {
        -String dealerCode
        -Date fromDate
        -Date toDate
        -String reportType
        -String finYear
        -Integer month
        -Integer week
        -String country
        -String zone
        -String state
        -String ccm
        -String cce
        -String dealer
        +getDealerCode() String
        +setDealerCode(String)
        +getFromDate() Date
        +setFromDate(Date)
        +getToDate() Date
        +setToDate(Date)
        +getReportType() String
        +setReportType(String)
        +getFinYear() String
        +setFinYear(String)
        +getMonth() Integer
        +setMonth(Integer)
        +getWeek() Integer
        +setWeek(Integer)
        +getCountry() String
        +setCountry(String)
        +getZone() String
        +setZone(String)
        +getState() String
        +setState(String)
        +getCcm() String
        +setCcm(String)
        +getCce() String
        +setCce(String)
        +getDealer() String
        +setDealer(String)
    }
    
    class MisReportResponseDto {
        -String reportName
        -String reportType
        -byte[] reportData
        -List~Map~String,Object~~ reportDataList
        -Integer totalRecords
        -Map~String,Object~ summary
        +getReportName() String
        +setReportName(String)
        +getReportType() String
        +setReportType(String)
        +getReportData() byte[]
        +setReportData(byte[])
        +getReportDataList() List~Map~String,Object~~
        +setReportDataList(List~Map~String,Object~~)
        +getTotalRecords() Integer
        +setTotalRecords(Integer)
        +getSummary() Map~String,Object~
        +setSummary(Map~String,Object~)
    }
    
    %% ============================================
    %% EXTERNAL DEPENDENCIES
    %% ============================================
    
    class ApiResponse {
        -int status
        -String message
        -T result
        -Long count
        -Long id
        -String token
        +success(result) ApiResponse
        +success(result, message) ApiResponse
        +error(message) ApiResponse
        +error(status, message) ApiResponse
        +getStatus() int
        +setStatus(int)
        +getMessage() String
        +setMessage(String)
        +getResult() T
        +setResult(T)
        +getCount() Long
        +setCount(Long)
        +getId() Long
        +setId(Long)
        +getToken() String
        +setToken(String)
    }
    
    class EntityManager {
        +createNativeQuery(sql) Query
        +createQuery(jpql) Query
        +find(entityClass, primaryKey) Object
        +persist(entity) void
        +merge(entity) Object
        +remove(entity) void
        +flush() void
        +clear() void
        +close() void
    }
    
    %% ============================================
    %% RELATIONSHIPS
    %% ============================================
    
    ReportController --> ReportService : uses
    ReportController --> ApiResponse : returns
    ReportController --> MisReportRequestDto : receives
    ReportController --> MisReportResponseDto : returns
    
    ReportServiceImpl ..|> ReportService : implements
    ReportServiceImpl --> ReportRepository : uses
    ReportServiceImpl --> MisReportRequestDto : receives
    ReportServiceImpl --> MisReportResponseDto : returns
    
    ReportRepositoryImpl ..|> ReportRepository : implements
    ReportRepositoryImpl --> EntityManager : uses
    
    ReportService --> MisReportRequestDto : receives
    ReportService --> MisReportResponseDto : returns
    
    ReportRepository --> MisReportRequestDto : uses parameters
```

## Report Module Overview

The **Report Module** is responsible for generating various business reports in the ITLDIS system. It provides a centralized reporting service that handles MIS reports, Spares & Lubes reports, Sale Invoice reports, GSTR reports, and other business intelligence reports.

### Module Structure

#### **Controller Layer**
- **ReportController**: REST controller that handles HTTP requests for report generation
  - Endpoints for initializing and generating various report types
  - Supports MIS reports, Spares & Lubes reports, Sale Invoice reports, GSTR-1, GSTR-2, and Pending PI reports

#### **Service Layer**
- **ReportService**: Interface defining all report generation methods
- **ReportServiceImpl**: Implementation of report generation logic
  - Handles business logic for report generation
  - Coordinates with repository layer for data retrieval
  - Formats and structures report data

#### **Repository Layer**
- **ReportRepository**: Interface for custom query methods
- **ReportRepositoryImpl**: Implementation using native SQL queries
  - Executes stored procedures for report data
  - Uses EntityManager for database operations
  - Returns raw data arrays for processing

#### **DTO Layer**
- **MisReportRequestDto**: Request DTO containing report parameters
  - Includes dealer code, date ranges, filters (country, zone, state, CCM, CCE)
  - Supports various report types and time periods (financial year, month, week)
  
- **MisReportResponseDto**: Response DTO containing report results
  - Includes report name, type (PDF/XLS), report data (byte array or list)
  - Contains summary information and total record counts

### Report Types Supported

1. **MIS Report**: Management Information System report with job types and installations
2. **Spares & Lubes Report**: Inventory and sales report for spare parts and lubricants
3. **Spares & Lubes Rolling Report**: Rolling period analysis report
4. **Sale Invoice Report**: Sales invoice details and summaries
5. **Pending PI Confirmation Report**: Pending Proforma Invoice confirmation status
6. **Order Invoice Detail Report**: Detailed order and invoice information
7. **GSTR-1 Report**: GST return report for outward supplies
8. **GSTR-2 Report**: GST return report for inward supplies

### External Dependencies

- **ApiResponse**: Utility class for standardized API responses
- **EntityManager**: JPA EntityManager for database operations
- **Logger**: SLF4J logger for logging operations

### Key Features

- Centralized report generation service
- Support for multiple report formats (PDF, XLS)
- Flexible filtering and date range selection
- Integration with stored procedures for complex queries
- Standardized request/response DTOs
- Error handling and logging

