# How to Run ITLDIS-BACKEND and ITLDIS-MICROFRONTEND

## 📋 Prerequisites

### Required Software
1. **Java 1.8** (JDK 8)
2. **Maven 3.6+**
3. **Node.js 12.x or 14.x** (for Angular 8.2.14)
4. **npm 6.x** or **yarn**
5. **SQL Server** (with database `itldis_uat_2`)
6. **IDE** (IntelliJ IDEA, Eclipse, or VS Code)

### Verify Installation
```bash
java -version    # Should show 1.8.x
mvn -version     # Should show 3.6+
node -v          # Should show 12.x or 14.x
npm -v           # Should show 6.x+
```

---

## 🔧 Backend Setup (ITLDIS-BACKEND)

### Step 1: Configure Database

Edit `ITLDIS-BACKEND\src\main\resources\application.properties`:

```properties
# Local Database Configuration
spring.datasource.url=jdbc:sqlserver://YOUR_SERVER:1433;instanceName=SQLEXPRESS;databaseName=itldis_uat_2
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

# Server Port (default: 8383)
server.port=8383
```

**Current Configuration:**
- Server: `192.168.17.1:1433`
- Database: `itldis_uat_2`
- Username: `admin`
- Password: `hrsl@2024#`

### Step 2: Configure JWE Keystore Path

Update the keystore path in `application.properties`:
```properties
jwe.keystore.path=C:/ITLDIS-BACKEND/src/main/resources/jweKeyStore.jks
```

**Note:** Ensure the keystore file exists at the specified path, or update the path to your actual keystore location.

### Step 3: Build the Project

Open PowerShell/Command Prompt and navigate to the backend directory:

```powershell
cd C:\MyProjectITLDIS\ITLDIS-BACKEND
mvn clean install
```

**Expected Output:**
```
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

### Step 4: Run the Backend

#### Option A: Using Maven (Recommended for Development)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-BACKEND
mvn spring-boot:run
```

#### Option B: Using IDE
1. Open `ITLDIS-BACKEND` in your IDE
2. Locate `ItldisApplication.java` in `src\main\java\com\i4o\dms\itldis\`
3. Right-click → Run `ItldisApplication`

#### Option C: Run as WAR (Production)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-BACKEND
mvn clean package
java -jar target\itldis-0.0.1-SNAPSHOT.war
```

### Step 5: Verify Backend is Running

1. **Check Application Logs:**
   ```
   Started ItldisApplication in X.XXX seconds
   ```

2. **Access Swagger UI:**
   ```
   http://localhost:8383/swagger-ui.html
   ```

3. **Health Check:**
   ```
   http://localhost:8383/actuator/health
   ```

4. **API Base URL:**
   ```
   http://localhost:8383/api
   ```

### Backend Port Configuration
- **Default Port**: `8383`
- **Swagger UI**: `http://localhost:8383/swagger-ui.html`
- **Actuator**: `http://localhost:8383/actuator`

---

## 🎨 Frontend Setup (ITLDIS-MICROFRONTEND)

### Overview
The frontend consists of **8 microapps** that can run independently. Each microapp needs to be installed and started separately.

### Microapps and Ports

| Microapp | Port | Description |
|----------|------|-------------|
| MAIN-MICROAPP | 9000 | Main application shell |
| MASTERS-MICROAPP | 9005 | Master data management |
| SALES-PRESALES-MICROAPP | 9001 | Sales operations |
| SERVICE-MICROAPP | 9002 | Service operations |
| SPARES-MICROAPP | 9003 | Spares management |
| TRAINING-MICROAPP | 9004 | Training management |
| WARRANTY-MICROAPP | 9006 | Warranty operations |
| CRM-MICROAPP | 9007 | CRM operations |

### Step 1: Install Dependencies for Each Microapp

You need to install dependencies for each microapp. Run these commands:

```powershell
# MAIN-MICROAPP
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\MAIN-MICROAPP
npm install

# MASTERS-MICROAPP
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\MASTERS-MICROAPP
npm install

# SALES-PRESALES-MICROAPP
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\SALES-PRESALES-MICROAPP
npm install

# SERVICE-MICROAPP
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\SERVICE-MICROAPP
npm install

# SPARES-MICROAPP
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\SPARES-MICROAPP
npm install

# TRAINING-MICROAPP
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\TRAINING-MICROAPP
npm install

# WARRANTY-MICROAPP
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\WARRANTY-MICROAPP
npm install

# CRM-MICROAPP
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\CRM-MICROAPP
npm install
```

**Note:** This may take 10-15 minutes for all microapps. Each microapp has its own `node_modules` folder.

### Step 2: Configure API Base URL

For each microapp, update the environment files to point to your backend:

**File:** `src/environments/environment.ts`

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8383/api',  // Backend API URL
  // ... other configurations
};
```

**File:** `src/environments/environment.prod.ts`

```typescript
export const environment = {
  production: true,
  apiUrl: 'http://your-production-server:8383/api',
  // ... other configurations
};
```

### Step 3: Run Each Microapp

#### MAIN-MICROAPP (Port 9000)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\MAIN-MICROAPP
npm start
```
**Access:** `http://localhost:9000`

#### MASTERS-MICROAPP (Port 9005)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\MASTERS-MICROAPP
npm start
```
**Access:** `http://localhost:9005`

#### SALES-PRESALES-MICROAPP (Port 9001)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\SALES-PRESALES-MICROAPP
npm start
```
**Access:** `http://localhost:9001`

#### SERVICE-MICROAPP (Port 9002)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\SERVICE-MICROAPP
npm start
```
**Access:** `http://localhost:9002`

#### SPARES-MICROAPP (Port 9003)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\SPARES-MICROAPP
npm start
```
**Access:** `http://localhost:9003`

#### TRAINING-MICROAPP (Port 9004)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\TRAINING-MICROAPP
npm start
```
**Access:** `http://localhost:9004`

#### WARRANTY-MICROAPP (Port 9006)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\WARRANTY-MICROAPP
npm start
```
**Access:** `http://localhost:9006`

#### CRM-MICROAPP (Port 9007)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\CRM-MICROAPP
npm start
```
**Access:** `http://localhost:9007`

### Step 4: Run All Microapps Simultaneously (Optional)

You can use multiple terminal windows or a process manager like `concurrently`:

**Install concurrently globally:**
```powershell
npm install -g concurrently
```

**Create a script to run all microapps:**
Create `C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\start-all.ps1`:

```powershell
# Start all microapps
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\MAIN-MICROAPP; npm start"
Start-Sleep -Seconds 2
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\MASTERS-MICROAPP; npm start"
Start-Sleep -Seconds 2
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\SALES-PRESALES-MICROAPP; npm start"
# ... repeat for all microapps
```

---

## 🚀 Quick Start Guide

### Minimal Setup (Backend + Main Microapp)

1. **Start Backend:**
   ```powershell
   cd C:\MyProjectITLDIS\ITLDIS-BACKEND
   mvn spring-boot:run
   ```
   Wait for: `Started ItldisApplication`

2. **Start Main Microapp:**
   ```powershell
   cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\MAIN-MICROAPP
   npm install  # First time only
   npm start
   ```

3. **Access Application:**
   - Frontend: `http://localhost:9000`
   - Backend API: `http://localhost:8383/api`
   - Swagger: `http://localhost:8383/swagger-ui.html`

---

## 🔍 Verification Checklist

### Backend ✅
- [ ] Database connection successful
- [ ] Application starts without errors
- [ ] Swagger UI accessible at `http://localhost:8383/swagger-ui.html`
- [ ] Health check returns `{"status":"UP"}` at `http://localhost:8383/actuator/health`

### Frontend ✅
- [ ] Dependencies installed (`node_modules` exists)
- [ ] Microapp starts without errors
- [ ] Browser opens automatically or accessible at configured port
- [ ] No console errors in browser DevTools
- [ ] API calls to backend are successful

---

## 🐛 Troubleshooting

### Backend Issues

#### 1. Database Connection Failed
**Error:** `Cannot create PoolableConnectionFactory`
**Solution:**
- Verify SQL Server is running
- Check database name, username, password in `application.properties`
- Ensure SQL Server allows TCP/IP connections
- Check firewall settings

#### 2. Port Already in Use
**Error:** `Port 8383 is already in use`
**Solution:**
```powershell
# Find process using port 8383
netstat -ano | findstr :8383
# Kill the process (replace PID)
taskkill /PID <PID> /F
# Or change port in application.properties
server.port=8384
```

#### 3. Maven Build Fails
**Error:** `BUILD FAILURE`
**Solution:**
```powershell
# Clean and rebuild
mvn clean install -U
# Or skip tests
mvn clean install -DskipTests
```

#### 4. JWE Keystore Not Found
**Error:** `FileNotFoundException: jweKeyStore.jks`
**Solution:**
- Create the keystore file or update the path in `application.properties`
- Or comment out JWE-related properties if not needed

### Frontend Issues

#### 1. npm install Fails
**Error:** `npm ERR!`
**Solution:**
```powershell
# Clear npm cache
npm cache clean --force
# Delete node_modules and package-lock.json
Remove-Item -Recurse -Force node_modules, package-lock.json
# Reinstall
npm install
```

#### 2. Port Already in Use
**Error:** `Port 9000 is already in use`
**Solution:**
```powershell
# Find and kill process
netstat -ano | findstr :9000
taskkill /PID <PID> /F
# Or change port in angular.json
"port": 9010
```

#### 3. Angular CLI Not Found
**Error:** `ng: command not found`
**Solution:**
```powershell
# Install Angular CLI globally
npm install -g @angular/cli@8.3.29
```

#### 4. CORS Errors
**Error:** `Access-Control-Allow-Origin`
**Solution:**
- Backend already has `@CrossOrigin` annotations
- Verify backend is running
- Check API URL in environment files

#### 5. Module Not Found
**Error:** `Cannot find module`
**Solution:**
```powershell
# Reinstall dependencies
npm install
# Or install specific package
npm install <package-name>
```

---

## 📝 Development Workflow

### Daily Development

1. **Start Backend:**
   ```powershell
   cd C:\MyProjectITLDIS\ITLDIS-BACKEND
   mvn spring-boot:run
   ```

2. **Start Required Microapps:**
   ```powershell
   # For example, if working on Masters module
   cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\MASTERS-MICROAPP
   npm start
   ```

3. **Make Changes:**
   - Backend: Changes auto-reload (if using Spring Boot DevTools)
   - Frontend: Changes auto-reload via Angular CLI

4. **Test:**
   - Backend: Use Swagger UI or Postman
   - Frontend: Test in browser

### Building for Production

#### Backend (WAR File)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-BACKEND
mvn clean package
# WAR file: target\itldis-0.0.1-SNAPSHOT.war
```

#### Frontend (Production Build)
```powershell
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\MAIN-MICROAPP
npm run build
# Output: dist\browser\
```

---

## 🔐 Security Notes

1. **Database Credentials:** Never commit `application.properties` with real credentials to version control
2. **JWT Secret:** Change `service.dms.jwtSecret` in production
3. **CORS:** Configure allowed origins in production
4. **HTTPS:** Use SSL certificates in production

---

## 📚 Additional Resources

- **Backend API Docs:** `http://localhost:8383/swagger-ui.html`
- **Spring Boot Docs:** https://spring.io/projects/spring-boot
- **Angular Docs:** https://angular.io/docs
- **Project Documentation:** See `ITLDIS-ALL-SERVICES-AVAILABLE.md`

---

## ✅ Summary

### Backend
- **Location:** `C:\MyProjectITLDIS\ITLDIS-BACKEND`
- **Port:** `8383`
- **Run:** `mvn spring-boot:run`
- **URL:** `http://localhost:8383`

### Frontend
- **Location:** `C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND`
- **Main Port:** `9000`
- **Run:** `npm start` (in each microapp directory)
- **URL:** `http://localhost:9000` (for MAIN-MICROAPP)

**All services are available and ready to run!** 🎉
