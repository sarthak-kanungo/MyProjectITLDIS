# Database Connection Fix

## Error Encountered

```
com.microsoft.sqlserver.jdbc.SQLServerException: Cannot open database "itldis_uat_2" requested by the login. The login failed.
```

## Problem

The application cannot connect to the SQL Server database. This can happen due to:
1. Database server not accessible at `192.168.17.1:1433`
2. Incorrect username/password
3. Database `itldis_uat_2` doesn't exist
4. Network/firewall blocking connection
5. SQL Server not running

## Solutions

### Option 1: Update Database Connection (Recommended for Production)

Edit `src/main/resources/application.properties` and update:

```properties
# Update with your actual database details
spring.datasource.url=jdbc:sqlserver://YOUR_SERVER:1433;instanceName=SQLEXPRESS;databaseName=YOUR_DATABASE
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Option 2: Use H2 In-Memory Database (For Testing/Development)

If you don't have SQL Server available, you can use H2 in-memory database:

1. **Edit `application.properties`:**

```properties
# Comment out SQL Server config
#spring.datasource.url=jdbc:sqlserver://192.168.17.1:1433;...
#spring.datasource.username=admin
#spring.datasource.password=hrsl@2024#

# Use H2 instead
spring.datasource.url=jdbc:h2:mem:itldisdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# H2 Console (optional - for viewing data)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Update Hibernate dialect
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.dialect=org.hibernate.dialect.H2Dialect
```

2. **H2 is already in pom.xml** - no additional dependency needed

3. **Access H2 Console:** http://localhost:8383/h2-console
   - JDBC URL: `jdbc:h2:mem:itldisdb`
   - Username: `sa`
   - Password: (leave empty)

### Option 3: Make Database Connection Optional (Advanced)

You can configure Spring Boot to start even if database connection fails by adding:

```properties
# Allow application to start even if database is not available
spring.datasource.continue-on-error=true
```

**Note:** This may cause issues with JPA repositories that require database access.

## Verification Steps

### Check SQL Server Connection

```powershell
# Test SQL Server connectivity
Test-NetConnection -ComputerName 192.168.17.1 -Port 1433
```

### Verify Database Exists

Connect to SQL Server using SQL Server Management Studio or command line and verify:
- Database `itldis_uat_2` exists
- User `admin` has access
- Password is correct

### Check SQL Server Service

```powershell
# Check if SQL Server service is running
Get-Service | Where-Object {$_.Name -like "*SQL*"}
```

## Quick Fix for Testing

If you just want to test the application startup:

1. Use H2 database (Option 2 above)
2. Or update database connection to point to an available database
3. Or start SQL Server and ensure it's accessible

## After Fixing

Restart the application:

```powershell
cd C:\MyProjectITLDIS\ITLDIS-BACKEND
mvn spring-boot:run
```

The application should start successfully once the database connection is resolved.

