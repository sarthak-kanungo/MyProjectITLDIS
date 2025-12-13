# ITLDIS Project - KUBOTA Way

This project follows the KUBOTA architecture pattern with a microservices-based structure.

## Project Structure

```
ITLDIS-BACKEND/
├── src/
│   ├── main/
│   │   ├── java/com/i4o/dms/itldis/
│   │   │   └── ItldisApplication.java
│   │   ├── resources/
│   │   └── webapp/WEB-INF/
│   └── test/java/
├── pom.xml
└── .gitignore

ITLDIS-MICROFRONTEND/
├── CRM-MICROAPP/
├── MAIN-MICROAPP/
├── MASTERS-MICROAPP/
├── SALES-PRESALES-MICROAPP/
├── SERVICE-MICROAPP/
├── SPARES-MICROAPP/
├── TRAINING-MICROAPP/
└── WARRANTY-MICROAPP/
```

## Backend (ITLDIS-BACKEND)

- **Technology**: Spring Boot 2.3.3.RELEASE
- **Java Version**: 1.8
- **Packaging**: WAR
- **Main Application**: `com.i4o.dms.itldis.ItldisApplication`

### Features
- Spring Boot Web
- Spring Data JPA
- Spring Security
- JWT Authentication
- Swagger/OpenAPI Documentation
- Jasper Reports
- Email Support
- File Upload Support

## Frontend (ITLDIS-MICROFRONTEND)

- **Technology**: Angular 8.2.14
- **Architecture**: Microfrontend with multiple microapps
- **Microapps**: 
  - CRM-MICROAPP
  - MAIN-MICROAPP (port 9000)
  - MASTERS-MICROAPP (port 9005)
  - SALES-PRESALES-MICROAPP
  - SERVICE-MICROAPP
  - SPARES-MICROAPP
  - TRAINING-MICROAPP
  - WARRANTY-MICROAPP

### Features
- Angular Material UI
- Bootstrap 4
- Server-Side Rendering (SSR) support
- Toastr notifications
- RxJS for reactive programming

## Getting Started

### Backend Setup

1. Navigate to `ITLDIS-BACKEND`
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Frontend Setup

1. Navigate to `ITLDIS-MICROFRONTEND/MAIN-MICROAPP`
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm start
   ```
   The app will be available at `http://localhost:9000`

## Architecture Pattern

This project follows the **KUBOTA Way** architecture:

1. **Backend**: Spring Boot REST API with WAR packaging for deployment flexibility
2. **Frontend**: Angular microfrontend architecture allowing independent deployment of microapps
3. **Separation of Concerns**: Clear separation between backend services and frontend applications
4. **Scalability**: Microfrontend pattern allows teams to work independently on different microapps

## Project Status

✅ **Structure Complete**: The project now has the exact same design/structure as KUBOTA:
- All 20+ backend packages copied and renamed (kubota → itldis)
- All 8 microfrontend apps copied and renamed
- Package names updated throughout
- Configuration files updated

⚠️ **Next Steps**:
1. **Configure Database**: Update `application.properties` with ITLDIS database connection
2. **Adapt Business Logic**: Modify the workflow to match ITLDIS business requirements (structure is identical to KUBOTA)
3. **Install Dependencies**: Run `npm install` in each microapp
4. **Build & Test**: Build backend with `mvn clean install` and test each microapp

## Important Notes

- **Design**: ✅ Identical to KUBOTA (same packages, modules, structure)
- **Workflow**: ⚠️ Needs adaptation to match ITLDIS business logic
- All references have been updated from "kubota" to "itldis" in:
  - Java package names
  - Angular project names
  - TypeScript/HTML files
  - Configuration files

See `ITLDIS-SETUP-STATUS.md` for detailed setup status and remaining tasks.
