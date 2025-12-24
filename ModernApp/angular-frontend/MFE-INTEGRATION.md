# Micro Frontend (MFE) Integration Guide

## Overview

This project uses Module Federation to integrate Services and Spares Micro Frontends (MFEs) that communicate with their respective Spring Boot microservices.

## Architecture

```
┌─────────────────┐
│  Shell App      │
│  (Port 4200)    │
└────────┬────────┘
         │
         ├──► Services MFE (Port 4203) ──► Services Microservice (Port 8082)
         │
         └──► Spares MFE (Port 4204) ────► Spares Microservice (Port 8083)
```

## Flow

1. **User clicks "Services Module" link** in the dashboard
2. **Shell app loads Services MFE** using Module Federation from `http://localhost:4203/remoteEntry.js`
3. **Services MFE component loads** and calls `ServicesApiService`
4. **ServicesApiService makes HTTP calls** to `http://localhost:8082/api/services/*`
5. **Services Microservice** processes requests and returns data
6. **Services MFE displays** the data in the UI

Same flow applies for Spares Module → Spares MFE → Spares Microservice.

## Running the Application

### 1. Start Backend Microservices

```bash
# Terminal 1 - Services Service
cd ModernApp/spring-boot-backend/services-service
mvn spring-boot:run

# Terminal 2 - Spares Service  
cd ModernApp/spring-boot-backend/spares-service
mvn spring-boot:run
```

### 2. Start Micro Frontends

```bash
# Terminal 3 - Services MFE
cd ModernApp/angular-frontend
npm run serve:services

# Terminal 4 - Spares MFE
cd ModernApp/angular-frontend
npm run serve:spares
```

### 3. Start Shell Application

```bash
# Terminal 5 - Shell App
cd ModernApp/angular-frontend
npm start
```

## API Endpoints

### Services Microservice (Port 8082)
- `GET /api/services/job-cards` - Get all job cards
- `GET /api/services/job-cards/{id}` - Get job card by ID
- `POST /api/services/job-cards` - Create job card
- `PUT /api/services/job-cards/{id}/approve` - Approve job card
- `PUT /api/services/job-cards/{id}/close` - Close job card
- `GET /api/services/invoices` - Get all service invoices
- `POST /api/services/invoices` - Create invoice

### Spares Microservice (Port 8083)
- `GET /api/spares/invoices` - Get all spare invoices
- `GET /api/spares/invoices/{id}` - Get invoice by ID
- `POST /api/spares/invoices` - Create invoice
- `GET /api/spares/purchase-orders` - Get all purchase orders
- `POST /api/spares/purchase-orders` - Create purchase order
- `PUT /api/spares/purchase-orders/{id}/approve` - Approve purchase order

## Module Federation Configuration

### Services MFE
- **Remote Entry**: `http://localhost:4203/remoteEntry.js`
- **Exposed Module**: `./ServicesModule`
- **Component**: `ServicesModuleComponent`

### Spares MFE
- **Remote Entry**: `http://localhost:4204/remoteEntry.js`
- **Exposed Module**: `./SparesModule`
- **Component**: `SparesModuleComponent`

## Fallback Behavior

If the MFE fails to load (e.g., MFE server not running), the shell app falls back to loading the local component from `./features/dashboard/pages/services-module/` or `./features/dashboard/pages/spares-module/`.

## Development Workflow

1. **Develop MFE independently** - Each MFE can be developed and tested separately
2. **Test integration** - Start all services and test the full flow
3. **Deploy independently** - Each MFE and microservice can be deployed separately

## Troubleshooting

### MFE not loading
- Ensure the MFE dev server is running on the correct port
- Check browser console for Module Federation errors
- Verify `remoteEntry.js` is accessible at the expected URL

### API calls failing
- Verify the microservice is running
- Check CORS configuration in the microservice
- Verify the API URL in the service files matches the microservice port

### Data not displaying
- Check browser network tab for API calls
- Verify the microservice returns data in the expected format
- Check browser console for errors

