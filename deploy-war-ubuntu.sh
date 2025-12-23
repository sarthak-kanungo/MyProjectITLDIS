#!/bin/bash

# Ubuntu: WAR File Deployment Script for Tomcat
# Usage: sudo ./deploy-war-ubuntu.sh /path/to/application.war [app-context-name]

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# Default values
TOMCAT_HOME="${CATALINA_HOME:-/opt/tomcat}"
TOMCAT_USER="tomcat"

# Check if running as root
if [ "$EUID" -ne 0 ]; then 
    echo -e "${RED}Please run as root or with sudo${NC}"
    exit 1
fi

# Check arguments
WAR_FILE=$1
APP_CONTEXT=$2

if [ -z "$WAR_FILE" ]; then
    echo -e "${RED}Usage: sudo ./deploy-war-ubuntu.sh /path/to/application.war [app-context-name]${NC}"
    echo ""
    echo "Examples:"
    echo "  sudo ./deploy-war-ubuntu.sh /path/to/itldis.war"
    echo "  sudo ./deploy-war-ubuntu.sh /path/to/itldis.war myapp"
    exit 1
fi

# Check if WAR file exists
if [ ! -f "$WAR_FILE" ]; then
    echo -e "${RED}Error: WAR file not found: $WAR_FILE${NC}"
    exit 1
fi

# Determine app name
if [ -z "$APP_CONTEXT" ]; then
    APP_NAME=$(basename "$WAR_FILE" .war)
else
    APP_NAME="$APP_CONTEXT"
fi

# Check if Tomcat exists
if [ ! -d "$TOMCAT_HOME" ]; then
    echo -e "${RED}Error: Tomcat not found at: $TOMCAT_HOME${NC}"
    echo -e "${YELLOW}Please install Tomcat first or set CATALINA_HOME environment variable${NC}"
    exit 1
fi

echo -e "${CYAN}========================================${NC}"
echo -e "${CYAN}WAR File Deployment${NC}"
echo -e "${CYAN}========================================${NC}"
echo ""
echo -e "${GREEN}WAR File:${NC} $WAR_FILE"
echo -e "${GREEN}App Name:${NC} $APP_NAME"
echo -e "${GREEN}Tomcat Home:${NC} $TOMCAT_HOME"
echo ""

# Step 1: Stop Tomcat
echo -e "${YELLOW}[1/5] Stopping Tomcat...${NC}"
if systemctl is-active --quiet tomcat 2>/dev/null; then
    systemctl stop tomcat
    echo -e "${GREEN}✓ Tomcat stopped${NC}"
else
    echo -e "${YELLOW}⚠ Tomcat is not running${NC}"
fi
echo ""

# Step 2: Remove old deployment
echo -e "${YELLOW}[2/5] Removing old deployment...${NC}"
if [ -d "$TOMCAT_HOME/webapps/$APP_NAME" ]; then
    rm -rf "$TOMCAT_HOME/webapps/$APP_NAME"
    echo -e "${GREEN}✓ Removed old application directory${NC}"
fi

if [ -f "$TOMCAT_HOME/webapps/$APP_NAME.war" ]; then
    rm -f "$TOMCAT_HOME/webapps/$APP_NAME.war"
    echo -e "${GREEN}✓ Removed old WAR file${NC}"
fi
echo ""

# Step 3: Copy WAR file
echo -e "${YELLOW}[3/5] Copying WAR file...${NC}"
cp "$WAR_FILE" "$TOMCAT_HOME/webapps/$APP_NAME.war"
chown "$TOMCAT_USER:$TOMCAT_USER" "$TOMCAT_HOME/webapps/$APP_NAME.war"
chmod 644 "$TOMCAT_HOME/webapps/$APP_NAME.war"

WAR_SIZE=$(du -h "$TOMCAT_HOME/webapps/$APP_NAME.war" | cut -f1)
echo -e "${GREEN}✓ WAR file copied ($WAR_SIZE)${NC}"
echo ""

# Step 4: Start Tomcat
echo -e "${YELLOW}[4/5] Starting Tomcat...${NC}"
systemctl start tomcat
sleep 3

if systemctl is-active --quiet tomcat; then
    echo -e "${GREEN}✓ Tomcat started${NC}"
else
    echo -e "${RED}✗ Tomcat failed to start${NC}"
    echo -e "${YELLOW}Check logs: journalctl -u tomcat -n 50${NC}"
    exit 1
fi
echo ""

# Step 5: Wait for deployment
echo -e "${YELLOW}[5/5] Waiting for deployment...${NC}"
echo "This may take 30-60 seconds depending on application size..."
sleep 10

# Check if application directory was created
MAX_WAIT=60
WAIT_COUNT=0
while [ $WAIT_COUNT -lt $MAX_WAIT ]; do
    if [ -d "$TOMCAT_HOME/webapps/$APP_NAME" ]; then
        echo -e "${GREEN}✓ Application deployed successfully${NC}"
        break
    fi
    sleep 2
    WAIT_COUNT=$((WAIT_COUNT + 2))
    echo -n "."
done
echo ""

if [ ! -d "$TOMCAT_HOME/webapps/$APP_NAME" ]; then
    echo -e "${YELLOW}⚠ Application directory not found. Check logs for errors.${NC}"
fi

echo ""
echo -e "${CYAN}========================================${NC}"
echo -e "${CYAN}Deployment Complete!${NC}"
echo -e "${CYAN}========================================${NC}"
echo ""
echo -e "${GREEN}Application Details:${NC}"
echo "  WAR File: $TOMCAT_HOME/webapps/$APP_NAME.war"
echo "  App Directory: $TOMCAT_HOME/webapps/$APP_NAME"
echo "  Application URL: http://$(hostname -I | awk '{print $1}'):8080/$APP_NAME"
echo ""
echo -e "${GREEN}Tomcat Status:${NC}"
systemctl status tomcat --no-pager -l | head -n 5
echo ""
echo -e "${YELLOW}Monitor Deployment:${NC}"
echo "  sudo tail -f $TOMCAT_HOME/logs/catalina.out"
echo ""
echo -e "${YELLOW}Check Application:${NC}"
echo "  curl http://localhost:8080/$APP_NAME"
echo ""


