# How to Run ModernApp - Complete Guide

## 📋 Prerequisites

Before running the application, ensure you have:

- **Node.js** 18+ and **npm** (for Angular frontend)
- **Java** 17+ (for Spring Boot backend)
- **Maven** 3.6+ (for building Spring Boot services)

Verify installations:
```bash
node --version    # Should be 18+
npm --version
java -version     # Should be 17+
mvn --version     # Should be 3.6+
```

---

## 🏗️ Application Architecture

This is a **microservices architecture** with:

### Backend Services (3 microservices):
1. **auth-service** - Authentication & Authorization (Port: **8886**)
2. **services-service** - Services Module APIs (Port: **8082**)
3. **spares-service** - Spares Module APIs (Port: **8083**)

### Frontend:
- **Angular 18+** Application (Port: **4200**)

---

## 🚀 Quick Start (All Services)

### Option 1: Run All Services Manually (Recommended for Development)

#### Step 1: Start Backend Services

Open **3 separate terminal windows** and run each service:

**Terminal 1 - Auth Service:**
```bash
cd ModernApp/spring-boot-backend/auth-service
mvn spring-boot:run
```
✅ Auth service will start on: `http://localhost:8886`

**Terminal 2 - Services Service:**
```bash
cd ModernApp/spring-boot-backend/services-service
mvn spring-boot:run
```
✅ Services service will start on: `http://localhost:8082`

**Terminal 3 - Spares Service:**
```bash
cd ModernApp/spring-boot-backend/spares-service
mvn spring-boot:run
```
✅ Spares service will start on: `http://localhost:8083`

#### Step 2: Start Frontend

Open **Terminal 4** and run:

```bash
cd ModernApp/angular-frontend
npm install    # Only needed first time or after dependency changes
npm start
```

✅ Frontend will start on: `http://localhost:4200`

---

## 📝 Detailed Step-by-Step Instructions

### Backend Services Setup

#### 1. Auth Service (Authentication)

```bash
# Navigate to auth service directory
cd ModernApp/spring-boot-backend/auth-service

# Run the service
mvn spring-boot:run
```

**Expected Output:**
```
Started AuthServiceApplication in X.XXX seconds
```

**Service Details:**
- **URL**: `http://localhost:8886`
- **H2 Console**: `http://localhost:8886/h2-console`
- **JDBC URL**: `jdbc:h2:mem:authdb`
- **Username**: `sa`
- **Password**: (empty)

**API Endpoints:**
- `POST /api/auth/login` - User login
- `POST /api/auth/refresh` - Refresh JWT token

---

#### 2. Services Service

```bash
# Navigate to services service directory
cd ModernApp/spring-boot-backend/services-service

# Run the service
mvn spring-boot:run
```

**Expected Output:**
```
Started ServicesServiceApplication in X.XXX seconds
```

**Service Details:**
- **URL**: `http://localhost:8082`
- **H2 Console**: `http://localhost:8082/h2-console`
- **JDBC URL**: `jdbc:h2:mem:servicesdb`
- **Username**: `sa`
- **Password**: (empty)

---

#### 3. Spares Service

```bash
# Navigate to spares service directory
cd ModernApp/spring-boot-backend/spares-service

# Run the service
mvn spring-boot:run
```

**Expected Output:**
```
Started SparesServiceApplication in X.XXX seconds
```

**Service Details:**
- **URL**: `http://localhost:8083`
- **H2 Console**: `http://localhost:8083/h2-console`
- **JDBC URL**: `jdbc:h2:mem:sparesdb`
- **Username**: `sa`
- **Password**: (empty)

---

### Frontend Setup

#### 1. Install Dependencies (First Time Only)

```bash
# Navigate to frontend directory
cd ModernApp/angular-frontend

# Install all dependencies
npm install
```

**Note**: This may take a few minutes. Only needed:
- First time setup
- After adding new dependencies
- After pulling updates that change `package.json`

#### 2. Start Development Server

```bash
# Start Angular development server
npm start
```

**Or alternatively:**
```bash
ng serve
```

**Expected Output:**
```
✔ Browser application bundle generation complete.
** Angular Live Development Server is listening on localhost:4200 **
```

#### 3. Access the Application

Open your browser and navigate to:
**http://localhost:4200**

---

## 🔐 Default Login Credentials

- **Username**: `admin`
- **Password**: `password123`
- **Captcha**: Use Google reCAPTCHA test key (automatically handled)

---

## 🌐 Service URLs Summary

| Service | Port | URL | H2 Console |
|---------|------|-----|------------|
| **Auth Service** | 8886 | http://localhost:8886 | http://localhost:8886/h2-console |
| **Services Service** | 8082 | http://localhost:8082 | http://localhost:8082/h2-console |
| **Spares Service** | 8083 | http://localhost:8083 | http://localhost:8083/h2-console |
| **Frontend** | 4200 | http://localhost:4200 | - |

---

## 🔄 Running Order

**Recommended order:**
1. ✅ Start **Auth Service** first (authentication required)
2. ✅ Start **Services Service**
3. ✅ Start **Spares Service**
4. ✅ Start **Frontend** (Angular)

**Note**: Frontend can start before backend services, but login won't work until auth-service is running.

---

## 🛠️ Troubleshooting

### Port Already in Use

**Backend:**
- Check if port is already in use: `netstat -ano | findstr :8886` (Windows)
- Kill the process or change port in `application.yml`

**Frontend:**
```bash
ng serve --port 4201
```

### Maven Build Fails

```bash
# Clean and rebuild
mvn clean install
mvn spring-boot:run
```

### Node Modules Issues

```bash
# Remove and reinstall
cd ModernApp/angular-frontend
rm -rf node_modules package-lock.json
npm install
```

### Java Version Issues

- Ensure Java 17+ is installed
- Check `JAVA_HOME` environment variable
- See `spring-boot-backend/JAVA-INSTALLATION-GUIDE.md` for help

### CORS Errors

- Ensure all backend services are running
- Check CORS configuration in each service's `CorsConfig.java`
- Verify frontend is calling correct backend URLs

### Backend Not Starting

- Check Java version: `java -version` (must be 17+)
- Check Maven version: `mvn --version` (must be 3.6+)
- Check for port conflicts
- Review logs for specific errors

### Frontend Not Connecting to Backend

- Verify backend services are running
- Check browser console for errors
- Verify API endpoints in frontend services match backend URLs
- Check network tab in browser DevTools

---

## 📦 Building for Production

### Backend (JAR files)

```bash
# Build auth-service
cd ModernApp/spring-boot-backend/auth-service
mvn clean package

# Build services-service
cd ModernApp/spring-boot-backend/services-service
mvn clean package

# Build spares-service
cd ModernApp/spring-boot-backend/spares-service
mvn clean package
```

JAR files will be in `target/` directory.

### Frontend (Production Build)

```bash
cd ModernApp/angular-frontend
npm run build
```

Production build will be in `dist/` directory.

---

## 🧪 Testing the Application

### 1. Verify Backend Services

**Auth Service:**
```bash
curl http://localhost:8886/actuator/health
```

**Services Service:**
```bash
curl http://localhost:8082/actuator/health
```

**Spares Service:**
```bash
curl http://localhost:8083/actuator/health
```

### 2. Test Login API

```bash
curl -X POST http://localhost:8886/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123","captchaToken":"test"}'
```

### 3. Access Frontend

1. Open browser: `http://localhost:4200`
2. Enter login credentials
3. Navigate through dashboard features

---

## 📚 Additional Resources

- **Frontend Quick Start**: `angular-frontend/QUICK-START.md`
- **Backend README**: `spring-boot-backend/README.md`
- **Java Installation**: `spring-boot-backend/JAVA-INSTALLATION-GUIDE.md`
- **Set JAVA_HOME**: `spring-boot-backend/SET-JAVA-HOME.md`
- **Restart Backend**: `spring-boot-backend/auth-service/RESTART-BACKEND.md`

---

## 🎯 Quick Command Reference

### Start All Services (4 Terminals)

**Terminal 1:**
```bash
cd ModernApp/spring-boot-backend/auth-service && mvn spring-boot:run
```

**Terminal 2:**
```bash
cd ModernApp/spring-boot-backend/services-service && mvn spring-boot:run
```

**Terminal 3:**
```bash
cd ModernApp/spring-boot-backend/spares-service && mvn spring-boot:run
```

**Terminal 4:**
```bash
cd ModernApp/angular-frontend && npm start
```

---

## ✅ Verification Checklist

Before using the application, verify:

- [ ] Java 17+ installed and `JAVA_HOME` set
- [ ] Node.js 18+ and npm installed
- [ ] Maven 3.6+ installed
- [ ] Auth service running on port 8886
- [ ] Services service running on port 8082
- [ ] Spares service running on port 8083
- [ ] Frontend running on port 4200
- [ ] Can access `http://localhost:4200`
- [ ] Can login with default credentials
- [ ] Dashboard loads successfully

---

## 🆘 Need Help?

1. Check the troubleshooting section above
2. Review service logs in terminal windows
3. Check browser console for frontend errors
4. Verify all prerequisites are installed
5. Ensure all services are running on correct ports

---

**Happy Coding! 🚀**

