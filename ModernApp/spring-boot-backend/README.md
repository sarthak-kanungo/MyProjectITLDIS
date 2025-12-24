# Modern App Backend - Spring Boot Microservices

This project contains multiple Spring Boot microservices for the Modern App application.

## Services

1. **auth-service** (Port 8080)
   - Authentication and authorization
   - JWT token generation and validation
   - User management

2. **spares-service** (Port 8083)
   - Spare parts invoice management
   - Purchase order management
   - Inventory management

3. **services-service** (Port 8082)
   - Job card management
   - Service invoice management
   - Service scheduling

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Running the Services

### Run All Services

From the root directory (`spring-boot-backend`):

```bash
mvn clean install
```

### Run Individual Services

#### Auth Service
```bash
cd auth-service
mvn spring-boot:run
```

#### Spares Service
```bash
cd spares-service
mvn spring-boot:run
```

#### Services Service
```bash
cd services-service
mvn spring-boot:run
```

## API Endpoints

### Auth Service (Port 8080)
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Spares Service (Port 8083)
- `GET /api/spares/invoices` - Get all invoices
- `GET /api/spares/invoices/{id}` - Get invoice by ID
- `POST /api/spares/invoices` - Create invoice
- `PUT /api/spares/invoices/{id}` - Update invoice
- `PUT /api/spares/invoices/{id}/cancel` - Cancel invoice
- `GET /api/spares/purchase-orders` - Get all purchase orders
- `GET /api/spares/purchase-orders/{id}` - Get purchase order by ID
- `POST /api/spares/purchase-orders` - Create purchase order
- `PUT /api/spares/purchase-orders/{id}/approve` - Approve purchase order

### Services Service (Port 8082)
- `GET /api/services/job-cards` - Get all job cards
- `GET /api/services/job-cards/{id}` - Get job card by ID
- `POST /api/services/job-cards` - Create job card
- `PUT /api/services/job-cards/{id}/approve` - Approve job card
- `PUT /api/services/job-cards/{id}/close` - Close job card
- `GET /api/services/invoices` - Get all service invoices
- `GET /api/services/invoices/{id}` - Get invoice by ID
- `POST /api/services/invoices` - Create invoice
- `POST /api/services/invoices/generate/{jobCardNo}` - Generate invoice from job card

## Database

All services use H2 in-memory database for development:
- Auth Service: `jdbc:h2:mem:authdb`
- Spares Service: `jdbc:h2:mem:sparesdb`
- Services Service: `jdbc:h2:mem:servicesdb`

H2 Console is enabled for all services:
- Auth Service: http://localhost:8080/h2-console
- Spares Service: http://localhost:8083/h2-console
- Services Service: http://localhost:8082/h2-console

## Project Structure

```
spring-boot-backend/
├── auth-service/
│   ├── src/main/java/com/modernapp/auth/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── model/
│   │   └── security/
│   └── pom.xml
├── spares-service/
│   ├── src/main/java/com/modernapp/spares/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   └── model/
│   └── pom.xml
├── services-service/
│   ├── src/main/java/com/modernapp/services/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   └── model/
│   └── pom.xml
└── pom.xml
```

## Technology Stack

- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database (in-memory)
- Maven
- Java 17
