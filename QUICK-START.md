# Quick Start Guide - ITLDIS

## 🚀 Fastest Way to Run

### 1. Start Backend (Required First)

**Option A: With SQL Server Database (Production)**
```powershell
cd C:\MyProjectITLDIS\ITLDIS-BACKEND
mvn spring-boot:run
```

**Option B: With H2 In-Memory Database (Development/Testing)**
```powershell
cd C:\MyProjectITLDIS\ITLDIS-BACKEND
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
*Note: H2 database is reset on each restart. Use this for testing without SQL Server.*

**Wait for:** `Started ItldisApplication`
**URL:** `http://localhost:8383`
**Swagger:** `http://localhost:8383/swagger-ui.html`
**H2 Console (dev only):** `http://localhost:8383/h2-console`

### 2. Start Frontend (Main App)

```powershell
cd C:\MyProjectITLDIS\ITLDIS-MICROFRONTEND\MAIN-MICROAPP
npm install    # First time only
npm start
```

**URL:** `http://localhost:9000`

---

## 📋 Prerequisites Check

```powershell
java -version    # Need: 1.8.x
mvn -version     # Need: 3.6+
node -v          # Need: 12.x or 14.x
npm -v           # Need: 6.x+
```

---

## ⚙️ Configuration

### Backend Database
Edit: `ITLDIS-BACKEND\src\main\resources\application.properties`
```properties
spring.datasource.url=jdbc:sqlserver://YOUR_SERVER:1433;databaseName=itldis_uat_2
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Frontend API URL
Edit: `ITLDIS-MICROFRONTEND\MAIN-MICROAPP\src\environments\environment.ts`
```typescript
apiUrl: 'http://localhost:8383/api'
```

---

## 🎯 All Microapps Ports

| Microapp | Port | Command |
|----------|------|---------|
| MAIN-MICROAPP | 9000 | `cd MAIN-MICROAPP && npm start` |
| MASTERS-MICROAPP | 9005 | `cd MASTERS-MICROAPP && npm start` |
| SALES-PRESALES-MICROAPP | 9001 | `cd SALES-PRESALES-MICROAPP && npm start` |
| SERVICE-MICROAPP | 9002 | `cd SERVICE-MICROAPP && npm start` |
| SPARES-MICROAPP | 9003 | `cd SPARES-MICROAPP && npm start` |
| TRAINING-MICROAPP | 9004 | `cd TRAINING-MICROAPP && npm start` |
| WARRANTY-MICROAPP | 9006 | `cd WARRANTY-MICROAPP && npm start` |
| CRM-MICROAPP | 9007 | `cd CRM-MICROAPP && npm start` |

---

## ✅ Verify It's Working

1. **Backend:** Open `http://localhost:8383/swagger-ui.html`
2. **Frontend:** Open `http://localhost:9000`
3. **Health:** `http://localhost:8383/actuator/health`

---

## 🐛 Common Issues

**Port in use?**
```powershell
netstat -ano | findstr :8383
taskkill /PID <PID> /F
```

**npm install fails?**
```powershell
npm cache clean --force
Remove-Item -Recurse -Force node_modules, package-lock.json
npm install
```

**Database connection fails?**
- **Option 1:** Use dev profile: `mvn spring-boot:run -Dspring-boot.run.profiles=dev` (uses H2 in-memory DB)
- **Option 2:** Check SQL Server is running
- **Option 3:** Verify credentials in `application.properties`

---

For detailed instructions, see: **HOW-TO-RUN.md**
