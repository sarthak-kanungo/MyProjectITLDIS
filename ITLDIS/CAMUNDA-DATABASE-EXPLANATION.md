# Camunda BPM Database Configuration Explained

## Why H2 Database?

### Current Configuration: H2 In-Memory Database

**H2 is being used for:**
- ✅ **Development/Testing** - No database setup required
- ✅ **Quick Start** - Works immediately without installation
- ✅ **Isolation** - Doesn't affect your existing ITLDIS database
- ✅ **Zero Configuration** - No server setup needed

**H2 Database Details:**
- **Type:** In-memory database (data stored in RAM)
- **Persistence:** Data is lost when Tomcat stops (by design for development)
- **Location:** `jdbc:h2:mem:camunda` (memory only, no files)

---

## Does Camunda Create Tables?

### ✅ YES - Camunda Automatically Creates Tables

**Configuration:**
```java
.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
```

This setting means:
- ✅ **Auto-creates tables** if they don't exist
- ✅ **Updates schema** if Camunda version changes
- ✅ **No manual SQL scripts needed**

---

## Why These Tables Are Created

Camunda is a **process engine** that needs to store and manage:
1. **Process Definitions** - Your BPMN workflow designs
2. **Running Processes** - Active workflow instances
3. **Tasks** - User tasks waiting to be completed
4. **History** - Completed processes for auditing
5. **Variables** - Data passed through workflows
6. **Jobs** - Scheduled and asynchronous tasks

**Without these tables, Camunda cannot:**
- ❌ Store process definitions
- ❌ Track running workflows
- ❌ Manage user tasks
- ❌ Keep history/audit trail
- ❌ Store process variables
- ❌ Execute scheduled jobs

---

## Tables Created by Camunda (~40+ tables)

### 1. Repository Tables (ACT_RE_*) - Process Definitions

**Purpose:** Store deployed BPMN process definitions

| Table | Purpose | Why Needed |
|-------|---------|------------|
| `ACT_RE_DEPLOYMENT` | Stores deployment information | Tracks when/which processes were deployed |
| `ACT_RE_PROCDEF` | Process definitions (your BPMN files) | Stores the actual workflow design |
| `ACT_RE_MODEL` | Process models | Stores process model metadata |

**Example:** When you deploy `sample-process.bpmn`, it's stored here.

---

### 2. Runtime Tables (ACT_RU_*) - Active Processes

**Purpose:** Track currently running process instances

| Table | Purpose | Why Needed |
|-------|---------|------------|
| `ACT_RU_EXECUTION` | Running process instances | Tracks which workflows are currently executing |
| `ACT_RU_TASK` | Active user tasks | Stores tasks waiting for users to complete |
| `ACT_RU_VARIABLE` | Process variables | Stores data (like claimId, amount) in running processes |
| `ACT_RU_JOB` | Asynchronous jobs | Stores scheduled tasks (timers, async service tasks) |
| `ACT_RU_EVENT_SUBSCR` | Event subscriptions | Manages message events and signal events |
| `ACT_RU_IDENTITYLINK` | Task assignments | Links tasks to users/groups |

**Example:** When you start a warranty claim process, an execution record is created here.

---

### 3. History Tables (ACT_HI_*) - Audit Trail

**Purpose:** Store completed process history for auditing and reporting

| Table | Purpose | Why Needed |
|-------|---------|------------|
| `ACT_HI_PROCINST` | Process instance history | Complete history of all process runs |
| `ACT_HI_TASKINST` | Task instance history | History of all completed tasks |
| `ACT_HI_VARINST` | Variable history | Historical values of process variables |
| `ACT_HI_ACTINST` | Activity instance history | Every step/activity that executed |
| `ACT_HI_COMMENT` | Comments/notes | User comments on tasks/processes |
| `ACT_HI_ATTACHMENT` | Attachments | Files attached to processes/tasks |
| `ACT_HI_DETAIL` | Detail history | Additional historical data |

**Example:** You can query: "How many warranty claims were approved last month?"

---

### 4. General Tables (ACT_GE_*) - Engine Data

**Purpose:** Store engine configuration and general data

| Table | Purpose | Why Needed |
|-------|---------|------------|
| `ACT_GE_BYTEARRAY` | Binary data storage | Stores BPMN files, diagrams, attachments |
| `ACT_GE_PROPERTY` | Engine properties | Stores engine configuration and version info |

**Example:** Your BPMN file content is stored as binary data here.

---

## Why Each Category is Essential

### Repository Tables (ACT_RE_*)
**Without them:** Can't deploy or store process definitions
- ❌ No BPMN processes can be deployed
- ❌ Can't version control processes
- ❌ Can't track which processes are available

### Runtime Tables (ACT_RU_*)
**Without them:** Can't run processes
- ❌ Can't start process instances
- ❌ Can't create user tasks
- ❌ Can't store process variables
- ❌ Can't execute asynchronous jobs

### History Tables (ACT_HI_*)
**Without them:** No audit trail or reporting
- ❌ Can't see completed processes
- ❌ Can't generate reports
- ❌ Can't audit what happened
- ❌ Can't analyze process performance

### General Tables (ACT_GE_*)
**Without them:** Engine can't function
- ❌ Can't store BPMN file content
- ❌ Can't track engine version
- ❌ Can't manage binary data

---

## Real-World Example: Warranty Claim Process

When you start a warranty claim workflow:

1. **Repository (ACT_RE_*):**
   - `ACT_RE_PROCDEF` - Stores the warranty claim BPMN definition

2. **Runtime (ACT_RU_*):**
   - `ACT_RU_EXECUTION` - Creates execution record for this claim
   - `ACT_RU_TASK` - Creates "Approve Claim" task for manager
   - `ACT_RU_VARIABLE` - Stores claimId=12345, amount=5000, etc.

3. **History (ACT_HI_*):**
   - `ACT_HI_PROCINST` - Records that process started
   - `ACT_HI_ACTINST` - Records each step (start → task → end)

4. **When task is completed:**
   - `ACT_RU_TASK` - Task removed (moved to history)
   - `ACT_HI_TASKINST` - Task completion recorded
   - `ACT_HI_VARINST` - Variable changes tracked

5. **When process completes:**
   - `ACT_RU_EXECUTION` - Removed (moved to history)
   - `ACT_HI_PROCINST` - Process completion recorded
   - All history preserved for reporting

---

## Summary: Why These Tables Are Created

| Category | Purpose | Critical? |
|----------|---------|----------|
| **Repository** | Store process definitions | ✅ YES - Can't deploy processes without it |
| **Runtime** | Track active processes | ✅ YES - Can't run processes without it |
| **History** | Audit and reporting | ✅ YES - No audit trail without it |
| **General** | Engine data storage | ✅ YES - Engine can't function without it |

**Bottom Line:**
- These tables are **essential** for Camunda to function
- They store **everything** Camunda needs to manage workflows
- **No manual creation needed** - Camunda handles it automatically
- **Standard for all Camunda installations** - Every Camunda setup has these tables

---

## Database Changes Summary

### What Happens on Startup:

1. **Camunda checks if tables exist**
2. **If tables don't exist:**
   - Creates all required tables automatically
   - Sets up indexes and constraints
   - Initializes engine data

3. **If tables exist:**
   - Checks schema version
   - Updates if needed (schema migration)

### No Manual Changes Required

- ✅ No SQL scripts to run
- ✅ No manual table creation
- ✅ No schema setup needed
- ✅ Everything is automatic

---

## H2 vs SQL Server

### Current Setup: H2 (Development)

**Pros:**
- ✅ No installation needed
- ✅ Works immediately
- ✅ Good for testing
- ✅ Doesn't affect production database

**Cons:**
- ❌ Data lost on restart (in-memory)
- ❌ Not suitable for production
- ❌ No persistence

### Production Setup: SQL Server

**Configuration Available:**
```properties
# Uncomment in camunda.properties:
camunda.db.url=jdbc:sqlserver://localhost:1433;databaseName=itldis_camunda
camunda.db.username=your_username
camunda.db.password=your_password
camunda.db.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver
camunda.db.type=mssql
```

**Pros:**
- ✅ Data persists across restarts
- ✅ Production-ready
- ✅ Can share with other applications
- ✅ Backup and recovery

**Setup Required:**
1. Create database: `itldis_camunda`
2. Update `camunda.properties`
3. Restart application
4. Camunda will create tables automatically

---

## Does This Affect Your Existing Database?

### ✅ NO - Completely Separate

**Your ITLDIS Application Database:**
- Uses: SQL Server (your existing database)
- Connection: `dbConnection.java` → SQL Server
- Tables: Your application tables (warranty, service, inventory, etc.)
- **Not affected by Camunda** - Completely separate connection

**Camunda Database:**
- Uses: H2 (development) or separate SQL Server database (production)
- Tables: Only Camunda tables (ACT_*)
- **Completely separate**

**They are independent:**
- Different database connections
- Different schemas
- No conflicts
- No interference

---

## Switching to SQL Server (Production)

### Step 1: Create Database

```sql
CREATE DATABASE itldis_camunda;
```

### Step 2: Update Configuration

Edit `src/main/resources/camunda.properties`:

```properties
# Comment out H2:
#camunda.db.url=jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
#camunda.db.type=h2

# Uncomment SQL Server:
camunda.db.url=jdbc:sqlserver://localhost:1433;databaseName=itldis_camunda
camunda.db.username=your_username
camunda.db.password=your_password
camunda.db.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver
camunda.db.type=mssql
```

### Step 3: Rebuild and Deploy

```batch
mvn clean package
fix-login-404.bat
```

### Step 4: Verify

On first startup, Camunda will:
- Connect to SQL Server
- Create all tables automatically
- Be ready to use

---

## Database Schema Update Options

**Current Setting:**
```java
DB_SCHEMA_UPDATE_TRUE
```

**Other Options:**
- `DB_SCHEMA_UPDATE_TRUE` - Auto-create/update (development)
- `DB_SCHEMA_UPDATE_FALSE` - No auto-update (production)
- `DB_SCHEMA_UPDATE_CREATE` - Create only, no updates

**For Production:**
Change to `DB_SCHEMA_UPDATE_FALSE` after initial setup:
```java
.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
```

---

## Summary

### Why H2?
- ✅ **Development convenience** - No setup needed
- ✅ **Quick testing** - Works immediately
- ✅ **Isolation** - Doesn't affect your database
- ✅ **No installation** - Embedded database

### Does Camunda Create Tables?
- ✅ **YES** - Automatically creates ~40+ tables
- ✅ **On first startup** - No manual SQL needed
- ✅ **Schema updates** - Handles version upgrades
- ✅ **Zero manual work** - Fully automatic

### Database Changes?
- ✅ **Only Camunda tables** - ACT_* tables (ACT_RU_*, ACT_RE_*, ACT_HI_*, etc.)
- ✅ **Separate database** - Doesn't touch your ITLDIS tables
- ✅ **Automatic** - No manual changes needed
- ✅ **No impact** - Your existing database is unaffected

### For Production?
- ✅ **Switch to SQL Server** - Update `camunda.properties`
- ✅ **Create database** - `itldis_camunda` (or your preferred name)
- ✅ **Camunda creates tables** - Still automatic
- ✅ **Use script:** `switch-to-sqlserver-camunda.bat`

---

## Quick Reference

**Current Setup:**
- Database: H2 (In-Memory)
- Tables: Auto-created by Camunda (~40+ tables)
- Data: Lost on restart (development only)

**Production Setup:**
- Database: SQL Server
- Tables: Auto-created by Camunda (same tables)
- Data: Persists across restarts

**Your ITLDIS Database:**
- Completely separate
- Not affected by Camunda
- Uses your existing SQL Server database

---

**Last Updated:** 2025-12-16  
**Current Database:** H2 (In-Memory) - Development  
**Production Database:** SQL Server (ready to configure)  
**Tables Created:** ~40+ Camunda tables (automatic)

