# Camunda BPM Testing Guide for ITLDIS

## 🎯 Quick Start Testing

### 1. Access the Test Page

After deploying the application, navigate to:
```
http://localhost:8080/itldis/camunda/test.jsp
```

This page provides a comprehensive interface to test all Camunda operations.

---

## 📋 Testing Checklist

### ✅ Step 1: Verify ProcessEngine Initialization

**Check Application Logs:**
```
[INFO] Camunda ProcessEngine initialized: default
[INFO] Deployed Camunda process: processes/sample-process.bpmn
[INFO] Deployed Camunda process: processes/approval-workflow.bpmn
```

**If you see errors:**
- Check `web.xml` has listeners configured
- Verify `camunda.properties` is in classpath
- Ensure database connection is working

---

### ✅ Step 2: Test Process Deployment

**Expected Behavior:**
- On application startup, all BPMN files in `src/main/resources/processes/` are automatically deployed
- Check logs for deployment messages

**Available Processes:**
- `SampleProcess` - Simple review workflow
- `ApprovalProcess` - Multi-step approval workflow

---

### ✅ Step 3: Start a Process Instance

**Using Test Page:**
1. Go to "1. Start a Process Instance" section
2. Select process: `SampleProcess`
3. Enter User ID: `demo`
4. Click "▶ Start Process"

**Expected Result:**
- Redirects to `success.jsp`
- Shows Process Instance ID
- Process instance is created in Camunda database

**Using URL:**
```
POST /itldis/camunda/startProcess.do
Parameters:
  - processKey: SampleProcess
  - userId: demo
  - businessKey: (optional)
  - variables: (optional JSON)
```

---

### ✅ Step 4: Get User Tasks

**Using Test Page:**
1. Go to "2. Get User Tasks" section
2. Enter User ID: `demo`
3. Click "📋 Get My Tasks"

**Expected Result:**
- Redirects to `taskList.jsp`
- Shows list of tasks assigned to the user
- Each task shows: ID, Name, Process Instance ID, Created Date

**Using URL:**
```
GET /itldis/camunda/getUserTasks.do?userId=demo
```

---

### ✅ Step 5: Get Task Details

**Using Test Page:**
1. Go to "3. Get Task Details" section
2. Enter Task ID (from Step 4)
3. Click "🔍 View Task Details"

**Expected Result:**
- Redirects to `taskDetails.jsp`
- Shows complete task information
- Displays process variables
- Shows form fields

**Using URL:**
```
GET /itldis/camunda/getTaskDetails.do?taskId=<task-id>
```

---

### ✅ Step 6: Complete a Task

**Using Test Page:**
1. Go to "4. Complete a Task" section
2. Enter Task ID
3. Add comment (optional)
4. Add variables (optional JSON): `{"approved": true}`
5. Click "✅ Complete Task"

**Expected Result:**
- Task is completed
- Process moves to next step
- Redirects to `success.jsp`

**Using URL:**
```
POST /itldis/camunda/completeTask.do
Parameters:
  - taskId: <task-id>
  - comment: (optional)
  - variables: (optional JSON)
```

---

### ✅ Step 7: View Process Instance Details

**Using Test Page:**
1. Go to "5. Process Instance Management" section
2. Enter Process Instance ID
3. Click "🔍 View Process Details"

**Expected Result:**
- Shows process instance information
- Displays current activities
- Shows process variables
- Shows business key

**Using URL:**
```
GET /itldis/camunda/getProcessInstanceDetails.do?processInstanceId=<process-instance-id>
```

---

## 🔧 Testing Scenarios

### Scenario 1: Simple Process Flow

1. **Start Process:**
   ```
   Process Key: SampleProcess
   User ID: demo
   ```

2. **Get Tasks:**
   - Should show 1 task: "Review Task"
   - Assigned to: demo

3. **Complete Task:**
   ```
   Task ID: <from step 2>
   Variables: {"approved": true, "comment": "Looks good"}
   ```

4. **Verify:**
   - Process should complete
   - No more tasks for user
   - Process instance ended

---

### Scenario 2: Approval Workflow

1. **Start Process:**
   ```
   Process Key: ApprovalProcess
   User ID: john
   Variables: {"initiator": "john", "requestTitle": "Purchase Request", "amount": 5000}
   ```

2. **Complete Submit Task:**
   - Task assigned to: john
   - Complete with variables

3. **Get Tasks for Manager:**
   ```
   User ID: manager1
   ```
   - Should show "Review Request" task
   - Assigned to managers group

4. **Complete Review:**
   ```
   Variables: {"approved": true, "reviewComment": "Approved"}
   ```

5. **Verify:**
   - Process moves to "Process Approval" task
   - Assigned back to john

---

## 🐛 Troubleshooting

### Issue: ProcessEngine not initialized

**Symptoms:**
- No logs about ProcessEngine initialization
- Errors when accessing Camunda actions

**Solutions:**
1. Check `web.xml` has listeners:
   ```xml
   <listener>
       <listener-class>com.i4o.dms.itldis.camunda.ProcessEngineFactory</listener-class>
   </listener>
   ```

2. Verify `camunda.properties` exists in `src/main/resources/`

3. Check database connection settings

---

### Issue: Processes not deploying

**Symptoms:**
- No deployment logs
- Cannot find process definitions

**Solutions:**
1. Verify BPMN files are in `src/main/resources/processes/`
2. Check file names end with `.bpmn`
3. Verify BPMN files are valid XML
4. Check `ProcessDeploymentListener` logs

---

### Issue: Tasks not showing

**Symptoms:**
- Process started but no tasks visible

**Solutions:**
1. Check task assignee matches user ID
2. For candidate groups, verify user is in group
3. Check process variables are set correctly
4. Verify task is not already completed

---

### Issue: Cannot complete task

**Symptoms:**
- Task exists but completion fails

**Solutions:**
1. Verify task ID is correct
2. Check task is assigned to current user
3. Verify required form fields are provided
4. Check process variables format (must be valid JSON)

---

## 📊 Database Verification

### Check Process Instances

**H2 Console (Development):**
```
http://localhost:8080/itldis/h2-console
JDBC URL: jdbc:h2:mem:camunda
```

**SQL Queries:**
```sql
-- List all process instances
SELECT * FROM ACT_RU_EXECUTION;

-- List all tasks
SELECT * FROM ACT_RU_TASK;

-- List deployed processes
SELECT * FROM ACT_RE_PROCDEF;

-- List process variables
SELECT * FROM ACT_RU_VARIABLE;
```

---

## 🎨 UI Testing Pages

### Available Pages:

1. **`/camunda/test.jsp`** - Comprehensive test interface
2. **`/camunda/taskList.jsp`** - List of user tasks
3. **`/camunda/taskDetails.jsp`** - Task details and completion form
4. **`/camunda/success.jsp`** - Success messages
5. **`/camunda/error.jsp`** - Error messages

---

## 📝 Code Testing Examples

### Test in Java Code:

```java
import com.i4o.dms.itldis.camunda.CamundaServiceHelper;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import java.util.*;

// Start process
Map<String, Object> vars = new HashMap<>();
vars.put("claimId", "12345");
vars.put("amount", 1000.0);
ProcessInstance pi = CamundaServiceHelper.startProcess("SampleProcess", vars);
System.out.println("Process Instance ID: " + pi.getId());

// Get tasks
List<Task> tasks = CamundaServiceHelper.getUserTasks("demo");
for (Task task : tasks) {
    System.out.println("Task: " + task.getName() + " (ID: " + task.getId() + ")");
}

// Complete task
Map<String, Object> taskVars = new HashMap<>();
taskVars.put("approved", true);
taskVars.put("comment", "Approved by manager");
CamundaServiceHelper.completeTask(tasks.get(0).getId(), taskVars);
```

---

## ✅ Success Criteria

Your Camunda integration is working correctly if:

- ✅ ProcessEngine initializes on application startup
- ✅ BPMN processes deploy automatically
- ✅ Can start process instances
- ✅ Can retrieve user tasks
- ✅ Can complete tasks
- ✅ Process variables are stored and retrieved correctly
- ✅ Process instances move through workflow steps
- ✅ Process instances complete successfully

---

## 📚 Additional Resources

- **Camunda Documentation**: https://docs.camunda.org/manual/7.18/
- **BPMN 2.0 Reference**: https://www.omg.org/spec/BPMN/2.0/
- **Camunda Modeler**: https://camunda.com/download/modeler/

---

**Last Updated**: Testing guide created  
**Status**: ✅ Ready for Testing
