@echo off
echo ============================================
echo Fix 404 Error - ITLDIS Deployment
echo ============================================
echo.

REM Stop Tomcat if running
echo [1/5] Stopping Tomcat...
call "C:\apache-tomcat-9.0.100\bin\shutdown.bat" 2>nul
timeout /t 5 /nobreak >nul

REM Clean up old deployment
echo [2/5] Cleaning old deployment...
if exist "C:\apache-tomcat-9.0.100\webapps\itldis" (
    rmdir /s /q "C:\apache-tomcat-9.0.100\webapps\itldis"
    echo    Removed old exploded deployment
)
if exist "C:\apache-tomcat-9.0.100\webapps\itldis.war" (
    del /f "C:\apache-tomcat-9.0.100\webapps\itldis.war"
    echo    Removed old WAR file
)

REM Rebuild project
echo [3/5] Rebuilding project...
cd /d "%~dp0"
call mvn clean package -DskipTests
if errorlevel 1 (
    echo    ERROR: Build failed!
    pause
    exit /b 1
)
echo    Build successful

REM Copy new WAR
echo [4/5] Deploying new WAR...
copy /y "target\itldis.war" "C:\apache-tomcat-9.0.100\webapps\itldis.war" >nul
echo    WAR file copied

REM Create context file
echo [5/5] Creating context configuration...
if not exist "C:\apache-tomcat-9.0.100\conf\Catalina\localhost" (
    mkdir "C:\apache-tomcat-9.0.100\conf\Catalina\localhost"
)
(
    echo ^<?xml version="1.0" encoding="UTF-8"?^>
    echo ^<Context docBase="itldis" /^>
) > "C:\apache-tomcat-9.0.100\conf\Catalina\localhost\ITLDIS.xml"
echo    Context file created

echo.
echo ============================================
echo Deployment Complete!
echo ============================================
echo.
echo Starting Tomcat...
echo Waiting 30 seconds for deployment...
echo.
start "Tomcat Server" "C:\apache-tomcat-9.0.100\bin\startup.bat"
timeout /t 30 /nobreak >nul

echo.
echo ============================================
echo Testing Application Access
echo ============================================
echo.
echo Application URL: http://localhost:8080/itldis/
echo Login URL: http://localhost:8080/itldis/login/Login.jsp
echo.
echo If you still get 404, wait a bit longer for Tomcat to finish deploying.
echo Large WAR files can take 1-2 minutes to deploy.
echo.
pause

