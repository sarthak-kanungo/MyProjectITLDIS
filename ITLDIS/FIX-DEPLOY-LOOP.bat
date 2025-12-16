@echo off
echo ============================================
echo Fix Deploy/Undeploy Loop - ITLDIS
echo ============================================
echo.

echo [1/4] Stopping Tomcat...
call "C:\apache-tomcat-9.0.100\bin\shutdown.bat" 2>nul
timeout /t 5 /nobreak >nul

echo [2/4] Cleaning old deployment...
if exist "C:\apache-tomcat-9.0.100\webapps\itldis" (
    rmdir /s /q "C:\apache-tomcat-9.0.100\webapps\itldis"
)
if exist "C:\apache-tomcat-9.0.100\webapps\itldis.war" (
    del /f "C:\apache-tomcat-9.0.100\webapps\itldis.war"
)

echo [3/4] Creating context file to prevent deploy loop...
if not exist "C:\apache-tomcat-9.0.100\conf\Catalina\localhost" (
    mkdir "C:\apache-tomcat-9.0.100\conf\Catalina\localhost"
)
(
    echo ^<?xml version="1.0" encoding="UTF-8"?^>
    echo ^<Context docBase="itldis" reloadable="false" /^>
) > "C:\apache-tomcat-9.0.100\conf\Catalina\localhost\ITLDIS.xml"
echo    Context file created with reloadable="false"

echo [4/4] Copying WAR and starting Tomcat...
cd /d "%~dp0"
if exist "target\itldis.war" (
    copy /y "target\itldis.war" "C:\apache-tomcat-9.0.100\webapps\itldis.war" >nul
    echo    WAR file copied
) else (
    echo    WARNING: target\itldis.war not found
    echo    Please run: mvn clean package
    pause
    exit /b 1
)

echo.
echo Starting Tomcat...
start "Tomcat Server" "C:\apache-tomcat-9.0.100\bin\startup.bat"

echo.
echo ============================================
echo Deployment Complete!
echo ============================================
echo.
echo Waiting 90 seconds for deployment...
echo Large WAR files take 2-3 minutes to deploy.
echo.
timeout /t 90 /nobreak >nul

echo.
echo ============================================
echo Testing Application
echo ============================================
echo.
echo Application URL: http://localhost:8080/ITLDIS/
echo Welcome URL: http://localhost:8080/ITLDIS/Welcome.do
echo Login URL: http://localhost:8080/ITLDIS/login/Login.jsp
echo.
echo If you still get 404, wait another 1-2 minutes for
echo the large WAR file to finish deploying.
echo.
pause

