#!/bin/bash
# E-Commerce Application Launcher for Linux/Mac

echo "========== E-Commerce Platform =========="
echo ""

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Error: Java is not installed or not in PATH"
    echo "Please install Java 25 or higher"
    exit 1
fi

# Run the application
echo "Starting E-Commerce Application..."
echo ""

java -jar target/E-commerce-platform-1.0-SNAPSHOT.jar

if [ $? -eq 0 ]; then
    echo ""
    echo "✓ Application closed successfully"
else
    echo ""
    echo "✗ Application encountered an error"
fi
