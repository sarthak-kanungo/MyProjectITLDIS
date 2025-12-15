<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Camunda Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f5f5f5;
        }
        .error-container {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .error-message {
            color: #d32f2f;
            font-size: 18px;
            margin-bottom: 20px;
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
    <div class="error-container">
        <h2>Camunda Process Error</h2>
        <div class="error-message">
            <strong>Error:</strong> ${error}
        </div>
        <div class="back-link">
            <a href="javascript:history.back()">Go Back</a> | 
            <a href="<%= request.getContextPath() %>/Welcome.do">Home</a>
        </div>
    </div>
</body>
</html>
