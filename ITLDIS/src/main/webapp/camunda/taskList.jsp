<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="org.camunda.bpm.engine.task.Task" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Tasks - Camunda</title>
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
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .task-count {
            color: #666;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #333;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .btn {
            padding: 8px 16px;
            background-color: #1976d2;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            display: inline-block;
        }
        .btn:hover {
            background-color: #1565c0;
        }
        .btn-view {
            background-color: #388e3c;
        }
        .btn-view:hover {
            background-color: #2e7d32;
        }
        .no-tasks {
            text-align: center;
            padding: 40px;
            color: #666;
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
        <h2>My Tasks</h2>
        
        <div class="task-count">
            <strong>Total Tasks:</strong> ${taskCount != null ? taskCount : 0}
        </div>
        
        <%
            List<Task> tasks = (List<Task>) request.getAttribute("tasks");
            if (tasks == null || tasks.isEmpty()) {
        %>
            <div class="no-tasks">
                <p>No tasks found.</p>
                <p>You don't have any assigned tasks at the moment.</p>
            </div>
        <%
            } else {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Task ID</th>
                        <th>Task Name</th>
                        <th>Process Instance ID</th>
                        <th>Created</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Task task : tasks) {
                    %>
                        <tr>
                            <td><%= task.getId() %></td>
                            <td><%= task.getName() != null ? task.getName() : "N/A" %></td>
                            <td><%= task.getProcessInstanceId() != null ? task.getProcessInstanceId() : "N/A" %></td>
                            <td><%= task.getCreateTime() != null ? task.getCreateTime() : "N/A" %></td>
                            <td>
                                <a href="<%= request.getContextPath() %>/camunda/getTaskDetails.do?taskId=<%= task.getId() %>" class="btn btn-view">View Details</a>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        <%
            }
        %>
        
        <div class="back-link">
            <a href="javascript:history.back()">Go Back</a> | 
            <a href="<%= request.getContextPath() %>/Welcome.do">Home</a>
        </div>
    </div>
</body>
</html>
