@echo off
REM Complete Build and Deploy Script
REM Builds the project and optionally deploys to Tomcat

echo ========================================
echo Camunda BPM - Build and Deploy Script
echo ========================================
echo.

REM Step 1: Build
echo [1/3] Building project...
call mvn clean package -DskipTests
if errorlevel 1 (
    echo ERROR: Build failed!
    pause
    exit /b 1
)
echo OK: Build successful
echo.

REM Step 2: Verify WAR file
echo [2/3] Verifying WAR file...
if not exist "target\itldis.war" (
    echo ERROR: WAR file not found!
    pause
    exit /b 1
)
echo OK: WAR file created: target\itldis.war
for %%A in (target\itldis.war) do echo     Size: %%~zA bytes
echo.

REM Step 3: Optional deployment
echo [3/3] Deployment options:
echo.
echo 1. Deploy to Tomcat (requires Tomcat home path)
echo 2. Skip deployment (WAR file ready at target\itldis.war)
echo.
set /p choice="Choose option (1 or 2, press Enter to skip): "

if "%choice%"=="1" (
    set /p tomcat_home="Enter Tomcat home directory: "
    if "%tomcat_home%"=="" (
        echo ERROR: Tomcat home directory not provided
        goto :skip_deploy
    )
    if exist "deploy-tomcat.bat" (
        call deploy-tomcat.bat "%tomcat_home%"
    ) else (
        echo ERROR: deploy-tomcat.bat not found
        echo Please deploy manually: copy target\itldis.war to %tomcat_home%\webapps\
    )
) else (
    :skip_deploy
    echo.
    echo ========================================
    echo Build Complete!
    echo ========================================
    echo.
    echo WAR file ready at: target\itldis.war
    echo.
    echo To deploy manually:
    echo   1. Copy target\itldis.war to your application server
    echo   2. Start the server
    echo   3. Access: http://localhost:8080/itldis/camunda/test.jsp
    echo.
    echo To deploy automatically, run:
    echo   deploy-tomcat.bat [tomcat-home-path]
    echo.
)

pause
