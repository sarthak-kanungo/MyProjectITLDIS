# Restart Backend to Apply Captcha Fix

## Quick Restart Instructions

1. **Stop the current backend** (if running):
   - Press `Ctrl+C` in the terminal where the backend is running

2. **Restart the backend**:
   ```bash
   cd ModernApp/spring-boot-backend/auth-service
   mvn spring-boot:run
   ```

3. **Wait for startup** - You should see:
   ```
   Started AuthServiceApplication in X.XXX seconds
   ```

4. **Verify backend is running**:
   - Check that it's listening on `http://localhost:8080`

## What Was Fixed

The backend now accepts `"custom-verified"` as a valid captcha response, allowing the custom math/string captcha challenges to work properly.

## Test Login

After restarting:
- Username: `admin`
- Password: `password123`
- Complete the custom captcha challenge
- Login should now succeed!

