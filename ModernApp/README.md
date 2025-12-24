# Modern App - Full Stack Application

A comprehensive full-stack application with Angular 18+ frontend and Spring Boot 3 microservices backend.

## Project Structure

```
ModernApp/
├── angular-frontend/      # Angular 18+ application
│   ├── src/
│   │   └── app/
│   │       ├── core/          # Core services, guards, interceptors
│   │       └── features/      # Feature modules
│   ├── package.json
│   └── README.md
└── spring-boot-backend/   # Spring Boot 3 microservices
    ├── auth-service/      # Authentication service
    │   ├── src/
    │   └── pom.xml
    ├── pom.xml
    └── README.md
```

## Features

### Frontend (Angular)
- ✅ Login screen with username, password, and captcha
- ✅ Dashboard with dynamic menus
- ✅ Dynamic reactive forms
- ✅ Reactive search box
- ✅ Reactive data table
- ✅ Modern UI with Material Design
- ✅ JWT authentication
- ✅ Route guards
- ✅ HTTP interceptors

### Backend (Spring Boot)
- ✅ Spring Boot 3.2.0
- ✅ JWT authentication
- ✅ Spring Security
- ✅ RESTful APIs
- ✅ H2 in-memory database
- ✅ CORS configuration
- ✅ Captcha validation

## Quick Start

### Prerequisites
- Node.js 18+ and npm
- Java 17+
- Maven 3.6+

### Running the Application

#### 1. Start Backend (Spring Boot)

```bash
cd spring-boot-backend/auth-service
mvn spring-boot:run
```

Backend will start on `http://localhost:8080`

#### 2. Start Frontend (Angular)

```bash
cd angular-frontend
npm install
npm start
```

Frontend will start on `http://localhost:4200`

### Default Credentials

- **Username**: `admin`
- **Password**: `password123`
- **Captcha**: Use Google reCAPTCHA test key (automatically handled)

## API Endpoints

### Authentication Service

- `POST /api/auth/login` - User login
- `POST /api/auth/refresh` - Refresh JWT token

## Development

### Frontend Development
See [angular-frontend/README.md](./angular-frontend/README.md) for detailed frontend documentation.

### Backend Development
See [spring-boot-backend/README.md](./spring-boot-backend/README.md) for detailed backend documentation.

## Technologies Used

### Frontend
- Angular 18+
- TypeScript
- RxJS
- Angular Material
- SCSS
- Signals API

### Backend
- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- JWT (jjwt)
- H2 Database
- Maven

## Architecture

### Frontend Architecture
- Standalone components
- Feature-based folder structure
- Core services for shared functionality
- Reactive programming with RxJS and Signals

### Backend Architecture
- Microservices architecture
- RESTful API design
- JWT-based authentication
- Service layer pattern
- Repository pattern

## Security

- JWT token-based authentication
- Password encryption with BCrypt
- CORS configuration
- Route guards
- HTTP interceptors

## Database

H2 in-memory database is used for development. Access H2 Console at:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:authdb`
- Username: `sa`
- Password: (empty)

## License

This project is for demonstration purposes.

