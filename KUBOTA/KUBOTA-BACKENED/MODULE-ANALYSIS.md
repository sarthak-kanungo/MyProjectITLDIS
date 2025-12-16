# KUBOTA-BACKENED Module Analysis

## Summary

**Total Modules: 20 Application Modules**

This is a **single-module Maven project** (packaging: `war`) with **20 functional application modules** organized as Java packages.

---

## Module Breakdown

### 1. **accpac** - 22 Java files
   - ACCPAC integration module
   - Controller, Domain, Repository components

### 2. **aop** - 1 Java file
   - Aspect-Oriented Programming
   - PurchaseOrderAop.java

### 3. **common** - 15 Java files
   - Common utilities and shared components
   - Job, Model, Repository, Service, System utilities

### 4. **configurations** - 8 Java files
   - Application configuration classes
   - Constants, CORS, Jackson, Swagger, Web configuration

### 5. **connection** - 2 Java files
   - Database connection configuration
   - ConnectionConfiguration classes

### 6. **constant** - 1 Java file
   - Application constants

### 7. **crm** - 68 Java files
   - Customer Relationship Management module
   - CRM functionality and features

### 8. **dashboard** - 17 Java files
   - Dashboard module
   - Controller, DTO, Model, Repository components

### 9. **exception** - 2 Java files
   - Exception handling
   - ApiErrorResponse, GlobalExceptionHandler

### 10. **feedback** - 3 Java files
    - Feedback module
    - Controller, Domain, Repository components

### 11. **masters** - 442 Java files ⭐ (Largest Module)
    - Master data management
    - Includes: Area Master, CRM Masters, Dealer Master, KAI Common Masters, KDM, KFM Masters, Old Customer Details, PO Status, PO Type, Products, Profile Picture, Sales, Sales and Presales, Service, Spares, User Management, Warranty Masters

### 12. **salesandpresales** - 297 Java files ⭐ (Second Largest)
    - Sales and Pre-sales module
    - Branch Transfer, Enquiry, GRN, Marketing Activity, Market Intelligence, Purchase, Purchase Order Machine Details, Reports, Sales, Schemes

### 13. **security** - 17 Java files
    - Security and authentication module
    - Config, Controller, Domain, DTO, JWT, Model, Repository, Service components

### 14. **service** - 180 Java files
    - Service module
    - ACCPAC, Activity Claim, Activity Proposal, Activity Report, Claim, Job Card, Machine Installation, Machine Reinstallation, MRC, PDC, PDI, PSC, Report, Service Booking

### 15. **spares** - 239 Java files ⭐ (Third Largest)
    - Spares management module
    - Inventory Management, Invoice, Part Requisition, Picklist, Purchase, Quotation, Reports, Sales Order, Stock

### 16. **storage** - 5 Java files
    - File storage service
    - FileStorageService, StorageProperties, StorageService

### 17. **training** - 32 Java files
    - Training module
    - Attendance Sheet, Training Nomination, Training Programme Calendar, Training Program Report

### 18. **utils** - 14 Java files
    - Utility classes
    - Helper and utility functions

### 19. **validation** - 3 Java files
    - Validation module
    - Service and Validator components

### 20. **warranty** - 117 Java files
    - Warranty module
    - Hotline Report, KAI Inspection Sheet, Logsheet, PCR (Product Complaint Report), Retrofitment Campaign, Warranty Claim Request, Reports

---

## Statistics

- **Total Java Files**: ~1,489 Java files
- **Maven Modules**: 1 (Single-module project)
- **Application Modules**: 20 functional modules
- **Largest Modules**:
  1. **masters** - 442 files (29.7%)
  2. **salesandpresales** - 297 files (19.9%)
  3. **spares** - 239 files (16.1%)
  4. **service** - 180 files (12.1%)
  5. **warranty** - 117 files (7.9%)

---

## Project Structure

```
KUBOTA-BACKENED/
├── pom.xml (Single Maven module)
└── src/main/java/com/i4o/dms/kubota/
    ├── accpac/
    ├── aop/
    ├── common/
    ├── configurations/
    ├── connection/
    ├── constant/
    ├── crm/
    ├── dashboard/
    ├── exception/
    ├── feedback/
    ├── masters/ (442 files)
    ├── salesandpresales/ (297 files)
    ├── security/
    ├── service/ (180 files)
    ├── spares/ (239 files)
    ├── storage/
    ├── training/
    ├── utils/
    ├── validation/
    └── warranty/ (117 files)
```

---

## Technology Stack

- **Framework**: Spring Boot 2.3.3.RELEASE
- **Packaging**: WAR (Web Application Archive)
- **Java Version**: 1.8
- **Database**: Microsoft SQL Server
- **Security**: Spring Security with JWT
- **API Documentation**: Swagger 3.0.0
- **Reporting**: JasperReports 6.17.0

---

## Answer

**There are 20 application modules** in the KUBOTA-BACKENED folder, organized as Java packages within a single Maven module project.

