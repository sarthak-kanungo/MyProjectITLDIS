@echo off
REM Camunda BPM Deployment Verification Script
REM Checks if deployment was successful

echo ========================================
echo Camunda BPM Deployment Verification
echo ========================================
echo.

set BASE_URL=http://localhost:8080/itldis
set TEST_PAGE=%BASE_URL%/camunda/test.jsp

echo [1/4] Checking if application is running...
curl -s -o nul -w "HTTP Status: %%{http_code}\n" "%BASE_URL%" >nul 2>&1
if errorlevel 1 (
    echo ERROR: Application is not responding at %BASE_URL%
    echo Please ensure the application server is running
    exit /b 1
) else (
    echo OK: Application is running
)

echo.
echo [2/4] Checking test page...
curl -s -o nul -w "HTTP Status: %%{http_code}\n" "%TEST_PAGE%" >nul 2>&1
if errorlevel 1 (
    echo WARNING: Test page not accessible at %TEST_PAGE%
) else (
    echo OK: Test page is accessible
)

echo.
echo [3/4] Checking Camunda endpoints...
curl -s "%BASE_URL%/camunda/getUserTasks.do?userId=demo" >nul 2>&1
if errorlevel 1 (
    echo WARNING: Camunda endpoints may not be working
) else (
    echo OK: Camunda endpoints are responding
)

echo.
echo [4/4] Verification Summary
echo ========================================
echo Application URL: %BASE_URL%
echo Test Page: %TEST_PAGE%
echo.
echo Next Steps:
echo 1. Open test page in browser: %TEST_PAGE%
echo 2. Start a process instance
echo 3. Check application logs for Camunda initialization
echo.
echo Check logs for these messages:
echo   - "Camunda ProcessEngine initialized: default"
echo   - "Deployed Camunda process: processes/sample-process.bpmn"
echo.
pause
