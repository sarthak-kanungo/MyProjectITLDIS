#!/bin/bash
# ITLDIS Project Runner Script (Bash/Linux/Mac)
# This script builds and runs the ITLDIS project on Tomcat

set -e  # Exit on error

# Default values
TOMCAT_HOME="${CATALINA_HOME:-/opt/tomcat}"
BUILD_ONLY=false
SKIP_BUILD=false

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# Parse command line arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        --tomcat-home)
            TOMCAT_HOME="$2"
            shift 2
            ;;
        --build-only)
            BUILD_ONLY=true
            shift
            ;;
        --skip-build)
            SKIP_BUILD=true
            shift
            ;;
        --help|-h)
            echo ""
            echo "ITLDIS Project Runner"
            echo "===================="
            echo ""
            echo "Usage:"
            echo "  ./run-project.sh [options]"
            echo ""
            echo "Options:"
            echo "  --tomcat-home <path>  Tomcat installation directory (default: \$CATALINA_HOME or /opt/tomcat)"
            echo "  --build-only          Only build the project, don't start Tomcat"
            echo "  --skip-build          Skip build step (use existing WAR file)"
            echo "  --help, -h            Show this help message"
            echo ""
            echo "Examples:"
            echo "  ./run-project.sh"
            echo "  ./run-project.sh --tomcat-home /opt/tomcat"
            echo "  ./run-project.sh --build-only"
            echo "  ./run-project.sh --skip-build"
            echo ""
            exit 0
            ;;
        *)
            echo "Unknown option: $1"
            echo "Use --help for usage information"
            exit 1
            ;;
    esac
done

# Change to script directory
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

echo ""
echo -e "${CYAN}============================================${NC}"
echo -e "${CYAN}ITLDIS Project Runner${NC}"
echo -e "${CYAN}============================================${NC}"
echo ""

# Check if Maven is available
if ! command -v mvn &> /dev/null; then
    echo -e "${RED}ERROR: Maven (mvn) is not found in PATH${NC}"
    echo -e "${YELLOW}Please install Maven and add it to your PATH${NC}"
    echo -e "${YELLOW}Download from: https://maven.apache.org/download.cgi${NC}"
    exit 1
fi

echo -e "${GREEN}[OK] Maven found: $(which mvn)${NC}"

# Check if Java is available
if ! command -v java &> /dev/null; then
    echo -e "${RED}ERROR: Java is not found in PATH${NC}"
    echo -e "${YELLOW}Please install Java JDK 8 and add it to your PATH${NC}"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | head -n 1)
echo -e "${GREEN}[OK] Java found: $JAVA_VERSION${NC}"

# Build step
if [ "$SKIP_BUILD" = false ]; then
    echo ""
    echo -e "${YELLOW}[1/3] Building project...${NC}"
    echo "Running: mvn clean package -DskipTests"
    
    mvn clean package -DskipTests
    
    if [ $? -ne 0 ]; then
        echo ""
        echo -e "${RED}ERROR: Build failed!${NC}"
        exit 1
    fi
    
    if [ ! -f "target/itldis.war" ]; then
        echo ""
        echo -e "${RED}ERROR: WAR file not found after build!${NC}"
        exit 1
    fi
    
    WAR_SIZE=$(du -h target/itldis.war | cut -f1)
    echo -e "${GREEN}[OK] Build successful! WAR file: target/itldis.war ($WAR_SIZE)${NC}"
else
    echo ""
    echo -e "${YELLOW}[1/3] Skipping build (using existing WAR file)${NC}"
    if [ ! -f "target/itldis.war" ]; then
        echo -e "${RED}ERROR: WAR file not found at target/itldis.war${NC}"
        echo -e "${YELLOW}Please build the project first or remove --skip-build flag${NC}"
        exit 1
    fi
fi

if [ "$BUILD_ONLY" = true ]; then
    echo ""
    echo -e "${CYAN}============================================${NC}"
    echo -e "${CYAN}Build Complete!${NC}"
    echo -e "${CYAN}============================================${NC}"
    echo ""
    echo -e "${GREEN}WAR file ready at: target/itldis.war${NC}"
    echo ""
    echo -e "${YELLOW}To deploy manually:${NC}"
    echo "  1. Copy target/itldis.war to your Tomcat webapps directory"
    echo "  2. Start Tomcat server"
    echo "  3. Access: http://localhost:8080/itldis/"
    echo ""
    exit 0
fi

# Check Tomcat
echo ""
echo -e "${YELLOW}[2/3] Checking Tomcat installation...${NC}"

if [ ! -f "$TOMCAT_HOME/bin/catalina.sh" ]; then
    echo ""
    echo -e "${RED}ERROR: Tomcat not found at: $TOMCAT_HOME${NC}"
    echo ""
    echo -e "${YELLOW}Please specify Tomcat path:${NC}"
    echo -e "${CYAN}  ./run-project.sh --tomcat-home /path/to/tomcat${NC}"
    echo ""
    echo -e "${YELLOW}Or set CATALINA_HOME environment variable${NC}"
    exit 1
fi

echo -e "${GREEN}[OK] Tomcat found at: $TOMCAT_HOME${NC}"

# Deploy WAR to Tomcat
echo ""
echo -e "${YELLOW}[3/3] Deploying WAR to Tomcat...${NC}"

# Stop Tomcat if running
if [ -f "$TOMCAT_HOME/bin/shutdown.sh" ]; then
    echo "[INFO] Stopping existing Tomcat instance..."
    "$TOMCAT_HOME/bin/shutdown.sh" 2>/dev/null || true
    sleep 3
fi

# Copy WAR file
cp -f target/itldis.war "$TOMCAT_HOME/webapps/itldis.war"
echo -e "${GREEN}[OK] WAR deployed to: $TOMCAT_HOME/webapps/itldis.war${NC}"

# Check if port 8080 is in use
if command -v netstat &> /dev/null; then
    if netstat -an | grep -q ":8080.*LISTEN"; then
        echo ""
        echo -e "${YELLOW}[WARNING] Port 8080 is already in use${NC}"
        echo -e "${YELLOW}Tomcat may already be running or another application is using port 8080${NC}"
        echo ""
        read -p "Continue anyway? (Y/N): " -n 1 -r
        echo ""
        if [[ ! $REPLY =~ ^[Yy]$ ]]; then
            exit 0
        fi
    fi
fi

# Start Tomcat
echo ""
echo -e "${CYAN}============================================${NC}"
echo -e "${CYAN}Starting Tomcat Server...${NC}"
echo -e "${CYAN}============================================${NC}"
echo ""
echo -e "${GREEN}Application will be available at:${NC}"
echo -e "${CYAN}  http://localhost:8080/itldis/${NC}"
echo ""
echo -e "${GREEN}Login page:${NC}"
echo -e "${CYAN}  http://localhost:8080/itldis/login/Login.jsp${NC}"
echo ""
echo -e "${GREEN}Log files:${NC}"
echo -e "${CYAN}  $TOMCAT_HOME/logs/catalina.out${NC}"
echo ""
echo -e "${YELLOW}Press Ctrl+C to stop the server${NC}"
echo ""

# Start Tomcat in foreground
cd "$TOMCAT_HOME/bin"
./catalina.sh run

