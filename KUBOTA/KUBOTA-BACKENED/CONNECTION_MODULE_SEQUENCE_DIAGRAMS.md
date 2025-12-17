## Connection Module - Detailed Sequence Diagrams

This document describes the **key technical flows** implemented in the `com.i4o.dms.kubota.connection` module:

- **Connection Configuration** (EntityManager injection and JDBC connection extraction).
- **Connection Retrieval Flow** (Extracting JDBC Connection from Hibernate Session).
- **Usage in Jasper Reports** (JasperPrintService using connection for report generation).
- **Usage in Repositories** (Repositories implementing ConnectionConfiguration interface).

All diagrams use Mermaid sequence diagrams and reflect the current implementation of the connection module.

---

## 1. Connection Configuration - Bean Initialization and EntityManager Injection

This flow shows how **ConnectionConfigurationImpl** is initialized and how Spring injects the **EntityManager** through `@PersistenceContext`.

```mermaid
sequenceDiagram
    %% Participants
    participant Spring as Spring Application Context
    participant Persistence as JPA Persistence Provider
    participant EntityManagerFactory as EntityManagerFactory
    participant EntityManager as EntityManager Bean
    participant ConnectionConfig as ConnectionConfigurationImpl
    participant DataSource as DataSource

    %% Application Startup - Connection Configuration Initialization
    Note over Spring,ConnectionConfig: Application Startup - Bean Initialization
    Spring->>Spring: Scan for @Component, @Service, @Repository annotations
    Spring->>Spring: Detect ConnectionConfigurationImpl class
    
    Spring->>DataSource: Initialize DataSource from application.properties
    Note over Spring: Read JDBC URL, username, password,<br/>driver class, connection pool settings
    DataSource-->>Spring: DataSource configured
    
    Spring->>Persistence: Initialize JPA Persistence Provider
    Persistence->>EntityManagerFactory: Create EntityManagerFactory
    EntityManagerFactory->>EntityManagerFactory: Configure from persistence.xml or<br/>application.properties
    EntityManagerFactory->>EntityManagerFactory: Set DataSource
    EntityManagerFactory-->>Persistence: EntityManagerFactory ready
    
    Spring->>EntityManager: Create EntityManager bean
    EntityManager->>EntityManagerFactory: Create EntityManager instance
    EntityManagerFactory->>DataSource: Get connection from pool
    DataSource-->>EntityManagerFactory: JDBC Connection
    EntityManagerFactory-->>EntityManager: EntityManager instance
    EntityManager-->>Spring: EntityManager bean registered
    
    Spring->>ConnectionConfig: Initialize ConnectionConfigurationImpl
    Spring->>ConnectionConfig: Inject EntityManager via @PersistenceContext
    ConnectionConfig->>ConnectionConfig: Store EntityManager reference
    ConnectionConfig-->>Spring: ConnectionConfigurationImpl ready
    
    Note over Spring,ConnectionConfig: ConnectionConfigurationImpl is now ready<br/>to provide JDBC connections
```

---

## 2. Connection Retrieval Flow - Extracting JDBC Connection from Hibernate Session

This flow shows how **getConnection()** method extracts a raw JDBC **Connection** from the Hibernate **EntityManager** by accessing the underlying Hibernate **Session**.

```mermaid
sequenceDiagram
    %% Participants
    participant Service as Service/Repository Class
    participant ConnectionConfig as ConnectionConfigurationImpl
    participant EntityManager as EntityManager
    participant HibernateSession as Hibernate Session
    participant SessionImpl as SessionImpl (Hibernate Internal)
    participant JDBCConnection as JDBC Connection
    participant Database as Database

    %% Connection Retrieval Flow
    Note over Service,Database: Connection Retrieval Request
    Service->>ConnectionConfig: getConnection()
    
    ConnectionConfig->>EntityManager: entityManager.getDelegate()
    Note over ConnectionConfig: Cast EntityManager delegate<br/>to Hibernate Session
    EntityManager->>HibernateSession: Get underlying Hibernate Session
    HibernateSession-->>EntityManager: Session instance
    EntityManager-->>ConnectionConfig: Session delegate
    
    ConnectionConfig->>ConnectionConfig: Cast Session to SessionImpl
    Note over ConnectionConfig: SessionImpl is Hibernate's<br/>internal session implementation
    ConnectionConfig->>SessionImpl: sessionImpl.connection()
    
    SessionImpl->>SessionImpl: Check if connection is already open
    alt Connection already exists in session
        SessionImpl->>SessionImpl: Return existing connection
    else Connection not yet opened
        SessionImpl->>JDBCConnection: Open new JDBC connection
        JDBCConnection->>Database: Establish connection
        Database-->>JDBCConnection: Connection established
        JDBCConnection-->>SessionImpl: JDBC Connection instance
        SessionImpl->>SessionImpl: Store connection in session
    end
    
    SessionImpl-->>ConnectionConfig: JDBC Connection
    ConnectionConfig-->>Service: Return Connection object
    
    Note over Service: Service can now use JDBC Connection<br/>for direct SQL queries or Jasper Reports
```

---

## 3. Usage in Jasper Reports - JasperPrintService Connection Flow

This flow shows how **JasperPrintService** (which extends **ConnectionConfigurationImpl**) uses the connection to generate Jasper reports by filling report templates with database data.

```mermaid
sequenceDiagram
    %% Participants
    participant Controller as Report Controller
    participant JasperService as JasperPrintService
    participant ConnectionConfig as ConnectionConfigurationImpl
    participant EntityManager as EntityManager
    participant HibernateSession as Hibernate Session
    participant JDBCConnection as JDBC Connection
    participant JasperEngine as JasperReports Engine
    participant Database as Database
    participant Client as Client / Browser

    %% Jasper Report Generation Flow
    Note over Controller,Client: Jasper Report Generation Request
    Controller->>Controller: Build jasperFile path<br/>(e.g., /WEB-INF/reports/ExportPartDumpList.jasper)
    Controller->>Controller: Build jasperParameter HashMap<br/>(e.g., date range, dealerId, etc.)
    
    Controller->>JasperService: getJasperPrint(jasperFile, jasperParameter)
    
    JasperService->>JasperService: Initialize connection = null
    JasperService->>JasperService: try block
    
    Note over JasperService,ConnectionConfig: Get JDBC Connection
    JasperService->>ConnectionConfig: getConnection() (inherited method)
    ConnectionConfig->>EntityManager: entityManager.getDelegate()
    EntityManager->>HibernateSession: Get Hibernate Session
    HibernateSession-->>EntityManager: Session instance
    EntityManager-->>ConnectionConfig: Session delegate
    
    ConnectionConfig->>ConnectionConfig: Cast to SessionImpl
    ConnectionConfig->>HibernateSession: sessionImpl.connection()
    HibernateSession->>JDBCConnection: Get or create JDBC connection
    JDBCConnection->>Database: Establish connection (if needed)
    Database-->>JDBCConnection: Connection ready
    JDBCConnection-->>HibernateSession: JDBC Connection
    HibernateSession-->>ConnectionConfig: Connection instance
    ConnectionConfig-->>JasperService: JDBC Connection
    
    JasperService->>JasperService: Check if connection != null
    
    alt Connection is valid
        JasperService->>JasperEngine: JasperFillManager.fillReport(<br/>jasperFile, jasperParameter, connection)
        
        JasperEngine->>JDBCConnection: Execute SQL queries from .jasper file
        JDBCConnection->>Database: Execute SELECT queries / stored procedures
        Database->>Database: Process queries with parameters
        Database-->>JDBCConnection: Result sets
        JDBCConnection-->>JasperEngine: Query results
        
        JasperEngine->>JasperEngine: Fill report template with data
        JasperEngine->>JasperEngine: Generate JasperPrint object
        JasperEngine-->>JasperService: JasperPrint object
        
        JasperService->>JasperService: Store jasperPrint
    else Connection is null
        JasperService->>JasperService: Set jasperPrint = null
        JasperService->>JasperService: Log error
    end
    
    JasperService->>JasperService: finally block
    
    alt Connection exists
        JasperService->>JDBCConnection: connection.close()
        JDBCConnection->>Database: Close connection
        Database-->>JDBCConnection: Connection closed
        JDBCConnection-->>JasperService: Connection released
        Note over JasperService: Connection returned to pool
    end
    
    JasperService-->>Controller: Return JasperPrint
    
    Note over Controller,Client: Controller then exports JasperPrint<br/>to PDF/Excel/CSV/HTML format
    Controller->>JasperService: printPdfReport() or exportToXlsx() etc.
    JasperService-->>Client: Report file stream
```

---

## 4. Usage in Repositories - Repository ConnectionConfiguration Implementation

This flow shows how various **Repository interfaces** extend **ConnectionConfiguration** to provide connection access, and how services use repositories to get connections for custom queries or reports.

```mermaid
sequenceDiagram
    %% Participants
    participant Service as Service Class
    participant Repository as Repository Interface<br/>(e.g., PurchaseOrderRepo,<br/>QuotationRepo, EnquiryRepo)
    participant SpringData as Spring Data JPA
    participant ConnectionConfig as ConnectionConfigurationImpl
    participant EntityManager as EntityManager
    participant JDBCConnection as JDBC Connection
    participant Database as Database

    %% Repository Implementation Flow
    Note over Service,Database: Repository with ConnectionConfiguration Usage
    
    %% Repository Declaration
    Note over Repository: Repository Interface Declaration<br/>public interface PurchaseOrderRepo<br/>extends JpaRepository PurchaseOrder, Long,<br/>ConnectionConfiguration
    
    %% Service Usage
    Service->>Service: @Autowired PurchaseOrderRepo repository
    SpringData->>Repository: Create repository proxy implementation
    Repository->>SpringData: Repository proxy ready
    SpringData-->>Service: Injected repository instance
    
    %% Standard JPA Repository Methods
    Service->>Repository: findAll() or findById() etc.
    Repository->>SpringData: Delegate to JpaRepository methods
    SpringData->>EntityManager: Execute JPA queries
    EntityManager->>Database: Execute SQL queries
    Database-->>EntityManager: Result sets
    EntityManager-->>SpringData: Entity objects
    SpringData-->>Repository: List of entities
    Repository-->>Service: Return entities
    
    %% ConnectionConfiguration Method Usage
    Note over Service,Database: Custom Query or Report Generation
    Service->>Repository: getConnection() (from ConnectionConfiguration interface)
    
    Repository->>ConnectionConfig: getConnection() (default implementation)
    Note over Repository: Since repository extends ConnectionConfiguration,<br/>it can call getConnection() method
    
    ConnectionConfig->>EntityManager: entityManager.getDelegate()
    EntityManager->>EntityManager: Get Hibernate Session
    EntityManager->>ConnectionConfig: Session instance
    
    ConnectionConfig->>ConnectionConfig: Cast to SessionImpl
    ConnectionConfig->>ConnectionConfig: sessionImpl.connection()
    ConnectionConfig->>JDBCConnection: Get JDBC connection
    JDBCConnection->>Database: Establish connection
    Database-->>JDBCConnection: Connection ready
    JDBCConnection-->>ConnectionConfig: Connection instance
    ConnectionConfig-->>Repository: JDBC Connection
    Repository-->>Service: Return Connection
    
    Service->>Service: Use connection for custom SQL queries<br/>or pass to JasperPrintService
    
    alt Custom SQL Query
        Service->>JDBCConnection: Create PreparedStatement
        JDBCConnection->>Database: Execute custom SQL
        Database-->>JDBCConnection: Query results
        JDBCConnection-->>Service: ResultSet
    else Pass to Jasper Service
        Service->>Service: Pass connection to JasperPrintService<br/>for report generation
    end
    
    Service->>JDBCConnection: connection.close()
    JDBCConnection->>Database: Release connection
    Database-->>JDBCConnection: Connection returned to pool
```

---

## 5. Complete Flow - Report Generation with Connection Module

This flow shows the **complete end-to-end flow** of how a report request flows through the system, using the connection module to access the database.

```mermaid
sequenceDiagram
    %% Participants
    participant Client as Client Application
    participant Controller as Report Controller
    participant JasperService as JasperPrintService
    participant ConnectionConfig as ConnectionConfigurationImpl
    participant EntityManager as EntityManager
    participant JDBCConnection as JDBC Connection
    participant JasperEngine as JasperReports Engine
    participant Database as Database

    %% Complete Report Generation Flow
    Note over Client,Database: Complete Report Generation Request Flow
    
    Client->>Controller: GET /api/.../exportReport?format=pdf&params=...
    
    Controller->>Controller: Parse request parameters
    Controller->>Controller: Build jasperFile path<br/>(/WEB-INF/reports/ReportName.jasper)
    Controller->>Controller: Build jasperParameter HashMap
    
    Controller->>JasperService: getJasperPrint(jasperFile, jasperParameter)
    
    Note over JasperService,ConnectionConfig: Connection Module Usage
    JasperService->>ConnectionConfig: getConnection() (inherited)
    ConnectionConfig->>EntityManager: entityManager.getDelegate()
    EntityManager->>EntityManager: Get Hibernate Session
    EntityManager-->>ConnectionConfig: Session instance
    
    ConnectionConfig->>ConnectionConfig: Cast to SessionImpl
    ConnectionConfig->>ConnectionConfig: sessionImpl.connection()
    ConnectionConfig->>JDBCConnection: Get JDBC connection from session
    JDBCConnection->>Database: Establish connection (if needed)
    Database-->>JDBCConnection: Connection ready
    JDBCConnection-->>ConnectionConfig: Connection instance
    ConnectionConfig-->>JasperService: JDBC Connection
    
    Note over JasperService,JasperEngine: Report Generation
    JasperService->>JasperEngine: JasperFillManager.fillReport(<br/>jasperFile, jasperParameter, connection)
    
    JasperEngine->>JDBCConnection: Execute SQL queries from .jasper template
    JDBCConnection->>Database: Execute SELECT / stored procedures<br/>with parameters
    Database->>Database: Process queries
    Database-->>JDBCConnection: Result sets
    JDBCConnection-->>JasperEngine: Query results
    
    JasperEngine->>JasperEngine: Fill report template with data
    JasperEngine->>JasperEngine: Generate JasperPrint object
    JasperEngine-->>JasperService: JasperPrint
    
    JasperService->>JDBCConnection: connection.close()
    JDBCConnection->>Database: Release connection
    Database-->>JDBCConnection: Connection returned to pool
    JDBCConnection-->>JasperService: Connection closed
    
    JasperService-->>Controller: Return JasperPrint
    
    Note over Controller,Client: Export Report
    Controller->>JasperService: printPdfReport(jasperPrint, printStatus, outputStream)
    JasperService->>JasperService: Create JRPdfExporter
    JasperService->>JasperService: Configure PDF exporter
    JasperService->>JasperService: Export to outputStream
    JasperService-->>Controller: PDF bytes in outputStream
    
    Controller->>Controller: Set HTTP response headers<br/>(Content-Type: application/pdf)
    Controller-->>Client: HTTP Response with PDF file
    
    Client->>Client: Display or download PDF report
```

---

## Summary

The **connection** module provides a **centralized way to access JDBC connections** from the Hibernate EntityManager for use in Jasper Reports and custom SQL queries:

- **ConnectionConfiguration Interface**:
  - Defines a simple contract: `Connection getConnection()`
  - Implemented by repositories and services that need direct JDBC access

- **ConnectionConfigurationImpl**:
  - Extracts JDBC `Connection` from Hibernate `EntityManager` by accessing the underlying `Session`
  - Uses Hibernate's internal `SessionImpl` to get the raw JDBC connection
  - Provides connection management without manual DataSource configuration

- **Usage Patterns**:
  - **JasperPrintService**: Extends `ConnectionConfigurationImpl` to get connections for filling Jasper report templates
  - **Repositories**: Implement `ConnectionConfiguration` interface to provide connection access for custom queries
  - **Services**: Can use repository's `getConnection()` method for direct SQL operations

- **Connection Lifecycle**:
  - Connections are obtained from the Hibernate session's connection pool
  - Connections must be properly closed after use (handled in `finally` blocks)
  - Connections are returned to the pool when closed

This module **bridges the gap** between JPA/Hibernate's EntityManager and JasperReports' requirement for raw JDBC connections, enabling seamless report generation while maintaining Spring's dependency injection and connection pooling benefits.

