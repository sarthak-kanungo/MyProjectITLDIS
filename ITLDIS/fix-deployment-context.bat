@echo off
REM ============================================
REM Fix Deployment Context Path Issue
REM ============================================

echo.
echo ============================================
echo Fixing Deployment Context Path
echo ============================================
echo.

set CATALINA_HOME=C:\apache-tomcat-9.0.100

if not exist "%CATALINA_HOME%\bin\catalina.bat" (
    echo ERROR: Tomcat not found at %CATALINA_HOME%
    pause
    exit /b 1
)

echo [1/4] Stopping Tomcat (if running)...
call "%CATALINA_HOME%\bin\shutdown.bat" >nul 2>&1
timeout /t 3 /nobreak >nul 2>&1

echo [2/4] Cleaning up old deployments...
if exist "%CATALINA_HOME%\webapps\ITLDIS" (
    echo Removing old ITLDIS directory...
    rd /s /q "%CATALINA_HOME%\webapps\ITLDIS" 2>nul
)
if exist "%CATALINA_HOME%\webapps\ITLDIS.war" (
    echo Removing old ITLDIS.war...
    del /f /q "%CATALINA_HOME%\webapps\ITLDIS.war" 2>nul
)

echo [3/4] Ensuring correct WAR file is deployed...
if exist "target\itldis.war" (
    echo Copying itldis.war to Tomcat...
    copy /Y "target\itldis.war" "%CATALINA_HOME%\webapps\itldis.war" >nul 2>&1
    echo [OK] WAR file deployed
) else (
    echo [WARNING] WAR file not found in target directory
    echo Please run: package-camunda.bat first
)

echo [4/4] Creating context.xml for case-insensitive access...
if not exist "%CATALINA_HOME%\conf\Catalina\localhost" (
    mkdir "%CATALINA_HOME%\conf\Catalina\localhost"
)

REM Create context file that maps ITLDIS to itldis
echo ^<?xml version="1.0" encoding="UTF-8"?^> > "%CATALINA_HOME%\conf\Catalina\localhost\ITLDIS.xml"
echo ^<Context docBase="${catalina.home}/webapps/itldis" /^> >> "%CATALINA_HOME%\conf\Catalina\localhost\ITLDIS.xml"

echo [OK] Context mapping created: /ITLDIS -^> itldis

echo.
echo ============================================
echo Fix Complete!
echo ============================================
echo.
echo IMPORTANT: You must restart Tomcat for the changes to take effect!
echo.
echo The application will be accessible at:
echo   http://localhost:8080/itldis/  (lowercase - original)
echo   http://localhost:8080/ITLDIS/  (uppercase - mapped)
echo.
echo To restart Tomcat, run:
echo   start-camunda-tomcat.bat
echo.
echo Or manually:
echo   1. Stop: %CATALINA_HOME%\bin\shutdown.bat
echo   2. Start: %CATALINA_HOME%\bin\startup.bat
echo.
pause

