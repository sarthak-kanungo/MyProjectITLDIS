<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Camunda BPM Test Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        h1 {
            color: #1976d2;
            border-bottom: 3px solid #1976d2;
            padding-bottom: 10px;
        }
        h2 {
            color: #424242;
            margin-top: 30px;
            border-bottom: 2px solid #e0e0e0;
            padding-bottom: 8px;
        }
        .section {
            margin: 25px 0;
            padding: 20px;
            background-color: #fafafa;
            border-radius: 5px;
            border-left: 4px solid #1976d2;
        }
        .form-group {
            margin: 15px 0;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #424242;
        }
        input[type="text"], input[type="password"], textarea, select {
            width: 100%;
            max-width: 400px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        textarea {
            height: 80px;
            resize: vertical;
        }
        button {
            background-color: #1976d2;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            margin-right: 10px;
            margin-top: 10px;
        }
        button:hover {
            background-color: #1565c0;
        }
        button.danger {
            background-color: #d32f2f;
        }
        button.danger:hover {
            background-color: #c62828;
        }
        .info-box {
            background-color: #e3f2fd;
            padding: 15px;
            border-radius: 4px;
            margin: 15px 0;
            border-left: 4px solid #1976d2;
        }
        .success-box {
            background-color: #e8f5e9;
            padding: 15px;
            border-radius: 4px;
            margin: 15px 0;
            border-left: 4px solid #4caf50;
        }
        .error-box {
            background-color: #ffebee;
            padding: 15px;
            border-radius: 4px;
            margin: 15px 0;
            border-left: 4px solid #f44336;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 15px 0;
        }
        table th, table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        table th {
            background-color: #1976d2;
            color: white;
        }
        table tr:hover {
            background-color: #f5f5f5;
        }
        .code-block {
            background-color: #263238;
            color: #aed581;
            padding: 15px;
            border-radius: 4px;
            font-family: 'Courier New', monospace;
            font-size: 12px;
            overflow-x: auto;
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🚀 Camunda BPM Test & Demo Page</h1>
        
        <div class="info-box">
            <strong>📋 Instructions:</strong> Use this page to test all Camunda BPM operations. 
            Make sure the application server is running and Camunda ProcessEngine is initialized.
        </div>

        <!-- Section 1: Start Process -->
        <div class="section">
            <h2>1. Start a Process Instance</h2>
            <form action="<%= request.getContextPath() %>/camunda/startProcess.do" method="post">
                <div class="form-group">
                    <label for="processKey">Process Key:</label>
                    <select id="processKey" name="processKey" required>
                        <option value="SampleProcess">SampleProcess</option>
                        <option value="ApprovalProcess">ApprovalProcess (if exists)</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="userId">User ID:</label>
                    <input type="text" id="userId" name="userId" value="${sessionScope.userId != null ? sessionScope.userId : 'demo'}" required>
                </div>
                <div class="form-group">
                    <label for="businessKey">Business Key (Optional):</label>
                    <input type="text" id="businessKey" name="businessKey" placeholder="e.g., ORDER-12345">
                </div>
                <div class="form-group">
                    <label for="variables">Process Variables (JSON format, optional):</label>
                    <textarea id="variables" name="variables" placeholder='{"claimId": "12345", "amount": 1000}'></textarea>
                    <small>Format: {"key1": "value1", "key2": 123}</small>
                </div>
                <button type="submit">▶ Start Process</button>
            </form>
        </div>

        <!-- Section 2: Get User Tasks -->
        <div class="section">
            <h2>2. Get User Tasks</h2>
            <form action="<%= request.getContextPath() %>/camunda/getUserTasks.do" method="get">
                <div class="form-group">
                    <label for="taskUserId">User ID:</label>
                    <input type="text" id="taskUserId" name="userId" value="${sessionScope.userId != null ? sessionScope.userId : 'demo'}" required>
                </div>
                <button type="submit">📋 Get My Tasks</button>
            </form>
            
            <form action="<%= request.getContextPath() %>/camunda/getAllUserTasks.do" method="get" style="margin-top: 15px;">
                <button type="submit">📋 Get All User Tasks</button>
            </form>
        </div>

        <!-- Section 3: Get Task Details -->
        <div class="section">
            <h2>3. Get Task Details</h2>
            <form action="<%= request.getContextPath() %>/camunda/getTaskDetails.do" method="get">
                <div class="form-group">
                    <label for="taskId">Task ID:</label>
                    <input type="text" id="taskId" name="taskId" placeholder="Enter task ID" required>
                </div>
                <button type="submit">🔍 View Task Details</button>
            </form>
        </div>

        <!-- Section 4: Complete Task -->
        <div class="section">
            <h2>4. Complete a Task</h2>
            <form action="<%= request.getContextPath() %>/camunda/completeTask.do" method="post">
                <div class="form-group">
                    <label for="completeTaskId">Task ID:</label>
                    <input type="text" id="completeTaskId" name="taskId" placeholder="Enter task ID" required>
                </div>
                <div class="form-group">
                    <label for="comment">Comment:</label>
                    <textarea id="comment" name="comment" placeholder="Optional comment"></textarea>
                </div>
                <div class="form-group">
                    <label for="taskVariables">Task Variables (JSON format, optional):</label>
                    <textarea id="taskVariables" name="variables" placeholder='{"approved": true, "comment": "Looks good"}'></textarea>
                </div>
                <button type="submit">✅ Complete Task</button>
            </form>
        </div>

        <!-- Section 5: Process Instance Management -->
        <div class="section">
            <h2>5. Process Instance Management</h2>
            <form action="<%= request.getContextPath() %>/camunda/getProcessInstanceDetails.do" method="get">
                <div class="form-group">
                    <label for="processInstanceId">Process Instance ID:</label>
                    <input type="text" id="processInstanceId" name="processInstanceId" placeholder="Enter process instance ID" required>
                </div>
                <button type="submit">🔍 View Process Details</button>
            </form>
            
            <form action="<%= request.getContextPath() %>/camunda/deleteProcessInstance.do" method="post" style="margin-top: 15px;">
                <div class="form-group">
                    <label for="deleteProcessInstanceId">Process Instance ID to Delete:</label>
                    <input type="text" id="deleteProcessInstanceId" name="processInstanceId" placeholder="Enter process instance ID" required>
                </div>
                <div class="form-group">
                    <label for="deleteReason">Delete Reason:</label>
                    <input type="text" id="deleteReason" name="reason" placeholder="Reason for deletion">
                </div>
                <button type="submit" class="danger">🗑️ Delete Process Instance</button>
            </form>
        </div>

        <!-- Section 6: API Information -->
        <div class="section">
            <h2>6. Available Camunda Actions</h2>
            <table>
                <thead>
                    <tr>
                        <th>Action</th>
                        <th>URL</th>
                        <th>Method</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Start Process</td>
                        <td><code>/camunda/startProcess.do</code></td>
                        <td>POST</td>
                        <td>Start a new process instance</td>
                    </tr>
                    <tr>
                        <td>Get User Tasks</td>
                        <td><code>/camunda/getUserTasks.do</code></td>
                        <td>GET</td>
                        <td>Get tasks assigned to a user</td>
                    </tr>
                    <tr>
                        <td>Get All User Tasks</td>
                        <td><code>/camunda/getAllUserTasks.do</code></td>
                        <td>GET</td>
                        <td>Get all user tasks (candidate groups)</td>
                    </tr>
                    <tr>
                        <td>Get Task Details</td>
                        <td><code>/camunda/getTaskDetails.do</code></td>
                        <td>GET</td>
                        <td>Get detailed information about a task</td>
                    </tr>
                    <tr>
                        <td>Complete Task</td>
                        <td><code>/camunda/completeTask.do</code></td>
                        <td>POST</td>
                        <td>Complete a user task</td>
                    </tr>
                    <tr>
                        <td>Get Process Details</td>
                        <td><code>/camunda/getProcessInstanceDetails.do</code></td>
                        <td>GET</td>
                        <td>Get process instance information</td>
                    </tr>
                    <tr>
                        <td>Delete Process</td>
                        <td><code>/camunda/deleteProcessInstance.do</code></td>
                        <td>POST</td>
                        <td>Delete a process instance</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Section 7: Code Examples -->
        <div class="section">
            <h2>7. Code Examples</h2>
            <h3>Using CamundaServiceHelper in Java:</h3>
            <div class="code-block">
// Start a process
Map&lt;String, Object&gt; variables = new HashMap&lt;&gt;();
variables.put("claimId", "12345");
variables.put("amount", 1000.0);
ProcessInstance instance = CamundaServiceHelper.startProcess("SampleProcess", variables);

// Get user tasks
List&lt;Task&gt; tasks = CamundaServiceHelper.getUserTasks("demo");

// Complete a task
Map&lt;String, Object&gt; taskVars = new HashMap&lt;&gt;();
taskVars.put("approved", true);
CamundaServiceHelper.completeTask("taskId123", taskVars);
            </div>
        </div>

        <!-- Navigation -->
        <div style="margin-top: 30px; padding-top: 20px; border-top: 2px solid #e0e0e0;">
            <a href="<%= request.getContextPath() %>/Welcome.do" style="color: #1976d2; text-decoration: none;">← Back to Home</a>
        </div>
    </div>
</body>
</html>
