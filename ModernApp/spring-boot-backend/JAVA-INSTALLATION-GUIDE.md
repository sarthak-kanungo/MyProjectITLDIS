# Java 17 Installation Guide

## Issue
Spring Boot 3.2.0 requires **Java 17 or higher**, but your system currently has **Java 8**.

## Solution: Install Java 17

### Option 1: Install Java 17 (Recommended)

#### Windows:
1. **Download Java 17:**
   - Visit: https://adoptium.net/temurin/releases/
   - Select: Version 17, Windows x64, JDK
   - Download the installer (.msi file)

2. **Install:**
   - Run the installer
   - Follow the installation wizard
   - Make sure to check "Set JAVA_HOME variable"

3. **Verify Installation:**
   ```bash
   java -version
   ```
   Should show: `openjdk version "17.x.x"` or similar

4. **Update JAVA_HOME (if needed):**
   - Open System Properties → Environment Variables
   - Set `JAVA_HOME` to: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot`
   - Update `PATH` to include: `%JAVA_HOME%\bin`

#### Alternative: Using Chocolatey (if installed)
```bash
choco install openjdk17
```

### Option 2: Use Java 11 (Minimum for Spring Boot 3)

If you can't install Java 17, you can use Java 11 (minimum required):
- Download from: https://adoptium.net/temurin/releases/?version=11

### Option 3: Downgrade Spring Boot (Not Recommended)

If you must use Java 8, downgrade to Spring Boot 2.7.x:
- Change `spring-boot-starter-parent` version to `2.7.18`
- This loses Spring Boot 3 features

## After Installing Java 17

1. **Restart your terminal/PowerShell**

2. **Verify Java version:**
   ```bash
   java -version
   javac -version
   ```

3. **Run Maven build:**
   ```bash
   cd ModernApp\spring-boot-backend\auth-service
   mvn clean install
   ```

4. **Start the backend:**
   ```bash
   mvn spring-boot:run
   ```

## Quick Check Commands

```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Check JAVA_HOME
echo $env:JAVA_HOME  # PowerShell
```

## Troubleshooting

If `java -version` still shows Java 8 after installation:
1. Restart your terminal/PowerShell
2. Check `JAVA_HOME` environment variable
3. Ensure Java 17 is first in your PATH
4. Try: `refreshenv` (if using Chocolatey)

