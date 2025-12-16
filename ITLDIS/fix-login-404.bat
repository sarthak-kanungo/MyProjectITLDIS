@echo off
REM ============================================
REM Fix Login 404 Error - Rebuild and Redeploy
REM ============================================

echo.
echo ============================================
echo Fixing Login 404 Error
echo ============================================
echo.

cd /d %~dp0

echo [1/5] Stopping Tomcat...
call C:\apache-tomcat-9.0.100\bin\shutdown.bat >nul 2>&1
timeout /t 5 /nobreak >nul 2>&1
echo [OK] Tomcat stopped

echo.
echo [2/5] Rebuilding project...
call mvn clean package -DskipTests
if errorlevel 1 (
    echo [ERROR] Build failed
    pause
    exit /b 1
)
echo [OK] Project rebuilt successfully

echo.
echo [3/5] Removing old deployment...
if exist "C:\apache-tomcat-9.0.100\webapps\itldis" (
    rd /s /q "C:\apache-tomcat-9.0.100\webapps\itldis" 2>nul
)
if exist "C:\apache-tomcat-9.0.100\webapps\itldis.war" (
    del /f /q "C:\apache-tomcat-9.0.100\webapps\itldis.war" 2>nul
)
echo [OK] Old deployment removed

echo.
echo [4/5] Deploying new WAR...
if exist "target\itldis.war" (
    copy /Y target\itldis.war C:\apache-tomcat-9.0.100\webapps\itldis.war >nul 2>&1
    echo [OK] New WAR deployed
) else (
    echo [ERROR] WAR file not found
    pause
    exit /b 1
)

echo.
echo [5/5] Starting Tomcat...
cd /d C:\apache-tomcat-9.0.100\bin
start "Tomcat Server - ITLDIS" cmd /k "catalina.bat run"
echo [OK] Tomcat starting in new window

echo.
echo ============================================
echo Fix Complete!
echo ============================================
echo.
echo IMPORTANT: Wait 30-60 seconds for deployment
echo.
echo Then test login at:
echo   http://localhost:8080/itldis/login/Login.jsp
echo.
echo The login form should now work correctly.
echo.
pause

