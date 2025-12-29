# How to Start the Services Service

## Prerequisites
- Java 17 or higher
- Maven 3.6+

## Steps to Start

1. Navigate to the services-service directory:
```bash
cd ModernApp/spring-boot-backend/services-service
```

2. Build and run the service:
```bash
mvn spring-boot:run
```

Or if you prefer to build first:
```bash
mvn clean install
java -jar target/services-service-1.0.0.jar
```

## Verify Service is Running

1. Check if the service started successfully - you should see:
   - "Started ServicesServiceApplication"
   - Port 8082 should be listening

2. Test the health endpoint:
```bash
curl http://localhost:8082/api/services/pdi/health
```
Should return: "PDI Service is running"

3. Test the pending chassis endpoint:
```bash
curl http://localhost:8082/api/services/pdi/pending-chassis?page=0&size=15
```

## Troubleshooting

### Port 8082 Already in Use
If port 8082 is already in use, either:
- Stop the other service using port 8082
- Change the port in `application.yml`:
```yaml
server:
  port: 8083  # or another available port
```

### Database Connection Issues
- The service uses H2 in-memory database
- Data is loaded from `data.sql` on startup
- Check the console logs for any SQL errors

### CORS Issues
- CORS is enabled for all origins (`@CrossOrigin(origins = "*")`)
- If you still have CORS issues, check the browser console

## Sample Data

The service automatically loads:
- 5 dealers (DLR001-DLR005)
- 15 vehicles with pending PDI status

You can test with:
- Chassis numbers: CHASSIS001, CHASSIS002, etc.
- Dealer codes: DLR001, DLR002, etc.

