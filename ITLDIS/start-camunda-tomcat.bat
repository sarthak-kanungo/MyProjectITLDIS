@echo off
REM ============================================
REM Start Tomcat with ITLDIS and Camunda BPM
REM ============================================

echo.
echo ============================================
echo Starting Tomcat with ITLDIS and Camunda BPM
echo ============================================
echo.

REM Use CATALINA_HOME from environment if set, otherwise use default
if "%CATALINA_HOME%"=="" (
    set CATALINA_HOME=C:\apache-tomcat-9.0.100
)

if not exist "%CATALINA_HOME%\bin\catalina.bat" (
    echo ERROR: Tomcat not found at %CATALINA_HOME%
    echo.
    echo Please verify your Tomcat installation path
    echo Or set CATALINA_HOME environment variable
    echo.
    pause
    exit /b 1
)

echo Tomcat: %CATALINA_HOME%
echo.

REM Check if WAR is deployed
if exist "%CATALINA_HOME%\webapps\itldis.war" (
    echo [OK] WAR file found: itldis.war
) else if exist "target\itldis.war" (
    echo [INFO] Copying WAR file to Tomcat...
    copy /Y target\itldis.war "%CATALINA_HOME%\webapps\itldis.war" >nul 2>&1
    if exist "%CATALINA_HOME%\webapps\itldis.war" (
        echo [OK] WAR file deployed successfully
    ) else (
        echo [ERROR] Failed to deploy WAR file
        pause
        exit /b 1
    )
) else (
    echo [WARNING] WAR file not found
    echo Please run: package-camunda.bat first
    echo.
    pause
    exit /b 1
)

REM Check if port 8080 is already in use
netstat -an | find "8080" | find "LISTENING" >nul 2>&1
if %errorlevel%==0 (
    echo [WARNING] Port 8080 is already in use
    echo Tomcat may already be running
    echo.
    set /p CONTINUE="Continue anyway? (Y/N): "
    if /i not "%CONTINUE%"=="Y" (
        exit /b 0
    )
    echo.
)

echo.
echo ============================================
echo Starting Tomcat Server...
echo ============================================
echo.
echo The application will be available at:
echo   http://localhost:8080/itldis/
echo.
echo Camunda endpoints:
echo   - Get User Tasks: http://localhost:8080/itldis/camunda/getUserTasks.do?userId=demo
echo   - Start Process: http://localhost:8080/itldis/camunda/startProcess.do
echo.
echo Log files location:
echo   %CATALINA_HOME%\logs\catalina.out
echo.
echo Check logs for: "Camunda ProcessEngine initialized: default"
echo.
echo Press Ctrl+C to stop the server
echo.
echo ============================================
echo.

REM Change to Tomcat bin directory
cd /d "%CATALINA_HOME%\bin"

REM Start Tomcat in a new window so it stays running
start "Tomcat Server - ITLDIS with Camunda" cmd /k "catalina.bat run"

echo.
echo Tomcat is starting in a new window...
echo Please wait 30-60 seconds for deployment to complete
echo Then test: http://localhost:8080/itldis/
echo.

REM Script continues here after starting Tomcat in new window
echo.
echo To stop Tomcat, close the Tomcat window or run:
echo   %CATALINA_HOME%\bin\shutdown.bat
echo.
pause

