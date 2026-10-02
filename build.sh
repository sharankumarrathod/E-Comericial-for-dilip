#!/bin/bash
# Build Script for Linux/Mac

echo "========== Building E-Commerce Platform =========="
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Error: Maven is not installed or not in PATH"
    echo "Please install Maven 3.9.11 or higher"
    exit 1
fi

# Clean and package
echo "Cleaning and building the project..."
echo ""

mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo ""
    echo "✓ Build successful!"
    echo ""
    echo "To run the application, execute:"
    echo "  - bash run.sh (on Linux/Mac)"
    echo "  - java -jar target/E-commerce-platform-1.0-SNAPSHOT.jar"
else
    echo ""
    echo "✗ Build failed"
fi
