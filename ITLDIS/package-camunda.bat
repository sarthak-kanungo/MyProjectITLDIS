@echo off
REM ============================================
REM Package ITLDIS with Camunda BPM
REM ============================================

echo.
echo ============================================
echo Packaging ITLDIS with Camunda BPM
echo ============================================
echo.

cd /d %~dp0

echo Building WAR file...
call mvn clean package -DskipTests

if errorlevel 1 (
    echo ERROR: Build failed
    pause
    exit /b 1
)

if exist "target\itldis.war" (
    echo.
    echo ============================================
    echo SUCCESS! WAR file created
    echo ============================================
    echo.
    echo Location: target\itldis.war
    echo.
    echo To deploy:
    echo 1. Copy target\itldis.war to your Tomcat webapps directory
    echo 2. Start Tomcat
    echo 3. Check logs for: "Camunda ProcessEngine initialized"
    echo.
) else (
    echo ERROR: WAR file not found in target directory
    pause
    exit /b 1
)

pause

