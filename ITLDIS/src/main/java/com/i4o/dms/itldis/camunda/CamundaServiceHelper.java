package com.i4o.dms.itldis.camunda;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * Helper class to access Camunda services in Struts Actions
 * Provides static methods for common Camunda operations
 * 
 * @author ITLDIS Team
 */
public class CamundaServiceHelper {
    
    private static ProcessEngine processEngine;
    
    static {
        try {
            processEngine = ProcessEngineFactory.getProcessEngine();
        } catch (IllegalStateException e) {
            System.err.println("WARNING: ProcessEngine not initialized. " +
                "CamundaServiceHelper methods will fail until ProcessEngine is initialized.");
        }
    }
    
    /**
     * Get RuntimeService
     */
    public static RuntimeService getRuntimeService() {
        ensureProcessEngine();
        return processEngine.getRuntimeService();
    }
    
    /**
     * Get TaskService
     */
    public static TaskService getTaskService() {
        ensureProcessEngine();
        return processEngine.getTaskService();
    }
    
    /**
     * Get RepositoryService
     */
    public static RepositoryService getRepositoryService() {
        ensureProcessEngine();
        return processEngine.getRepositoryService();
    }
    
    /**
     * Get HistoryService
     */
    public static HistoryService getHistoryService() {
        ensureProcessEngine();
        return processEngine.getHistoryService();
    }
    
    /**
     * Get IdentityService
     */
    public static IdentityService getIdentityService() {
        ensureProcessEngine();
        return processEngine.getIdentityService();
    }
    
    /**
     * Start a process instance by process key
     */
    public static ProcessInstance startProcess(String processKey, Map<String, Object> variables) {
        return getRuntimeService().startProcessInstanceByKey(processKey, variables);
    }
    
    /**
     * Start a process instance by process key with business key
     */
    public static ProcessInstance startProcess(String processKey, String businessKey, 
                                               Map<String, Object> variables) {
        return getRuntimeService().startProcessInstanceByKey(processKey, businessKey, variables);
    }
    
    /**
     * Get tasks for a specific assignee
     */
    public static List<Task> getUserTasks(String assignee) {
        return getTaskService().createTaskQuery()
            .taskAssignee(assignee)
            .orderByTaskCreateTime()
            .desc()
            .list();
    }
    
    /**
     * Get all tasks for a user (including candidate tasks)
     */
    public static List<Task> getAllUserTasks(String userId) {
        return getTaskService().createTaskQuery()
            .taskCandidateUser(userId)
            .orderByTaskCreateTime()
            .desc()
            .list();
    }
    
    /**
     * Complete a task
     */
    public static void completeTask(String taskId, Map<String, Object> variables) {
        getTaskService().complete(taskId, variables);
    }
    
    /**
     * Complete a task without variables
     */
    public static void completeTask(String taskId) {
        getTaskService().complete(taskId);
    }
    
    /**
     * Get task by ID
     */
    public static Task getTaskById(String taskId) {
        return getTaskService().createTaskQuery()
            .taskId(taskId)
            .singleResult();
    }
    
    /**
     * Get process instance by ID
     */
    public static ProcessInstance getProcessInstance(String processInstanceId) {
        return getRuntimeService().createProcessInstanceQuery()
            .processInstanceId(processInstanceId)
            .singleResult();
    }
    
    /**
     * Get process variables
     */
    public static Map<String, Object> getProcessVariables(String processInstanceId) {
        return getRuntimeService().getVariables(processInstanceId);
    }
    
    /**
     * Set process variable
     */
    public static void setProcessVariable(String processInstanceId, String variableName, Object value) {
        getRuntimeService().setVariable(processInstanceId, variableName, value);
    }
    
    /**
     * Delete process instance
     */
    public static void deleteProcessInstance(String processInstanceId, String reason) {
        getRuntimeService().deleteProcessInstance(processInstanceId, reason);
    }
    
    /**
     * Ensure ProcessEngine is initialized
     */
    private static void ensureProcessEngine() {
        if (processEngine == null) {
            processEngine = ProcessEngineFactory.getProcessEngine();
        }
    }
}
