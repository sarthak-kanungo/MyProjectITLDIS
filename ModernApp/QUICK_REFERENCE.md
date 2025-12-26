# 🚀 Quick Reference - ModernApp

## Service Ports

| Service | Port | URL |
|---------|------|-----|
| **Auth Service** | 8886 | http://localhost:8886 |
| **Services Service** | 8082 | http://localhost:8082 |
| **Spares Service** | 8083 | http://localhost:8083 |
| **Frontend** | 4200 | http://localhost:4200 |

## Quick Commands

### Start All Services (4 Terminals)

**Terminal 1 - Auth:**
```bash
cd ModernApp/spring-boot-backend/auth-service && mvn spring-boot:run
```

**Terminal 2 - Services:**
```bash
cd ModernApp/spring-boot-backend/services-service && mvn spring-boot:run
```

**Terminal 3 - Spares:**
```bash
cd ModernApp/spring-boot-backend/spares-service && mvn spring-boot:run
```

**Terminal 4 - Frontend:**
```bash
cd ModernApp/angular-frontend && npm start
```

## Login Credentials

- **Username**: `admin`
- **Password**: `password123`

## H2 Database Console

- **Auth**: http://localhost:8886/h2-console
- **Services**: http://localhost:8082/h2-console
- **Spares**: http://localhost:8083/h2-console

**JDBC URL**: `jdbc:h2:mem:authdb` (or `servicesdb` / `sparesdb`)  
**Username**: `sa`  
**Password**: (empty)

## Prerequisites

- ✅ Node.js 18+
- ✅ Java 17+
- ✅ Maven 3.6+

## Full Documentation

📖 See [HOW_TO_RUN.md](./HOW_TO_RUN.md) for complete instructions

