# Camunda Database Tables - Detailed Explanation

## Why Camunda Creates ~40+ Tables

Camunda BPM is a **workflow engine** that needs persistent storage for:
- Process definitions (your BPMN workflows)
- Running process instances
- User tasks
- Process history and audit trail
- Variables and data
- Scheduled jobs

**All of this requires database tables!**

---

## Table Categories and Their Purpose

### 1. Repository Tables (ACT_RE_*) - "What Processes Do We Have?"

**Purpose:** Store deployed BPMN process definitions

**Key Tables:**

#### `ACT_RE_DEPLOYMENT`
- **Stores:** Deployment metadata
- **Contains:** Deployment ID, name, deployment time
- **Why:** Tracks when processes were deployed, allows rollback

#### `ACT_RE_PROCDEF`
- **Stores:** Process definitions (your BPMN files)
- **Contains:** Process key, version, BPMN XML, diagram
- **Why:** This is where your `sample-process.bpmn` is stored

#### `ACT_RE_MODEL`
- **Stores:** Process models
- **Contains:** Model metadata
- **Why:** Additional model information

**Real Example:**
```
When you deploy "warranty-approval.bpmn":
→ ACT_RE_DEPLOYMENT: Records deployment #123
→ ACT_RE_PROCDEF: Stores the BPMN XML and creates process definition
```

---

### 2. Runtime Tables (ACT_RU_*) - "What's Running Right Now?"

**Purpose:** Track currently executing process instances

**Key Tables:**

#### `ACT_RU_EXECUTION`
- **Stores:** Active process instances
- **Contains:** Process instance ID, current activity, parent execution
- **Why:** Tracks where each process is in the workflow
- **Example:** Warranty claim #12345 is at "Manager Approval" step

#### `ACT_RU_TASK`
- **Stores:** Active user tasks
- **Contains:** Task ID, assignee, due date, task name
- **Why:** Lists tasks waiting for users (e.g., "Approve Warranty Claim")
- **Example:** Task assigned to manager@company.com

#### `ACT_RU_VARIABLE`
- **Stores:** Process variables (data)
- **Contains:** Variable name, value, type
- **Why:** Stores data passed through the process
- **Example:** claimId="12345", amount=5000.00, status="pending"

#### `ACT_RU_JOB`
- **Stores:** Asynchronous jobs
- **Contains:** Job type, retries, due date
- **Why:** Handles timers, async service tasks, scheduled jobs
- **Example:** "Send email reminder" scheduled for tomorrow

#### `ACT_RU_EVENT_SUBSCR`
- **Stores:** Event subscriptions
- **Contains:** Event type, activity ID
- **Why:** Manages message events, signal events
- **Example:** Waiting for "PaymentReceived" message

#### `ACT_RU_IDENTITYLINK`
- **Stores:** Task assignments
- **Contains:** User ID, group ID, task ID
- **Why:** Links tasks to users/groups (who can work on what)
- **Example:** Task assigned to "managers" group

**Real Example:**
```
Warranty claim process running:
→ ACT_RU_EXECUTION: Process instance #789 running
→ ACT_RU_TASK: Task "Approve Claim" created for manager
→ ACT_RU_VARIABLE: claimId=12345, amount=5000 stored
```

---

### 3. History Tables (ACT_HI_*) - "What Happened Before?"

**Purpose:** Complete audit trail of all process executions

**Key Tables:**

#### `ACT_HI_PROCINST`
- **Stores:** Process instance history
- **Contains:** Start time, end time, duration, start user
- **Why:** Complete record of every process that ran
- **Example:** Warranty claim #12345: Started 2025-12-16, Completed 2025-12-17

#### `ACT_HI_TASKINST`
- **Stores:** Task instance history
- **Contains:** Task name, assignee, start time, end time, duration
- **Why:** History of all completed tasks
- **Example:** "Approve Claim" task: Completed by manager@company.com in 2 hours

#### `ACT_HI_VARINST`
- **Stores:** Variable history
- **Contains:** Variable name, value at each point in time
- **Why:** Track how variables changed over time
- **Example:** status changed: "pending" → "approved" → "completed"

#### `ACT_HI_ACTINST`
- **Stores:** Activity instance history
- **Contains:** Every activity/step that executed
- **Why:** Detailed step-by-step execution history
- **Example:** Start → User Task → Service Task → End (all recorded)

#### `ACT_HI_COMMENT`
- **Stores:** Comments and notes
- **Contains:** Comment text, author, time
- **Why:** User comments on tasks/processes
- **Example:** Manager comment: "Approved - within policy limits"

#### `ACT_HI_ATTACHMENT`
- **Stores:** File attachments
- **Contains:** File name, type, content
- **Why:** Documents attached to processes/tasks
- **Example:** Invoice PDF attached to warranty claim

#### `ACT_HI_DETAIL`
- **Stores:** Additional detail history
- **Contains:** Various historical details
- **Why:** Extra historical information

**Real Example:**
```
Completed warranty claim:
→ ACT_HI_PROCINST: Process completed, took 2 days
→ ACT_HI_TASKINST: 3 tasks completed (Review, Approve, Dispatch)
→ ACT_HI_VARINST: status changed 3 times
→ ACT_HI_ACTINST: 8 activities executed
```

---

### 4. General Tables (ACT_GE_*) - "Engine Data"

**Purpose:** Store engine configuration and binary data

**Key Tables:**

#### `ACT_GE_BYTEARRAY`
- **Stores:** Binary data
- **Contains:** BPMN files, process diagrams, attachments
- **Why:** Stores actual file content (BPMN XML, images, etc.)
- **Example:** Your `sample-process.bpmn` file content stored here

#### `ACT_GE_PROPERTY`
- **Stores:** Engine properties
- **Contains:** Engine version, schema version, configuration
- **Why:** Tracks Camunda version and schema version
- **Example:** schema.version=7.18.0, next.dbid=1000

---

## Why These Tables Are Necessary

### Without Repository Tables (ACT_RE_*)
❌ **Can't deploy processes**
- No way to store BPMN definitions
- Can't version control processes
- Can't track available processes

### Without Runtime Tables (ACT_RU_*)
❌ **Can't run processes**
- Can't start process instances
- Can't create user tasks
- Can't store process data
- Can't execute jobs

### Without History Tables (ACT_HI_*)
❌ **No audit trail**
- Can't see what happened
- Can't generate reports
- Can't analyze performance
- No compliance/audit records

### Without General Tables (ACT_GE_*)
❌ **Engine can't function**
- Can't store BPMN file content
- Can't track engine version
- Can't manage binary data

---

## Complete Workflow Example

### Scenario: Warranty Claim Approval Process

**1. Deploy Process:**
```
→ ACT_RE_DEPLOYMENT: Deployment #1 created
→ ACT_RE_PROCDEF: "WarrantyClaimApproval" process definition stored
→ ACT_GE_BYTEARRAY: BPMN XML file stored
```

**2. Start Process Instance:**
```
→ ACT_RU_EXECUTION: Process instance #1001 created
→ ACT_RU_VARIABLE: claimId=12345, amount=5000 stored
→ ACT_HI_PROCINST: Process start recorded
→ ACT_HI_ACTINST: "Start Event" activity recorded
```

**3. Create User Task:**
```
→ ACT_RU_TASK: "Approve Claim" task created
→ ACT_RU_IDENTITYLINK: Task assigned to "managers" group
→ ACT_HI_TASKINST: Task creation recorded
→ ACT_HI_ACTINST: "User Task" activity recorded
```

**4. Complete Task:**
```
→ ACT_RU_TASK: Task removed (completed)
→ ACT_HI_TASKINST: Task completion recorded (who, when, duration)
→ ACT_HI_VARINST: status changed to "approved"
→ ACT_HI_ACTINST: Task completion activity recorded
```

**5. Process Completes:**
```
→ ACT_RU_EXECUTION: Execution removed (completed)
→ ACT_HI_PROCINST: Process completion recorded
→ ACT_HI_ACTINST: "End Event" activity recorded
→ All data moved to history tables
```

---

## Table Count Summary

| Category | Approx. Tables | Purpose |
|----------|----------------|---------|
| **Repository (ACT_RE_*)** | ~3-5 tables | Store process definitions |
| **Runtime (ACT_RU_*)** | ~6-8 tables | Track active processes |
| **History (ACT_HI_*)** | ~15-20 tables | Audit trail and reporting |
| **General (ACT_GE_*)** | ~2 tables | Engine data |
| **Identity (ACT_ID_*)** | ~5-8 tables | User/group management (if enabled) |
| **Total** | **~40+ tables** | Complete workflow management |

---

## Key Points

### ✅ Why So Many Tables?

1. **Separation of Concerns:**
   - Runtime vs History (performance)
   - Different data types (executions, tasks, variables)
   - Efficient querying (indexed properly)

2. **Performance:**
   - Active processes in runtime tables (fast)
   - Completed processes in history tables (archived)
   - Separate indexes for different queries

3. **Audit Compliance:**
   - Complete history preserved
   - Can't modify history (read-only)
   - Full traceability

4. **Scalability:**
   - Can archive old history
   - Runtime tables stay lean
   - Efficient for large deployments

### ✅ Are They Necessary?

**YES - Absolutely essential!**

- Camunda **cannot function** without these tables
- They store **everything** the engine needs
- This is **standard** for all Camunda installations
- **No way around it** - this is how Camunda works

### ✅ Do They Affect Your Database?

**NO - Completely separate:**
- Camunda uses its own database (H2 or separate SQL Server)
- Your ITLDIS tables are untouched
- No conflicts or interference
- Can even use same SQL Server, different database

---

## Summary

**Why these tables?**
- ✅ Camunda needs to store process definitions
- ✅ Track running workflows
- ✅ Manage user tasks
- ✅ Keep complete audit trail
- ✅ Store process data
- ✅ Execute scheduled jobs

**Are they necessary?**
- ✅ **YES** - Camunda cannot work without them
- ✅ Standard for all Camunda installations
- ✅ Automatic creation (no manual work)

**Do they affect your database?**
- ✅ **NO** - Completely separate
- ✅ Your ITLDIS database is untouched
- ✅ Can use same SQL Server, different database

---

**Last Updated:** 2025-12-16  
**Total Tables:** ~40+  
**Purpose:** Complete workflow engine functionality  
**Creation:** Automatic on first startup

