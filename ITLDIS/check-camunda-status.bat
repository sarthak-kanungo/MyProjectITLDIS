@echo off
REM ============================================
REM Check Camunda BPM Status
REM ============================================

echo.
echo ============================================
echo Camunda BPM Status Check
echo ============================================
echo.

set CATALINA_HOME=C:\apache-tomcat-9.0.100
if not "%CATALINA_HOME%"=="" (
    if not exist "%CATALINA_HOME%" (
        set CATALINA_HOME=
    )
)

if "%CATALINA_HOME%"=="" (
    echo [ERROR] CATALINA_HOME not set
    echo Please set CATALINA_HOME environment variable
    pause
    exit /b 1
)

echo [1/4] Checking Tomcat installation...
if exist "%CATALINA_HOME%\bin\catalina.bat" (
    echo [OK] Tomcat found at: %CATALINA_HOME%
) else (
    echo [ERROR] Tomcat not found at: %CATALINA_HOME%
    pause
    exit /b 1
)

echo.
echo [2/4] Checking WAR deployment...
if exist "%CATALINA_HOME%\webapps\itldis.war" (
    echo [OK] WAR file deployed: itldis.war
    goto war_check_done
)
if exist "%CATALINA_HOME%\webapps\itldis" (
    echo [OK] Application deployed (exploded): itldis
    goto war_check_done
)
echo [WARNING] Application not deployed
echo Please run: package-camunda.bat
:war_check_done

echo.
echo [3/4] Checking if Tomcat is running...
netstat -an | find "8080" | find "LISTENING" >nul 2>&1
if %errorlevel%==0 (
    echo [OK] Port 8080 is in use - Tomcat appears to be running
    echo.
    echo Testing application access...
    powershell -Command "try { $r = Invoke-WebRequest -Uri 'http://localhost:8080/itldis/' -TimeoutSec 3 -UseBasicParsing; Write-Host '[OK] Application is accessible - Status:' $r.StatusCode } catch { Write-Host '[WARNING] Application not accessible:' $_.Exception.Message }"
) else (
    echo [INFO] Port 8080 is not in use - Tomcat is not running
    echo Run: start-camunda-tomcat.bat to start Tomcat
)

echo.
echo [4/4] Checking Camunda ProcessEngine...
if exist "%CATALINA_HOME%\logs\catalina.out" (
    findstr /C:"Camunda ProcessEngine initialized" "%CATALINA_HOME%\logs\catalina.out" >nul 2>&1
    if %errorlevel%==0 (
        echo [OK] Camunda ProcessEngine initialized (found in logs)
    ) else (
        echo [INFO] Camunda ProcessEngine initialization not found in logs
        echo This is normal if Tomcat just started or logs were cleared
    )
) else (
    echo [INFO] Log file not found - Tomcat may not have started yet
)

echo.
echo ============================================
echo Status Check Complete
echo ============================================
echo.
echo Application URL: http://localhost:8080/itldis/
echo Camunda Endpoint: http://localhost:8080/itldis/camunda/getUserTasks.do?userId=demo
echo Logs: %CATALINA_HOME%\logs\catalina.out
echo.
pause

