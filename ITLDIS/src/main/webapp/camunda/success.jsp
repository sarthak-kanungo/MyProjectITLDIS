<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Camunda Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f5f5f5;
        }
        .success-container {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .success-message {
            color: #388e3c;
            font-size: 18px;
            margin-bottom: 20px;
        }
        .info {
            background-color: #e3f2fd;
            padding: 15px;
            border-radius: 4px;
            margin: 15px 0;
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
    <div class="success-container">
        <h2>Success</h2>
        <div class="success-message">
            <strong>${message}</strong>
        </div>
        
        <c:if test="${not empty processInstanceId}">
            <div class="info">
                <strong>Process Instance ID:</strong> ${processInstanceId}<br>
                <c:if test="${not empty processDefinitionId}">
                    <strong>Process Definition ID:</strong> ${processDefinitionId}<br>
                </c:if>
                <c:if test="${not empty businessKey}">
                    <strong>Business Key:</strong> ${businessKey}<br>
                </c:if>
            </div>
        </c:if>
        
        <c:if test="${not empty taskId}">
            <div class="info">
                <strong>Task ID:</strong> ${taskId}
            </div>
        </c:if>
        
        <div class="back-link">
            <a href="javascript:history.back()">Go Back</a> | 
            <a href="<%= request.getContextPath() %>/Welcome.do">Home</a>
        </div>
    </div>
</body>
</html>
