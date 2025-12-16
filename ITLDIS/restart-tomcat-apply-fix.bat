@echo off
REM ============================================
REM Restart Tomcat to Apply Context Fix
REM ============================================

echo.
echo ============================================
echo Restarting Tomcat to Apply Context Fix
echo ============================================
echo.

set CATALINA_HOME=C:\apache-tomcat-9.0.100

if not exist "%CATALINA_HOME%\bin\catalina.bat" (
    echo ERROR: Tomcat not found at %CATALINA_HOME%
    pause
    exit /b 1
)

echo [1/3] Stopping Tomcat...
call "%CATALINA_HOME%\bin\shutdown.bat" >nul 2>&1
timeout /t 5 /nobreak >nul 2>&1

echo [2/3] Verifying context mapping...
if exist "%CATALINA_HOME%\conf\Catalina\localhost\ITLDIS.xml" (
    echo [OK] Context mapping file exists
) else (
    echo [INFO] Creating context mapping file...
    if not exist "%CATALINA_HOME%\conf\Catalina\localhost" (
        mkdir "%CATALINA_HOME%\conf\Catalina\localhost"
    )
    echo ^<?xml version="1.0" encoding="UTF-8"?^> > "%CATALINA_HOME%\conf\Catalina\localhost\ITLDIS.xml"
    echo ^<Context docBase="itldis" /^> >> "%CATALINA_HOME%\conf\Catalina\localhost\ITLDIS.xml"
    echo [OK] Context mapping created
)

echo [3/3] Starting Tomcat...
echo.
echo Tomcat is starting...
echo Please wait 15-20 seconds for the application to deploy...
echo.
echo After Tomcat starts, both URLs will work:
echo   http://localhost:8080/itldis/   (lowercase)
echo   http://localhost:8080/ITLDIS/   (uppercase)
echo.

cd /d "%CATALINA_HOME%\bin"
start "Tomcat Server" cmd /k "catalina.bat run"

echo.
echo Tomcat is starting in a new window...
echo Check the Tomcat window for startup messages.
echo.
echo To verify, run: check-camunda-status.bat
echo.
pause

