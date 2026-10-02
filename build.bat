@echo off
REM Build Script for Windows
REM This script compiles and packages the E-Commerce Application

echo ========== Building E-Commerce Platform ==========
echo.

REM Check if Maven is installed
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Maven is not installed or not in PATH
    echo Please install Maven 3.9.11 or higher
    pause
    exit /b 1
)

REM Clean and package
echo Cleaning and building the project...
echo.

mvn clean package -DskipTests

if %errorlevel% equ 0 (
    echo.
    echo ✓ Build successful!
    echo.
    echo To run the application, execute:
    echo   - run.bat (on Windows)
    echo   - bash run.sh (on Linux/Mac)
    echo   - java -jar target/E-commerce-platform-1.0-SNAPSHOT.jar
) else (
    echo.
    echo ✗ Build failed
)

pause
