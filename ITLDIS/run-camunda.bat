@echo off
REM ============================================
REM Run ITLDIS with Camunda BPM on Tomcat
REM ============================================

echo.
echo ============================================
echo Starting ITLDIS with Camunda BPM
echo ============================================
echo.

cd /d %~dp0

REM Check if Tomcat path is set
if "%CATALINA_HOME%"=="" (
    echo WARNING: CATALINA_HOME environment variable is not set
    echo.
    echo Please set CATALINA_HOME to your Tomcat installation directory
    echo Example: set CATALINA_HOME=C:\apache-tomcat-9.0.65
    echo.
    set /p CATALINA_HOME="Enter Tomcat installation path: "
    if "%CATALINA_HOME%"=="" (
        echo ERROR: Tomcat path is required
        pause
        exit /b 1
    )
)

if not exist "%CATALINA_HOME%\bin\catalina.bat" (
    echo ERROR: Tomcat not found at %CATALINA_HOME%
    echo Please verify your Tomcat installation path
    pause
    exit /b 1
)

echo Tomcat found at: %CATALINA_HOME%
echo.

REM Build WAR file
echo [1/3] Building WAR file...
call mvn clean package -DskipTests
if errorlevel 1 (
    echo ERROR: Build failed
    pause
    exit /b 1
)
echo Build successful!

REM Copy WAR to Tomcat
echo.
echo [2/3] Deploying WAR to Tomcat...
if exist "target\itldis.war" (
    copy /Y target\itldis.war "%CATALINA_HOME%\webapps\itldis.war" >nul 2>&1
    echo WAR deployed to %CATALINA_HOME%\webapps\
) else (
    echo ERROR: WAR file not found
    pause
    exit /b 1
)

REM Start Tomcat
echo.
echo [3/3] Starting Tomcat...
echo.
echo ============================================
echo Starting Tomcat Server...
echo ============================================
echo.
echo The application will be available at:
echo http://localhost:8080/itldis/
echo.
echo Camunda endpoints:
echo - Get User Tasks: http://localhost:8080/itldis/camunda/getUserTasks.do?userId=demo
echo - Start Process: http://localhost:8080/itldis/camunda/startProcess.do
echo.
echo Press Ctrl+C to stop the server
echo.
echo ============================================
echo.

cd /d "%CATALINA_HOME%\bin"
call catalina.bat run

pause

