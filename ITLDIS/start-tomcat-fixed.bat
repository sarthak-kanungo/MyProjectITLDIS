@echo off
REM ============================================
REM Start Tomcat - Fixed Version
REM ============================================

echo.
echo ============================================
echo Starting Tomcat with ITLDIS and Camunda BPM
echo ============================================
echo.

set CATALINA_HOME=C:\apache-tomcat-9.0.100

REM Verify Tomcat exists
if not exist "%CATALINA_HOME%\bin\catalina.bat" (
    echo [ERROR] Tomcat not found at %CATALINA_HOME%
    echo Please verify your Tomcat installation
    pause
    exit /b 1
)

echo [1/4] Verifying Tomcat installation...
echo [OK] Tomcat found: %CATALINA_HOME%
echo.

REM Check if already running
echo [2/4] Checking if Tomcat is already running...
netstat -an | find "8080" | find "LISTENING" >nul 2>&1
if %errorlevel%==0 (
    echo [INFO] Port 8080 is in use - Tomcat may already be running
    echo.
    echo Testing application...
    powershell -Command "try { $r = Invoke-WebRequest -Uri 'http://localhost:8080/itldis/' -TimeoutSec 5 -UseBasicParsing; Write-Host '[OK] Application is accessible!' } catch { Write-Host '[INFO] Tomcat running but app may still be deploying' }"
    echo.
    echo If you see ERR_CONNECTION_REFUSED, Tomcat may have stopped.
    echo Press any key to start/restart Tomcat...
    pause >nul
    echo.
    echo Stopping any existing Tomcat instance...
    call "%CATALINA_HOME%\bin\shutdown.bat" >nul 2>&1
    timeout /t 3 /nobreak >nul 2>&1
)

REM Verify WAR is deployed
echo [3/4] Checking WAR deployment...
if exist "%CATALINA_HOME%\webapps\itldis.war" (
    echo [OK] WAR file deployed: itldis.war
) else if exist "target\itldis.war" (
    echo [INFO] Copying WAR file...
    copy /Y target\itldis.war "%CATALINA_HOME%\webapps\itldis.war" >nul 2>&1
    echo [OK] WAR file deployed
) else (
    echo [WARNING] WAR file not found
    echo Please run: package-camunda.bat first
    pause
    exit /b 1
)

REM Start Tomcat
echo.
echo [4/4] Starting Tomcat...
echo.
echo ============================================
echo IMPORTANT: Please wait 30-60 seconds
echo ============================================
echo.
echo The application will be available at:
echo   http://localhost:8080/itldis/
echo.
echo Large WAR file (107.5 MB) takes time to deploy
echo Watch the Tomcat window for deployment messages
echo.
echo Look for: "Deployment of web application ... has finished"
echo.
echo ============================================
echo.

cd /d "%CATALINA_HOME%\bin"

REM Start Tomcat in a new window so it stays running
start "Tomcat Server - ITLDIS with Camunda" cmd /k "catalina.bat run"

echo.
echo Tomcat is starting in a new window...
echo.
echo Please wait 30-60 seconds, then test:
echo   http://localhost:8080/itldis/
echo.
echo To check status, run: check-camunda-status.bat
echo.
pause

