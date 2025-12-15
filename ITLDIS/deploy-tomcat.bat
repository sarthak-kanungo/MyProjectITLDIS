@echo off
REM Camunda BPM Deployment Script for Tomcat
REM Usage: deploy-tomcat.bat [tomcat-home]

setlocal enabledelayedexpansion

echo ========================================
echo Camunda BPM Deployment Script
echo ========================================
echo.

REM Check if Tomcat home is provided
if "%1"=="" (
    echo ERROR: Tomcat home directory not provided
    echo Usage: deploy-tomcat.bat [tomcat-home]
    echo Example: deploy-tomcat.bat C:\apache-tomcat-9.0.65
    exit /b 1
)

set TOMCAT_HOME=%1
set WAR_FILE=target\itldis.war
set DEPLOY_PATH=%TOMCAT_HOME%\webapps\itldis.war

REM Verify WAR file exists
if not exist "%WAR_FILE%" (
    echo ERROR: WAR file not found: %WAR_FILE%
    echo Please build the project first: mvn clean package -DskipTests
    exit /b 1
)

REM Verify Tomcat directory exists
if not exist "%TOMCAT_HOME%" (
    echo ERROR: Tomcat directory not found: %TOMCAT_HOME%
    exit /b 1
)

echo [1/5] Stopping Tomcat...
set CATALINA_HOME=%TOMCAT_HOME%
set CATALINA_BASE=%TOMCAT_HOME%
if exist "%TOMCAT_HOME%\bin\shutdown.bat" (
    call "%TOMCAT_HOME%\bin\shutdown.bat" >nul 2>&1
    ping 127.0.0.1 -n 6 >nul
) else (
    echo WARNING: shutdown.bat not found, Tomcat may already be stopped
)
echo OK: Tomcat shutdown attempted (may already be stopped)

echo [2/5] Removing old deployment...
if exist "%DEPLOY_PATH%" del /f "%DEPLOY_PATH%"
if exist "%TOMCAT_HOME%\webapps\itldis" rmdir /s /q "%TOMCAT_HOME%\webapps\itldis"

echo [3/5] Copying WAR file...
copy "%WAR_FILE%" "%DEPLOY_PATH%"
if errorlevel 1 (
    echo ERROR: Failed to copy WAR file
    exit /b 1
)
echo OK: WAR file copied successfully

echo [4/5] Starting Tomcat...
set CATALINA_HOME=%TOMCAT_HOME%
set CATALINA_BASE=%TOMCAT_HOME%
if exist "%TOMCAT_HOME%\bin\startup.bat" (
    call "%TOMCAT_HOME%\bin\startup.bat"
) else (
    echo ERROR: startup.bat not found at %TOMCAT_HOME%\bin\startup.bat
    echo Please start Tomcat manually
    exit /b 1
)

echo [5/5] Waiting for deployment...
echo Waiting 10 seconds for Tomcat to start...
ping 127.0.0.1 -n 11 >nul
echo OK: Deployment wait complete

echo.
echo ========================================
echo Deployment Complete!
echo ========================================
echo.
echo WAR File: %DEPLOY_PATH%
echo Application URL: http://localhost:8080/itldis
echo Test Page: http://localhost:8080/itldis/camunda/test.jsp
echo.
echo Check logs at: %TOMCAT_HOME%\logs\catalina.out
echo Look for: "Camunda ProcessEngine initialized: default"
echo.
pause
