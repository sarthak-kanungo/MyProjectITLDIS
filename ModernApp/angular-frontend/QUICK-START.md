# Quick Start Guide - Angular Frontend

## Prerequisites

Before running the application, ensure you have:
- **Node.js** 18 or higher installed
- **npm** (comes with Node.js) or **yarn**

Check your versions:
```bash
node --version
npm --version
```

## Step-by-Step Instructions

### 1. Navigate to the Angular Frontend Directory

```bash
cd ModernApp/angular-frontend
```

### 2. Install Dependencies

First time setup - install all required packages:
```bash
npm install
```

This will install all dependencies listed in `package.json` including:
- Angular 18+
- Angular Material
- RxJS
- ngx-captcha
- And other required packages

**Note:** This may take a few minutes depending on your internet connection.

### 3. Start the Development Server

Run the application:
```bash
npm start
```

Or alternatively:
```bash
ng serve
```

### 4. Access the Application

Once the server starts, you'll see output like:
```
✔ Browser application bundle generation complete.
** Angular Live Development Server is listening on localhost:4200 **
```

Open your browser and navigate to:
**http://localhost:4200**

The application will automatically reload when you make changes to the source files.

## Available Scripts

- `npm start` - Start the development server (default port 4200)
- `npm run build` - Build the application for production
- `npm test` - Run unit tests
- `npm run watch` - Build and watch for changes

## Backend Connection

**Important:** The Angular app connects to the Spring Boot backend at `http://localhost:8080/api/auth`

Make sure the backend is running before testing the login functionality:

```bash
# In a separate terminal, navigate to backend
cd ModernApp/spring-boot-backend/auth-service
mvn spring-boot:run
```

## Troubleshooting

### Port Already in Use
If port 4200 is already in use, you can specify a different port:
```bash
ng serve --port 4201
```

### Module Not Found Errors
If you see module errors, try:
```bash
rm -rf node_modules package-lock.json
npm install
```

### Angular CLI Not Found
If `ng` command is not found:
```bash
npm install -g @angular/cli
```

## Default Login Credentials

- **Username:** `admin`
- **Password:** `password123`
- **Captcha:** Use the Google reCAPTCHA test key (automatically handled)

## Development Tips

1. The app uses **Hot Module Replacement (HMR)** - changes reflect immediately
2. Check the browser console for any errors
3. Use Angular DevTools browser extension for debugging
4. The app runs in development mode with source maps enabled

## Next Steps

After the app is running:
1. Navigate to the login page
2. Enter credentials and complete captcha
3. Explore the dashboard features:
   - Dynamic Forms
   - Reactive Search
   - Reactive Data Table

