@echo off
REM ITLDIS Project Runner Script (Windows Batch)
REM This script builds and runs the ITLDIS project on Tomcat

setlocal enabledelayedexpansion

REM Default values
set "TOMCAT_HOME=C:\apache-tomcat-9.0.100"
set "BUILD_ONLY=0"
set "SKIP_BUILD=0"

REM Parse command line arguments
:parse_args
if "%~1"=="" goto :args_done
if /i "%~1"=="--tomcat-home" (
    set "TOMCAT_HOME=%~2"
    shift
    shift
    goto :parse_args
)
if /i "%~1"=="--build-only" (
    set "BUILD_ONLY=1"
    shift
    goto :parse_args
)
if /i "%~1"=="--skip-build" (
    set "SKIP_BUILD=1"
    shift
    goto :parse_args
)
if /i "%~1"=="--help" (
    goto :show_help
)
if /i "%~1"=="-h" (
    goto :show_help
)
shift
goto :parse_args

:args_done

REM Use CATALINA_HOME if set
if not "%CATALINA_HOME%"=="" (
    set "TOMCAT_HOME=%CATALINA_HOME%"
)

REM Change to script directory
cd /d "%~dp0"

echo.
echo ============================================
echo ITLDIS Project Runner
echo ============================================
echo.

REM Check if Maven is available
where mvn >nul 2>&1
if errorlevel 1 (
    echo ERROR: Maven (mvn) is not found in PATH
    echo Please install Maven and add it to your PATH
    echo Download from: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

for /f "delims=" %%i in ('where mvn') do set "MVN_PATH=%%i"
echo [OK] Maven found: %MVN_PATH%

REM Check if Java is available
where java >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not found in PATH
    echo Please install Java JDK 8 and add it to your PATH
    pause
    exit /b 1
)

java -version >nul 2>&1
echo [OK] Java found

REM Build step
if "%SKIP_BUILD%"=="0" (
    echo.
    echo [1/3] Building project...
    echo Running: mvn clean package -DskipTests
    
    call mvn clean package -DskipTests
    
    if errorlevel 1 (
        echo.
        echo ERROR: Build failed!
        pause
        exit /b 1
    )
    
    if not exist "target\itldis.war" (
        echo.
        echo ERROR: WAR file not found after build!
        pause
        exit /b 1
    )
    
    for %%A in (target\itldis.war) do set "WAR_SIZE=%%~zA"
    set /a "WAR_SIZE_MB=!WAR_SIZE! / 1048576"
    echo [OK] Build successful! WAR file: target\itldis.war (!WAR_SIZE_MB! MB)
) else (
    echo.
    echo [1/3] Skipping build (using existing WAR file)
    if not exist "target\itldis.war" (
        echo ERROR: WAR file not found at target\itldis.war
        echo Please build the project first or remove --skip-build flag
        pause
        exit /b 1
    )
)

if "%BUILD_ONLY%"=="1" (
    echo.
    echo ============================================
    echo Build Complete!
    echo ============================================
    echo.
    echo WAR file ready at: target\itldis.war
    echo.
    echo To deploy manually:
    echo   1. Copy target\itldis.war to your Tomcat webapps directory
    echo   2. Start Tomcat server
    echo   3. Access: http://localhost:8080/itldis/
    echo.
    pause
    exit /b 0
)

REM Check Tomcat
echo.
echo [2/3] Checking Tomcat installation...

if not exist "%TOMCAT_HOME%\bin\catalina.bat" (
    echo.
    echo ERROR: Tomcat not found at: %TOMCAT_HOME%
    echo.
    echo Please specify Tomcat path:
    echo   run-project.bat --tomcat-home C:\path\to\tomcat
    echo.
    echo Or set CATALINA_HOME environment variable
    pause
    exit /b 1
)

echo [OK] Tomcat found at: %TOMCAT_HOME%

REM Deploy WAR to Tomcat
echo.
echo [3/3] Deploying WAR to Tomcat...

REM Stop Tomcat if running
if exist "%TOMCAT_HOME%\bin\shutdown.bat" (
    echo [INFO] Stopping existing Tomcat instance...
    call "%TOMCAT_HOME%\bin\shutdown.bat" >nul 2>&1
    timeout /t 3 >nul
)

REM Copy WAR file
copy /Y "target\itldis.war" "%TOMCAT_HOME%\webapps\itldis.war" >nul
echo [OK] WAR deployed to: %TOMCAT_HOME%\webapps\itldis.war

REM Check if port 8080 is in use
netstat -an | find "8080" | find "LISTENING" >nul 2>&1
if not errorlevel 1 (
    echo.
    echo [WARNING] Port 8080 is already in use
    echo Tomcat may already be running or another application is using port 8080
    echo.
    set /p "CONTINUE=Continue anyway? (Y/N): "
    if /i not "!CONTINUE!"=="Y" (
        exit /b 0
    )
)

REM Start Tomcat
echo.
echo ============================================
echo Starting Tomcat Server...
echo ============================================
echo.
echo Application will be available at:
echo   http://localhost:8080/itldis/
echo.
echo Login page:
echo   http://localhost:8080/itldis/login/Login.jsp
echo.
echo Log files:
echo   %TOMCAT_HOME%\logs\catalina.out
echo.
echo Press Ctrl+C to stop the server
echo.

REM Start Tomcat in foreground
cd /d "%TOMCAT_HOME%\bin"
call catalina.bat run

goto :end

:show_help
echo.
echo ITLDIS Project Runner
echo ====================
echo.
echo Usage:
echo   run-project.bat [options]
echo.
echo Options:
echo   --tomcat-home ^<path^>    Tomcat installation directory (default: C:\apache-tomcat-9.0.100)
echo   --build-only              Only build the project, don't start Tomcat
echo   --skip-build              Skip build step (use existing WAR file)
echo   --help, -h                Show this help message
echo.
echo Examples:
echo   run-project.bat
echo   run-project.bat --tomcat-home C:\tomcat
echo   run-project.bat --build-only
echo   run-project.bat --skip-build
echo.
pause
exit /b 0

:end

