# ITLDIS System Architecture Diagram

## High-Level Architecture

```mermaid
graph TB
    subgraph "Client Layer"
        Browser[Web Browser]
        Mobile[Mobile Devices]
    end
    
    subgraph "Presentation Layer"
        JSP[JSP Pages]
        Struts[Struts Framework]
        SessionFilter[Session Filter]
    end
    
    subgraph "Application Layer"
        Actions[Struts Actions]
        Forms[Action Forms/Beans]
        Services[Business Logic]
    end
    
    subgraph "Data Access Layer"
        DAOs[DAO Classes]
        Hibernate[Hibernate ORM]
        DBConnection[Database Connection]
    end
    
    subgraph "External Systems"
        SAP[SAP System]
        Email[Email Server]
        ECAT[E-Catalog System]
    end
    
    subgraph "Data Layer"
        SQLServer[(SQL Server Database)]
        H2DB[(H2 Database)]
    end
    
    Browser --> JSP
    Mobile --> JSP
    JSP --> Struts
    Struts --> SessionFilter
    SessionFilter --> Actions
    Actions --> Forms
    Actions --> Services
    Services --> DAOs
    DAOs --> Hibernate
    DAOs --> DBConnection
    Hibernate --> SQLServer
    DBConnection --> SQLServer
    DBConnection --> H2DB
    Actions --> SAP
    Services --> Email
    Actions --> ECAT
```

## Detailed Architecture Layers

```mermaid
graph LR
    subgraph "Frontend Layer"
        A1[JSP Views]
        A2[JavaScript/CSS]
        A3[HTML Forms]
    end
    
    subgraph "Controller Layer"
        B1[LoginAction]
        B2[ServiceAction]
        B3[InventoryAction]
        B4[WarrantyAction]
        B5[InstallAction]
        B6[ReportAction]
        B7[MasterAction]
        B8[PDIAction]
        B9[PIAction]
        B10[UserManagementAction]
    end
    
    subgraph "Business Logic Layer"
        C1[Service Logic]
        C2[Validation Logic]
        C3[Utility Classes]
        C4[Common Methods]
    end
    
    subgraph "Data Access Layer"
        D1[LoginDAO]
        D2[ServiceDAO]
        D3[InventoryDAO]
        D4[WarrantyDAO]
        D5[InstallDAO]
        D6[ReportDAO]
        D7[MasterDAO]
        D8[PDIDAO]
        D9[PIDao]
        D10[UserManagementDAO]
    end
    
    subgraph "Integration Layer"
        E1[SAP Integration]
        E2[Email Service]
        E3[E-Catalog]
        E4[Web Services]
    end
    
    subgraph "Persistence Layer"
        F1[Hibernate Entities]
        F2[JDBC Connections]
        F3[Stored Procedures]
    end
    
    A1 --> B1
    A1 --> B2
    A1 --> B3
    A2 --> B1
    A3 --> B1
    
    B1 --> C1
    B2 --> C1
    B3 --> C1
    B4 --> C1
    B5 --> C1
    B6 --> C1
    B7 --> C1
    B8 --> C1
    B9 --> C1
    B10 --> C1
    
    C1 --> D1
    C1 --> D2
    C1 --> D3
    C1 --> D4
    C1 --> D5
    C1 --> D6
    C1 --> D7
    C1 --> D8
    C1 --> D9
    C1 --> D10
    
    D1 --> F1
    D2 --> F1
    D3 --> F1
    D4 --> F1
    D5 --> F1
    D6 --> F1
    D7 --> F1
    D8 --> F1
    D9 --> F1
    D10 --> F1
    
    D1 --> F2
    D2 --> F2
    D3 --> F2
    
    C1 --> E1
    C1 --> E2
    C1 --> E3
    C1 --> E4
```

## Module Architecture

```mermaid
graph TB
    subgraph "ITLDIS Core Modules"
        Login[Login & Authentication]
        UserMgmt[User Management]
        Master[Master Data Management]
    end
    
    subgraph "Service Management"
        Service[Service Operations]
        JobCard[Job Card Creation]
        PDI[Pre-Delivery Inspection]
        Install[Installation Management]
    end
    
    subgraph "Inventory Management"
        Inventory[Inventory Operations]
        InventoryEXP[Export Inventory]
        Purchase[Purchase Orders]
        GRN[Goods Receipt Note]
    end
    
    subgraph "Warranty Management"
        Warranty[Warranty Claims]
        Approval[Approval Workflow]
        Dispatch[Dispatch Management]
    end
    
    subgraph "Reporting & Analytics"
        Reports[MIS Reports]
        GSTR[GST Reports]
        Invoice[Invoice Reports]
    end
    
    subgraph "Integration Modules"
        SAP[SAP Integration]
        ECAT[E-Catalog]
        WebService[Web Services]
    end
    
    subgraph "EAMG Module"
        EAMG_Group[Group Management]
        EAMG_Part[Part Management]
        EAMG_Kit[Kit Management]
        EAMG_Model[Model Management]
        EAMG_Tool[Tool Management]
    end
    
    Login --> UserMgmt
    Login --> Service
    Login --> Inventory
    Login --> Warranty
    Login --> Reports
    
    Service --> JobCard
    Service --> PDI
    Service --> Install
    
    Inventory --> InventoryEXP
    Inventory --> Purchase
    Inventory --> GRN
    
    Warranty --> Approval
    Warranty --> Dispatch
    
    Reports --> GSTR
    Reports --> Invoice
    
    Service --> SAP
    Inventory --> SAP
    Warranty --> SAP
    
    Master --> EAMG_Group
    Master --> EAMG_Part
    Master --> EAMG_Kit
    Master --> EAMG_Model
    Master --> EAMG_Tool
```

## Technology Stack

```mermaid
graph TB
    subgraph "Frontend Technologies"
        A1[JSP 2.5]
        A2[JavaScript]
        A3[JQuery]
        A4[HTML/CSS]
        A5[JasperReports]
    end
    
    subgraph "Framework & Libraries"
        B1[Apache Struts 1.2]
        B2[Hibernate 3.x]
        B3[JDBC]
        B4[Apache Commons]
        B5[Log4j]
    end
    
    subgraph "Application Server"
        C1[Apache Tomcat]
        C2[JSP Container]
        C3[Servlet Container]
    end
    
    subgraph "Database"
        D1[SQL Server]
        D2[H2 Database]
        D3[Stored Procedures]
    end
    
    subgraph "External Integrations"
        E1[SAP JCo]
        E2[SMTP Server]
        E3[E-Catalog API]
    end
    
    A1 --> B1
    A2 --> B1
    A3 --> B1
    A4 --> B1
    A5 --> B1
    
    B1 --> C1
    B2 --> C1
    B3 --> C1
    
    B2 --> D1
    B3 --> D1
    B3 --> D2
    B3 --> D3
    
    B1 --> E1
    B1 --> E2
    B1 --> E3
```

## Data Flow Architecture

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant Struts
    participant Action
    participant Service
    participant DAO
    participant Hibernate
    participant Database
    participant SAP
    
    User->>Browser: Access Application
    Browser->>Struts: HTTP Request (.do)
    Struts->>Action: Route to Action Class
    Action->>Service: Business Logic
    Service->>DAO: Data Access Request
    DAO->>Hibernate: ORM Query
    Hibernate->>Database: SQL Query
    Database-->>Hibernate: Result Set
    Hibernate-->>DAO: Entity Objects
    DAO-->>Service: Data Objects
    Service->>SAP: External API Call (if needed)
    SAP-->>Service: Response
    Service-->>Action: Business Result
    Action->>Struts: Forward/Redirect
    Struts->>Browser: JSP Response
    Browser-->>User: Rendered Page
```

## Security Architecture

```mermaid
graph TB
    subgraph "Security Layers"
        A1[Session Filter]
        A2[Authentication]
        A3[Authorization]
        A4[Encryption]
    end
    
    subgraph "User Management"
        B1[User Roles]
        B2[Permissions]
        B3[Session Management]
    end
    
    subgraph "Data Security"
        C1[Password Encryption]
        C2[SQL Injection Prevention]
        C3[Input Validation]
    end
    
    A1 --> A2
    A2 --> A3
    A3 --> B1
    B1 --> B2
    B2 --> B3
    
    A2 --> C1
    A3 --> C2
    A4 --> C1
    A4 --> C3
```

