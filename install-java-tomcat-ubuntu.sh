#!/bin/bash

# Ubuntu: Automated Installation Script for Java 8 and Tomcat 9.0.70
# Usage: sudo ./install-java-tomcat-ubuntu.sh

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# Check if running as root
if [ "$EUID" -ne 0 ]; then 
    echo -e "${RED}Please run as root or with sudo${NC}"
    exit 1
fi

echo -e "${CYAN}========================================${NC}"
echo -e "${CYAN}Java 8 & Tomcat 9.0.70 Installation${NC}"
echo -e "${CYAN}========================================${NC}"
echo ""

# Variables
JAVA_VERSION="8"
TOMCAT_VERSION="9.0.70"
TOMCAT_HOME="/opt/tomcat"
TOMCAT_USER="tomcat"
JAVA_HOME_PATH=""

# Step 1: Update package list
echo -e "${YELLOW}[1/6] Updating package list...${NC}"
apt update -y
echo -e "${GREEN}✓ Package list updated${NC}"
echo ""

# Step 2: Install Java 8
echo -e "${YELLOW}[2/6] Installing Java 8 JDK...${NC}"
apt install openjdk-8-jdk -y

# Find Java installation path
JAVA_HOME_PATH=$(readlink -f /usr/bin/java | sed "s:bin/java::")
if [ -z "$JAVA_HOME_PATH" ]; then
    JAVA_HOME_PATH="/usr/lib/jvm/java-8-openjdk-amd64"
fi

# Verify Java installation
if ! command -v java &> /dev/null; then
    echo -e "${RED}✗ Java installation failed${NC}"
    exit 1
fi

JAVA_VER=$(java -version 2>&1 | head -n 1)
echo -e "${GREEN}✓ Java installed: $JAVA_VER${NC}"
echo ""

# Step 3: Set JAVA_HOME
echo -e "${YELLOW}[3/6] Configuring JAVA_HOME...${NC}"
if ! grep -q "JAVA_HOME" /etc/environment; then
    echo "JAVA_HOME=\"$JAVA_HOME_PATH\"" >> /etc/environment
    echo "PATH=\"\$PATH:\$JAVA_HOME/bin\"" >> /etc/environment
fi

export JAVA_HOME="$JAVA_HOME_PATH"
export PATH="$PATH:$JAVA_HOME/bin"

echo -e "${GREEN}✓ JAVA_HOME set to: $JAVA_HOME${NC}"
echo ""

# Step 4: Create Tomcat user
echo -e "${YELLOW}[4/6] Creating Tomcat user...${NC}"
if ! id "$TOMCAT_USER" &>/dev/null; then
    useradd -m -U -d "$TOMCAT_HOME" -s /bin/false "$TOMCAT_USER"
    echo -e "${GREEN}✓ Tomcat user created${NC}"
else
    echo -e "${YELLOW}⚠ Tomcat user already exists${NC}"
fi
echo ""

# Step 5: Download and install Tomcat
echo -e "${YELLOW}[5/6] Downloading Tomcat $TOMCAT_VERSION...${NC}"
cd /tmp

# Try multiple download URLs
TOMCAT_URLS=(
    "https://archive.apache.org/dist/tomcat/tomcat-9/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz"
    "https://dlcdn.apache.org/tomcat/tomcat-9/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz"
)

TOMCAT_DOWNLOADED=false
for URL in "${TOMCAT_URLS[@]}"; do
    echo "Trying: $URL"
    if wget -q --spider "$URL" 2>/dev/null; then
        wget "$URL" -O "apache-tomcat-${TOMCAT_VERSION}.tar.gz"
        TOMCAT_DOWNLOADED=true
        break
    fi
done

if [ "$TOMCAT_DOWNLOADED" = false ]; then
    echo -e "${RED}✗ Failed to download Tomcat${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Tomcat downloaded${NC}"

# Extract Tomcat
echo -e "${YELLOW}Extracting Tomcat...${NC}"
mkdir -p "$TOMCAT_HOME"
tar xzf "apache-tomcat-${TOMCAT_VERSION}.tar.gz" -C "$TOMCAT_HOME" --strip-components=1

# Set permissions
chown -R "$TOMCAT_USER:$TOMCAT_USER" "$TOMCAT_HOME"
chmod +x "$TOMCAT_HOME/bin"/*.sh

echo -e "${GREEN}✓ Tomcat extracted to $TOMCAT_HOME${NC}"
echo ""

# Step 6: Create systemd service
echo -e "${YELLOW}[6/6] Creating systemd service...${NC}"
cat > /etc/systemd/system/tomcat.service << EOF
[Unit]
Description=Apache Tomcat $TOMCAT_VERSION
After=network.target

[Service]
Type=forking

User=$TOMCAT_USER
Group=$TOMCAT_USER

Environment="JAVA_HOME=$JAVA_HOME_PATH"
Environment="CATALINA_PID=$TOMCAT_HOME/temp/tomcat.pid"
Environment="CATALINA_HOME=$TOMCAT_HOME"
Environment="CATALINA_BASE=$TOMCAT_HOME"
Environment="CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC"

ExecStart=$TOMCAT_HOME/bin/startup.sh
ExecStop=$TOMCAT_HOME/bin/shutdown.sh

RestartSec=10
Restart=always

[Install]
WantedBy=multi-user.target
EOF

# Reload systemd and enable Tomcat
systemctl daemon-reload
systemctl enable tomcat

echo -e "${GREEN}✓ Systemd service created${NC}"
echo ""

# Configure firewall (if ufw is active)
if command -v ufw &> /dev/null && ufw status | grep -q "Status: active"; then
    echo -e "${YELLOW}Configuring firewall...${NC}"
    ufw allow 8080/tcp
    echo -e "${GREEN}✓ Firewall rule added for port 8080${NC}"
    echo ""
fi

# Start Tomcat
echo -e "${YELLOW}Starting Tomcat...${NC}"
systemctl start tomcat
sleep 5

# Verify Tomcat is running
if systemctl is-active --quiet tomcat; then
    echo -e "${GREEN}✓ Tomcat is running${NC}"
else
    echo -e "${RED}✗ Tomcat failed to start. Check logs: journalctl -u tomcat${NC}"
    exit 1
fi

echo ""
echo -e "${CYAN}========================================${NC}"
echo -e "${CYAN}Installation Complete!${NC}"
echo -e "${CYAN}========================================${NC}"
echo ""
echo -e "${GREEN}Java 8:${NC}"
echo "  Version: $(java -version 2>&1 | head -n 1)"
echo "  JAVA_HOME: $JAVA_HOME_PATH"
echo ""
echo -e "${GREEN}Tomcat $TOMCAT_VERSION:${NC}"
echo "  Installation: $TOMCAT_HOME"
echo "  Status: $(systemctl is-active tomcat)"
echo "  Port: 8080"
echo ""
echo -e "${GREEN}Access Points:${NC}"
echo "  Tomcat: http://$(hostname -I | awk '{print $1}'):8080"
echo "  Manager: http://$(hostname -I | awk '{print $1}'):8080/manager/html"
echo ""
echo -e "${YELLOW}Next Steps:${NC}"
echo "  1. Configure Tomcat Manager users: sudo nano $TOMCAT_HOME/conf/tomcat-users.xml"
echo "  2. Deploy your WAR file: sudo cp your-app.war $TOMCAT_HOME/webapps/"
echo "  3. Check logs: sudo tail -f $TOMCAT_HOME/logs/catalina.out"
echo ""
echo -e "${CYAN}Useful Commands:${NC}"
echo "  Start:   sudo systemctl start tomcat"
echo "  Stop:    sudo systemctl stop tomcat"
echo "  Restart: sudo systemctl restart tomcat"
echo "  Status:  sudo systemctl status tomcat"
echo "  Logs:    sudo tail -f $TOMCAT_HOME/logs/catalina.out"
echo ""

