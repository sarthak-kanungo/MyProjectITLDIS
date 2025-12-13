# How to Run ITLDIS-BACKEND

This guide explains how to run the ITLDIS-BACKEND Spring Boot application.

---

## Prerequisites

### 1. Java 8 JDK
- **Required:** Java 8 (JDK 1.8)
- **Check version:** `java -version`
- **Download:** [Oracle JDK 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) or [OpenJDK 8](https://adoptium.net/temurin/releases/?version=8)

### 2. Maven
- **Required:** Maven 3.6+ (included as `mvnw` wrapper)
- **Check version:** `mvnw -version` or `mvn -version`

### 3. SQL Server Database
- **Required:** SQL Server database
- **Current config:** `itldis_uat_2` database on `192.168.17.1:1433`
- **Update:** Edit `src/main/resources/application.properties` if needed

### 4. Lombok (for IDE)
- **Required:** Lombok plugin for your IDE (IntelliJ IDEA, Eclipse, etc.)
- **Installation:** See `LOMBOK-INSTALLATION-GUIDE.md`

---

## Quick Start

### Option 1: Using Maven Wrapper (Recommended)

```powershell
# Navigate to project directory
cd C:\MyProjectITLDIS\ITLDIS-BACKEND

# Set Java 8 (if not already set)
.\SET-JAVA8.ps1

# Run the application
.\mvnw.cmd spring-boot:run
```

### Option 2: Using Maven (if installed globally)

```powershell
# Navigate to project directory
cd C:\MyProjectITLDIS\ITLDIS-BACKEND

# Set Java 8
.\SET-JAVA8.ps1

# Run the application
mvn spring-boot:run
```

### Option 3: Using IDE (IntelliJ IDEA / Eclipse)

1. **Import Project:**
   - IntelliJ: File → Open → Select `ITLDIS-BACKEND` folder
   - Eclipse: File → Import → Existing Maven Projects

2. **Set Java 8:**
   - IntelliJ: File → Project Structure → Project → SDK: Java 1.8
   - Eclipse: Project → Properties → Java Build Path → Libraries → Java 1.8

3. **Run:**
   - Find `ItldisApplication.java`
   - Right-click → Run 'ItldisApplication.main()'

---

## Step-by-Step Instructions

### Step 1: Verify Java 8 Installation

```powershell
# Check Java version
java -version

# Should show: java version "1.8.0_XXX"
```

If Java 8 is not set, run:
```powershell
cd C:\MyProjectITLDIS\ITLDIS-BACKEND
.\SET-JAVA8.ps1
```

### Step 2: Configure Database Connection

Edit `src/main/resources/application.properties`:

```properties
# Update these values for your database
spring.datasource.url=jdbc:sqlserver://YOUR_SERVER:1433;instanceName=SQLEXPRESS;databaseName=itldis_uat_2
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Step 3: Build the Project (Optional)

```powershell
# Clean and compile
.\mvnw.cmd clean compile

# Or build WAR file
.\mvnw.cmd clean package
```

### Step 4: Run the Application

```powershell
# Run using Spring Boot Maven plugin
.\mvnw.cmd spring-boot:run
```

**Expected Output:**
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.3.3.RELEASE)

... (application logs) ...

Started ItldisApplication in X.XXX seconds
```

### Step 5: Verify Application is Running

- **Application URL:** http://localhost:8383
- **Health Check:** http://localhost:8383/actuator/health
- **Swagger UI:** http://localhost:8383/swagger-ui.html (if enabled)

---

## Running as WAR File (Production)

### Build WAR File

```powershell
.\mvnw.cmd clean package
```

This creates: `target/itldis-0.0.1-SNAPSHOT.war`

### Deploy to Tomcat

1. Copy WAR file to Tomcat `webapps` directory
2. Start Tomcat server
3. Access: http://localhost:8080/itldis-0.0.1-SNAPSHOT

---

## Configuration

### Application Properties

Key configuration in `src/main/resources/application.properties`:

```properties
# Server Port
server.port=8383

# Database Configuration
spring.datasource.url=jdbc:sqlserver://192.168.17.1:1433;...
spring.datasource.username=admin
spring.datasource.password=hrsl@2024#

# JPA Configuration
spring.jpa.show-sql=true
spring.hibernate.ddl-auto=none

# JWT Configuration
service.dms.jwtSecret=melayer
service.dms.jwtExpiration=180000000
```

### Environment-Specific Configuration

Create profile-specific files:
- `application-dev.properties` (Development)
- `application-prod.properties` (Production)

Run with profile:
```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev
```

---

## Troubleshooting

### Issue: Java Version Error

**Error:** `UnsupportedClassVersionError` or `java.lang.UnsupportedClassVersionError`

**Solution:**
```powershell
# Set Java 8
.\SET-JAVA8.ps1

# Verify
java -version
```

### Issue: Database Connection Error

**Error:** `Cannot connect to database` or `Connection refused`

**Solution:**
1. Verify SQL Server is running
2. Check database connection settings in `application.properties`
3. Verify network connectivity to database server
4. Check firewall settings

### Issue: Lombok Compilation Error

**Error:** `Cannot find symbol` for Lombok annotations

**Solution:**
1. Install Lombok plugin in your IDE
2. Enable annotation processing
3. See `LOMBOK-INSTALLATION-GUIDE.md` for details

### Issue: Port Already in Use

**Error:** `Port 8383 is already in use`

**Solution:**
1. Change port in `application.properties`:
   ```properties
   server.port=8384
   ```
2. Or stop the process using port 8383:
   ```powershell
   # Find process
   netstat -ano | findstr :8383
   
   # Kill process (replace PID)
   taskkill /PID <PID> /F
   ```

### Issue: SAP JCo Library Missing

**Error:** `ClassNotFoundException: com.sap.conn.jco.JCoDestination`

**Solution:**
1. Download SAP JCo from SAP Service Marketplace
2. Install to local Maven repository:
   ```powershell
   mvn install:install-file -Dfile=sapjco3.jar -DgroupId=com.sap -DartifactId=sapjco3 -Dversion=3.0.19 -Dpackaging=jar
   ```
3. Place native library (`sapjco3.dll`) in system PATH

---

## Common Commands

### Development

```powershell
# Run application
.\mvnw.cmd spring-boot:run

# Run with debug
.\mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"

# Clean and run
.\mvnw.cmd clean spring-boot:run
```

### Building

```powershell
# Compile only
.\mvnw.cmd compile

# Package (create WAR)
.\mvnw.cmd package

# Skip tests
.\mvnw.cmd package -DskipTests

# Clean build
.\mvnw.cmd clean package
```

### Testing

```powershell
# Run tests
.\mvnw.cmd test

# Run specific test
.\mvnw.cmd test -Dtest=ClassNameTest
```

---

## API Endpoints

Once running, access:

- **Base URL:** http://localhost:8383
- **API Base:** http://localhost:8383/api
- **Health Check:** http://localhost:8383/actuator/health
- **Swagger UI:** http://localhost:8383/swagger-ui.html
- **Actuator:** http://localhost:8383/actuator

### Example API Endpoints

- **Authentication:** `POST /api/auth/login`
- **Inventory:** `GET /api/inventory/list`
- **SAP Integration:** `POST /api/sap/sync/apn`
- **Reports:** `GET /api/reports/...`

---

## Development Tips

### Hot Reload (Spring Boot DevTools)

The project includes `spring-boot-devtools` for automatic restart:

1. Make changes to code
2. Save file
3. Application automatically restarts

### Logging

Logs are configured via `log4j.properties` or `application.properties`:

```properties
# Enable SQL logging
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Logging level
logging.level.com.i4o.dms.itldis=DEBUG
```

### Debug Mode

Run with debug enabled:
```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

Then attach debugger to `localhost:5005`

---

## Production Deployment

### Build Production WAR

```powershell
.\mvnw.cmd clean package -Pprod
```

### Deploy to Application Server

1. Copy `target/itldis-0.0.1-SNAPSHOT.war` to server
2. Configure production database
3. Set environment variables
4. Start application server

---

## Support

For issues or questions:
- Check `COMPILATION-FIXED.md` for compilation issues
- Check `LOMBOK-FIX.md` for Lombok issues
- Check `CRITICAL-FIX-REQUIRED.md` for critical fixes

---

**Happy Coding! 🚀**

