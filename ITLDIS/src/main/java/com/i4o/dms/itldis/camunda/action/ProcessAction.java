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
 * Provides endpoints for starting processes, managing tasks, etc.
 * 
 * @author ITLDIS Team
 */
public class ProcessAction extends Action {
    
    /**
     * Start a process instance
     * Parameters: processKey (required), businessKey (optional), variables (optional)
     */
    public ActionForward startProcess(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) {
        try {
            String processKey = request.getParameter("processKey");
            if (processKey == null || processKey.trim().isEmpty()) {
                request.setAttribute("error", "Process key is required");
                return mapping.findForward("error");
            }
            
            String businessKey = request.getParameter("businessKey");
            String userId = (String) request.getSession().getAttribute("userId");
            if (userId == null) {
                userId = request.getParameter("userId");
            }
            
            // Build variables from request parameters
            Map<String, Object> variables = new HashMap<>();
            if (userId != null) {
                variables.put("userId", userId);
            }
            
            // Add any additional variables from request
            String requestId = request.getParameter("requestId");
            if (requestId != null) {
                variables.put("requestId", requestId);
            }
            
            // Add other parameters as variables
            java.util.Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                if (!paramName.equals("processKey") && !paramName.equals("businessKey") 
                    && !paramName.equals("option") && !paramName.equals("method")) {
                    variables.put(paramName, request.getParameter(paramName));
                }
            }
            
            ProcessInstance instance;
            if (businessKey != null && !businessKey.trim().isEmpty()) {
                instance = CamundaServiceHelper.startProcess(processKey, businessKey, variables);
            } else {
                instance = CamundaServiceHelper.startProcess(processKey, variables);
            }
            
            request.setAttribute("processInstanceId", instance.getId());
            request.setAttribute("processDefinitionId", instance.getProcessDefinitionId());
            request.setAttribute("businessKey", instance.getBusinessKey());
            request.setAttribute("message", "Process started successfully");
            
            return mapping.findForward("success");
            
        } catch (Exception e) {
            request.setAttribute("error", "Failed to start process: " + e.getMessage());
            e.printStackTrace();
            return mapping.findForward("error");
        }
    }
    
    /**
     * Get user tasks
     * Parameters: userId (optional, uses session userId if not provided)
     */
    public ActionForward getUserTasks(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("userId");
            if (userId == null || userId.trim().isEmpty()) {
                userId = (String) request.getSession().getAttribute("userId");
            }
            
            if (userId == null || userId.trim().isEmpty()) {
                request.setAttribute("error", "User ID is required");
                return mapping.findForward("error");
            }
            
            List<Task> tasks = CamundaServiceHelper.getUserTasks(userId);
            request.setAttribute("tasks", tasks);
            request.setAttribute("taskCount", tasks.size());
            
            return mapping.findForward("taskList");
            
        } catch (Exception e) {
            request.setAttribute("error", "Failed to get tasks: " + e.getMessage());
            e.printStackTrace();
            return mapping.findForward("error");
        }
    }
    
    /**
     * Get all user tasks (including candidate tasks)
     */
    public ActionForward getAllUserTasks(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("userId");
            if (userId == null || userId.trim().isEmpty()) {
                userId = (String) request.getSession().getAttribute("userId");
            }
            
            if (userId == null || userId.trim().isEmpty()) {
                request.setAttribute("error", "User ID is required");
                return mapping.findForward("error");
            }
            
            List<Task> tasks = CamundaServiceHelper.getAllUserTasks(userId);
            request.setAttribute("tasks", tasks);
            request.setAttribute("taskCount", tasks.size());
            
            return mapping.findForward("taskList");
            
        } catch (Exception e) {
            request.setAttribute("error", "Failed to get tasks: " + e.getMessage());
            e.printStackTrace();
            return mapping.findForward("error");
        }
    }
    
    /**
     * Get task details
     * Parameters: taskId (required)
     */
    public ActionForward getTaskDetails(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
            String taskId = request.getParameter("taskId");
            if (taskId == null || taskId.trim().isEmpty()) {
                request.setAttribute("error", "Task ID is required");
                return mapping.findForward("error");
            }
            
            Task task = CamundaServiceHelper.getTaskById(taskId);
            if (task == null) {
                request.setAttribute("error", "Task not found: " + taskId);
                return mapping.findForward("error");
            }
            
            request.setAttribute("task", task);
            
            // Get process variables if process instance exists
            if (task.getProcessInstanceId() != null) {
                Map<String, Object> variables = CamundaServiceHelper.getProcessVariables(
                    task.getProcessInstanceId());
                request.setAttribute("processVariables", variables);
            }
            
            return mapping.findForward("taskDetails");
            
        } catch (Exception e) {
            request.setAttribute("error", "Failed to get task details: " + e.getMessage());
            e.printStackTrace();
            return mapping.findForward("error");
        }
    }
    
    /**
     * Complete a task
     * Parameters: taskId (required), variables (optional)
     */
    public ActionForward completeTask(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) {
        try {
            String taskId = request.getParameter("taskId");
            if (taskId == null || taskId.trim().isEmpty()) {
                request.setAttribute("error", "Task ID is required");
                return mapping.findForward("error");
            }
            
            // Build variables from request parameters
            Map<String, Object> variables = new HashMap<>();
            java.util.Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                if (!paramName.equals("taskId") && !paramName.equals("option") 
                    && !paramName.equals("method")) {
                    String paramValue = request.getParameter(paramName);
                    // Try to parse as boolean if value is "true" or "false"
                    if ("true".equalsIgnoreCase(paramValue)) {
                        variables.put(paramName, true);
                    } else if ("false".equalsIgnoreCase(paramValue)) {
                        variables.put(paramName, false);
                    } else {
                        variables.put(paramName, paramValue);
                    }
                }
            }
            
            if (variables.isEmpty()) {
                CamundaServiceHelper.completeTask(taskId);
            } else {
                CamundaServiceHelper.completeTask(taskId, variables);
            }
            
            request.setAttribute("taskId", taskId);
            request.setAttribute("message", "Task completed successfully");
            
            return mapping.findForward("success");
            
        } catch (Exception e) {
            request.setAttribute("error", "Failed to complete task: " + e.getMessage());
            e.printStackTrace();
            return mapping.findForward("error");
        }
    }
    
    /**
     * Delete process instance
     * Parameters: processInstanceId (required), reason (optional)
     */
    public ActionForward deleteProcessInstance(ActionMapping mapping, ActionForm form,
                                              HttpServletRequest request, HttpServletResponse response) {
        try {
            String processInstanceId = request.getParameter("processInstanceId");
            if (processInstanceId == null || processInstanceId.trim().isEmpty()) {
                request.setAttribute("error", "Process instance ID is required");
                return mapping.findForward("error");
            }
            
            String reason = request.getParameter("reason");
            if (reason == null || reason.trim().isEmpty()) {
                reason = "Deleted by user";
            }
            
            CamundaServiceHelper.deleteProcessInstance(processInstanceId, reason);
            
            request.setAttribute("processInstanceId", processInstanceId);
            request.setAttribute("message", "Process instance deleted successfully");
            
            return mapping.findForward("success");
            
        } catch (Exception e) {
            request.setAttribute("error", "Failed to delete process instance: " + e.getMessage());
            e.printStackTrace();
            return mapping.findForward("error");
        }
    }
    
    /**
     * Get process instance details
     * Parameters: processInstanceId (required)
     */
    public ActionForward getProcessInstanceDetails(ActionMapping mapping, ActionForm form,
                                                  HttpServletRequest request, HttpServletResponse response) {
        try {
            String processInstanceId = request.getParameter("processInstanceId");
            if (processInstanceId == null || processInstanceId.trim().isEmpty()) {
                request.setAttribute("error", "Process instance ID is required");
                return mapping.findForward("error");
            }
            
            ProcessInstance instance = CamundaServiceHelper.getProcessInstance(processInstanceId);
            if (instance == null) {
                request.setAttribute("error", "Process instance not found: " + processInstanceId);
                return mapping.findForward("error");
            }
            
            request.setAttribute("processInstance", instance);
            
            // Get process variables
            Map<String, Object> variables = CamundaServiceHelper.getProcessVariables(processInstanceId);
            request.setAttribute("processVariables", variables);
            
            return mapping.findForward("processInstanceDetails");
            
        } catch (Exception e) {
            request.setAttribute("error", "Failed to get process instance details: " + e.getMessage());
            e.printStackTrace();
            return mapping.findForward("error");
        }
    }
}
