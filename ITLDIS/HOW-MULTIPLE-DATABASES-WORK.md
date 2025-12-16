# How ITLDIS (SQL Server) and Camunda (H2) Work Together

## The Question

**How is it possible for:**
- ITLDIS application → Uses **SQL Server**
- Camunda BPM → Uses **H2 Database**

**Answer: They use completely separate database connections!**

---

## How It Works

### Two Independent Database Connections

Your application has **TWO separate database connections** that don't interfere with each other:

#### 1. ITLDIS Database Connection (SQL Server)
```
ITLDIS Application Code
    ↓
dbConnection.java
    ↓
SQL Server Database
    ↓
Your ITLDIS Tables (warranty, service, inventory, etc.)
```

#### 2. Camunda Database Connection (H2)
```
Camunda ProcessEngine
    ↓
ProcessEngineFactory.java
    ↓
H2 In-Memory Database
    ↓
Camunda Tables (ACT_* tables)
```

---

## Technical Explanation

### Separate Connection Objects

**ITLDIS uses:**
```java
// In dbConnection.java
Connection con = new dbConnection().getConnection();
// Connects to: SQL Server (your existing database)
```

**Camunda uses:**
```java
// In ProcessEngineFactory.java
ProcessEngineConfiguration config = ...
    .setJdbcUrl("jdbc:h2:mem:camunda;...")
    .setJdbcDriver("org.h2.Driver")
// Connects to: H2 In-Memory Database
```

### Different JDBC URLs

**ITLDIS Connection:**
```
jdbc:sqlserver://localhost:1433;databaseName=your_itldis_db
Driver: com.microsoft.sqlserver.jdbc.SQLServerDriver
```

**Camunda Connection:**
```
jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1
Driver: org.h2.Driver
```

**They are completely different connections!**

---

## Visual Diagram

```
┌─────────────────────────────────────────────────┐
│           ITLDIS Application (Tomcat)           │
├─────────────────────────────────────────────────┤
│                                                   │
│  ┌──────────────────┐      ┌──────────────────┐ │
│  │  ITLDIS Code     │      │  Camunda Code    │ │
│  │  (Struts Actions)│      │  (ProcessEngine)  │ │
│  └────────┬─────────┘      └────────┬─────────┘ │
│           │                          │           │
│           │ Connection 1             │ Connection 2
│           │                          │           │
└───────────┼──────────────────────────┼───────────┘
            │                          │
            ▼                          ▼
    ┌──────────────┐          ┌──────────────┐
    │ SQL Server  │          │  H2 Database │
    │  Database   │          │  (In-Memory) │
    └──────────────┘          └──────────────┘
    │                          │
    │ Your ITLDIS Tables       │ Camunda Tables
    │ - warranty               │ - ACT_RU_*
    │ - service                │ - ACT_RE_*
    │ - inventory              │ - ACT_HI_*
    │ - users                  │ - ACT_GE_*
    │ - etc.                   │
    └──────────────────────────┘
```

---

## Why This Works

### 1. Separate Connection Pools

Each component has its own connection:
- **ITLDIS:** Uses `dbConnection.java` → SQL Server
- **Camunda:** Uses `ProcessEngineFactory` → H2

### 2. Different JDBC Drivers

- **ITLDIS:** `sqljdbc4.jar` (SQL Server driver)
- **Camunda:** `h2-1.4.200.jar` (H2 driver)

Both drivers are in `WEB-INF/lib/` and don't conflict.

### 3. Independent Lifecycle

- **ITLDIS connection:** Created when your code needs it
- **Camunda connection:** Created when ProcessEngine initializes

They operate independently.

---

## Real Example

### When ITLDIS Code Runs:

```java
// In WarrantyAction.java
Connection con = new dbConnection().getConnection();
// → Connects to SQL Server
// → Queries: SELECT * FROM warranty_claims
// → Uses your existing ITLDIS database
```

### When Camunda Code Runs:

```java
// In ProcessEngineFactory.java
ProcessEngine engine = config.buildProcessEngine();
// → Connects to H2 database
// → Creates: ACT_RU_TASK, ACT_RU_EXECUTION, etc.
// → Uses separate H2 database
```

**They never interfere with each other!**

---

## Can They Share the Same SQL Server?

### ✅ YES - Even Better Option!

You can configure Camunda to use **SQL Server** (same server, different database):

```
SQL Server Instance
├── Database: itldis_main (your ITLDIS tables)
│   └── Tables: warranty, service, inventory, users, etc.
│
└── Database: itldis_camunda (Camunda tables)
    └── Tables: ACT_RU_*, ACT_RE_*, ACT_HI_*, etc.
```

**Benefits:**
- ✅ Same SQL Server instance
- ✅ Different databases (no conflicts)
- ✅ Data persists (not in-memory)
- ✅ Production-ready

**Configuration:**
```properties
# In camunda.properties
camunda.db.url=jdbc:sqlserver://localhost:1433;databaseName=itldis_camunda
camunda.db.type=mssql
```

---

## Current Setup Explained

### Development Setup (Current)

```
ITLDIS Application
│
├── Connection 1: SQL Server
│   └── Database: your_existing_itldis_db
│       └── Your application tables
│
└── Connection 2: H2 (In-Memory)
    └── Database: camunda (in RAM)
        └── Camunda ACT_* tables
```

**Why H2 for Camunda?**
- ✅ No setup needed
- ✅ Works immediately
- ✅ Doesn't affect your SQL Server
- ✅ Good for development/testing

### Production Setup (Recommended)

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
```

**Benefits:**
- ✅ Data persists
- ✅ Production-ready
- ✅ Can backup together
- ✅ Same server, different databases

---

## Key Points

### ✅ How It's Possible

1. **Separate JDBC Connections**
   - ITLDIS: One connection to SQL Server
   - Camunda: Another connection to H2
   - They don't know about each other

2. **Different Drivers**
   - SQL Server driver for ITLDIS
   - H2 driver for Camunda
   - Both in classpath, no conflicts

3. **Independent Configuration**
   - ITLDIS: Configured in `dbConnection.java`
   - Camunda: Configured in `camunda.properties`
   - Completely separate

4. **No Interference**
   - ITLDIS queries its tables
   - Camunda queries its tables
   - They never conflict

### ✅ Why H2 for Camunda?

**Development Benefits:**
- ✅ No database setup needed
- ✅ Works immediately
- ✅ Doesn't require SQL Server configuration
- ✅ Good for testing

**Production:**
- Switch to SQL Server (same server, different database)
- Use `switch-to-sqlserver-camunda.bat`

---

## Comparison

| Aspect | ITLDIS | Camunda |
|--------|--------|---------|
| **Database** | SQL Server | H2 (dev) / SQL Server (prod) |
| **Connection** | `dbConnection.java` | `ProcessEngineFactory.java` |
| **Driver** | `sqljdbc4.jar` | `h2-1.4.200.jar` |
| **Tables** | Your app tables | ACT_* tables |
| **Purpose** | Application data | Workflow engine data |
| **Location** | SQL Server instance | H2 in-memory (or SQL Server) |

---

## Summary

### How It Works:

1. **Two Separate Connections:**
   - ITLDIS → SQL Server (your database)
   - Camunda → H2 (separate database)

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

Just like you can have:
- Excel connecting to Database A
- Word connecting to Database B
- They don't interfere with each other

Same concept: ITLDIS and Camunda each have their own database connection.

---

**Last Updated:** 2025-12-16  
**Current Setup:** ITLDIS (SQL Server) + Camunda (H2)  
**Production Setup:** Both can use SQL Server (different databases)

