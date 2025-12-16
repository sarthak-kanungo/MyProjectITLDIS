@echo off
REM ============================================
REM Camunda BPM Setup Script for ITLDIS
REM ============================================

echo.
echo ============================================
echo Camunda BPM Setup for ITLDIS
echo ============================================
echo.

cd /d %~dp0

echo [1/4] Checking Maven installation...
call mvn --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven from https://maven.apache.org/
    pause
    exit /b 1
)
echo Maven found!

echo.
echo [2/4] Downloading Camunda BPM dependencies...
call mvn dependency:resolve
if errorlevel 1 (
    echo ERROR: Failed to download dependencies
    pause
    exit /b 1
)
echo Dependencies downloaded successfully!

echo.
echo [3/4] Copying Camunda JARs to WEB-INF/lib...
if not exist "target\lib" (
    call mvn dependency:copy-dependencies -DoutputDirectory=target\lib
)
if exist "target\lib\camunda-engine-7.18.0.jar" (
    copy /Y target\lib\camunda*.jar src\main\webapp\WEB-INF\lib\ >nul 2>&1
    copy /Y target\lib\h2-1.4.200.jar src\main\webapp\WEB-INF\lib\ >nul 2>&1
    echo Camunda JARs copied to WEB-INF/lib
) else (
    echo WARNING: Camunda JARs not found in target\lib
    echo Please run: mvn dependency:copy-dependencies -DoutputDirectory=target\lib
)

echo.
echo [4/4] Building project...
call mvn clean compile
if errorlevel 1 (
    echo ERROR: Build failed
    pause
    exit /b 1
)
echo Build successful!

echo.
echo ============================================
echo Setup Complete!
echo ============================================
echo.
echo Next steps:
echo 1. Run: package-camunda.bat (to create WAR file)
echo 2. Deploy the WAR to your application server
echo 3. Start your server and check logs for:
echo    "Camunda ProcessEngine initialized: default"
echo.
echo Test URLs:
echo - Get User Tasks: http://localhost:8080/itldis/camunda/getUserTasks.do?userId=demo
echo - Start Process: http://localhost:8080/itldis/camunda/startProcess.do
echo.
pause

