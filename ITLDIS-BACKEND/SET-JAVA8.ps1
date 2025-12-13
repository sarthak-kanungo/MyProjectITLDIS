# PowerShell script to set Java 8 for ITLDIS-BACKEND project
# Run this script before building the project

Write-Host "=== Setting Java 8 for ITLDIS-BACKEND ===" -ForegroundColor Green

# Common Java 8 installation paths - update if your path is different
$java8Paths = @(
    "C:\Program Files\Java\jdk1.8.0_XXX",
    "C:\Program Files\Java\jdk-8",
    "C:\Program Files (x86)\Java\jdk1.8.0_XXX",
    "$env:JAVA_HOME"
)

$java8Found = $false

foreach ($path in $java8Paths) {
    if (Test-Path $path) {
        $javaExe = Join-Path $path "bin\java.exe"
        if (Test-Path $javaExe) {
            $version = & $javaExe -version 2>&1 | Select-String "version"
            if ($version -match "1\.8") {
                Write-Host "Found Java 8 at: $path" -ForegroundColor Green
                $env:JAVA_HOME = $path
                $env:PATH = "$path\bin;$env:PATH"
                $java8Found = $true
                break
            }
        }
    }
}

if (-not $java8Found) {
    Write-Host "`n❌ Java 8 not found!" -ForegroundColor Red
    Write-Host "Please install Java 8 JDK and update this script with the correct path." -ForegroundColor Yellow
    Write-Host "`nTo find Java installations:" -ForegroundColor Yellow
    Write-Host "  Get-ChildItem 'C:\Program Files\Java' -Directory" -ForegroundColor Cyan
    Write-Host "`nThen update JAVA_HOME manually:" -ForegroundColor Yellow
    Write-Host "  `$env:JAVA_HOME = 'C:\Program Files\Java\jdk1.8.0_XXX'" -ForegroundColor Cyan
    Write-Host "  `$env:PATH = '`$env:JAVA_HOME\bin;`$env:PATH'" -ForegroundColor Cyan
    exit 1
}

Write-Host "`n✅ Java 8 configured!" -ForegroundColor Green
Write-Host "Java version:" -ForegroundColor Cyan
java -version
Write-Host "`nMaven will use this Java version for compilation." -ForegroundColor Green
Write-Host "`nNow run: mvn clean compile" -ForegroundColor Yellow
