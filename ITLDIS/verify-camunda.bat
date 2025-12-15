@echo off
REM Camunda Verification Script
REM Checks if Camunda is working correctly

echo ========================================
echo Camunda BPM Verification Script
echo ========================================
echo.

set BASE_URL=http://localhost:8080/itldis

echo [1/4] Checking if application is running...
curl -s -o nul -w "HTTP Status: %%{http_code}\n" "%BASE_URL%/" >nul 2>&1
if errorlevel 1 (
    echo ERROR: Application is not responding
    echo Please ensure Tomcat is running
    exit /b 1
) else (
    echo OK: Application is running
)
echo.

echo [2/4] Checking Camunda test page...
curl -s -o nul -w "HTTP Status: %%{http_code}\n" "%BASE_URL%/camunda/test.jsp" >nul 2>&1
if errorlevel 1 (
    echo WARNING: Test page not accessible
) else (
    echo OK: Test page is accessible
)
echo.

echo [3/4] Checking Tomcat logs for Camunda initialization...
set LOG_DIR=C:\apache-tomcat-9.0.100\logs
if exist "%LOG_DIR%" (
    echo Checking log files...
    for %%f in ("%LOG_DIR%\catalina*.log") do (
        echo Checking: %%~nxf
        findstr /C:"Camunda ProcessEngine initialized" /C:"Deployed Camunda process" "%%f" >nul 2>&1
        if not errorlevel 1 (
            echo OK: Found Camunda initialization logs in %%~nxf
            findstr /C:"Camunda ProcessEngine initialized" /C:"Deployed Camunda process" "%%f" | Select-Object -Last 5
        )
    )
) else (
    echo WARNING: Log directory not found: %LOG_DIR%
)
echo.

echo [4/4] Verification Summary
echo ========================================
echo.
echo Application URL: %BASE_URL%
echo Test Page: %BASE_URL%/camunda/test.jsp
echo.
echo Next Steps:
echo 1. Login to application (kundan/kundan)
echo 2. Navigate to test page
echo 3. Start a process instance
echo 4. Get and complete tasks
echo.
echo Check logs at: %LOG_DIR%\catalina.out
echo Look for: "Camunda ProcessEngine initialized: default"
echo.
pause
