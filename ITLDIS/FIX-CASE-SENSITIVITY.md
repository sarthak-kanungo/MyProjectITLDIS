# Fix for Case Sensitivity Issue (/ITLDIS/ vs /itldis/)

## Issue

Accessing `/ITLDIS/` (uppercase) returns 404, while `/itldis/` (lowercase) works.

## Root Cause

Tomcat context paths are case-sensitive by default. The application is deployed as `itldis` (lowercase), but users may try to access it as `/ITLDIS/` (uppercase).

## Solution

Created a context configuration file to map uppercase `/ITLDIS` to lowercase `itldis`:

**File:** `C:\apache-tomcat-9.0.100\conf\Catalina\localhost\ITLDIS.xml`

**Content:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context docBase="itldis" />
```

## How It Works

- When you access `/ITLDIS/`, Tomcat checks the context file
- The context file maps `/ITLDIS` to the `itldis` deployed application
- Both URLs work: `/ITLDIS/` and `/itldis/`

## Verification

After Tomcat restart, both URLs should work:

✅ `http://localhost:8080/ITLDIS/` (uppercase)  
✅ `http://localhost:8080/itldis/` (lowercase)

## Important Notes

1. **Tomcat restart required:** The context file is read when Tomcat starts
2. **Case sensitivity:** The context file name `ITLDIS.xml` (uppercase) maps to `/ITLDIS/` URL
3. **docBase:** Points to `itldis` (lowercase) - the actual deployed application name

## Troubleshooting

If `/ITLDIS/` still returns 404:

1. **Verify context file exists:**
   ```powershell
   Test-Path "C:\apache-tomcat-9.0.100\conf\Catalina\localhost\ITLDIS.xml"
   ```

2. **Restart Tomcat:**
   ```batch
   cd C:\apache-tomcat-9.0.100\bin
   shutdown.bat
   # Wait 5 seconds
   startup.bat
   ```

3. **Wait for deployment:** Large WAR files take 2-3 minutes to deploy

4. **Use lowercase as fallback:** `http://localhost:8080/itldis/` always works

---

**Last Updated:** 2025-12-16  
**Status:** Fixed - Context file created, restart Tomcat to apply

