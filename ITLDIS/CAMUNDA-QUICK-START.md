# Camunda BPM Quick Start Guide for ITLDIS

## ✅ Implementation Status

**Camunda BPM has been fully integrated into your Struts 1 application!**

All Java classes, configuration files, and action mappings have been created and configured.

---

## What Was Implemented

### ✅ Core Components Created
1. ProcessEngineFactory - Initializes Camunda engine
2. CamundaServiceHelper - Helper methods for Camunda operations
3. ProcessAction - Struts actions for process management
4. ProcessDeploymentListener - Auto-deploys BPMN processes
5. Configuration files and web.xml updates

### ✅ Configuration Updated
- `web.xml` - Added listeners
- `struts-config.xml` - Added 7 action mappings
- `camunda.properties` - Database configuration

---

## Next Steps (Required)

### 1. Add Camunda Dependencies ⚠️

**You must add Camunda JARs to your project:**

**Option A: Manual Download**
1. Download from Maven Central:
   - https://repo1.maven.org/maven2/org/camunda/bpm/engine/camunda-engine/7.18.0/
   - https://repo1.maven.org/maven2/org/camunda/bpm/engine/camunda-engine-spring/7.18.0/
2. Copy JARs to: `ITLDIS/src/main/webapp/WEB-INF/lib/`

**Required JARs:**
- `camunda-engine-7.18.0.jar`
- `camunda-engine-spring-7.18.0.jar`
- Database driver (H2: `h2-1.4.200.jar` or SQL Server: `mssql-jdbc-9.4.1.jre8.jar`)
- Connection pool (optional): `HikariCP-3.4.5.jar`

**Option B: Maven** (if using Maven)
- See `CAMUNDA-BPM-STRUTS1-INTEGRATION.md` for pom.xml configuration

### 2. Create BPMN Process Files

1. Download Camunda Modeler: https://camunda.com/download/modeler/
2. Create a simple process (Start Event → User Task → End Event)
3. Save as `sample-process.bpmn` in `src/main/resources/processes/`
4. The process will auto-deploy on application startup

### 3. Test the Integration

1. **Deploy application** to your server (Tomcat/WebLogic/etc.)
2. **Start server** and check logs for:
   ```
   Camunda ProcessEngine initialized: default
   Deployed Camunda process: processes/sample-process.bpmn
   ```
3. **Test via browser**:
   - `http://localhost:8080/itldis/camunda/getUserTasks.do?userId=demo`

---

## Quick Usage Examples

### Start a Process
```jsp
<form action="camunda/startProcess.do" method="post">
    <input type="hidden" name="processKey" value="SampleProcess"/>
    <input type="hidden" name="userId" value="${sessionScope.userId}"/>
    <button type="submit">Start Process</button>
</form>
```

### Complete a Task
```jsp
<form action="camunda/completeTask.do" method="post">
    <input type="hidden" name="taskId" value="${task.id}"/>
    <textarea name="comment"></textarea>
    <input type="checkbox" name="approved" value="true"/> Approve
    <button type="submit">Complete</button>
</form>
```

### Use in Java Code
```java
import com.i4o.dms.itldis.camunda.CamundaServiceHelper;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import java.util.HashMap;
import java.util.Map;

// Start a process
Map<String, Object> variables = new HashMap<>();
variables.put("claimId", "12345");
ProcessInstance instance = CamundaServiceHelper.startProcess("SampleProcess", variables);
```

---

## Available Actions

| URL | Method | Description |
|-----|--------|-------------|
| `/camunda/startProcess.do` | POST | Start process instance |
| `/camunda/getUserTasks.do` | GET | Get user tasks |
| `/camunda/getAllUserTasks.do` | GET | Get all user tasks |
| `/camunda/getTaskDetails.do` | GET | Get task details |
| `/camunda/completeTask.do` | POST | Complete task |
| `/camunda/getProcessInstanceDetails.do` | GET | Get process details |
| `/camunda/deleteProcessInstance.do` | POST | Delete process |

---

## Configuration

### Database Setup

Edit `src/main/resources/camunda.properties`:

**H2 (Development)** - Already configured ✅
```properties
camunda.db.url=jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
camunda.db.type=h2
```

**SQL Server (Production)** - Uncomment and update:
```properties
camunda.db.url=jdbc:sqlserver://localhost:1433;databaseName=itldis_camunda
camunda.db.username=your_username
camunda.db.password=your_password
camunda.db.type=mssql
```

---

## Troubleshooting

### ❌ ClassNotFoundException: org.camunda...
**Solution**: Add Camunda JARs to `WEB-INF/lib/` (Step 1 above)

### ❌ ProcessEngine not initialized
**Solution**: Check `web.xml` has listeners configured correctly

### ❌ Processes not deploying
**Solution**: 
- Verify BPMN files in `src/main/resources/processes/`
- Check `ProcessDeploymentListener.java` includes your process files

---

## Files Created Summary

```
ITLDIS/
├── src/main/java/com/i4o/dms/itldis/camunda/
│   ├── ProcessEngineFactory.java          ✅ Created
│   ├── CamundaServiceHelper.java          ✅ Created
│   ├── ProcessDeploymentListener.java     ✅ Created
│   └── action/
│       └── ProcessAction.java             ✅ Created
├── src/main/resources/
│   ├── camunda.properties                 ✅ Created
│   └── processes/                         ✅ Created
│       └── README.md                      ✅ Created
├── src/main/webapp/
│   ├── camunda/
│   │   ├── error.jsp                      ✅ Created
│   │   └── success.jsp                    ✅ Created
│   └── WEB-INF/
│       ├── web.xml                        ✅ Updated
│       └── struts-config.xml              ✅ Updated
└── CAMUNDA-IMPLEMENTATION-SUMMARY.md      ✅ Created
```

---

## Documentation

- **`CAMUNDA-IMPLEMENTATION-SUMMARY.md`** - Complete implementation details
- **`CAMUNDA-BPM-STRUTS1-INTEGRATION.md`** - Full integration guide
- **`CAMUNDA-STRUTS1-EXECUTION-TODO.md`** - Step-by-step checklist

---

## Support

- **Camunda Docs**: https://docs.camunda.org/
- **Camunda Modeler**: https://camunda.com/download/modeler/
- **BPMN 2.0 Spec**: https://www.omg.org/spec/BPMN/2.0/

---

**Status**: ✅ Ready for Testing  
**Next Action**: Add Camunda JARs and create BPMN processes  
**Estimated Time**: 30 minutes to complete setup
