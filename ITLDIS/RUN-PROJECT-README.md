# Running ITLDIS Project from Terminal

This document explains how to run the ITLDIS project from the terminal/command line.

## Prerequisites

Before running the project, ensure you have:

1. **Java JDK 8** installed and added to PATH
   - Verify: `java -version`
   - Download: https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html

2. **Apache Maven** installed and added to PATH
   - Verify: `mvn -version`
   - Download: https://maven.apache.org/download.cgi

3. **Apache Tomcat 9.x** installed
   - Default location: `C:\apache-tomcat-9.0.100` (Windows) or `/opt/tomcat` (Linux/Mac)
   - Download: https://tomcat.apache.org/download-90.cgi

4. **SQL Server** running (for database)
   - The application connects to SQL Server database
   - Ensure SQL Server is running and accessible

## Quick Start

### Windows (PowerShell - Recommended)

```powershell
# Navigate to project directory
cd C:\MyProjectITLDIS\ITLDIS

# Run with default Tomcat location
.\run-project.ps1

# Or specify custom Tomcat location
.\run-project.ps1 -TomcatHome C:\path\to\tomcat
```

### Windows (Command Prompt / Batch)

```cmd
# Navigate to project directory
cd C:\MyProjectITLDIS\ITLDIS

# Run with default Tomcat location
run-project.bat

# Or specify custom Tomcat location
run-project.bat --tomcat-home C:\path\to\tomcat
```

### Linux / Mac (Bash)

```bash
# Make script executable (first time only)
chmod +x run-project.sh

# Navigate to project directory
cd /path/to/ITLDIS

# Run with default Tomcat location
./run-project.sh

# Or specify custom Tomcat location
./run-project.sh --tomcat-home /opt/tomcat
```

## Script Options

All scripts support the following options:

| Option | Description | Example |
|--------|-------------|---------|
| `--tomcat-home <path>` | Specify Tomcat installation directory | `--tomcat-home C:\tomcat` |
| `--build-only` | Only build the project, don't start Tomcat | `--build-only` |
| `--skip-build` | Skip build step (use existing WAR file) | `--skip-build` |
| `--help` or `-h` | Show help message | `--help` |

## What the Scripts Do

The run scripts perform the following steps:

1. **Check Prerequisites**
   - Verify Maven is installed
   - Verify Java is installed
   - Verify Tomcat is installed

2. **Build Project** (unless `--skip-build` is used)
   - Run `mvn clean package -DskipTests`
   - Creates WAR file at `target/itldis.war`

3. **Deploy to Tomcat**
   - Copy WAR file to Tomcat's `webapps` directory
   - Stop existing Tomcat instance if running

4. **Start Tomcat**
   - Start Tomcat server in foreground
   - Application will be available at `http://localhost:8080/itldis/`

## Manual Steps (Alternative)

If you prefer to run steps manually:

### 1. Build the Project

```bash
# Windows / Linux / Mac
mvn clean package -DskipTests
```

This creates `target/itldis.war`

### 2. Deploy to Tomcat

**Windows:**
```cmd
copy target\itldis.war C:\apache-tomcat-9.0.100\webapps\itldis.war
```

**Linux/Mac:**
```bash
cp target/itldis.war /opt/tomcat/webapps/itldis.war
```

### 3. Start Tomcat

**Windows:**
```cmd
cd C:\apache-tomcat-9.0.100\bin
catalina.bat run
```

**Linux/Mac:**
```bash
cd /opt/tomcat/bin
./catalina.sh run
```

## Accessing the Application

Once Tomcat is running, access the application at:

- **Main Application**: http://localhost:8080/itldis/
- **Login Page**: http://localhost:8080/itldis/login/Login.jsp
- **Home Page** (after login): http://localhost:8080/itldis/home

## Stopping the Server

- **Windows**: Press `Ctrl+C` in the terminal window
- **Linux/Mac**: Press `Ctrl+C` in the terminal window

Or use Tomcat's shutdown script:

**Windows:**
```cmd
C:\apache-tomcat-9.0.100\bin\shutdown.bat
```

**Linux/Mac:**
```bash
/opt/tomcat/bin/shutdown.sh
```

## Troubleshooting

### Port 8080 Already in Use

If you see "Port 8080 is already in use":

1. **Find the process using port 8080:**

   **Windows:**
   ```cmd
   netstat -ano | findstr :8080
   ```

   **Linux/Mac:**
   ```bash
   lsof -i :8080
   ```

2. **Stop the process** or change Tomcat's port in `server.xml`

### Build Fails

- Ensure Maven is installed: `mvn -version`
- Ensure Java JDK 8 is installed: `java -version`
- Check for compilation errors in the output
- Ensure all dependencies are available (Maven will download them)

### Tomcat Not Found

- Verify Tomcat is installed at the specified path
- Set `CATALINA_HOME` environment variable:
  
  **Windows:**
  ```cmd
  set CATALINA_HOME=C:\apache-tomcat-9.0.100
  ```
  
  **Linux/Mac:**
  ```bash
  export CATALINA_HOME=/opt/tomcat
  ```

### Application Not Loading

- Check Tomcat logs: `$CATALINA_HOME/logs/catalina.out`
- Verify WAR file was deployed: Check `$CATALINA_HOME/webapps/itldis/`
- Ensure SQL Server is running and accessible
- Check database connection settings in `src/main/resources/camunda.properties`

## Environment Variables

You can set these environment variables for convenience:

- `CATALINA_HOME`: Tomcat installation directory
- `JAVA_HOME`: Java installation directory
- `MAVEN_HOME`: Maven installation directory (optional, if Maven is in PATH)

## Project Structure

```
ITLDIS/
├── pom.xml                    # Maven configuration
├── src/                       # Source code
│   ├── main/
│   │   ├── java/              # Java source files
│   │   ├── webapp/            # Web application files (JSP, etc.)
│   │   └── resources/          # Configuration files
│   └── test/                  # Test files
├── target/                     # Build output (WAR file)
├── run-project.ps1            # PowerShell script (Windows)
├── run-project.bat            # Batch script (Windows)
└── run-project.sh             # Bash script (Linux/Mac)
```

## Additional Information

- **Maven Build**: The project uses Maven for dependency management and building
- **WAR Packaging**: The project is packaged as a WAR (Web Application Archive) file
- **Tomcat Deployment**: The WAR file is deployed to Tomcat's `webapps` directory
- **Database**: Requires SQL Server database connection (configured in properties files)

## Support

For issues or questions:
1. Check Tomcat logs: `$CATALINA_HOME/logs/catalina.out`
2. Check Maven build output for errors
3. Verify all prerequisites are installed correctly
4. Ensure database is accessible

