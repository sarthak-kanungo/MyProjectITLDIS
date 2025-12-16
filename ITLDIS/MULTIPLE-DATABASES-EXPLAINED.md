# How ITLDIS (SQL Server) and Camunda (H2) Work Together

## The Question

**How is it possible for:**
- ITLDIS application → Uses **SQL Server**
- Camunda BPM → Uses **H2 Database**

**Answer: They use completely separate, independent database connections!**

---

## Simple Explanation

Think of it like this:
- **ITLDIS** has its own database connection → SQL Server
- **Camunda** has its own database connection → H2
- They are **completely independent** - like two separate applications

---

## How It Works Technically

### Two Separate JDBC Connections

#### 1. ITLDIS Database Connection

**Code:** `dbConnection.java`
```java
Connection con = new dbConnection().getConnection();
// Uses: ApplicationResource.properties
// Connects to: SQL Server
// URL: jdbc:sqlserver://your_server:1433;databaseName=itldis_db
// Driver: com.microsoft.sqlserver.jdbc.SQLServerDriver
```

**Configuration:** `ApplicationResource.properties`
```properties
db_Path=jdbc:sqlserver://your_server:1433;databaseName=itldis_db
sqlsDriver=com.microsoft.sqlserver.jdbc.SQLServerDriver
db_usernm=your_username
db_password=your_password
```

**Tables Used:**
- Your ITLDIS application tables
- warranty, service, inventory, users, etc.

---

#### 2. Camunda Database Connection

**Code:** `ProcessEngineFactory.java`
```java
ProcessEngineConfiguration config = ...
    .setJdbcUrl("jdbc:h2:mem:camunda;...")
    .setJdbcDriver("org.h2.Driver")
    .setJdbcUsername("sa")
    .setJdbcPassword("")
```

**Configuration:** `camunda.properties`
```properties
camunda.db.url=jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1
camunda.db.driver=org.h2.Driver
camunda.db.type=h2
```

**Tables Used:**
- Camunda engine tables
- ACT_RU_*, ACT_RE_*, ACT_HI_*, ACT_GE_*

---

## Visual Diagram

```
┌─────────────────────────────────────────────────────────┐
│              ITLDIS Application (Tomcat)                │
├─────────────────────────────────────────────────────────┤
│                                                           │
│  ┌──────────────────────┐    ┌──────────────────────┐  │
│  │   ITLDIS Code        │    │   Camunda Code        │  │
│  │                      │    │                      │  │
│  │  WarrantyAction     │    │  ProcessEngine       │  │
│  │  ServiceAction      │    │  RuntimeService      │  │
│  │  LoginAction        │    │  TaskService         │  │
│  │  etc.               │    │  etc.                │  │
│  └──────────┬───────────┘    └──────────┬───────────┘  │
│             │                           │              │
│             │ JDBC Connection 1         │ JDBC Connection 2
│             │                           │              │
└─────────────┼───────────────────────────┼──────────────┘
              │                           │
              ▼                           ▼
    ┌──────────────────┐        ┌──────────────────┐
    │   SQL Server     │        │   H2 Database    │
    │   (Your DB)      │        │   (In-Memory)    │
    └──────────────────┘        └──────────────────┘
    │                           │
    │ ITLDIS Tables              │ Camunda Tables
    │ - warranty                 │ - ACT_RU_EXECUTION
    │ - service                  │ - ACT_RU_TASK
    │ - inventory                │ - ACT_RU_VARIABLE
    │ - users                    │ - ACT_RE_DEPLOYMENT
    │ - etc.                     │ - ACT_HI_PROCINST
    │                            │ - etc. (~40+ tables)
    └────────────────────────────┘
```

---

## Why This Works

### 1. Separate Connection Objects

Each component creates its own `Connection` object:
- **ITLDIS:** `Connection con = new dbConnection().getConnection()`
- **Camunda:** Created internally by ProcessEngine

They are **completely independent objects** in memory.

### 2. Different JDBC Drivers

Both drivers are in `WEB-INF/lib/`:
- **ITLDIS:** `sqljdbc4.jar` (SQL Server driver)
- **Camunda:** `h2-1.4.200.jar` (H2 driver)

They don't conflict because:
- Different class names
- Different packages
- Loaded separately by classloader

### 3. Different JDBC URLs

**ITLDIS:**
```
jdbc:sqlserver://localhost:1433;databaseName=itldis_db
```

**Camunda:**
```
jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1
```

These are **completely different** connection strings pointing to different databases.

### 4. Independent Lifecycle

- **ITLDIS connection:** Created when your action needs it
- **Camunda connection:** Created when ProcessEngine initializes
- They operate **independently**

---

## Real-World Example

### Scenario: User logs in and starts a warranty claim process

**Step 1: User Login (ITLDIS)**
```java
// LoginAction.java
Connection con = new dbConnection().getConnection();
// → Connects to SQL Server
// → Queries: SELECT * FROM users WHERE userid=?
// → Uses your ITLDIS database
```

**Step 2: Start Warranty Process (Camunda)**
```java
// ProcessEngineFactory creates connection
ProcessEngine engine = config.buildProcessEngine();
// → Connects to H2 database
// → Creates: ACT_RU_EXECUTION, ACT_RU_TASK
// → Uses separate H2 database
```

**They work together but use different databases!**

---

## Can They Use the Same SQL Server?

### ✅ YES - Even Better for Production!

You can configure both to use **SQL Server** (same server, different databases):

```
SQL Server Instance (localhost:1433)
│
├── Database: itldis_main
│   └── Your ITLDIS Tables
│       - warranty
│       - service
│       - inventory
│       - users
│       - etc.
│
└── Database: itldis_camunda
    └── Camunda Tables
        - ACT_RU_*
        - ACT_RE_*
        - ACT_HI_*
        - ACT_GE_*
```

**Benefits:**
- ✅ Same SQL Server instance
- ✅ Different databases (completely isolated)
- ✅ Data persists (not in-memory)
- ✅ Production-ready
- ✅ Can backup together

**Configuration:**
```properties
# In camunda.properties
camunda.db.url=jdbc:sqlserver://localhost:1433;databaseName=itldis_camunda
camunda.db.type=mssql
```

---

## Current Setup

### Development (Current)

```
ITLDIS Application
│
├── Connection 1: SQL Server
│   └── Database: your_existing_itldis_db
│       └── Your application tables
│
└── Connection 2: H2 (In-Memory)
    └── Database: camunda (stored in RAM)
        └── Camunda ACT_* tables
        └── Data lost on restart
```

**Why H2?**
- ✅ No setup needed
- ✅ Works immediately
- ✅ Doesn't require SQL Server configuration
- ✅ Good for development/testing

### Production (Recommended)

```
ITLDIS Application
│
├── Connection 1: SQL Server
│   └── Database: itldis_main
│       └── Your application tables
│
└── Connection 2: SQL Server (same instance)
    └── Database: itldis_camunda
        └── Camunda ACT_* tables
        └── Data persists
```

---

## Key Points

### ✅ How It's Possible

1. **Separate JDBC Connections**
   - Each component has its own `Connection` object
   - They don't share or interfere with each other

2. **Different JDBC URLs**
   - ITLDIS: `jdbc:sqlserver://...`
   - Camunda: `jdbc:h2:mem:camunda`
   - Point to completely different databases

3. **Different Drivers**
   - SQL Server driver for ITLDIS
   - H2 driver for Camunda
   - Both in classpath, no conflicts

4. **Independent Operation**
   - ITLDIS queries its database
   - Camunda queries its database
   - They never conflict

### ✅ Why H2 for Camunda?

**Development Benefits:**
- ✅ No database setup needed
- ✅ Works immediately
- ✅ Doesn't affect your SQL Server
- ✅ Good for testing

**Production:**
- Switch to SQL Server (same server, different database)
- Use `switch-to-sqlserver-camunda.bat`

---

## Comparison

| Aspect | ITLDIS | Camunda |
|--------|--------|---------|
| **Database** | SQL Server | H2 (dev) / SQL Server (prod) |
| **Connection Class** | `dbConnection.java` | `ProcessEngineFactory.java` |
| **Configuration** | `ApplicationResource.properties` | `camunda.properties` |
| **JDBC Driver** | `sqljdbc4.jar` | `h2-1.4.200.jar` |
| **JDBC URL** | `jdbc:sqlserver://...` | `jdbc:h2:mem:camunda` |
| **Tables** | Your app tables | ACT_* tables |
| **Purpose** | Application data | Workflow engine data |

---

## Summary

### How It Works:

1. **Two Separate Connections:**
   - ITLDIS → SQL Server (via `dbConnection.java`)
   - Camunda → H2 (via `ProcessEngineFactory.java`)

2. **No Conflicts:**
   - Different JDBC URLs
   - Different drivers
   - Different tables
   - Independent operation

3. **Why H2:**
   - Development convenience
   - No setup required
   - Doesn't affect your SQL Server

4. **Production Option:**
   - Can use SQL Server for both
   - Same server, different databases
   - Better for production

### Bottom Line:

**It's possible because they are completely separate database connections that operate independently!**

Just like:
- Excel can connect to Database A
- Word can connect to Database B
- They don't interfere with each other

**Same concept:** ITLDIS and Camunda each have their own database connection.

---

**Last Updated:** 2025-12-16  
**Current Setup:** ITLDIS (SQL Server) + Camunda (H2)  
**Production Setup:** Both can use SQL Server (different databases)

