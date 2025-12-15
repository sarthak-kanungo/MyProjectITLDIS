# Camunda BPM Integration Guide for Struts 1 Application

This guide explains how to integrate Camunda BPM with your Struts 1 application in the ITLDIS project.

## Overview

**Yes, Camunda BPM can be used with Struts 1 applications!** However, the integration approach is different from Spring Boot:

- **Spring Boot**: Uses auto-configuration and starters
- **Struts 1**: Requires manual configuration and ProcessEngine initialization

## Key Differences

| Aspect | Spring Boot | Struts 1 |
|--------|-------------|----------|
| Configuration | Auto-configuration | Manual configuration |
| ProcessEngine | Auto-created | Manual initialization |
| Dependencies | Spring Boot Starters | Core Camunda JARs |
| Integration | @Autowired | Manual lookup/injection |

---

## Phase 1: Add Dependencies

### Step 1: Create/Update pom.xml

If your Struts 1 application uses Maven, add these dependencies:

**Location**: `ITLDIS/pom.xml` (create if doesn't exist)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.i4o.dms</groupId>
    <artifactId>itldis-struts</artifactId>
    <version>1.0.0</version>
    <packaging>war</packaging>
    
    <properties>
        <java.version>1.8</java.version>
        <camunda.version>7.18.0</camunda.version>
    </properties>
    
    <dependencies>
        <!-- Camunda BPM Engine -->
        <dependency>
            <groupId>org.camunda.bpm</groupId>
            <artifactId>camunda-engine</artifactId>
            <version>${camunda.version}</version>
        </dependency>
        
        <!-- Camunda BPM Spring (for ProcessApplication support) -->
        <dependency>
            <groupId>org.camunda.bpm</groupId>
            <artifactId>camunda-engine-spring</artifactId>
            <version>${camunda.version}</version>
        </dependency>
        
        <!-- Database Driver (choose one) -->
        <!-- For H2 Database -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.200</version>
        </dependency>
        
        <!-- For SQL Server -->
        <dependency>
            <groupId>com.microsoft.sqlserver</groupId>
            <artifactId>mssql-jdbc</artifactId>
            <version>9.4.1.jre8</version>
        </dependency>
        
        <!-- Connection Pool (HikariCP recommended) -->
        <dependency>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP</artifactId>
            <version>3.4.5</version>
        </dependency>
        
        <!-- Camunda REST API (optional, if you want REST endpoints) -->
        <dependency>
            <groupId>org.camunda.bpm</groupId>
            <artifactId>camunda-engine-rest</artifactId>
            <version>${camunda.version}</version>
            <classifier>classes</classifier>
        </dependency>
        
        <!-- JSON Processing (for REST API) -->
        <dependency>
            <groupId>org.camunda.bpm</groupId>
            <artifactId>camunda-commons-utils</artifactId>
            <version>${camunda.version}</version>
        </dependency>
        
        <!-- Existing Struts 1 dependencies -->
        <dependency>
            <groupId>org.apache.struts</groupId>
            <artifactId>struts-core</artifactId>
            <version>1.3.10</version>
        </dependency>
        
        <!-- Add other existing dependencies here -->
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

**If you don't use Maven**: Download JARs manually from Maven Central and add to `WEB-INF/lib/`

---

## Phase 2: Configure ProcessEngine

### Step 2: Create ProcessEngine Factory

Create a singleton factory to manage the ProcessEngine lifecycle.

**Location**: `ITLDIS/src/main/java/com/i4o/dms/itldis/camunda/ProcessEngineFactory.java`

```java
package com.i4o.dms.itldis.camunda;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;
import java.io.InputStream;

/**
 * ProcessEngine Factory for Struts 1 Application
 * Initializes and manages Camunda ProcessEngine lifecycle
 */
public class ProcessEngineFactory implements ServletContextListener {
    
    private static ProcessEngine processEngine;
    private static final String PROCESS_ENGINE_NAME = "default";
    
    /**
     * Initialize ProcessEngine when application starts
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        
        try {
            // Load database configuration from properties file
            Properties props = loadProperties();
            
            // Create ProcessEngine configuration
            ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setJdbcUrl(props.getProperty("camunda.db.url"))
                .setJdbcUsername(props.getProperty("camunda.db.username"))
                .setJdbcPassword(props.getProperty("camunda.db.password"))
                .setJdbcDriver(props.getProperty("camunda.db.driver"))
                .setDatabaseType(props.getProperty("camunda.db.type", "h2"))
                .setHistory(ProcessEngineConfiguration.HISTORY_FULL)
                .setJobExecutorActivate(true)
                .setProcessEngineName(PROCESS_ENGINE_NAME);
            
            // Build ProcessEngine
            processEngine = config.buildProcessEngine();
            
            // Register with ProcessEngines
            ProcessEngines.registerProcessEngine(processEngine);
            
            servletContext.log("Camunda ProcessEngine initialized successfully");
            System.out.println("Camunda ProcessEngine initialized: " + processEngine.getName());
            
        } catch (Exception e) {
            servletContext.log("Failed to initialize Camunda ProcessEngine", e);
            throw new RuntimeException("ProcessEngine initialization failed", e);
        }
    }
    
    /**
     * Shutdown ProcessEngine when application stops
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (processEngine != null) {
            ProcessEngines.unregister(processEngine);
            processEngine.close();
            processEngine = null;
            System.out.println("Camunda ProcessEngine shut down");
        }
    }
    
    /**
     * Get the ProcessEngine instance
     */
    public static ProcessEngine getProcessEngine() {
        if (processEngine == null) {
            throw new IllegalStateException("ProcessEngine not initialized. " +
                "Ensure ProcessEngineFactory is configured as ServletContextListener.");
        }
        return processEngine;
    }
    
    /**
     * Get ProcessEngine by name (for multiple engines)
     */
    public static ProcessEngine getProcessEngine(String name) {
        return ProcessEngines.getProcessEngine(name);
    }
    
    /**
     * Load properties from camunda.properties file
     */
    private Properties loadProperties() {
        Properties props = new Properties();
        try {
            InputStream is = getClass().getClassLoader()
                .getResourceAsStream("camunda.properties");
            if (is != null) {
                props.load(is);
            } else {
                // Use default H2 configuration
                props.setProperty("camunda.db.url", 
                    "jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
                props.setProperty("camunda.db.username", "sa");
                props.setProperty("camunda.db.password", "");
                props.setProperty("camunda.db.driver", "org.h2.Driver");
                props.setProperty("camunda.db.type", "h2");
            }
        } catch (Exception e) {
            System.err.println("Error loading camunda.properties, using defaults: " + e.getMessage());
            // Set defaults
            props.setProperty("camunda.db.url", 
                "jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
            props.setProperty("camunda.db.username", "sa");
            props.setProperty("camunda.db.password", "");
            props.setProperty("camunda.db.driver", "org.h2.Driver");
            props.setProperty("camunda.db.type", "h2");
        }
        return props;
    }
}
```

### Step 3: Create Configuration Properties File

**Location**: `ITLDIS/src/main/resources/camunda.properties`

```properties
# Camunda BPM Database Configuration

# For H2 Database (Development)
camunda.db.url=jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
camunda.db.username=sa
camunda.db.password=
camunda.db.driver=org.h2.Driver
camunda.db.type=h2

# For SQL Server (Production) - Uncomment and update
#camunda.db.url=jdbc:sqlserver://localhost:1433;instanceName=SQLEXPRESS;databaseName=itldis_camunda
#camunda.db.username=your_username
#camunda.db.password=your_password
#camunda.db.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver
#camunda.db.type=mssql

# ProcessEngine Configuration
camunda.history.level=full
camunda.job.executor.activate=true
```

### Step 4: Register ServletContextListener in web.xml

**Location**: `ITLDIS/src/main/webapp/WEB-INF/web.xml`

Add this listener configuration:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee">
    
    <!-- Existing configuration -->
    
    <!-- Camunda ProcessEngine Listener -->
    <listener>
        <listener-class>com.i4o.dms.itldis.camunda.ProcessEngineFactory</listener-class>
    </listener>
    
    <!-- Existing servlets and filters -->
    
</web-app>
```

**Important**: Place the listener before the Struts ActionServlet configuration.

---

## Phase 3: Create Helper Classes

### Step 5: Create Camunda Service Helper

**Location**: `ITLDIS/src/main/java/com/i4o/dms/itldis/camunda/CamundaServiceHelper.java`

```java
package com.i4o.dms.itldis.camunda;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * Helper class to access Camunda services in Struts Actions
 */
public class CamundaServiceHelper {
    
    private static ProcessEngine processEngine;
    
    static {
        processEngine = ProcessEngineFactory.getProcessEngine();
    }
    
    /**
     * Get RuntimeService
     */
    public static RuntimeService getRuntimeService() {
        return processEngine.getRuntimeService();
    }
    
    /**
     * Get TaskService
     */
    public static TaskService getTaskService() {
        return processEngine.getTaskService();
    }
    
    /**
     * Get RepositoryService
     */
    public static RepositoryService getRepositoryService() {
        return processEngine.getRepositoryService();
    }
    
    /**
     * Get HistoryService
     */
    public static HistoryService getHistoryService() {
        return processEngine.getHistoryService();
    }
    
    /**
     * Start a process instance
     */
    public static ProcessInstance startProcess(String processKey, Map<String, Object> variables) {
        return getRuntimeService().startProcessInstanceByKey(processKey, variables);
    }
    
    /**
     * Get tasks for a user
     */
    public static List<Task> getUserTasks(String assignee) {
        return getTaskService().createTaskQuery()
            .taskAssignee(assignee)
            .list();
    }
    
    /**
     * Complete a task
     */
    public static void completeTask(String taskId, Map<String, Object> variables) {
        getTaskService().complete(taskId, variables);
    }
}
```

---

## Phase 4: Integrate with Struts Actions

### Step 6: Create Struts Action for Process Management

**Location**: `ITLDIS/src/main/java/com/i4o/dms/itldis/camunda/action/ProcessAction.java`

```java
package com.i4o.dms.itldis.camunda.action;

import com.i4o.dms.itldis.camunda.CamundaServiceHelper;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Struts Action for Camunda Process Management
 */
public class ProcessAction extends Action {
    
    /**
     * Start a process instance
     */
    public ActionForward startProcess(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) {
        try {
            String processKey = request.getParameter("processKey");
            String userId = (String) request.getSession().getAttribute("userId");
            
            Map<String, Object> variables = new HashMap<>();
            variables.put("userId", userId);
            variables.put("requestId", request.getParameter("requestId"));
            
            ProcessInstance instance = CamundaServiceHelper.startProcess(processKey, variables);
            
            request.setAttribute("processInstanceId", instance.getId());
            request.setAttribute("message", "Process started successfully");
            
            return mapping.findForward("success");
            
        } catch (Exception e) {
            request.setAttribute("error", "Failed to start process: " + e.getMessage());
            return mapping.findForward("error");
        }
    }
    
    /**
     * Get user tasks
     */
    public ActionForward getUserTasks(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            
            List<Task> tasks = CamundaServiceHelper.getUserTasks(userId);
            
            request.setAttribute("tasks", tasks);
            
            return mapping.findForward("taskList");
            
        } catch (Exception e) {
            request.setAttribute("error", "Failed to get tasks: " + e.getMessage());
            return mapping.findForward("error");
        }
    }
    
    /**
     * Complete a task
     */
    public ActionForward completeTask(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) {
        try {
            String taskId = request.getParameter("taskId");
            String comment = request.getParameter("comment");
            
            Map<String, Object> variables = new HashMap<>();
            variables.put("comment", comment);
            variables.put("approved", "true".equals(request.getParameter("approved")));
            
            CamundaServiceHelper.completeTask(taskId, variables);
            
            request.setAttribute("message", "Task completed successfully");
            
            return mapping.findForward("success");
            
        } catch (Exception e) {
            request.setAttribute("error", "Failed to complete task: " + e.getMessage());
            return mapping.findForward("error");
        }
    }
}
```

### Step 7: Configure Action in struts-config.xml

**Location**: `ITLDIS/src/main/webapp/WEB-INF/struts-config.xml`

Add action mappings:

```xml
<struts-config>
    <!-- Existing configuration -->
    
    <!-- Camunda Process Actions -->
    <action path="/camunda/startProcess" 
            type="com.i4o.dms.itldis.camunda.action.ProcessAction" 
            method="startProcess"
            scope="request">
        <forward name="success" path="/camunda/processStarted.jsp"/>
        <forward name="error" path="/camunda/error.jsp"/>
    </action>
    
    <action path="/camunda/getUserTasks" 
            type="com.i4o.dms.itldis.camunda.action.ProcessAction" 
            method="getUserTasks"
            scope="request">
        <forward name="taskList" path="/camunda/taskList.jsp"/>
        <forward name="error" path="/camunda/error.jsp"/>
    </action>
    
    <action path="/camunda/completeTask" 
            type="com.i4o.dms.itldis.camunda.action.ProcessAction" 
            method="completeTask"
            scope="request">
        <forward name="success" path="/camunda/taskCompleted.jsp"/>
        <forward name="error" path="/camunda/error.jsp"/>
    </action>
    
</struts-config>
```

---

## Phase 5: Deploy Process Definitions

### Step 8: Create Process Resources Directory

**Location**: `ITLDIS/src/main/resources/processes/`

Create this directory and add your BPMN files here.

### Step 9: Deploy Processes Programmatically

**Location**: `ITLDIS/src/main/java/com/i4o/dms/itldis/camunda/ProcessDeploymentListener.java`

```java
package com.i4o.dms.itldis.camunda;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Deploys BPMN processes on application startup
 */
public class ProcessDeploymentListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ProcessEngine engine = ProcessEngineFactory.getProcessEngine();
            RepositoryService repositoryService = engine.getRepositoryService();
            
            // Deploy processes from classpath
            Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/sample-process.bpmn")
                .name("ITLDIS Processes")
                .deploy();
            
            System.out.println("Processes deployed: " + deployment.getId());
            sce.getServletContext().log("Camunda processes deployed successfully");
            
        } catch (Exception e) {
            sce.getServletContext().log("Failed to deploy processes", e);
            System.err.println("Process deployment failed: " + e.getMessage());
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup if needed
    }
}
```

**Add to web.xml**:
```xml
<listener>
    <listener-class>com.i4o.dms.itldis.camunda.ProcessDeploymentListener</listener-class>
</listener>
```

---

## Phase 6: Optional - REST API Integration

### Step 10: Add REST Servlet (Optional)

If you want REST API endpoints, add this servlet:

**Location**: `ITLDIS/src/main/java/com/i4o/dms/itldis/camunda/servlet/CamundaRestServlet.java`

```java
package com.i4o.dms.itldis.camunda.servlet;

import org.camunda.bpm.engine.rest.impl.CamundaRestResources;
import org.camunda.bpm.engine.rest.impl.DefaultExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * REST Servlet for Camunda BPM
 */
public class CamundaRestServlet extends HttpServlet {
    
    private Application application;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        application = new Application() {
            @Override
            public Set<Class<?>> getClasses() {
                Set<Class<?>> classes = new HashSet<>();
                classes.addAll(CamundaRestResources.getResourceClasses());
                classes.add(DefaultExceptionHandler.class);
                return classes;
            }
            
            @Override
            public Set<Object> getSingletons() {
                Set<Object> singletons = new HashSet<>();
                singletons.addAll(CamundaRestResources.getResourceSingletons());
                return singletons;
            }
        };
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, java.io.IOException {
        // Delegate to JAX-RS implementation (Jersey, RESTEasy, etc.)
        // This requires additional setup with JAX-RS provider
    }
}
```

**Note**: Full REST API requires JAX-RS implementation (Jersey or RESTEasy). For Struts 1, it's easier to use Struts Actions instead.

---

## Usage Examples

### Example 1: Start Process from Struts Action

```java
public class WarrantyClaimAction extends Action {
    public ActionForward createClaim(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) {
        WarrantyForm warrantyForm = (WarrantyForm) form;
        
        // Your existing business logic
        // ... save warranty claim ...
        
        // Start Camunda process
        Map<String, Object> variables = new HashMap<>();
        variables.put("claimId", warrantyForm.getClaimId());
        variables.put("claimAmount", warrantyForm.getAmount());
        
        ProcessInstance instance = CamundaServiceHelper.startProcess(
            "WarrantyClaimApproval", variables);
        
        // Store process instance ID for later reference
        warrantyForm.setProcessInstanceId(instance.getId());
        
        return mapping.findForward("success");
    }
}
```

### Example 2: Complete Task from JSP

```jsp
<!-- taskComplete.jsp -->
<form action="camunda/completeTask.do" method="post">
    <input type="hidden" name="taskId" value="${task.id}"/>
    <textarea name="comment" placeholder="Enter comment"></textarea>
    <input type="checkbox" name="approved" value="true"/> Approve
    <button type="submit">Complete Task</button>
</form>
```

---

## Configuration Summary

### Files Created/Modified:

1. ✅ `pom.xml` - Add Camunda dependencies
2. ✅ `web.xml` - Add ProcessEngineFactory listener
3. ✅ `camunda.properties` - Database configuration
4. ✅ `ProcessEngineFactory.java` - ProcessEngine initialization
5. ✅ `CamundaServiceHelper.java` - Service access helper
6. ✅ `ProcessAction.java` - Struts action for processes
7. ✅ `struts-config.xml` - Action mappings
8. ✅ `ProcessDeploymentListener.java` - Auto-deploy processes

---

## Testing Checklist

- [ ] Application starts without errors
- [ ] ProcessEngine initialized (check logs)
- [ ] Processes deployed successfully
- [ ] Can start process from Struts action
- [ ] Can query tasks
- [ ] Can complete tasks
- [ ] Database tables created
- [ ] Process instances visible in database

---

## Troubleshooting

### Issue: ProcessEngine not initialized
**Solution**: 
- Check `web.xml` has ProcessEngineFactory listener
- Verify listener is loaded before Struts ActionServlet
- Check application logs for initialization errors

### Issue: Cannot find ProcessEngine
**Solution**:
- Ensure ProcessEngineFactory.contextInitialized() was called
- Check that ProcessEngine is registered: `ProcessEngines.getProcessEngine("default")`

### Issue: Database connection fails
**Solution**:
- Verify `camunda.properties` has correct database settings
- Check database server is running
- Verify JDBC driver is in classpath

### Issue: Processes not deploying
**Solution**:
- Check BPMN files are in `src/main/resources/processes/`
- Verify BPMN syntax is valid
- Check ProcessDeploymentListener is configured

---

## Next Steps

1. **Add Dependencies**: Update `pom.xml` or add JARs manually
2. **Configure ProcessEngine**: Create ProcessEngineFactory
3. **Create Helper Classes**: Set up CamundaServiceHelper
4. **Integrate with Actions**: Create Struts actions for process management
5. **Deploy Processes**: Add BPMN files and deploy them
6. **Test Integration**: Verify everything works

---

## Resources

- **Camunda Documentation**: https://docs.camunda.org/
- **Camunda User Guide**: https://docs.camunda.org/manual/7.18/user-guide/
- **ProcessEngine API**: https://docs.camunda.org/manual/7.18/reference/javadoc/

---

**Last Updated**: Camunda BPM Struts 1 Integration Guide  
**Status**: Ready for implementation  
**Compatibility**: Struts 1.x, Java 8+, Camunda 7.18.0
