@echo off
REM ============================================
REM Switch Camunda from H2 to SQL Server
REM ============================================

echo.
echo ============================================
echo Switch Camunda Database to SQL Server
echo ============================================
echo.

cd /d %~dp0

echo [INFO] This script will help you switch from H2 to SQL Server
echo.
echo Prerequisites:
echo 1. SQL Server must be running
echo 2. Database 'itldis_camunda' must be created
echo 3. SQL Server JDBC driver must be in WEB-INF/lib
echo.
set /p CONTINUE="Continue? (Y/N): "
if /i not "%CONTINUE%"=="Y" (
    exit /b 0
)

echo.
echo [1/4] Checking SQL Server JDBC driver...
if exist "src\main\webapp\WEB-INF\lib\sqljdbc4.jar" (
    echo [OK] SQL Server JDBC driver found
) else if exist "src\main\webapp\WEB-INF\lib\mssql-jdbc*.jar" (
    echo [OK] SQL Server JDBC driver found
) else (
    echo [WARNING] SQL Server JDBC driver not found
    echo Please ensure sqljdbc4.jar or mssql-jdbc*.jar is in WEB-INF/lib
    echo.
    set /p CONTINUE="Continue anyway? (Y/N): "
    if /i not "%CONTINUE%"=="Y" (
        exit /b 0
    )
)

echo.
echo [2/4] Updating camunda.properties...
set PROP_FILE=src\main\resources\camunda.properties

REM Create backup
copy /Y "%PROP_FILE%" "%PROP_FILE%.backup" >nul 2>&1
echo [OK] Backup created: camunda.properties.backup

echo.
echo Please provide SQL Server connection details:
set /p DB_HOST="SQL Server host (default: localhost): "
if "%DB_HOST%"=="" set DB_HOST=localhost

set /p DB_PORT="SQL Server port (default: 1433): "
if "%DB_PORT%"=="" set DB_PORT=1433

set /p DB_NAME="Database name (default: itldis_camunda): "
if "%DB_NAME%"=="" set DB_NAME=itldis_camunda

set /p DB_USER="Database username: "
set /p DB_PASS="Database password: "

echo.
echo [3/4] Updating configuration file...

REM Use PowerShell to update the file
powershell -Command "$content = Get-Content '%PROP_FILE%' -Raw; $content = $content -replace '(?m)^camunda\.db\.url=.*', 'camunda.db.url=jdbc:sqlserver://%DB_HOST%:%DB_PORT%;databaseName=%DB_NAME%'; $content = $content -replace '(?m)^camunda\.db\.username=.*', 'camunda.db.username=%DB_USER%'; $content = $content -replace '(?m)^camunda\.db\.password=.*', 'camunda.db.password=%DB_PASS%'; $content = $content -replace '(?m)^camunda\.db\.driver=.*', 'camunda.db.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver'; $content = $content -replace '(?m)^camunda\.db\.type=.*', 'camunda.db.type=mssql'; $content = $content -replace '(?m)^#camunda\.db\.url=jdbc:sqlserver', 'camunda.db.url=jdbc:sqlserver'; Set-Content '%PROP_FILE%' -Value $content"

echo [OK] Configuration updated

echo.
echo [4/4] Instructions for completion:
echo.
echo 1. Create database in SQL Server:
echo    CREATE DATABASE %DB_NAME%;
echo.
echo 2. Rebuild and redeploy:
echo    mvn clean package
echo    fix-login-404.bat
echo.
echo 3. On first startup, Camunda will:
echo    - Connect to SQL Server
echo    - Create all tables automatically
echo    - Be ready to use
echo.
echo 4. Verify in SQL Server:
echo    SELECT * FROM %DB_NAME%.INFORMATION_SCHEMA.TABLES
echo    WHERE TABLE_NAME LIKE 'ACT_%%'
echo.
echo ============================================
echo Configuration Updated!
echo ============================================
echo.
echo Backup saved: camunda.properties.backup
echo.
pause

