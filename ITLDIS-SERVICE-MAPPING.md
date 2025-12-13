# ITLDIS Services Implementation Mapping

## Overview
This document maps all ITLDIS services from the legacy Struts-based application to the new Spring Boot + Angular microfrontend architecture.

## Service Modules Mapping

### 1. Service Module
**Legacy**: `action.serviceAction`, `action.serviceOptionsAction`, `action.createJobCardAction`
**New Backend**: `com.i4o.dms.itldis.service.*` (adapt existing KUBOTA service structure)
**New Frontend**: `SERVICE-MICROAPP` (already exists, needs ITLDIS-specific components)

**Key Features**:
- Job Card creation and management
- Service booking
- Service options management
- Part number lookup
- Part price lookup
- Service follow-up

### 2. Warranty Module
**Legacy**: `action.WarrantyAction`
**New Backend**: `com.i4o.dms.itldis.warranty.*` (adapt existing KUBOTA warranty structure)
**New Frontend**: `WARRANTY-MICROAPP` (already exists, needs ITLDIS-specific components)

**Key Features**:
- Warranty claim creation
- Warranty list management
- Warranty approval workflow
- Warranty reports

### 3. Reports Module
**Legacy**: `action.ReportAction`
**New Backend**: `com.i4o.dms.itldis.reports.*` (new module)
**New Frontend**: Create new `REPORTS-MICROAPP` or add to MAIN-MICROAPP

**Key Features**:
- MIS Reports
- Service Reports
- Warranty Reports
- Inventory Reports
- Sales Reports
- Spares Reports

### 4. User Management Module
**Legacy**: `action.UserManagementAction`
**New Backend**: `com.i4o.dms.itldis.masters.usermanagement.*` (adapt existing KUBOTA structure)
**New Frontend**: `MASTERS-MICROAPP` (already exists, needs ITLDIS-specific components)

**Key Features**:
- User creation and management
- User type management
- Role assignment
- Password management
- User profile management

### 5. Master Module
**Legacy**: `action.masterAction`
**New Backend**: `com.i4o.dms.itldis.masters.*` (adapt existing KUBOTA masters structure)
**New Frontend**: `MASTERS-MICROAPP` (already exists, needs ITLDIS-specific components)

**Key Features**:
- Job Type Master
- Service Type Master
- Job Location Master
- Job Card Status Master
- Next Service Master
- Owner Driven Master
- Component Masters
- Customer Category Master
- Application Master

### 6. PDI Module (Pre-Delivery Inspection)
**Legacy**: `action.pdiAction`
**New Backend**: `com.i4o.dms.itldis.service.pdi.*` (KUBOTA already has PDI, adapt ITLDIS logic)
**New Frontend**: `SERVICE-MICROAPP` (add PDI components)

**Key Features**:
- PDI creation
- PDI checklist management
- PDI approval

### 7. PI Module (Proforma Invoice)
**Legacy**: `action.piAction.CreatePIAction`
**New Backend**: `com.i4o.dms.itldis.salesandpresales.pi.*` (new module)
**New Frontend**: `SALES-PRESALES-MICROAPP` (add PI components)

**Key Features**:
- PI creation
- PI management
- PI export

### 8. Inventory Module
**Legacy**: `action.InvtoryAction`, `action.inventoryEXPAction`
**New Backend**: `com.i4o.dms.itldis.inventory.*` (new module, similar to KUBOTA spares)
**New Frontend**: Create new `INVENTORY-MICROAPP` or add to SPARES-MICROAPP

**Key Features**:
- Inventory management
- GRN (Goods Receipt Note) creation
- Stock management
- Inventory EXP (Export) management
- Stock reports

### 9. Installation Module
**Legacy**: `action.installAction`
**New Backend**: `com.i4o.dms.itldis.service.machineinstallation.*` (KUBOTA already has, adapt ITLDIS logic)
**New Frontend**: `SERVICE-MICROAPP` (add installation components)

**Key Features**:
- Machine installation
- Installation checklist
- Installation photos

### 10. Customer Management Module
**Legacy**: `action.ManageCustomerAction`
**New Backend**: `com.i4o.dms.itldis.masters.customer.*` (new module)
**New Frontend**: `MASTERS-MICROAPP` or `CRM-MICROAPP` (add customer components)

**Key Features**:
- Customer creation
- Customer modification
- Customer search
- Customer details management

### 11. EAMG Module (Enterprise Asset Management Group)
**Legacy**: `EAMG.*` (Group, Kit, Model, Part, Tool, Other, Service Bulletin)
**New Backend**: `com.i4o.dms.itldis.eamg.*` (completely new module - unique to ITLDIS)
**New Frontend**: Create new `EAMG-MICROAPP`

**Key Features**:
- **EAMG Group**: Group creation, BOM management, Group modification
- **EAMG Kit**: Kit creation, Kit management
- **EAMG Model**: Model creation, Variant management, Aggregate management
- **EAMG Part**: Part creation, Part pricing, SAP integration, Component management
- **EAMG Tool**: Tool creation, Tool management
- **EAMG Other**: TSN management, EAMG vs OEM comparison, PDF generation
- **EAMG Service Bulletin**: Service bulletin management, File downloads

### 12. E-Catalog Module
**Legacy**: `viewEcat.*` (comEcat, orderEcat)
**New Backend**: `com.i4o.dms.itldis.ecatalog.*` (new module)
**New Frontend**: Create new `ECATALOG-MICROAPP` or add to SPARES-MICROAPP

**Key Features**:
- Part catalog browsing
- Cart management
- Order management
- Price details
- Excel export

### 13. Web Services Module
**Legacy**: `webservice.*`
**New Backend**: `com.i4o.dms.itldis.webservice.*` (new module)
**New Frontend**: N/A (backend only)

**Key Features**:
- REST API endpoints
- SOAP services (if needed)
- Integration services

### 14. Login/Authentication Module
**Legacy**: `action.LoginAction`
**New Backend**: `com.i4o.dms.itldis.security.*` (KUBOTA already has, adapt ITLDIS logic)
**New Frontend**: `MAIN-MICROAPP` (add login components)

**Key Features**:
- User authentication
- Session management
- Dashboard

## Implementation Priority

### Phase 1: Core Services (High Priority)
1. ✅ Service Module (adapt existing)
2. ✅ Warranty Module (adapt existing)
3. ✅ User Management (adapt existing)
4. ✅ Master Module (adapt existing)
5. ✅ PDI Module (adapt existing)

### Phase 2: Business Services (Medium Priority)
6. Reports Module (new)
7. Inventory Module (new)
8. Customer Management (new)
9. Installation Module (adapt existing)
10. PI Module (new)

### Phase 3: Specialized Services (Lower Priority)
11. EAMG Module (new - unique to ITLDIS)
12. E-Catalog Module (new)
13. Web Services Module (new)

## Entity Mapping

### HibernateMapping → JPA Entities
All entities in `HibernateMapping.*` need to be converted to JPA entities:
- Location: `com.i4o.dms.itldis.domain.*` or `com.i4o.dms.itldis.masters.dbentities.*`
- Use JPA annotations instead of Hibernate XML
- Map to existing KUBOTA entity structure where applicable

## DAO → Repository Pattern
All `dao.*` classes need to be converted to Spring Data JPA repositories:
- Location: `com.i4o.dms.itldis.*.repository.*`
- Extend `JpaRepository` or `CrudRepository`
- Use Spring Data JPA query methods

## Action → Controller Pattern
All `action.*` classes need to be converted to Spring REST controllers:
- Location: `com.i4o.dms.itldis.*.controller.*`
- Use `@RestController` and `@RequestMapping`
- Return DTOs instead of forwarding to JSPs

## Form Beans → DTOs
All `beans.*` classes need to be converted to DTOs:
- Location: `com.i4o.dms.itldis.*.dto.*`
- Use Lombok for getters/setters
- Add validation annotations

## Next Steps
1. Start with Phase 1 services (adapt existing KUBOTA modules)
2. Create new modules for Phase 2 and 3
3. Map Hibernate entities to JPA entities
4. Convert DAOs to repositories
5. Create REST controllers
6. Create DTOs
7. Build Angular components for frontend
