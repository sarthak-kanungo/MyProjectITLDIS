@echo off
echo ============================================
echo Start Tomcat Properly - ITLDIS
echo ============================================
echo.

REM Set CATALINA_HOME
set CATALINA_HOME=C:\apache-tomcat-9.0.100

REM Check if Tomcat is already running
netstat -an | find "8080" >nul
if %errorlevel% == 0 (
    echo Tomcat is already running on port 8080
    echo Stopping existing instance...
    call "%CATALINA_HOME%\bin\shutdown.bat"
    timeout /t 5 /nobreak >nul
)

REM Ensure WAR is deployed
if not exist "%CATALINA_HOME%\webapps\itldis.war" (
    echo WARNING: itldis.war not found in webapps directory
    echo Please copy itldis.war to %CATALINA_HOME%\webapps\
    pause
    exit /b 1
)

REM Ensure context file exists
if not exist "%CATALINA_HOME%\conf\Catalina\localhost" (
    mkdir "%CATALINA_HOME%\conf\Catalina\localhost"
)
if not exist "%CATALINA_HOME%\conf\Catalina\localhost\ITLDIS.xml" (
    (
        echo ^<?xml version="1.0" encoding="UTF-8"?^>
        echo ^<Context docBase="itldis" reloadable="false" /^>
    ) > "%CATALINA_HOME%\conf\Catalina\localhost\ITLDIS.xml"
    echo Created context file
)

echo.
echo Starting Tomcat...
echo This window will stay open to keep Tomcat running.
echo.
echo Application will be available at:
echo   http://localhost:8080/ITLDIS/
echo   http://localhost:8080/ITLDIS/Welcome.do
echo   http://localhost:8080/ITLDIS/login/Login.jsp
echo.
echo Large WAR files take 2-3 minutes to deploy.
echo Please wait for deployment to complete.
echo.
echo Press Ctrl+C to stop Tomcat.
echo.

cd /d "%CATALINA_HOME%\bin"
call startup.bat

REM Keep window open
pause

