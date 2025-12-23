# Ubuntu: Java 8 & Tomcat 9.0.70 - Quick Reference

## 🚀 Quick Installation (Automated)

```bash
# Download and run installation script
sudo ./install-java-tomcat-ubuntu.sh
```

## 📦 Manual Installation (Step by Step)

### Install Java 8
```bash
sudo apt update
sudo apt install openjdk-8-jdk -y
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
echo 'export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64' >> ~/.bashrc
```

### Install Tomcat 9.0.70
```bash
# Create user
sudo useradd -m -U -d /opt/tomcat -s /bin/false tomcat

# Download
cd /tmp
wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.70/bin/apache-tomcat-9.0.70.tar.gz

# Extract
sudo mkdir -p /opt/tomcat
sudo tar xzf apache-tomcat-9.0.70.tar.gz -C /opt/tomcat --strip-components=1
sudo chown -R tomcat:tomcat /opt/tomcat
sudo chmod +x /opt/tomcat/bin/*.sh
```

### Create Systemd Service
```bash
sudo nano /etc/systemd/system/tomcat.service
```

Paste:
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
ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/opt/tomcat/bin/shutdown.sh
RestartSec=10
Restart=always

[Install]
WantedBy=multi-user.target
```

```bash
sudo systemctl daemon-reload
sudo systemctl start tomcat
sudo systemctl enable tomcat
```

## 🎯 Deploy WAR File

### Method 1: Automated Script
```bash
sudo ./deploy-war-ubuntu.sh /path/to/your-app.war
```

### Method 2: Manual Copy
```bash
sudo systemctl stop tomcat
sudo cp /path/to/your-app.war /opt/tomcat/webapps/
sudo chown tomcat:tomcat /opt/tomcat/webapps/your-app.war
sudo systemctl start tomcat
```

### Method 3: Tomcat Manager
1. Access: `http://your-server:8080/manager/html`
2. Upload WAR file via web interface

## 📋 Common Commands

```bash
# Tomcat Service
sudo systemctl start tomcat
sudo systemctl stop tomcat
sudo systemctl restart tomcat
sudo systemctl status tomcat

# View Logs
sudo tail -f /opt/tomcat/logs/catalina.out
sudo journalctl -u tomcat -f

# Check Java
java -version
echo $JAVA_HOME

# Check Tomcat
/opt/tomcat/bin/version.sh
sudo netstat -tlnp | grep 8080

# List Deployed Apps
ls -la /opt/tomcat/webapps/
```

## 🔧 Troubleshooting

### Tomcat Won't Start
```bash
sudo journalctl -u tomcat -n 50
sudo tail -100 /opt/tomcat/logs/catalina.out
```

### Port Already in Use
```bash
sudo lsof -i :8080
# Kill process or change port in /opt/tomcat/conf/server.xml
```

### Permission Issues
```bash
sudo chown -R tomcat:tomcat /opt/tomcat
sudo chmod +x /opt/tomcat/bin/*.sh
```

## 🌐 Access URLs

- **Tomcat Home**: `http://your-server:8080`
- **Manager**: `http://your-server:8080/manager/html`
- **Your App**: `http://your-server:8080/your-app-context`

## 📝 Configuration Files

- **Tomcat Config**: `/opt/tomcat/conf/server.xml`
- **Users**: `/opt/tomcat/conf/tomcat-users.xml`
- **Logs**: `/opt/tomcat/logs/catalina.out`
- **Webapps**: `/opt/tomcat/webapps/`

## ✅ Verification Checklist

- [ ] Java 8 installed: `java -version`
- [ ] JAVA_HOME set: `echo $JAVA_HOME`
- [ ] Tomcat running: `sudo systemctl status tomcat`
- [ ] Port 8080 listening: `sudo netstat -tlnp | grep 8080`
- [ ] Can access: `curl http://localhost:8080`
- [ ] WAR deployed: `ls -la /opt/tomcat/webapps/`

---

**For detailed instructions, see:** `UBUNTU-TOMCAT-DEPLOYMENT-GUIDE.md`


