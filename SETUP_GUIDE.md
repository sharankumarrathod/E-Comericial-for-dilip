# 🚀 E-Commerce Platform - Setup & Running Guide

## Quick Start (30 seconds)

### Windows
```bash
# 1. Navigate to project directory
cd E-comerciail-platform

# 2. Build the project (first time only)
build.bat

# 3. Run the application
run.bat
```

### Linux/Mac
```bash
# 1. Navigate to project directory
cd E-comerciail-platform

# 2. Build the project (first time only)
bash build.sh

# 3. Run the application
bash run.sh
```

---

## Prerequisites

### System Requirements
- **Operating System**: Windows, Linux, or macOS
- **RAM**: Minimum 1 GB
- **Disk Space**: ~200 MB for Java + Maven

### Required Software

#### 1. Java Development Kit (JDK) 25
Download from: https://www.oracle.com/java/technologies/downloads/

**Installation Instructions:**

**Windows:**
1. Download JDK 25 installer
2. Run the installer and follow the wizard
3. Accept default installation path
4. Verify installation:
   ```bash
   java -version
   ```

**Linux (Ubuntu/Debian):**
```bash
sudo apt-get update
sudo apt-get install openjdk-25-jdk
java -version
```

**macOS:**
```bash
brew install openjdk@25
java -version
```

#### 2. Maven 3.9.11 or Higher
Download from: https://maven.apache.org/download.cgi

**Installation Instructions:**

**Windows:**
1. Download Maven binary zip
2. Extract to C:\Program Files\apache-maven-3.9.11
3. Add to PATH environment variable
4. Verify installation:
   ```bash
   mvn -version
   ```

**Linux/macOS:**
```bash
# Using Homebrew (macOS)
brew install maven

# Using apt (Ubuntu/Debian)
sudo apt-get install maven

# Verify
mvn -version
```

---

## Method 1: Using Build Scripts (Recommended)

### Windows
```bash
cd E-comerciail-platform
build.bat  # Compiles and packages
run.bat    # Runs the application
```

### Linux/Mac
```bash
cd E-comerciail-platform
bash build.sh  # Compiles and packages
bash run.sh    # Runs the application
```

---

## Method 2: Using Maven Commands

### Step 1: Clean and Compile
```bash
cd E-comerciail-platform
mvn clean compile
```

### Step 2: Package the Application
```bash
mvn package -DskipTests
```

### Step 3: Run the Application
```bash
# Method A: Run from JAR
java -jar target/E-commerce-platform-1.0-SNAPSHOT.jar

# Method B: Run using Maven exec plugin
mvn exec:java -Dexec.mainClass="com.kodewala.ecommerce.main.EcommerceApplication"
```

---

## Method 3: Using IDE (IntelliJ IDEA / Eclipse)

### IntelliJ IDEA
1. Open project: File → Open → Select project folder
2. Maven will auto-import dependencies
3. Go to main class: Right-click on `EcommerceApplication.java`
4. Select "Run 'EcommerceApplication'"

### Eclipse
1. Import project: File → Import → Maven → Existing Maven Projects
2. Select project folder
3. Right-click on project → Run As → Java Application
4. Select `EcommerceApplication` class

---

## Project Directory Structure

```
E-comerciail-platform/
├── src/
│   └── main/
│       └── java/
│           └── com/kodewala/ecommerce/
│               ├── model/              # Data models
│               ├── repository/         # Data repositories
│               ├── service/            # Business logic
│               ├── exception/          # Custom exceptions
│               └── main/               # Application entry point
├── target/                             # Compiled files (auto-generated)
├── pom.xml                             # Maven configuration
├── build.bat / build.sh               # Build scripts
├── run.bat / run.sh                   # Run scripts
└── README.md                           # Project documentation
```

---

## Troubleshooting

### Error: "Java not found"
**Solution:**
```bash
# Windows: Check if Java is in PATH
java -version

# If not, add Java to PATH:
# Control Panel → System → Environment Variables
# Add C:\Program Files\Java\jdk-25\bin to PATH
```

### Error: "Maven not found"
**Solution:**
```bash
# Download Maven from: https://maven.apache.org/
# Extract to a folder
# Add Maven bin folder to PATH
mvn -version  # Verify installation
```

### Error: "Cannot find main class"
**Solution:**
```bash
# Rebuild the project
mvn clean package

# Verify JAR file exists
# Windows
dir target\*.jar

# Linux/Mac
ls -la target/*.jar
```

### Error: "Java version mismatch"
**Solution:**
```bash
# Check Java version
java -version

# Update pom.xml if using different Java version
# Current: Java 25
# Edit pom.xml and update:
# <maven.compiler.source>25</maven.compiler.source>
# <maven.compiler.target>25</maven.compiler.target>
```

### JAR file too small or corrupt
**Solution:**
```bash
# Clean and rebuild
mvn clean

# Remove target folder manually and rebuild
rmdir /s target  # Windows
rm -rf target    # Linux/Mac

mvn package -DskipTests
```

---

## Verifying Installation

### 1. Check Java
```bash
java -version
# Should show: Java(TM) SE Runtime Environment 25.x.x
```

### 2. Check Maven
```bash
mvn -version
# Should show: Apache Maven 3.9.x
```

### 3. Check Project Structure
```bash
# Windows
dir src\main\java\com\kodewala\ecommerce

# Linux/Mac
ls -la src/main/java/com/kodewala/ecommerce
```

### 4. Test Compilation
```bash
mvn clean compile
# Should show: BUILD SUCCESS
```

---

## Running the Application

Once built successfully, you'll see:

```
========== Welcome to E-Commerce Platform ==========

========== Main Menu ==========
1. Admin
2. Customer
3. Exit
Enter your choice:
```

### Admin Demo
```
Enter: 1
Enter: 2          # View Products
```

### Customer Demo
```
Enter: 2
Enter: 1          # Register
Enter username, email, mobile, address
Enter: 3          # View Products
Enter: 3          # Add to Cart
Enter product ID: 1001
Enter quantity: 2
Enter: 4          # View Cart
Enter: 6          # Place Order
```

---

## Common Commands Reference

```bash
# Build the project
mvn clean package

# Run with specific memory settings
java -Xmx512m -jar target/E-commerce-platform-1.0-SNAPSHOT.jar

# Generate project documentation
mvn site

# Run with Maven
mvn exec:java -Dexec.mainClass="com.kodewala.ecommerce.main.EcommerceApplication"

# Check dependencies
mvn dependency:tree

# Format code
mvn spotless:apply
```

---

## Performance Tips

- First run takes ~5-10 seconds (Java startup)
- Subsequent runs are faster
- No database overhead - data stored in memory
- ~20-30 MB RAM usage
- All operations are real-time

---

## FAQs

**Q: Will my data persist after closing the app?**
A: No, all data is stored in-memory. Data resets on restart.

**Q: Can I modify the sample data?**
A: Yes, edit `initializeSampleData()` in `EcommerceApplication.java`

**Q: How do I add more features?**
A: Follow the existing pattern - create models, repositories, services

**Q: Can I use this with a database?**
A: Yes! Replace `*Repository` classes with database queries

---

## Next Steps

1. ✅ Run the application
2. 📖 Read README.md for feature details
3. 📚 Explore the source code structure
4. 🔧 Modify and extend features
5. 📝 Add file-based persistence (optional enhancement)

---

## Support & Documentation

- **Project README**: `README.md`
- **Java Documentation**: https://docs.oracle.com/en/java/
- **Maven Guide**: https://maven.apache.org/guides/
- **Project Structure**: All code in `src/main/java/com/kodewala/ecommerce/`

---

**Enjoy building and learning! 🎓**
