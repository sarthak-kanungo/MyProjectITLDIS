# ITLDIS Services Implementation Guide

## Overview
This guide provides step-by-step instructions for implementing all ITLDIS services in the new Spring Boot + Angular architecture.

## Implementation Status

### ✅ Completed Structure
- Backend structure copied from KUBOTA
- Frontend microapps copied from KUBOTA
- Package names updated (kubota → itldis)
- Basic configuration files updated

### 🔄 In Progress
- Service implementation and adaptation

### ⏳ Pending
- All ITLDIS-specific business logic implementation
- New modules creation (EAMG, Reports, Inventory, E-Catalog, PI)
- Entity mapping from HibernateMapping
- DAO to Repository conversion
- Frontend component creation

## Implementation Approach

### Step 1: Analyze Legacy Code
For each service module:
1. Identify all Action methods
2. Identify all DAO methods
3. Identify all Form Beans
4. Identify all HibernateMapping entities used
5. Map to KUBOTA equivalent (if exists) or create new

### Step 2: Create Backend Structure
For each module:
1. Create controller package: `com.i4o.dms.itldis.{module}.controller`
2. Create service package: `com.i4o.dms.itldis.{module}.service`
3. Create repository package: `com.i4o.dms.itldis.{module}.repository`
4. Create dto package: `com.i4o.dms.itldis.{module}.dto`
5. Create domain package: `com.i4o.dms.itldis.{module}.domain` (if needed)

### Step 3: Convert Legacy Code

#### Action → Controller
```java
// Legacy (Struts)
public ActionForward methodName(ActionMapping mapping, ActionForm form, 
    HttpServletRequest request, HttpServletResponse response) {
    // logic
    return mapping.findForward("success");
}

// New (Spring Boot)
@RestController
@RequestMapping("/api/{module}")
public class ModuleController {
    @PostMapping("/method-name")
    public ResponseEntity<ResponseDto> methodName(@RequestBody RequestDto request) {
        // logic
        return ResponseEntity.ok(response);
    }
}
```

#### DAO → Repository
```java
// Legacy
public class ModuleDAO {
    public List<Entity> getList(Connection conn) {
        // JDBC code
    }
}

// New
@Repository
public interface ModuleRepository extends JpaRepository<Entity, Long> {
    @Query("SELECT e FROM Entity e WHERE ...")
    List<Entity> findByCriteria(...);
}
```

#### Form Bean → DTO
```java
// Legacy
public class ModuleForm {
    private String field1;
    // getters/setters
}

// New
@Data
public class ModuleDto {
    @NotNull
    private String field1;
    // Lombok generates getters/setters
}
```

### Step 4: Create Frontend Components
For each module:
1. Create Angular module
2. Create components
3. Create services
4. Create DTOs/interfaces
5. Create routing

## Module-Specific Implementation Notes

### 1. Reports Module (NEW)
**Location**: `com.i4o.dms.itldis.reports.*`

**Key Reports to Implement**:
- MIS Report (`initMisReport`, `generateMisReport`)
- Spares & Lubes Report (`getListSpareLubeReport`, `spareAndLubeReport`)
- Spares & Lubes Rolling Report (`getRollingSpareLubeReport`)
- Spares & Lubes Report STK (`getListSpareLubeReportSTK`, `spareAndLubeReportSTK`)
- Sale Invoice Report (`viewSaleInvoiceReport`, `createSaleInvoiceReport`)
- Pending PI Confirmation Report (`viewPedningPIConfirmationReport`)
- Order Invoice Detail Report (`viewOrderInvDetReport`)
- GSTR-1 Report (`initGstr1Report`, `generateGstr1Report`)
- GSTR-2 Report (`initGstr2Report`, `generateGstr2Report`)

**Implementation Steps**:
1. Create `ReportController` with all report endpoints
2. Create `ReportService` with business logic
3. Create `ReportRepository` for data access
4. Create DTOs for request/response
5. Integrate Jasper Reports for PDF/Excel generation

### 2. EAMG Module (NEW - Unique to ITLDIS)
**Location**: `com.i4o.dms.itldis.eamg.*`

**Sub-modules**:
- **Group**: `EAMG.Group.*` → `com.i4o.dms.itldis.eamg.group.*`
- **Kit**: `EAMG.Kit.*` → `com.i4o.dms.itldis.eamg.kit.*`
- **Model**: `EAMG.Model.*` → `com.i4o.dms.itldis.eamg.model.*`
- **Part**: `EAMG.Part.*` → `com.i4o.dms.itldis.eamg.part.*`
- **Tool**: `EAMG.Tool.*` → `com.i4o.dms.itldis.eamg.tool.*`
- **Other**: `EAMG.Other.*` → `com.i4o.dms.itldis.eamg.other.*`
- **Service Bulletin**: `EAMG_Service_Bulletin.*` → `com.i4o.dms.itldis.eamg.servicebulletin.*`

**Implementation Steps**:
1. Create package structure for each sub-module
2. Convert Action classes to Controllers
3. Convert DAO classes to Repositories
4. Create domain entities
5. Create DTOs
6. Implement business logic in Services

### 3. Inventory Module (NEW)
**Location**: `com.i4o.dms.itldis.inventory.*`

**Key Features**:
- Inventory management (`InvtoryAction`)
- Inventory EXP (Export) (`inventoryEXPAction`)
- GRN creation
- Stock management

**Implementation Steps**:
1. Create inventory controller
2. Create inventory service
3. Create inventory repository
4. Map inventory entities
5. Create DTOs

### 4. PI Module (NEW)
**Location**: `com.i4o.dms.itldis.salesandpresales.pi.*`

**Key Features**:
- PI creation (`CreatePIAction`)
- PI management
- PI export

### 5. E-Catalog Module (NEW)
**Location**: `com.i4o.dms.itldis.ecatalog.*`

**Key Features**:
- Part catalog (`viewEcat.comEcat.*`)
- Order management (`viewEcat.orderEcat.*`)
- Cart management
- Price details

## Entity Mapping Guide

### HibernateMapping → JPA Entities
All entities in `HibernateMapping.*` need to be converted:

```java
// Legacy (Hibernate XML mapping)
// HibernateMapping/EntityName.java
public class EntityName {
    private Long id;
    // fields
}

// New (JPA)
@Entity
@Table(name = "table_name")
public class EntityName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "column_name")
    private String field;
    // JPA annotations
}
```

## Testing Strategy

1. **Unit Tests**: Test each service method
2. **Integration Tests**: Test controller endpoints
3. **Repository Tests**: Test data access
4. **Frontend Tests**: Test Angular components

## Migration Checklist

For each service module:
- [ ] Analyze legacy Action class
- [ ] Create controller with REST endpoints
- [ ] Create service interface and implementation
- [ ] Create repository interface
- [ ] Convert Form Beans to DTOs
- [ ] Map HibernateMapping entities to JPA entities
- [ ] Convert DAO methods to repository methods
- [ ] Implement business logic
- [ ] Create unit tests
- [ ] Create integration tests
- [ ] Create Angular components
- [ ] Create Angular services
- [ ] Create routing
- [ ] Test end-to-end

## Priority Order

1. **Phase 1** (Core - Adapt Existing):
   - Service Module
   - Warranty Module
   - User Management
   - Master Module
   - PDI Module

2. **Phase 2** (New Modules):
   - Reports Module
   - Inventory Module
   - Customer Management
   - PI Module

3. **Phase 3** (Specialized):
   - EAMG Module
   - E-Catalog Module
   - Web Services

## Next Steps

1. Start with Reports Module (create basic structure)
2. Create EAMG Module structure
3. Create Inventory Module structure
4. Gradually implement each service method
5. Test as you go

## Resources

- Legacy Code: `ITLDIS/src/main/java/action/*`
- Legacy DAOs: `ITLDIS/src/main/java/dao/*`
- Legacy Entities: `ITLDIS/src/main/java/HibernateMapping/*`
- Reference Implementation: `KUBOTA/KUBOTA-BACKENED/src/main/java/com/i4o/dms/kubota/*`
