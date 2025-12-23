# Ubuntu: Install Java 8 and Tomcat 9.0.70 - Deployment Guide

This guide provides step-by-step instructions for installing Java 8 and Apache Tomcat 9.0.70 on Ubuntu, and deploying a WAR file.

---

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Install Java 8](#install-java-8)
3. [Install Tomcat 9.0.70](#install-tomcat-9070)
4. [Configure Tomcat](#configure-tomcat)
5. [Deploy WAR File](#deploy-war-file)
6. [Verify Deployment](#verify-deployment)
7. [Troubleshooting](#troubleshooting)

---

## Prerequisites

- Ubuntu 18.04 or later (tested on Ubuntu 20.04/22.04)
- Sudo/root access
- Internet connection

---

## Install Java 8

### Step 1: Update Package List

```bash
sudo apt update
```

### Step 2: Install Java 8 JDK

**Option A: Install OpenJDK 8 (Recommended)**

```bash
sudo apt install openjdk-8-jdk -y
```

**Option B: Install Oracle JDK 8 (Alternative)**

```bash
# Add Oracle JDK repository
sudo add-apt-repository ppa:linuxuprising/java -y
sudo apt update
sudo apt install oracle-java8-installer -y
```

### Step 3: Verify Java Installation

```bash
java -version
```

Expected output:
```
openjdk version "1.8.0_xxx"
OpenJDK Runtime Environment (build 1.8.0_xxx-xxx)
OpenJDK 64-Bit Server VM (build 25.xxx-bxx, mixed mode)
```

### Step 4: Set JAVA_HOME Environment Variable

```bash
# Find Java installation path
sudo update-alternatives --config java
# Note the path (e.g., /usr/lib/jvm/java-8-openjdk-amd64)

# Add to /etc/environment (system-wide)
sudo nano /etc/environment
```

Add these lines:
```
JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64"
PATH="$PATH:$JAVA_HOME/bin"
```

Or add to user profile (for current user only):
```bash
echo 'export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64' >> ~/.bashrc
echo 'export PATH=$PATH:$JAVA_HOME/bin' >> ~/.bashrc
source ~/.bashrc
```

### Step 5: Verify JAVA_HOME

```bash
echo $JAVA_HOME
java -version
```

---

## Install Tomcat 9.0.70

### Step 1: Create Tomcat User

```bash
sudo useradd -m -U -d /opt/tomcat -s /bin/false tomcat
```

### Step 2: Download Tomcat 9.0.70

```bash
cd /tmp
wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.70/bin/apache-tomcat-9.0.70.tar.gz
```

**Alternative download URL (if above fails):**
```bash
wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.70/bin/apache-tomcat-9.0.70.tar.gz
```

### Step 3: Extract Tomcat

```bash
sudo mkdir -p /opt/tomcat
sudo tar xzf apache-tomcat-9.0.70.tar.gz -C /opt/tomcat --strip-components=1
```

### Step 4: Set Permissions

```bash
sudo chown -R tomcat:tomcat /opt/tomcat
sudo chmod +x /opt/tomcat/bin/*.sh
```

### Step 5: Create Systemd Service (Optional but Recommended)

```bash
sudo nano /etc/systemd/system/tomcat.service
```

Add the following content:

```ini
[Unit]
Description=Apache Tomcat 9.0.70
After=network.target

[Service]
Type=forking

User=tomcat
Group=tomcat

Environment="JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64"
Environment="CATALINA_PID=/opt/tomcat/temp/tomcat.pid"
Environment="CATALINA_HOME=/opt/tomcat"
Environment="CATALINA_BASE=/opt/tomcat"
Environment="CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC"

ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/opt/tomcat/bin/shutdown.sh

RestartSec=10
Restart=always

[Install]
WantedBy=multi-user.target
```

**Note:** Update `JAVA_HOME` path if your Java installation is different.

### Step 6: Reload Systemd and Start Tomcat

```bash
sudo systemctl daemon-reload
sudo systemctl start tomcat
sudo systemctl enable tomcat
```

### Step 7: Verify Tomcat is Running

```bash
sudo systemctl status tomcat
```

Or check if port 8080 is listening:
```bash
sudo netstat -tlnp | grep 8080
```

### Step 8: Configure Firewall (if enabled)

```bash
sudo ufw allow 8080/tcp
sudo ufw reload
```

---

## Configure Tomcat

### Step 1: Set CATALINA_HOME Environment Variable

```bash
# Add to /etc/environment
sudo nano /etc/environment
```

Add:
```
CATALINA_HOME="/opt/tomcat"
```

Or add to user profile:
```bash
echo 'export CATALINA_HOME=/opt/tomcat' >> ~/.bashrc
source ~/.bashrc
```

### Step 2: Configure Tomcat Manager (Optional)

Edit `tomcat-users.xml`:
```bash
sudo nano /opt/tomcat/conf/tomcat-users.xml
```

Add before `</tomcat-users>`:
```xml
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<role rolename="manager-jmx"/>
<role rolename="admin-gui"/>
<user username="admin" password="your_secure_password" roles="manager-gui,manager-script,manager-jmx,admin-gui"/>
```

### Step 3: Allow Remote Access to Manager (if needed)

Edit `context.xml`:
```bash
sudo nano /opt/tomcat/webapps/manager/META-INF/context.xml
```

Comment out or remove the IP restriction:
```xml
<!--
<Valve className="org.apache.catalina.valves.RemoteAddrValve"
         allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1" />
-->
```

### Step 4: Restart Tomcat

```bash
sudo systemctl restart tomcat
```

---

## Deploy WAR File

### Method 1: Copy WAR to webapps Directory (Automatic Deployment)

```bash
# Stop Tomcat
sudo systemctl stop tomcat

# Remove old deployment (if exists)
sudo rm -rf /opt/tomcat/webapps/your-app-name
sudo rm -f /opt/tomcat/webapps/your-app-name.war

# Copy WAR file
sudo cp /path/to/your/application.war /opt/tomcat/webapps/

# Set proper permissions
sudo chown tomcat:tomcat /opt/tomcat/webapps/your-application.war

# Start Tomcat
sudo systemctl start tomcat

# Monitor deployment
sudo tail -f /opt/tomcat/logs/catalina.out
```

### Method 2: Using Tomcat Manager (Web Interface)

1. Access Tomcat Manager:
   ```
   http://your-server-ip:8080/manager/html
   ```

2. Login with credentials configured in `tomcat-users.xml`

3. Scroll to "WAR file to deploy" section

4. Click "Choose File" and select your WAR file

5. Click "Deploy"

### Method 3: Using Tomcat Manager Script (Command Line)

```bash
curl -u admin:your_password \
  -T /path/to/your/application.war \
  "http://localhost:8080/manager/text/deploy?path=/your-app-context&update=true"
```

### Method 4: Manual Deployment Script

Create a deployment script:

```bash
#!/bin/bash
# deploy-war.sh

WAR_FILE=$1
TOMCAT_HOME="/opt/tomcat"
APP_NAME=$(basename "$WAR_FILE" .war)

if [ -z "$WAR_FILE" ]; then
    echo "Usage: ./deploy-war.sh /path/to/application.war"
    exit 1
fi

if [ ! -f "$WAR_FILE" ]; then
    echo "Error: WAR file not found: $WAR_FILE"
    exit 1
fi

echo "Stopping Tomcat..."
sudo systemctl stop tomcat

echo "Removing old deployment..."
sudo rm -rf "$TOMCAT_HOME/webapps/$APP_NAME"
sudo rm -f "$TOMCAT_HOME/webapps/$APP_NAME.war"

echo "Copying WAR file..."
sudo cp "$WAR_FILE" "$TOMCAT_HOME/webapps/"
sudo chown tomcat:tomcat "$TOMCAT_HOME/webapps/$APP_NAME.war"

echo "Starting Tomcat..."
sudo systemctl start tomcat

echo "Waiting for deployment..."
sleep 10

echo "Deployment complete!"
echo "Application URL: http://localhost:8080/$APP_NAME"
echo "Check logs: sudo tail -f $TOMCAT_HOME/logs/catalina.out"
```

Make it executable:
```bash
chmod +x deploy-war.sh
```

Usage:
```bash
./deploy-war.sh /path/to/itldis.war
```

---

## Verify Deployment

### Step 1: Check Tomcat Status

```bash
sudo systemctl status tomcat
```

### Step 2: Check Application Logs

```bash
sudo tail -f /opt/tomcat/logs/catalina.out
```

Look for:
- `INFO: Deployment of web application archive [/opt/tomcat/webapps/your-app.war] has finished`
- No errors or exceptions

### Step 3: Check Application Directory

```bash
ls -la /opt/tomcat/webapps/
```

You should see:
- `your-app.war` (the WAR file)
- `your-app/` (extracted directory)

### Step 4: Access Application

Open browser:
```
http://your-server-ip:8080/your-app-context
```

Or test with curl:
```bash
curl http://localhost:8080/your-app-context
```

### Step 5: Check Tomcat Manager

```
http://your-server-ip:8080/manager/html
```

Your application should appear in the "Applications" list with status "Running".

---

## Troubleshooting

### Java Not Found

**Problem:** `java: command not found`

**Solution:**
```bash
# Verify Java is installed
dpkg -l | grep openjdk

# Reinstall if needed
sudo apt install --reinstall openjdk-8-jdk

# Verify JAVA_HOME
echo $JAVA_HOME
```

### Tomcat Won't Start

**Problem:** Tomcat service fails to start

**Solution:**
```bash
# Check service status
sudo systemctl status tomcat

# Check logs
sudo journalctl -u tomcat -n 50

# Check catalina.out
sudo tail -100 /opt/tomcat/logs/catalina.out

# Verify Java version
/opt/tomcat/bin/version.sh
```

### Port 8080 Already in Use

**Problem:** `Address already in use`

**Solution:**
```bash
# Find process using port 8080
sudo lsof -i :8080
# or
sudo netstat -tlnp | grep 8080

# Kill the process or change Tomcat port
sudo nano /opt/tomcat/conf/server.xml
# Change <Connector port="8080" ...> to <Connector port="8081" ...>
```

### Permission Denied

**Problem:** Permission errors when accessing files

**Solution:**
```bash
# Fix ownership
sudo chown -R tomcat:tomcat /opt/tomcat

# Fix permissions
sudo chmod +x /opt/tomcat/bin/*.sh
sudo chmod -R 755 /opt/tomcat
```

### WAR File Not Deploying

**Problem:** WAR file copied but not extracted

**Solution:**
```bash
# Check file permissions
ls -la /opt/tomcat/webapps/

# Check catalina.out for errors
sudo tail -f /opt/tomcat/logs/catalina.out

# Manually extract if needed
cd /opt/tomcat/webapps/
sudo unzip your-app.war -d your-app/
sudo chown -R tomcat:tomcat your-app/
```

### Application 404 Error

**Problem:** Application returns 404

**Solution:**
```bash
# Check if application directory exists
ls -la /opt/tomcat/webapps/

# Check context path in server.xml or application
# Verify WAR file was extracted correctly
# Check application logs in webapps/your-app/WEB-INF/logs/
```

### Out of Memory Errors

**Problem:** `java.lang.OutOfMemoryError`

**Solution:**
Edit Tomcat service file:
```bash
sudo nano /etc/systemd/system/tomcat.service
```

Update `CATALINA_OPTS`:
```ini
Environment="CATALINA_OPTS=-Xms1024M -Xmx2048M -server -XX:+UseParallelGC"
```

Reload and restart:
```bash
sudo systemctl daemon-reload
sudo systemctl restart tomcat
```

---

## Quick Reference Commands

```bash
# Start Tomcat
sudo systemctl start tomcat

# Stop Tomcat
sudo systemctl stop tomcat

# Restart Tomcat
sudo systemctl restart tomcat

# Check Status
sudo systemctl status tomcat

# View Logs
sudo tail -f /opt/tomcat/logs/catalina.out

# Check Java Version
java -version

# Check Tomcat Version
/opt/tomcat/bin/version.sh

# List Deployed Applications
ls -la /opt/tomcat/webapps/

# Undeploy Application
sudo rm -rf /opt/tomcat/webapps/your-app-name
sudo rm -f /opt/tomcat/webapps/your-app-name.war
```

---

## Additional Configuration

### Change Tomcat Port

Edit `server.xml`:
```bash
sudo nano /opt/tomcat/conf/server.xml
```

Find and change:
```xml
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" />
```

Change `port="8080"` to your desired port (e.g., `port="9090"`).

### Enable HTTPS

1. Generate SSL certificate (self-signed for testing):
```bash
sudo keytool -genkey -alias tomcat -keyalg RSA -keystore /opt/tomcat/conf/keystore
```

2. Edit `server.xml`:
```bash
sudo nano /opt/tomcat/conf/server.xml
```

Uncomment and configure:
```xml
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
           maxThreads="150" SSLEnabled="true">
    <SSLHostConfig>
        <Certificate certificateKeystoreFile="/opt/tomcat/conf/keystore"
                     certificateKeystorePassword="changeit"
                     type="RSA" />
    </SSLHostConfig>
</Connector>
```

---

## Summary

After completing this guide, you should have:

✅ Java 8 installed and configured  
✅ Tomcat 9.0.70 installed and running  
✅ WAR file deployed and accessible  
✅ Tomcat running as a systemd service  

**Default Access Points:**
- Tomcat: `http://your-server-ip:8080`
- Manager: `http://your-server-ip:8080/manager/html`
- Your Application: `http://your-server-ip:8080/your-app-context`

---

## Next Steps

- Configure reverse proxy (Nginx/Apache) if needed
- Set up SSL/TLS certificates for production
- Configure database connections
- Set up monitoring and logging
- Configure backup procedures

---

**For ITLDIS Project Specific Deployment:**

If deploying the ITLDIS project from this repository:

```bash
# Build WAR file
cd ITLDIS
mvn clean package -DskipTests

# Deploy using script
./deploy-tomcat.sh /opt/tomcat

# Or manually
sudo cp target/itldis.war /opt/tomcat/webapps/
sudo systemctl restart tomcat
```

Access at: `http://your-server-ip:8080/itldis`

