@echo off
REM Export ITLDIS architecture Mermaid diagrams to JPEG using Mermaid CLI (mmdc)
REM Usage:
REM   1) cd c:\MyProjectITLDIS\ITLDIS
REM   2) export-architecture-diagrams.bat

setlocal

where mmdc >nul 2>&1
if errorlevel 1 (
  echo ERROR: Mermaid CLI (mmdc) not found in PATH.
  echo Install it first:
  echo   npm install -g @mermaid-js/mermaid-cli
  goto :eof
)

if not exist diagrams (
  echo ERROR: diagrams folder not found. Expected .mmd files in .\diagrams
  goto :eof
)

set SRC=diagrams
set OUT=diagrams

call :render high-level
call :render layers
call :render modules
call :render stack
call :render dataflow
call :render security

echo.
echo Done. Check JPEG files in %OUT%\

goto :eof

:render
set NAME=%1
if exist "%SRC%\%NAME%.mmd" (
  echo Rendering %NAME%.mmd ^> %NAME%.jpg ...
  mmdc -i "%SRC%\%NAME%.mmd" -o "%OUT%\%NAME%.jpg" --quiet
) else (
  echo WARNING: %SRC%\%NAME%.mmd not found, skipping.
)
exit /b 0
