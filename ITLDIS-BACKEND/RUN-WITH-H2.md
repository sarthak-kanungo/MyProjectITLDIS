# Run Application with H2 Database (Quick Fix)

## Problem

The application fails to start because it cannot connect to SQL Server database:
```
Cannot open database "itldis_uat_2" requested by the login. The login failed.
```

## Quick Solution: Use H2 In-Memory Database

If you don't have SQL Server available, you can run the application with H2 in-memory database for testing.

### Option 1: Use H2 Profile (Recommended)

Run with H2 profile:

```powershell
cd C:\MyProjectITLDIS\ITLDIS-BACKEND
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

**Note:** You need to create `application-h2.properties` file first (see below).

### Option 2: Update application.properties

1. **Backup current configuration:**
   ```powershell
   copy src\main\resources\application.properties src\main\resources\application-sqlserver.properties
   ```

2. **Edit `application.properties`** and replace SQL Server config with H2:

```properties
# Comment out SQL Server
#spring.datasource.url=jdbc:sqlserver://192.168.17.1:1433;instanceName=SQLEXPRESS;databaseName=itldis_uat_2
#spring.datasource.username=admin
#spring.datasource.password=hrsl@2024#

# Use H2 instead
spring.datasource.url=jdbc:h2:mem:itldisdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Enable H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Update Hibernate dialect
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.hibernate.ddl-auto=update
```

3. **Run application:**
   ```powershell
   mvn spring-boot:run
   ```

### Option 3: Fix SQL Server Connection

If you want to use SQL Server, update the connection details in `application.properties`:

```properties
# Update with correct values
spring.datasource.url=jdbc:sqlserver://YOUR_SERVER:1433;instanceName=SQLEXPRESS;databaseName=YOUR_DATABASE
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

## Access H2 Console

Once running with H2, access the H2 console at:
- **URL:** http://localhost:8383/h2-console
- **JDBC URL:** `jdbc:h2:mem:itldisdb`
- **Username:** `sa`
- **Password:** (leave empty)

## Important Notes

⚠️ **H2 is in-memory database:**
- Data is lost when application stops
- Suitable for testing/development only
- Not recommended for production

✅ **For Production:**
- Use SQL Server with correct connection details
- Ensure database server is accessible
- Verify credentials are correct

## Verify Application Started

After fixing, check:
- **Application URL:** http://localhost:8383
- **Health Check:** http://localhost:8383/actuator/health
- **Swagger UI:** http://localhost:8383/swagger-ui.html

---

**Quick Command:**
```powershell
# Switch to H2 and run
cd C:\MyProjectITLDIS\ITLDIS-BACKEND
# Edit application.properties to use H2 (see Option 2 above)
mvn spring-boot:run
```

