# Camunda BPM Struts 1 Integration - Execution Todo List

Quick execution checklist for integrating Camunda BPM with your Struts 1 application.

## Quick Answer: ✅ YES, Camunda BPM works with Struts 1!

Camunda BPM can be integrated with Struts 1 applications, but requires manual configuration (unlike Spring Boot's auto-configuration).

---

## Execution Steps

### ✅ Step 1: Add Camunda Dependencies
**Status**: Ready  
**Action**: Add Camunda JARs to project

**Option A - Maven** (if using Maven):
- [ ] Create/update `pom.xml` in `ITLDIS/` directory
- [ ] Add Camunda dependencies (see `CAMUNDA-BPM-STRUTS1-INTEGRATION.md`)
- [ ] Run `mvn clean install`

**Option B - Manual** (if not using Maven):
- [ ] Download Camunda JARs from Maven Central
- [ ] Add JARs to `ITLDIS/src/main/webapp/WEB-INF/lib/`
- [ ] Required JARs:
  - `camunda-engine-7.18.0.jar`
  - `camunda-engine-spring-7.18.0.jar`
  - Database driver (H2 or SQL Server)
  - Connection pool (HikariCP)

**Verification**: [ ] Dependencies available in classpath

---

### ✅ Step 2: Create ProcessEngine Factory
**Status**: Ready  
**Location**: `ITLDIS/src/main/java/com/i4o/dms/itldis/camunda/ProcessEngineFactory.java`

**Action**:
- [ ] Create package: `com.i4o.dms.itldis.camunda`
- [ ] Create `ProcessEngineFactory.java` class
- [ ] Implement `ServletContextListener`
- [ ] Initialize ProcessEngine in `contextInitialized()`
- [ ] Shutdown ProcessEngine in `contextDestroyed()`

**Verification**: [ ] Class compiles without errors

---

### ✅ Step 3: Create Configuration File
**Status**: Ready  
**Location**: `ITLDIS/src/main/resources/camunda.properties`

**Action**:
- [ ] Create `camunda.properties` file
- [ ] Configure database connection (H2 or SQL Server)
- [ ] Set history level and job executor settings

**Verification**: [ ] Properties file exists and is readable

---

### ✅ Step 4: Register Listener in web.xml
**Status**: Ready  
**Location**: `ITLDIS/src/main/webapp/WEB-INF/web.xml`

**Action**:
- [ ] Open `web.xml`
- [ ] Add `<listener>` for `ProcessEngineFactory`
- [ ] Place listener BEFORE Struts ActionServlet configuration

**Code to Add**:
```xml
<listener>
    <listener-class>com.i4o.dms.itldis.camunda.ProcessEngineFactory</listener-class>
</listener>
```

**Verification**: [ ] Listener added correctly

---

### ✅ Step 5: Create Service Helper Class
**Status**: Ready  
**Location**: `ITLDIS/src/main/java/com/i4o/dms/itldis/camunda/CamundaServiceHelper.java`

**Action**:
- [ ] Create `CamundaServiceHelper.java`
- [ ] Add static methods to access Camunda services
- [ ] Methods: `getRuntimeService()`, `getTaskService()`, etc.

**Verification**: [ ] Helper class compiles

---

### ✅ Step 6: Create Struts Action
**Status**: Ready  
**Location**: `ITLDIS/src/main/java/com/i4o/dms/itldis/camunda/action/ProcessAction.java`

**Action**:
- [ ] Create `action` package
- [ ] Create `ProcessAction.java` extending `Action`
- [ ] Implement methods: `startProcess()`, `getUserTasks()`, `completeTask()`
- [ ] Use `CamundaServiceHelper` to access Camunda services

**Verification**: [ ] Action class compiles

---

### ✅ Step 7: Configure Action Mappings
**Status**: Ready  
**Location**: `ITLDIS/src/main/webapp/WEB-INF/struts-config.xml`

**Action**:
- [ ] Open `struts-config.xml`
- [ ] Add action mappings for process operations
- [ ] Configure forwards for success/error pages

**Verification**: [ ] Actions configured correctly

---

### ✅ Step 8: Create Process Deployment Listener
**Status**: Ready  
**Location**: `ITLDIS/src/main/java/com/i4o/dms/itldis/camunda/ProcessDeploymentListener.java`

**Action**:
- [ ] Create `ProcessDeploymentListener.java`
- [ ] Implement `ServletContextListener`
- [ ] Deploy BPMN files from classpath in `contextInitialized()`
- [ ] Add listener to `web.xml`

**Verification**: [ ] Listener compiles and is registered

---

### ✅ Step 9: Create Process Resources Directory
**Status**: Ready  
**Location**: `ITLDIS/src/main/resources/processes/`

**Action**:
- [ ] Create `processes` directory
- [ ] Add BPMN files (e.g., `sample-process.bpmn`)
- [ ] Use Camunda Modeler to create processes

**Verification**: [ ] Directory exists with BPMN files

---

### ✅ Step 10: Test Application Startup
**Status**: Ready  
**Action**: Start application and verify Camunda initialization

**Steps**:
1. [ ] Deploy application to server (Tomcat/WebLogic/etc.)
2. [ ] Start server
3. [ ] Check logs for: "Camunda ProcessEngine initialized"
4. [ ] Verify no errors related to Camunda

**Expected Log Output**:
```
Camunda ProcessEngine initialized: default
Processes deployed: deployment-12345
```

**Verification**: [ ] Application starts successfully

---

### ✅ Step 11: Test Process Operations
**Status**: Ready  
**Action**: Test process start, task query, and task completion

**Tests**:
- [ ] Start a process instance via Struts action
- [ ] Query user tasks
- [ ] Complete a task
- [ ] Verify process completes successfully

**Verification**: [ ] All operations work correctly

---

### ✅ Step 12: Integrate with Existing Modules
**Status**: Ready  
**Action**: Integrate Camunda with existing ITLDIS workflows

**Integration Points**:
- [ ] Warranty claim workflow
- [ ] Service request workflow
- [ ] Purchase order workflow
- [ ] Other business processes

**Verification**: [ ] Integration works with existing code

---

## Quick Reference

### Key Classes to Create:
1. `ProcessEngineFactory.java` - Initialize ProcessEngine
2. `CamundaServiceHelper.java` - Access Camunda services
3. `ProcessAction.java` - Struts action for processes
4. `ProcessDeploymentListener.java` - Auto-deploy processes

### Key Files to Modify:
1. `web.xml` - Add listeners
2. `struts-config.xml` - Add action mappings
3. `camunda.properties` - Database configuration

### Key Directories:
1. `src/main/resources/processes/` - BPMN files
2. `src/main/java/com/i4o/dms/itldis/camunda/` - Camunda classes

---

## Differences from Spring Boot

| Aspect | Spring Boot | Struts 1 |
|--------|-------------|----------|
| Configuration | Auto | Manual |
| ProcessEngine | Auto-created | Manual init |
| Dependency Injection | @Autowired | Static helper |
| Configuration File | application.properties | camunda.properties |

---

## Support

For detailed implementation guide, see:
- `CAMUNDA-BPM-STRUTS1-INTEGRATION.md` - Complete integration guide
- `CAMUNDA-BPM-SETUP-GUIDE.md` - General Camunda setup (Spring Boot)

---

**Status**: Ready for execution  
**Estimated Time**: 4-6 hours for complete integration  
**Difficulty**: Medium (requires manual configuration)
