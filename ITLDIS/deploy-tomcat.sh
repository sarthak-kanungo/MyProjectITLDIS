#!/bin/bash

# Camunda BPM Deployment Script for Tomcat (Linux/Mac)
# Usage: ./deploy-tomcat.sh [tomcat-home]

set -e

echo "========================================"
echo "Camunda BPM Deployment Script"
echo "========================================"
echo ""

# Check if Tomcat home is provided
if [ -z "$1" ]; then
    echo "ERROR: Tomcat home directory not provided"
    echo "Usage: ./deploy-tomcat.sh [tomcat-home]"
    echo "Example: ./deploy-tomcat.sh /opt/apache-tomcat-9.0.65"
    exit 1
fi

TOMCAT_HOME="$1"
WAR_FILE="target/itldis.war"
DEPLOY_PATH="$TOMCAT_HOME/webapps/itldis.war"

# Verify WAR file exists
if [ ! -f "$WAR_FILE" ]; then
    echo "ERROR: WAR file not found: $WAR_FILE"
    echo "Please build the project first: mvn clean package -DskipTests"
    exit 1
fi

# Verify Tomcat directory exists
if [ ! -d "$TOMCAT_HOME" ]; then
    echo "ERROR: Tomcat directory not found: $TOMCAT_HOME"
    exit 1
fi

echo "[1/5] Stopping Tomcat..."
"$TOMCAT_HOME/bin/shutdown.sh" || true
sleep 5

echo "[2/5] Removing old deployment..."
rm -f "$DEPLOY_PATH"
rm -rf "$TOMCAT_HOME/webapps/itldis"

echo "[3/5] Copying WAR file..."
cp "$WAR_FILE" "$DEPLOY_PATH"
if [ $? -ne 0 ]; then
    echo "ERROR: Failed to copy WAR file"
    exit 1
fi

echo "[4/5] Starting Tomcat..."
"$TOMCAT_HOME/bin/startup.sh"

echo "[5/5] Waiting for deployment..."
sleep 10

echo ""
echo "========================================"
echo "Deployment Complete!"
echo "========================================"
echo ""
echo "WAR File: $DEPLOY_PATH"
echo "Application URL: http://localhost:8080/itldis"
echo "Test Page: http://localhost:8080/itldis/camunda/test.jsp"
echo ""
echo "Check logs at: $TOMCAT_HOME/logs/catalina.out"
echo "Look for: 'Camunda ProcessEngine initialized: default'"
echo ""
