# Fix 404 Error - Context Path Issue

## Problem

Getting a 404 error when accessing:
```
http://localhost:8080/ITLDIS/
```

**Error Message:**
```
HTTP Status 404 – Not Found
The requested resource [/ITLDIS/] is not available
```

## Root Cause

The WAR file is deployed as `itldis.war` (lowercase), which creates the context path `/itldis/` (lowercase). Tomcat is case-sensitive, so accessing `/ITLDIS/` (uppercase) results in a 404 error.

## Solution

### Option 1: Use Lowercase URL (Recommended)

Simply use the lowercase URL:
```
http://localhost:8080/itldis/
```

This is the correct context path based on the WAR file name.

### Option 2: Create Context Mapping (Both URLs Work)

Run the fix script to create a context mapping:

```batch
fix-deployment-context.bat
```

This script will:
1. Stop Tomcat (if running)
2. Clean up any conflicting deployments
3. Ensure the correct WAR is deployed
4. Create a context mapping file: `ITLDIS.xml`

After running the script and restarting Tomcat, both URLs will work:
- `http://localhost:8080/itldis/` (lowercase - original)
- `http://localhost:8080/ITLDIS/` (uppercase - mapped)

### Option 3: Rename WAR File

If you prefer uppercase, you can rename the WAR file:

```batch
copy target\itldis.war C:\apache-tomcat-9.0.100\webapps\ITLDIS.war
```

Then restart Tomcat. The context path will be `/ITLDIS/`.

## Verification

After applying the fix:

1. **Restart Tomcat:**
   ```batch
   start-camunda-tomcat.bat
   ```

2. **Test both URLs:**
   - `http://localhost:8080/itldis/` - Should work
   - `http://localhost:8080/ITLDIS/` - Should work (if mapping created)

3. **Check Status:**
   ```batch
   check-camunda-status.bat
   ```

## Context Mapping File

If you used Option 2, a context mapping file is created at:
```
C:\apache-tomcat-9.0.100\conf\Catalina\localhost\ITLDIS.xml
```

Content:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context docBase="${catalina.home}/webapps/itldis" />
```

The filename `ITLDIS.xml` creates the context path `/ITLDIS/`, and it points to the `itldis` application directory.

## Quick Fix Command

```batch
fix-deployment-context.bat
```

Then restart Tomcat.

---

**Last Updated:** 2025-12-16  
**Status:** ✅ Fixed

