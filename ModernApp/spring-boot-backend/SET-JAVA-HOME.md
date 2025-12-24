# Set JAVA_HOME for Maven

## Issue
Maven is using Java 8, but Spring Boot 3 requires Java 17.

## Solution

### Option 1: Set JAVA_HOME Environment Variable (Recommended)

1. **Find Java 17 Installation:**
   - Usually at: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot`
   - Or: `C:\Program Files\Java\jdk-17`

2. **Set JAVA_HOME:**
   - Open System Properties → Environment Variables
   - Under "System variables", find or create `JAVA_HOME`
   - Set value to Java 17 path (e.g., `C:\Program Files\Eclipse Adoptium\jdk-17.0.12-hotspot`)
   - Update `PATH` to include: `%JAVA_HOME%\bin` (should be first in PATH)

3. **Restart PowerShell/Terminal**

4. **Verify:**
   ```bash
   java -version
   mvn -version
   ```
   Both should show Java 17.

### Option 2: Set JAVA_HOME in Current Session (Temporary)

```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.0.12-hotspot"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
```

Then verify:
```bash
java -version
mvn -version
```

### Option 3: Use Maven Toolchains (Advanced)

Create `~/.m2/toolchains.xml` with Java 17 configuration.

## After Setting JAVA_HOME

1. **Restart your terminal/PowerShell**
2. **Verify Java version:**
   ```bash
   java -version
   mvn -version
   ```
3. **Build and run:**
   ```bash
   cd ModernApp\spring-boot-backend\auth-service
   mvn clean install
   mvn spring-boot:run
   ```

