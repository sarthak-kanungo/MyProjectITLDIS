<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.camunda.bpm.engine.task.Task" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task Details - Camunda</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            max-width: 800px;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .info-section {
            margin-bottom: 30px;
        }
        .info-section h3 {
            color: #555;
            border-bottom: 2px solid #1976d2;
            padding-bottom: 10px;
            margin-bottom: 15px;
        }
        .info-row {
            display: flex;
            padding: 10px 0;
            border-bottom: 1px solid #eee;
        }
        .info-label {
            font-weight: bold;
            width: 200px;
            color: #666;
        }
        .info-value {
            flex: 1;
            color: #333;
        }
        .form-container {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 4px;
            margin-top: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        .form-group input[type="text"],
        .form-group textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }
        .form-group textarea {
            min-height: 100px;
        }
        .form-group input[type="checkbox"] {
            margin-right: 5px;
        }
        .btn {
            padding: 10px 20px;
            background-color: #1976d2;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            font-size: 14px;
        }
        .btn:hover {
            background-color: #1565c0;
        }
        .btn-complete {
            background-color: #388e3c;
        }
        .btn-complete:hover {
            background-color: #2e7d32;
        }
        .back-link {
            margin-top: 20px;
        }
        .back-link a {
            color: #1976d2;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Task Details</h2>
        
        <%
            Task task = (Task) request.getAttribute("task");
            if (task == null) {
        %>
            <div style="color: red; padding: 20px;">
                <strong>Error:</strong> Task not found.
            </div>
            <div class="back-link">
                <a href="javascript:history.back()">Go Back</a>
            </div>
        <%
            } else {
        %>
            <div class="info-section">
                <h3>Task Information</h3>
                <div class="info-row">
                    <div class="info-label">Task ID:</div>
                    <div class="info-value"><%= task.getId() %></div>
                </div>
                <div class="info-row">
                    <div class="info-label">Task Name:</div>
                    <div class="info-value"><%= task.getName() != null ? task.getName() : "N/A" %></div>
                </div>
                <div class="info-row">
                    <div class="info-label">Assignee:</div>
                    <div class="info-value"><%= task.getAssignee() != null ? task.getAssignee() : "Unassigned" %></div>
                </div>
                <div class="info-row">
                    <div class="info-label">Process Instance ID:</div>
                    <div class="info-value"><%= task.getProcessInstanceId() != null ? task.getProcessInstanceId() : "N/A" %></div>
                </div>
                <div class="info-row">
                    <div class="info-label">Created:</div>
                    <div class="info-value"><%= task.getCreateTime() != null ? task.getCreateTime() : "N/A" %></div>
                </div>
                <div class="info-row">
                    <div class="info-label">Due Date:</div>
                    <div class="info-value"><%= task.getDueDate() != null ? task.getDueDate() : "Not set" %></div>
                </div>
            </div>
            
            <%
                Map<String, Object> processVariables = (Map<String, Object>) request.getAttribute("processVariables");
                if (processVariables != null && !processVariables.isEmpty()) {
            %>
                <div class="info-section">
                    <h3>Process Variables</h3>
                    <%
                        for (Map.Entry<String, Object> entry : processVariables.entrySet()) {
                    %>
                        <div class="info-row">
                            <div class="info-label"><%= entry.getKey() %>:</div>
                            <div class="info-value"><%= entry.getValue() != null ? entry.getValue().toString() : "null" %></div>
                        </div>
                    <%
                        }
                    %>
                </div>
            <%
                }
            %>
            
            <div class="form-container">
                <h3>Complete Task</h3>
                <form action="<%= request.getContextPath() %>/camunda/completeTask.do" method="post">
                    <input type="hidden" name="taskId" value="<%= task.getId() %>"/>
                    
                    <div class="form-group">
                        <label for="comment">Comment:</label>
                        <textarea id="comment" name="comment" placeholder="Enter your comment"></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label>
                            <input type="checkbox" name="approved" value="true"/>
                            Approved
                        </label>
                    </div>
                    
                    <button type="submit" class="btn btn-complete">Complete Task</button>
                </form>
            </div>
            
            <div class="back-link">
                <a href="<%= request.getContextPath() %>/camunda/getUserTasks.do">Back to Task List</a> | 
                <a href="<%= request.getContextPath() %>/Welcome.do">Home</a>
            </div>
        <%
            }
        %>
    </div>
</body>
</html>
