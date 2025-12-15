@echo off
REM Test Camunda Operations Script
REM Tests all Camunda endpoints with user credentials

setlocal enabledelayedexpansion

echo ========================================
echo Camunda BPM Operations Test
echo ========================================
echo.
echo Testing with User ID: kundan
echo.

set BASE_URL=http://localhost:8080/itldis
set USER_ID=kundan

echo [1/5] Starting a process instance...
echo POST %BASE_URL%/camunda/startProcess.do
echo Parameters: processKey=SampleProcess, userId=%USER_ID%
echo.
curl -s -X POST "%BASE_URL%/camunda/startProcess.do" -d "processKey=SampleProcess&userId=%USER_ID%" -w "\nHTTP Status: %%{http_code}\n" > temp_start.txt
type temp_start.txt | findstr /C:"Process Instance" /C:"processInstanceId" /C:"Success" /C:"error" /C:"HTTP Status"
echo.

echo [2/5] Getting user tasks...
echo GET %BASE_URL%/camunda/getUserTasks.do?userId=%USER_ID%
echo.
curl -s "%BASE_URL%/camunda/getUserTasks.do?userId=%USER_ID%" -w "\nHTTP Status: %%{http_code}\n" > temp_tasks.txt
type temp_tasks.txt | findstr /C:"Task" /C:"taskId" /C:"taskCount" /C:"No tasks" /C:"HTTP Status"
echo.

echo [3/5] Getting all user tasks...
echo GET %BASE_URL%/camunda/getAllUserTasks.do
echo.
curl -s "%BASE_URL%/camunda/getAllUserTasks.do" -w "\nHTTP Status: %%{http_code}\n" > temp_alltasks.txt
type temp_alltasks.txt | findstr /C:"Task" /C:"taskId" /C:"taskCount" /C:"HTTP Status"
echo.

echo [4/5] Checking Camunda test page...
echo GET %BASE_URL%/camunda/test.jsp
echo.
curl -s "%BASE_URL%/camunda/test.jsp" -w "\nHTTP Status: %%{http_code}\n" > temp_testpage.txt
type temp_testpage.txt | findstr /C:"Camunda" /C:"Test" /C:"HTTP Status" | Select-Object -First 3
echo.

echo [5/5] Summary
echo ========================================
echo.
echo Full responses saved to:
echo   - temp_start.txt (Start Process)
echo   - temp_tasks.txt (Get User Tasks)
echo   - temp_alltasks.txt (Get All Tasks)
echo   - temp_testpage.txt (Test Page)
echo.
echo Check these files for detailed responses.
echo.
echo To view full HTML responses, open the files in a text editor.
echo.
pause
