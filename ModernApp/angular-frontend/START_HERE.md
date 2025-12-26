# 🚀 How to Start Angular Frontend

## Quick Start (3 Steps)

### Step 1: Navigate to Frontend Directory

```bash
cd ModernApp/angular-frontend
```

### Step 2: Install Dependencies (First Time Only)

```bash
npm install
```

**Note:** Only needed:
- First time setup
- After pulling new changes that update `package.json`
- After adding new dependencies

**Time:** This may take 2-5 minutes depending on your internet connection.

### Step 3: Start Development Server

```bash
npm start
```

**Or alternatively:**
```bash
ng serve
```

---

## ✅ Expected Output

When the server starts successfully, you'll see:

```
✔ Browser application bundle generation complete.
** Angular Live Development Server is listening on localhost:4200 **
✔ Compiled successfully.
```

---

## 🌐 Access the Application

Open your browser and navigate to:

**http://localhost:4200**

The application will automatically reload when you make changes to source files (Hot Module Replacement).

---

## 📋 Prerequisites Check

Before starting, verify you have:

```bash
node --version    # Should be 18 or higher
npm --version     # Should be 6 or higher
```

If Node.js is not installed:
- Download from: https://nodejs.org/
- Install Node.js 18+ (includes npm)

---

## 🔧 Available Scripts

| Command | Description |
|---------|-------------|
| `npm start` | Start development server (port 4200) |
| `ng serve` | Same as `npm start` |
| `npm run build` | Build for production |
| `npm test` | Run unit tests |
| `npm run watch` | Build and watch for changes |

### Micro Frontend Scripts (Advanced)

| Command | Description |
|---------|-------------|
| `npm run serve:mfe` | Serve shell MFE on port 4200 |
| `npm run serve:services` | Serve services MFE on port 4203 |
| `npm run serve:spares` | Serve spares MFE on port 4204 |

---

## 🔌 Backend Connection

**Important:** The Angular app needs backend services to be running for full functionality.

### Required Backend Services:

1. **Auth Service** (Port 8886) - Required for login
2. **Services Service** (Port 8082) - For services module
3. **Spares Service** (Port 8083) - For spares module

### Start Backend Services:

**Terminal 1 - Auth Service:**
```bash
cd ModernApp/spring-boot-backend/auth-service
mvn spring-boot:run
```

**Terminal 2 - Services Service:**
```bash
cd ModernApp/spring-boot-backend/services-service
mvn spring-boot:run
```

**Terminal 3 - Spares Service:**
```bash
cd ModernApp/spring-boot-backend/spares-service
mvn spring-boot:run
```

**Note:** Frontend can start without backend, but login and API features won't work.

---

## 🐛 Troubleshooting

### Port 4200 Already in Use

**Solution:** Use a different port:

```bash
ng serve --port 4201
```

Or kill the process using port 4200:
```bash
# Windows
netstat -ano | findstr :4200
taskkill /PID <PID> /F

# Mac/Linux
lsof -ti:4200 | xargs kill
```

### Module Not Found Errors

**Solution:** Clean install dependencies:

```bash
# Remove node_modules and lock file
rm -rf node_modules package-lock.json

# Reinstall
npm install
```

### Angular CLI Not Found

**Solution:** Install Angular CLI globally:

```bash
npm install -g @angular/cli
```

### Build Errors

**Solution:** Clear Angular cache:

```bash
# Remove .angular folder
rm -rf .angular

# Try again
npm start
```

### Slow Startup

**Solution:** 
- Close other applications
- Check available disk space
- Restart your computer
- Use `npm ci` instead of `npm install` for faster installs

---

## 🔐 Default Login Credentials

Once the app is running and backend is started:

- **Username:** `admin`
- **Password:** `password123`
- **Captcha:** Automatically handled (test mode)

---

## 📱 Development Features

- ✅ **Hot Module Replacement (HMR)** - Changes reflect immediately
- ✅ **Source Maps** - Easy debugging in browser DevTools
- ✅ **Live Reload** - Browser refreshes on file changes
- ✅ **TypeScript Compilation** - Real-time type checking

---

## 🎯 Next Steps After Starting

1. ✅ Verify frontend is running at `http://localhost:4200`
2. ✅ Start backend services (see Backend Connection section)
3. ✅ Open browser and navigate to login page
4. ✅ Enter credentials and explore dashboard features:
   - Dynamic Forms
   - Reactive Search
   - Reactive Data Table
   - Services Module
   - Spares Module

---

## 📚 Additional Resources

- **Quick Start Guide**: `QUICK-START.md`
- **Full README**: `README.md`
- **MFE Integration**: `MFE-INTEGRATION.md`
- **Main App Guide**: `../HOW_TO_RUN.md`

---

## ⚡ Quick Command Reference

```bash
# Navigate
cd ModernApp/angular-frontend

# Install (first time)
npm install

# Start
npm start

# Access
# http://localhost:4200
```

---

**Happy Coding! 🚀**

