@echo off
REM E-Commerce Application Launcher for Windows
REM This script runs the E-Commerce Product & Order Management System

echo ========== E-Commerce Platform ==========
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 25 or higher and add it to your PATH
    pause
    exit /b 1
)

REM Run the application
echo Starting E-Commerce Application...
echo.

java -jar target\E-commerce-platform-1.0-SNAPSHOT.jar

if %errorlevel% equ 0 (
    echo.
    echo ✓ Application closed successfully
) else (
    echo.
    echo ✗ Application encountered an error
)

pause
