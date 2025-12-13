# ITLDIS Architecture Overview

## System Overview

ITLDIS (ITL Dealer Information System) is a comprehensive enterprise application built using **Apache Struts 1.2** framework with **Hibernate ORM** for data persistence. The system manages dealer operations including service management, inventory, warranty claims, installations, and integration with SAP systems.

## Architecture Pattern

The application follows the **Model-View-Controller (MVC)** pattern implemented through Apache Struts framework:

- **Model**: Hibernate entities, Action Forms/Beans, Business Logic
- **View**: JSP pages, JavaScript, CSS
- **Controller**: Struts Action classes

## Key Components

### 1. Presentation Layer
- **JSP Pages**: User interface components organized by modules
- **JavaScript/CSS**: Client-side scripting and styling
- **JasperReports**: Report generation and rendering

### 2. Controller Layer (Struts Actions)
- **LoginAction**: Authentication and session management
- **ServiceAction**: Service operations and job card management
- **InvtoryAction**: Inventory management operations
- **WarrantyAction**: Warranty claim processing
- **InstallAction**: Installation management
- **ReportAction**: Report generation
- **MasterAction**: Master data management
- **PDIAction**: Pre-Delivery Inspection
- **PIAction**: Purchase Invoice management
- **UserManagementAction**: User administration

### 3. Business Logic Layer
- **Service Classes**: Business logic implementation
- **Utility Classes**: Common methods and helpers
- **Validation**: Input validation and business rules
- **Email Service**: Notification and communication

### 4. Data Access Layer
- **DAO Classes**: Data access objects for each module
- **Hibernate ORM**: Object-relational mapping
- **JDBC**: Direct database access for complex queries
- **Stored Procedures**: Database procedures for reports

### 5. Integration Layer
- **SAP Integration**: RFC calls for data synchronization
- **E-Catalog**: Parts and components catalog
- **Web Services**: External service integration
- **Email Service**: SMTP integration

### 6. Persistence Layer
- **SQL Server**: Primary database
- **H2 Database**: Development/testing database
- **Hibernate Entities**: 163+ entity mappings

## Module Structure

### Core Modules
1. **Authentication & Authorization**
   - User login/logout
   - Session management
   - Role-based access control

2. **Service Management**
   - Job card creation
   - Service scheduling
   - Parts and labor management
   - Invoice generation

3. **Inventory Management**
   - Stock entry/issue
   - Stock transfer
   - GRN (Goods Receipt Note)
   - Export inventory

4. **Warranty Management**
   - Warranty claim creation
   - Approval workflow
   - Parts dispatch
   - Credit note generation

5. **Installation Management**
   - Installation scheduling
   - PDI (Pre-Delivery Inspection)
   - Checkpoint tracking
   - Documentation

6. **Master Data Management**
   - Customer master
   - Dealer master
   - Parts master
   - Model master

7. **Reporting**
   - MIS reports
   - GSTR reports
   - Invoice reports
   - Custom reports

### EAMG Module (Equipment Assembly Management Group)
- **Group Management**: BOM structure management
- **Part Management**: Parts catalog and pricing
- **Kit Management**: Component kits
- **Model Management**: Machine models and variants
- **Tool Management**: Tool specifications

## Technology Stack

### Frontend
- JSP 2.5
- JavaScript/jQuery
- HTML/CSS
- JasperReports

### Backend
- Java (JDK 1.8)
- Apache Struts 1.2
- Hibernate 3.x
- JDBC

### Application Server
- Apache Tomcat

### Database
- SQL Server (Production)
- H2 Database (Development)

### External Integrations
- SAP JCo (Java Connector)
- SMTP Server
- E-Catalog API

## Security Architecture

1. **Session Management**
   - Session filter for authentication
   - Session timeout (30 minutes)
   - Session validation on each request

2. **Authentication**
   - Encrypted password storage
   - User credential validation
   - Active user verification

3. **Authorization**
   - Role-based access control
   - Permission-based feature access
   - User type-based routing

4. **Data Security**
   - SQL injection prevention
   - Input validation
   - Password encryption

## Data Flow

1. **Request Flow**
   - User request → JSP/Form → Struts Action → Business Logic → DAO → Database
   
2. **Response Flow**
   - Database → DAO → Business Logic → Action → JSP → User

3. **Integration Flow**
   - Application → SAP Integration → RFC Call → SAP System → Response → Application

## Deployment Architecture

```
┌─────────────────────────────────────┐
│      Web Browser (Client)           │
└──────────────┬──────────────────────┘
               │ HTTP/HTTPS
               ▼
┌─────────────────────────────────────┐
│   Apache Tomcat (Application Server) │
│  ┌──────────────────────────────┐  │
│  │   ITLDIS Application (WAR)   │  │
│  │  - Struts Framework           │  │
│  │  - Hibernate                  │  │
│  │  - Business Logic             │  │
│  └──────────────────────────────┘  │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│      SQL Server Database            │
│  - Master Data                      │
│  - Transaction Data                 │
│  - Stored Procedures               │
└─────────────────────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│      External Systems               │
│  - SAP System                       │
│  - Email Server                     │
│  - E-Catalog                       │
└─────────────────────────────────────┘
```

## Key Design Patterns

1. **MVC Pattern**: Separation of concerns
2. **DAO Pattern**: Data access abstraction
3. **Factory Pattern**: Object creation
4. **Singleton Pattern**: Utility classes
5. **Strategy Pattern**: Business logic variations

## Scalability Considerations

- **Stateless Design**: Session-based state management
- **Connection Pooling**: Database connection management
- **Caching**: Hibernate second-level cache
- **Lazy Loading**: Optimized data fetching

## Maintenance & Support

- **Logging**: Log4j for application logging
- **Error Handling**: Global exception handling
- **Configuration**: Externalized configuration files
- **Documentation**: Code comments and documentation

