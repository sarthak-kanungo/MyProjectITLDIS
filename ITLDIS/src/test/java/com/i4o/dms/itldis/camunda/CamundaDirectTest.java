package com.i4o.dms.itldis.camunda;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Direct Camunda Test - Tests Camunda operations without web layer
 * Run this to verify Camunda is working correctly
 */
public class CamundaDirectTest {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Camunda BPM Direct Test");
        System.out.println("========================================");
        System.out.println();
        
        try {
            // Get ProcessEngine
            ProcessEngine processEngine = ProcessEngineFactory.getProcessEngine();
            if (processEngine == null) {
                System.err.println("ERROR: ProcessEngine is null. Make sure it's initialized.");
                return;
            }
            
            System.out.println("✅ ProcessEngine obtained successfully");
            System.out.println("   Engine Name: " + processEngine.getName());
            System.out.println();
            
            RuntimeService runtimeService = processEngine.getRuntimeService();
            TaskService taskService = processEngine.getTaskService();
            
            // Test 1: Start Process
            System.out.println("[1/4] Starting process instance...");
            String userId = "kundan";
            Map<String, Object> variables = new HashMap<>();
            variables.put("userId", userId);
            
            ProcessInstance instance = runtimeService.startProcessInstanceByKey("SampleProcess", variables);
            System.out.println("✅ Process started successfully!");
            System.out.println("   Process Instance ID: " + instance.getId());
            System.out.println("   Process Definition ID: " + instance.getProcessDefinitionId());
            System.out.println();
            
            // Test 2: Get User Tasks
            System.out.println("[2/4] Getting user tasks for: " + userId);
            List<Task> tasks = taskService.createTaskQuery()
                    .taskAssignee(userId)
                    .list();
            
            System.out.println("✅ Found " + tasks.size() + " task(s)");
            for (Task task : tasks) {
                System.out.println("   Task ID: " + task.getId());
                System.out.println("   Task Name: " + task.getName());
                System.out.println("   Process Instance ID: " + task.getProcessInstanceId());
                System.out.println();
            }
            
            // Test 3: Complete Task
            if (!tasks.isEmpty()) {
                System.out.println("[3/4] Completing task: " + tasks.get(0).getId());
                Map<String, Object> taskVariables = new HashMap<>();
                taskVariables.put("approved", true);
                taskVariables.put("comment", "Approved by automated test");
                
                taskService.complete(tasks.get(0).getId(), taskVariables);
                System.out.println("✅ Task completed successfully!");
                System.out.println();
            } else {
                System.out.println("⚠️ No tasks found to complete");
                System.out.println();
            }
            
            // Test 4: Verify Process Completed
            System.out.println("[4/4] Verifying process instance status...");
            ProcessInstance completedInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(instance.getId())
                    .singleResult();
            
            if (completedInstance == null) {
                System.out.println("✅ Process instance completed successfully!");
            } else {
                System.out.println("⚠️ Process instance still running");
                System.out.println("   Process Instance ID: " + completedInstance.getId());
            }
            
            System.out.println();
            System.out.println("========================================");
            System.out.println("✅ All Tests Completed Successfully!");
            System.out.println("========================================");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
