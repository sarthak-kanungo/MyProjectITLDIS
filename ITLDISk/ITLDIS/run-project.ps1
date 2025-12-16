# ITLDIS Project Runner Script (PowerShell)
# This script builds and runs the ITLDIS project from ITLDISk/ITLDIS folder
# Note: This folder uses Eclipse project structure, so we'll use the main ITLDIS folder's pom.xml

param(
    [string]$TomcatHome = "C:\apache-tomcat-9.0.100",
    [switch]$BuildOnly = $false,
    [switch]$SkipBuild = $false,
    [switch]$Help = $false
)

function Show-Help {
    Write-Host ""
    Write-Host "ITLDIS Project Runner (ITLDISk/ITLDIS)"
    Write-Host "======================================="
    Write-Host ""
    Write-Host "Usage:"
    Write-Host "  .\run-project.ps1 [options]"
    Write-Host ""
    Write-Host "Options:"
    Write-Host "  -TomcatHome <path>    Tomcat installation directory (default: C:\apache-tomcat-9.0.100)"
    Write-Host "  -BuildOnly           Only build the project, don't start Tomcat"
    Write-Host "  -SkipBuild            Skip build step (use existing WAR file)"
    Write-Host "  -Help                 Show this help message"
    Write-Host ""
    Write-Host "Note: This script runs the project from the main ITLDIS folder"
    Write-Host "      which contains the Maven pom.xml file"
    Write-Host ""
}

if ($Help) {
    Show-Help
    exit 0
}

# Change to script directory
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$mainProjectDir = Join-Path $scriptDir "..\..\ITLDIS"

Write-Host ""
Write-Host "============================================" -ForegroundColor Cyan
Write-Host "ITLDIS Project Runner (ITLDISk/ITLDIS)" -ForegroundColor Cyan
Write-Host "============================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Current directory: $scriptDir" -ForegroundColor Gray
Write-Host "Main project directory: $mainProjectDir" -ForegroundColor Gray
Write-Host ""

# Check if main project directory exists
if (-not (Test-Path $mainProjectDir)) {
    Write-Host "ERROR: Main ITLDIS project folder not found at: $mainProjectDir" -ForegroundColor Red
    Write-Host "Please ensure the main ITLDIS folder exists with pom.xml" -ForegroundColor Yellow
    exit 1
}

# Check if pom.xml exists in main project
if (-not (Test-Path (Join-Path $mainProjectDir "pom.xml"))) {
    Write-Host "ERROR: pom.xml not found in main ITLDIS folder" -ForegroundColor Red
    Write-Host "Path checked: $(Join-Path $mainProjectDir 'pom.xml')" -ForegroundColor Yellow
    exit 1
}

Write-Host "[OK] Found main ITLDIS project with pom.xml" -ForegroundColor Green

# Change to main project directory
Set-Location $mainProjectDir
Write-Host "[INFO] Changed to main project directory: $mainProjectDir" -ForegroundColor Gray
Write-Host ""

# Check if Maven is available
$mvnCmd = Get-Command mvn -ErrorAction SilentlyContinue
if (-not $mvnCmd) {
    Write-Host "ERROR: Maven (mvn) is not found in PATH" -ForegroundColor Red
    Write-Host "Please install Maven and add it to your PATH" -ForegroundColor Yellow
    Write-Host "Download from: https://maven.apache.org/download.cgi" -ForegroundColor Yellow
    exit 1
}

Write-Host "[OK] Maven found: $($mvnCmd.Source)" -ForegroundColor Green

# Check if Java is available
$javaCmd = Get-Command java -ErrorAction SilentlyContinue
if (-not $javaCmd) {
    Write-Host "ERROR: Java is not found in PATH" -ForegroundColor Red
    Write-Host "Please install Java JDK 8 and add it to your PATH" -ForegroundColor Yellow
    exit 1
}

$javaVersion = java -version 2>&1 | Select-Object -First 1
Write-Host "[OK] Java found: $javaVersion" -ForegroundColor Green

# Build step
if (-not $SkipBuild) {
    Write-Host ""
    Write-Host "[1/3] Building project..." -ForegroundColor Yellow
    Write-Host "Running: mvn clean package -DskipTests" -ForegroundColor Gray
    Write-Host "Working directory: $(Get-Location)" -ForegroundColor Gray
    
    & mvn clean package -DskipTests
    
    if ($LASTEXITCODE -ne 0) {
        Write-Host ""
        Write-Host "ERROR: Build failed!" -ForegroundColor Red
        exit 1
    }
    
    if (-not (Test-Path "target\itldis.war")) {
        Write-Host ""
        Write-Host "ERROR: WAR file not found after build!" -ForegroundColor Red
        exit 1
    }
    
    $warSize = (Get-Item "target\itldis.war").Length / 1MB
    Write-Host "[OK] Build successful! WAR file: target\itldis.war ($([math]::Round($warSize, 2)) MB)" -ForegroundColor Green
} else {
    Write-Host ""
    Write-Host "[1/3] Skipping build (using existing WAR file)" -ForegroundColor Yellow
    if (-not (Test-Path "target\itldis.war")) {
        Write-Host "ERROR: WAR file not found at target\itldis.war" -ForegroundColor Red
        Write-Host "Please build the project first or remove -SkipBuild flag" -ForegroundColor Yellow
        exit 1
    }
}

if ($BuildOnly) {
    Write-Host ""
    Write-Host "============================================" -ForegroundColor Cyan
    Write-Host "Build Complete!" -ForegroundColor Cyan
    Write-Host "============================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "WAR file ready at: $(Join-Path (Get-Location) 'target\itldis.war')" -ForegroundColor Green
    Write-Host ""
    Write-Host "To deploy manually:" -ForegroundColor Yellow
    Write-Host "  1. Copy target\itldis.war to your Tomcat webapps directory"
    Write-Host "  2. Start Tomcat server"
    Write-Host "  3. Access: http://localhost:8080/itldis/" -ForegroundColor Cyan
    Write-Host ""
    exit 0
}

# Check Tomcat
Write-Host ""
Write-Host "[2/3] Checking Tomcat installation..." -ForegroundColor Yellow

if (-not (Test-Path "$TomcatHome\bin\catalina.bat")) {
    Write-Host ""
    Write-Host "ERROR: Tomcat not found at: $TomcatHome" -ForegroundColor Red
    Write-Host ""
    Write-Host "Please specify Tomcat path:" -ForegroundColor Yellow
    Write-Host "  .\run-project.ps1 -TomcatHome C:\path\to\tomcat" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "Or set CATALINA_HOME environment variable" -ForegroundColor Yellow
    exit 1
}

Write-Host "[OK] Tomcat found at: $TomcatHome" -ForegroundColor Green

# Deploy WAR to Tomcat
Write-Host ""
Write-Host "[3/3] Deploying WAR to Tomcat..." -ForegroundColor Yellow

# Stop Tomcat if running - Check if port 8080 is in use
$portInUse = Get-NetTCPConnection -LocalPort 8080 -ErrorAction SilentlyContinue

if ($portInUse) {
    Write-Host "[INFO] Port 8080 is in use. Attempting to stop Tomcat..." -ForegroundColor Yellow
    if (Test-Path "$TomcatHome\bin\shutdown.bat") {
        & "$TomcatHome\bin\shutdown.bat" 2>$null
        Start-Sleep -Seconds 3
    }
}

# Copy WAR file
Copy-Item -Path "target\itldis.war" -Destination "$TomcatHome\webapps\itldis.war" -Force
Write-Host "[OK] WAR deployed to: $TomcatHome\webapps\itldis.war" -ForegroundColor Green

# Check if port 8080 is in use
$portInUse = Get-NetTCPConnection -LocalPort 8080 -ErrorAction SilentlyContinue
if ($portInUse) {
    Write-Host ""
    Write-Host "[WARNING] Port 8080 is already in use" -ForegroundColor Yellow
    Write-Host "Tomcat may already be running or another application is using port 8080" -ForegroundColor Yellow
    Write-Host ""
    $continue = Read-Host "Continue anyway? (Y/N)"
    if ($continue -ne "Y" -and $continue -ne "y") {
        exit 0
    }
}

# Start Tomcat
Write-Host ""
Write-Host "============================================" -ForegroundColor Cyan
Write-Host "Starting Tomcat Server..." -ForegroundColor Cyan
Write-Host "============================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Application will be available at:" -ForegroundColor Green
Write-Host "  http://localhost:8080/itldis/" -ForegroundColor Cyan
Write-Host ""
Write-Host "Login page:" -ForegroundColor Green
Write-Host "  http://localhost:8080/itldis/login/Login.jsp" -ForegroundColor Cyan
Write-Host ""
Write-Host "Log files:" -ForegroundColor Green
Write-Host "  $TomcatHome\logs\catalina.out" -ForegroundColor Cyan
Write-Host ""
Write-Host "Press Ctrl+C to stop the server" -ForegroundColor Yellow
Write-Host ""

# Start Tomcat in foreground
Set-Location "$TomcatHome\bin"
& .\catalina.bat run

