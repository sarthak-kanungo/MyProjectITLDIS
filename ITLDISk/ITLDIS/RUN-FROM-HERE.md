# Running ITLDIS Project from ITLDISk/ITLDIS

## ✅ Script Created and Ready

A run script has been created in this folder that will automatically:
1. Navigate to the main ITLDIS folder (which contains `pom.xml`)
2. Build the project using Maven
3. Deploy the WAR file to Tomcat
4. Start Tomcat server

## 🚀 Quick Start

### Run the Project (Full Build + Start)

```powershell
cd C:\MyProjectITLDIS\ITLDISk\ITLDIS
.\run-project.ps1
```

This will:
- Build the project (takes 2-5 minutes)
- Deploy to Tomcat
- Start Tomcat server
- Application available at: http://localhost:8080/itldis/

### Build Only (Don't Start Tomcat)

```powershell
.\run-project.ps1 -BuildOnly
```

### Skip Build (Use Existing WAR)

```powershell
.\run-project.ps1 -SkipBuild
```

### Custom Tomcat Location

```powershell
.\run-project.ps1 -TomcatHome C:\path\to\tomcat
```

### Show Help

```powershell
.\run-project.ps1 -Help
```

## 📋 Prerequisites Verified

✅ **Maven**: Found and working  
✅ **Java**: Found (version 1.8.0_471)  
✅ **Tomcat**: Found at C:\apache-tomcat-9.0.100  
✅ **Main Project**: Found at C:\MyProjectITLDIS\ITLDIS  

## 🔍 How It Works

The script automatically:
1. Detects that this folder doesn't have `pom.xml`
2. Navigates to the main ITLDIS folder (`..\..\ITLDIS`)
3. Uses the Maven `pom.xml` from there to build
4. Builds the WAR file
5. Deploys to Tomcat
6. Starts Tomcat

## 📝 Notes

- The build process takes 2-5 minutes depending on your system
- First build will download dependencies (may take longer)
- The script will check for port 8080 conflicts
- Tomcat logs are available at: `C:\apache-tomcat-9.0.100\logs\catalina.out`

## 🌐 Access the Application

Once Tomcat starts:
- **Main Application**: http://localhost:8080/itldis/
- **Login Page**: http://localhost:8080/itldis/login/Login.jsp

## 🛑 Stop the Server

Press `Ctrl+C` in the terminal window, or run:
```cmd
C:\apache-tomcat-9.0.100\bin\shutdown.bat
```

---

**Status**: ✅ Ready to run!
**Script**: `run-project.ps1`

