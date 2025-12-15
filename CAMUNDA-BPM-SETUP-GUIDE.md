# Camunda BPM Configuration Guide for ITLDIS Project

This guide explains how Camunda BPM has been configured in the ITLDIS project and how to use it.

## Table of Contents
1. [Overview](#overview)
2. [Configuration Details](#configuration-details)
3. [Accessing Camunda Web Applications](#accessing-camunda-web-applications)
4. [Database Setup](#database-setup)
5. [Creating Your First Process](#creating-your-first-process)
6. [Using Camunda Services](#using-camunda-services)
7. [Security Configuration](#security-configuration)
8. [Troubleshooting](#troubleshooting)

## Overview

Camunda BPM (Business Process Management) has been integrated into the ITLDIS Spring Boot application to enable workflow automation and process management capabilities.

### What is Camunda BPM?
- **Process Engine**: Executes BPMN 2.0 process definitions
- **Task Management**: Manages user tasks and service tasks
- **Process Monitoring**: Track and monitor running process instances
- **Web Applications**: Cockpit (monitoring), Tasklist (task management), Admin (administration)

## Configuration Details

### 1. Dependencies Added

The following Camunda dependencies have been added to `pom.xml`:

```xml
<!-- Camunda BPM Spring Boot Starter -->
<dependency>
    <groupId>org.camunda.bpm.springboot</groupId>
    <artifactId>camunda-bpm-spring-boot-starter</artifactId>
    <version>7.18.0</version>
</dependency>

<!-- Camunda BPM Spring Boot Starter REST -->
<dependency>
    <groupId>org.camunda.bpm.springboot</groupId>
    <artifactId>camunda-bpm-spring-boot-starter-rest</artifactId>
    <version>7.18.0</version>
</dependency>

<!-- Camunda BPM Spring Boot Starter Webapp -->
<dependency>
    <groupId>org.camunda.bpm.springboot</groupId>
    <artifactId>camunda-bpm-spring-boot-starter-webapp</artifactId>
    <version>7.18.0</version>
</dependency>
```

### 2. Application Properties Configuration

Camunda configuration has been added to `application.properties`:

```properties
# Camunda Database Configuration
camunda.bpm.database.type=h2  # Use 'mssql' for SQL Server
camunda.bpm.database.schema-update=true

# Camunda Admin User (Default: demo/demo)
camunda.bpm.admin-user.id=demo
camunda.bpm.admin-user.password=demo
camunda.bpm.admin-user.first-name=Demo
camunda.bpm.admin-user.last-name=User

# Camunda Web Application Configuration
camunda.bpm.webapp.enabled=true

# Camunda REST API Configuration
camunda.bpm.rest.enabled=true
camunda.bpm.rest.path=/rest

# Camunda History Level (full, audit, activity, none)
camunda.bpm.history-level=full

# Camunda Process Application Name
camunda.bpm.process-application-name=itldis-process-application
```

### 3. Configuration Class

A `CamundaConfiguration` class has been created at:
`src/main/java/com/i4o/dms/itldis/configurations/CamundaConfiguration.java`

This class:
- Enables the Camunda Process Application
- Provides beans for accessing Camunda services (RuntimeService, TaskService, etc.)

### 4. Security Configuration

Security has been updated to allow access to Camunda webapps and REST API:
- `/camunda/**` - Camunda web applications
- `/app/**` - Camunda webapp resources
- `/api/engine/**` - Camunda REST API
- `/rest/**` - Camunda REST API (alternative path)

**Note**: Currently set to `permitAll()` for development. In production, secure these endpoints with proper authentication.

## Accessing Camunda Web Applications

After starting the application, you can access:

### 1. Camunda Cockpit (Process Monitoring)
- **URL**: `http://localhost:8383/camunda/app/cockpit/default/`
- **Purpose**: Monitor running process instances, view process definitions, analyze process performance
- **Login**: demo / demo (default)

### 2. Camunda Tasklist (Task Management)
- **URL**: `http://localhost:8383/camunda/app/tasklist/default/`
- **Purpose**: View and complete user tasks assigned to you
- **Login**: demo / demo (default)

### 3. Camunda Admin (Administration)
- **URL**: `http://localhost:8383/camunda/app/admin/default/`
- **Purpose**: Manage users, groups, authorizations, and system settings
- **Login**: demo / demo (default)

### 4. Camunda REST API
- **Base URL**: `http://localhost:8383/rest/`
- **Documentation**: `http://localhost:8383/camunda/app/api-docs/`
- **Example**: `http://localhost:8383/rest/engine/default/process-definition`

## Database Setup

### H2 Database (Development)
Camunda will automatically create its database tables in the H2 in-memory database when the application starts.

### SQL Server (Production)
When using SQL Server, update `application.properties`:

```properties
# Uncomment SQL Server configuration
spring.datasource.url=jdbc:sqlserver://your-server:1433;instanceName=SQLEXPRESS;databaseName=itldis_uat_2
spring.datasource.username=your-username
spring.datasource.password=your-password

# Update Camunda database type
camunda.bpm.database.type=mssql
camunda.bpm.database.schema-update=true
```

**Important**: On first run with SQL Server, Camunda will create all necessary tables automatically. Ensure the database user has CREATE TABLE permissions.

## Creating Your First Process

### Step 1: Create a BPMN Process Definition

1. Create a new directory: `src/main/resources/processes/`
2. Create a BPMN file (e.g., `sample-process.bpmn`) using:
   - **Camunda Modeler** (download from https://camunda.com/download/modeler/)
   - **Online BPMN Editor** (e.g., bpmn.io)
   - **IDE Plugin** (e.g., Eclipse BPMN2 Modeler)

### Step 2: Example BPMN Process

Here's a simple example (`src/main/resources/processes/sample-process.bpmn`):

```xml
<?xml version="1.0" encoding="UTF-8"?>
<bpmn:definitions xmlns:bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL"
                  xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
                  xmlns:dc="http://www.omg.org/spec/DD/20100524/DC"
                  xmlns:camunda="http://camunda.org/schema/1.0/bpmn"
                  id="Definitions_1"
                  targetNamespace="http://bpmn.io/schema/bpmn">
  <bpmn:process id="SampleProcess" name="Sample Process" isExecutable="true">
    <bpmn:startEvent id="StartEvent_1"/>
    <bpmn:userTask id="Task_1" name="Review Task">
      <bpmn:extensionElements>
        <camunda:formData>
          <camunda:formField id="comment" label="Comment" type="string"/>
        </camunda:formData>
      </bpmn:extensionElements>
    </bpmn:userTask>
    <bpmn:endEvent id="EndEvent_1"/>
    <bpmn:sequenceFlow id="Flow_1" sourceRef="StartEvent_1" targetRef="Task_1"/>
    <bpmn:sequenceFlow id="Flow_2" sourceRef="Task_1" targetRef="EndEvent_1"/>
  </bpmn:process>
  <bpmndi:BPMNDiagram id="BPMNDiagram_1">
    <!-- Diagram elements here -->
  </bpmndi:BPMNDiagram>
</bpmn:definitions>
```

### Step 3: Deploy and Start Process

The process will be automatically deployed when the application starts. You can also deploy programmatically:

```java
@Autowired
private RepositoryService repositoryService;

public void deployProcess() {
    repositoryService.createDeployment()
        .addClasspathResource("processes/sample-process.bpmn")
        .deploy();
}
```

### Step 4: Start a Process Instance

```java
@Autowired
private RuntimeService runtimeService;

public void startProcess() {
    Map<String, Object> variables = new HashMap<>();
    variables.put("processVariable", "value");
    
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
        "SampleProcess", 
        variables
    );
    
    System.out.println("Process started with ID: " + processInstance.getId());
}
```

## Using Camunda Services

### Injecting Camunda Services

```java
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ProcessController {
    
    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private RepositoryService repositoryService;
    
    @Autowired
    private HistoryService historyService;
    
    // Your methods here
}
```

### Common Operations

#### 1. Start a Process Instance
```java
ProcessInstance instance = runtimeService.startProcessInstanceByKey(
    "ProcessKey",
    variables
);
```

#### 2. Query Tasks
```java
List<Task> tasks = taskService.createTaskQuery()
    .taskAssignee("username")
    .list();
```

#### 3. Complete a Task
```java
taskService.complete(taskId, variables);
```

#### 4. Query Process Instances
```java
List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery()
    .processDefinitionKey("ProcessKey")
    .active()
    .list();
```

#### 5. Query History
```java
List<HistoricProcessInstance> historicInstances = historyService
    .createHistoricProcessInstanceQuery()
    .finished()
    .list();
```

## Security Configuration

### Current Setup (Development)
Camunda endpoints are currently open (`permitAll()`) for easy development access.

### Production Security Recommendations

1. **Secure Camunda Webapps**:
   ```java
   .antMatchers("/camunda/**", "/app/**").hasRole("ADMIN")
   ```

2. **Secure REST API**:
   ```java
   .antMatchers("/rest/**", "/api/engine/**").authenticated()
   ```

3. **Configure Camunda Authorization**:
   - Use Camunda Admin to create users and groups
   - Set up authorizations for processes, tasks, and resources
   - Enable authorization checks: `camunda.bpm.authorization.enabled=true`

## Troubleshooting

### Issue: Camunda tables not created
**Solution**: Ensure `camunda.bpm.database.schema-update=true` is set in `application.properties`

### Issue: Cannot access Camunda webapps
**Solution**: 
- Check security configuration allows `/camunda/**` paths
- Verify application is running on correct port (default: 8383)
- Check browser console for errors

### Issue: Process not deploying
**Solution**:
- Verify BPMN file is in `src/main/resources/processes/` or classpath
- Check BPMN file syntax using Camunda Modeler
- Review application logs for deployment errors

### Issue: Tasks not appearing
**Solution**:
- Verify task assignee matches logged-in user
- Check process instance is running
- Review task query filters

## Additional Resources

- **Camunda Documentation**: https://docs.camunda.org/
- **Camunda REST API**: https://docs.camunda.org/manual/7.18/reference/rest/
- **BPMN 2.0 Specification**: https://www.omg.org/spec/BPMN/2.0/
- **Camunda Modeler**: https://camunda.com/download/modeler/
- **Camunda Community Forum**: https://forum.camunda.org/

## Next Steps

1. **Create Process Definitions**: Design your business processes using Camunda Modeler
2. **Implement Service Tasks**: Create Java delegates for automated tasks
3. **Configure User Tasks**: Set up forms and task assignments
4. **Set Up Monitoring**: Use Cockpit to monitor process execution
5. **Integrate with Application**: Use Camunda services in your controllers and services

## Support

For issues or questions:
1. Check Camunda documentation
2. Review application logs
3. Consult Camunda community forum
4. Contact your development team

---

**Last Updated**: Configuration completed for ITLDIS Project
**Camunda Version**: 7.18.0
**Spring Boot Version**: 2.3.3.RELEASE
