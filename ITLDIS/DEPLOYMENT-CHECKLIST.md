# Camunda BPM Deployment Checklist for ITLDIS

## ✅ Pre-Deployment Verification

### Build Status
- [x] **Maven Build**: ✅ SUCCESS
- [x] **WAR File Created**: `target/itldis.war`
- [x] **Compilation**: ✅ All 415 source files compiled successfully
- [x] **Dependencies**: ✅ All Camunda dependencies resolved

### Files Verification
- [x] **Camunda Classes**: All integration classes compiled
- [x] **BPMN Processes**: `sample-process.bpmn` and `approval-workflow.bpmn` included
- [x] **Configuration**: `camunda.properties` included in WAR
- [x] **JSP Pages**: All test pages included (`test.jsp`, `taskList.jsp`, etc.)
- [x] **Web Configuration**: `web.xml` and `struts-config.xml` updated

---

## 📦 Deployment Steps

### Step 1: Prepare WAR File

**Location**: `ITLDIS/target/itldis.war`

**Verify WAR Contents**:
```bash
# Check WAR file exists
ls -lh target/itldis.war

# Extract and verify (optional)
jar -xf target/itldis.war
```

**Expected Contents**:
- `WEB-INF/classes/com/i4o/dms/itldis/camunda/` - Camunda integration classes
- `WEB-INF/classes/processes/` - BPMN process files
- `WEB-INF/classes/camunda.properties` - Configuration file
- `WEB-INF/lib/` - All dependencies including Camunda JARs
- `camunda/test.jsp` - Test page

---

### Step 2: Database Configuration

#### Option A: H2 Database (Development/Testing)

**Already Configured** ✅

The `camunda.properties` file is set to use H2 in-memory database:
```properties
camunda.db.url=jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
camunda.db.type=h2
```

**No additional setup required** - H2 will create tables automatically on first startup.

#### Option B: SQL Server (Production)

**Before Deployment:**

1. **Create Database**:
   ```sql
   CREATE DATABASE itldis_camunda;
   ```

2. **Update `camunda.properties`**:
   ```properties
   camunda.db.url=jdbc:sqlserver://localhost:1433;databaseName=itldis_camunda
   camunda.db.username=your_username
   camunda.db.password=your_password
   camunda.db.type=mssql
   ```

3. **Run Camunda Database Scripts**:
   - Download from: https://github.com/camunda/camunda-bpm-platform/tree/master/distro/sql
   - Execute: `sql/create/mssql_engine_7.18.0.sql`

---

### Step 3: Deploy to Application Server

#### For Tomcat:

1. **Stop Tomcat** (if running)

2. **Copy WAR File**:
   ```bash
   cp target/itldis.war $CATALINA_HOME/webapps/
   ```

3. **Start Tomcat**:
   ```bash
   $CATALINA_HOME/bin/startup.sh
   # or
   $CATALINA_HOME/bin/catalina.sh start
   ```

4. **Verify Deployment**:
   - Check logs: `$CATALINA_HOME/logs/catalina.out`
   - Look for: `Camunda ProcessEngine initialized: default`

#### For WebLogic:

1. **Access WebLogic Console**: `http://localhost:7001/console`

2. **Deploy Application**:
   - Navigate to: Deployments → Install
   - Select: `target/itldis.war`
   - Choose: Install as application
   - Target to: Your server/cluster
   - Finish deployment

3. **Start Application**:
   - Go to: Deployments
   - Select: `itldis`
   - Click: Start → Servicing all requests

#### For JBoss/WildFly:

1. **Copy WAR File**:
   ```bash
   cp target/itldis.war $JBOSS_HOME/standalone/deployments/
   ```

2. **Start JBoss**:
   ```bash
   $JBOSS_HOME/bin/standalone.sh
   ```

3. **Verify Deployment**:
   - Check logs for deployment messages
   - Look for Camunda initialization logs

---

### Step 4: Verify Deployment

#### Check Application Logs

**Look for these messages**:
```
[INFO] Camunda ProcessEngine initialized: default
[INFO] Deployed Camunda process: processes/sample-process.bpmn
[INFO] Deployed Camunda process: processes/approval-workflow.bpmn
```

**If you see errors**:
- Check database connection
- Verify `camunda.properties` is in classpath
- Check `web.xml` listeners are configured

#### Access Test Page

**URL**: `http://localhost:8080/itldis/camunda/test.jsp`

**Expected**: Test page loads with all Camunda operation forms

---

### Step 5: Initial Testing

#### Test 1: ProcessEngine Initialization

1. **Check Logs**: Verify ProcessEngine initialized
2. **Check Database**: Verify Camunda tables created (if using SQL Server)

#### Test 2: Process Deployment

1. **Check Logs**: Verify BPMN files deployed
2. **Access Test Page**: Navigate to test page

#### Test 3: Start Process

1. **Go to Test Page**: `http://localhost:8080/itldis/camunda/test.jsp`
2. **Start Process**:
   - Process Key: `SampleProcess`
   - User ID: `demo`
   - Click "Start Process"
3. **Verify**: Redirects to success page with Process Instance ID

#### Test 4: Get Tasks

1. **Get User Tasks**:
   - User ID: `demo`
   - Click "Get My Tasks"
2. **Verify**: Shows task list with "Review Task"

#### Test 5: Complete Task

1. **Complete Task**:
   - Enter Task ID from previous step
   - Variables: `{"approved": true}`
   - Click "Complete Task"
2. **Verify**: Task completed, process ended

---

## 🔧 Post-Deployment Configuration

### Configure Process Variables

If you need to set default process variables, edit:
- `src/main/java/com/i4o/dms/itldis/camunda/ProcessEngineFactory.java`
- Add default variables in ProcessEngine configuration

### Configure User Groups

For candidate groups (like `managers` in ApprovalProcess):

1. **Option 1**: Use Camunda Identity Service
   - Configure in `ProcessEngineFactory.java`
   - Add users and groups programmatically

2. **Option 2**: Use External Identity Provider
   - Integrate with your existing user management
   - Map users to Camunda groups

### Configure Email Notifications

If you want email notifications for tasks:

1. **Add JavaMail Configuration** to `camunda.properties`:
   ```properties
   camunda.mail.server.host=smtp.example.com
   camunda.mail.server.port=587
   camunda.mail.server.username=your_email@example.com
   camunda.mail.server.password=your_password
   ```

2. **Configure in ProcessEngineFactory**:
   - Add mail server configuration
   - Enable mail notifications

---

## 📊 Monitoring & Maintenance

### Database Maintenance

**For H2 (Development)**:
- Data is in-memory, lost on restart
- Use for testing only

**For SQL Server (Production)**:
- Regular backups recommended
- Monitor table sizes:
  ```sql
  SELECT COUNT(*) FROM ACT_RU_EXECUTION;
  SELECT COUNT(*) FROM ACT_HI_PROCINST;
  ```

### Log Monitoring

**Key Log Messages**:
- ProcessEngine initialization
- Process deployment
- Process instance creation
- Task completion
- Errors and exceptions

**Log Location**:
- Tomcat: `$CATALINA_HOME/logs/catalina.out`
- WebLogic: `$DOMAIN_HOME/servers/<server>/logs/<server>.log`
- JBoss: `$JBOSS_HOME/standalone/log/server.log`

---

## 🐛 Troubleshooting

### Issue: ProcessEngine Not Initialized

**Symptoms**:
- No initialization logs
- Errors when accessing Camunda actions

**Solutions**:
1. Check `web.xml` has listeners configured
2. Verify `camunda.properties` exists in classpath
3. Check database connection settings
4. Verify database driver is in `WEB-INF/lib/`

### Issue: Processes Not Deploying

**Symptoms**:
- No deployment logs
- Cannot find process definitions

**Solutions**:
1. Verify BPMN files in `src/main/resources/processes/`
2. Check file names end with `.bpmn`
3. Validate BPMN XML syntax
4. Check `ProcessDeploymentListener` logs

### Issue: Database Connection Failed

**Symptoms**:
- Connection errors in logs
- ProcessEngine fails to initialize

**Solutions**:
1. Verify database is running
2. Check connection URL format
3. Verify username/password
4. Check database driver JAR is included
5. For SQL Server: Verify instance name and port

### Issue: Tasks Not Showing

**Symptoms**:
- Process started but no tasks visible

**Solutions**:
1. Check task assignee matches user ID
2. For candidate groups, verify user is in group
3. Check process variables are set correctly
4. Verify task is not already completed

---

## ✅ Deployment Verification Checklist

- [ ] WAR file built successfully
- [ ] WAR file deployed to application server
- [ ] Application server started successfully
- [ ] ProcessEngine initialized (check logs)
- [ ] BPMN processes deployed (check logs)
- [ ] Test page accessible (`/camunda/test.jsp`)
- [ ] Can start a process instance
- [ ] Can retrieve user tasks
- [ ] Can complete tasks
- [ ] Process instances complete successfully

---

## 📚 Additional Resources

- **Camunda Documentation**: https://docs.camunda.org/manual/7.18/
- **Deployment Guide**: `CAMUNDA-BPM-STRUTS1-INTEGRATION.md`
- **Testing Guide**: `CAMUNDA-TESTING-GUIDE.md`
- **Quick Start**: `CAMUNDA-QUICK-START.md`

---

## 🎯 Next Steps After Deployment

1. **Test All Workflows**: Use test page to verify all processes work
2. **Integrate with Application**: Add Camunda calls to your existing Struts actions
3. **Create Custom Processes**: Design workflows specific to your business needs
4. **Configure Notifications**: Set up email/SMS notifications for tasks
5. **Monitor Performance**: Track process execution times and optimize

---

**Status**: ✅ Ready for Deployment  
**WAR File**: `target/itldis.war`  
**Build Status**: ✅ SUCCESS  
**Last Updated**: Deployment checklist created
