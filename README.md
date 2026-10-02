# E-Commerce Product & Order Management System

A console-based E-Commerce application built with **Core Java**, demonstrating **Object-Oriented Programming** concepts and **Java Collections** (ArrayList, HashMap, HashSet) without any database.

## 📋 Project Overview

This is a complete implementation of an E-Commerce platform where:
- **Customers** can view products, search, add to cart, and place orders
- **Administrators** can manage products, customers, and view all orders
- All data is stored in-memory using Java Collections
- Full inventory management with stock tracking

## ✨ Features Implemented

### Admin Features
- ✅ Add new products with details
- ✅ View all products
- ✅ Search products by ID, Name, Category, Brand
- ✅ Search products by price range
- ✅ Update product price and quantity
- ✅ Delete products
- ✅ View all registered customers
- ✅ View all placed orders with details

### Customer Features
- ✅ Register new account
- ✅ Login to existing account
- ✅ View all available products
- ✅ Search products (by name, category, brand, price range)
- ✅ Add products to shopping cart
- ✅ View cart with total calculation
- ✅ Remove products from cart
- ✅ Increase/decrease product quantity in cart
- ✅ Place orders with automatic inventory update
- ✅ View order history with details
- ✅ Cancel orders (with status validation)

### Core Concepts Used
- **Collections**: ArrayList, HashMap, HashSet
- **Stream API**: Used for filtering, searching, sorting products
- **Exception Handling**: Custom exceptions for error scenarios
- **OOP Principles**: Encapsulation, Inheritance, Polymorphism, Abstraction
- **Design Patterns**: Repository pattern, Service layer pattern, MVC-like structure

## 📦 Project Structure

```
com.kodewala.ecommerce
├── model/
│   ├── Product.java              # Product data model
│   ├── Customer.java             # Customer data model
│   ├── CartItem.java             # Shopping cart item model
│   └── Order.java                # Order data model with status enum
│
├── repository/
│   ├── ProductRepository.java    # Manages product collection
│   ├── CustomerRepository.java   # Manages customer map
│   └── OrderRepository.java      # Manages order collection
│
├── service/
│   ├── ProductService.java       # Product business logic
│   ├── CustomerService.java      # Customer business logic
│   ├── CartService.java          # Shopping cart operations
│   └── OrderService.java         # Order management logic
│
├── exception/
│   ├── ProductNotFoundException.java
│   ├── CustomerNotFoundException.java
│   ├── InsufficientStockException.java
│   ├── InvalidQuantityException.java
│   └── InvalidOrderException.java
│
└── main/
    └── EcommerceApplication.java # Main entry point with menu system
```

## 🚀 How to Run

### Prerequisites
- Java 25 or higher
- Maven 3.9.11 or higher

### Build the Project
```bash
cd E-comerciail-platform
mvn clean compile
```

### Run the Application
```bash
mvn compile exec:java -Dexec.mainClass="com.kodewala.ecommerce.main.EcommerceApplication"
```

Or run the JAR directly:
```bash
mvn package
java -jar target/E-comerciail-platform-1.0-SNAPSHOT.jar
```

## 📋 Usage Guide

### Main Menu
```
1. Admin        - Access admin panel
2. Customer    - Access customer portal
3. Exit        - Close the application
```

### Admin Menu
- Add/View/Search/Update/Delete Products
- View Customers
- View All Orders

### Customer Menu
- Register/Login
- Browse Products
- Search with multiple filters
- Manage Shopping Cart
- Place & Track Orders
- Cancel Orders

## 💡 Sample Data

The application comes pre-loaded with sample products:
- iPhone 16 (Electronics, ₹79,999)
- Samsung Galaxy S25 (Electronics, ₹74,999)
- iPad Pro (Electronics, ₹89,999)
- Sony WH-1000XM5 (Accessories, ₹34,999)
- USB-C Cable (Accessories, ₹799)

## 🔑 Key Implementation Details

### Collections Used

| Collection | Purpose | Where Used |
|-----------|---------|-----------|
| `ArrayList<Product>` | Store all products | ProductRepository |
| `HashMap<Integer, Customer>` | Store customers by ID | CustomerRepository |
| `ArrayList<Order>` | Store all orders | OrderRepository |
| `Map<Integer, List<CartItem>>` | Store carts for each customer | CartService |

### Stream API Usage

1. **Search Products** - Filter by name, category, brand using `.stream().filter()`
2. **Price Range Search** - Filter products by price range
3. **Sorting** - Sort products by price using `.sorted()`
4. **Cart Total** - Calculate cart total using `.mapToDouble().sum()`
5. **Find Operations** - Find specific products using `.findFirst()`

### Exception Handling

Custom exceptions provide detailed error messages:
```java
ProductNotFoundException         // Product not found
CustomerNotFoundException        // Customer not found
InsufficientStockException      // Not enough inventory
InvalidQuantityException         // Invalid quantity value
InvalidOrderException           // Order-related errors
```

## 🎯 Order Statuses

- **PLACED** - Order just created
- **CONFIRMED** - Order confirmed by admin
- **SHIPPED** - Order in transit
- **DELIVERED** - Order delivered
- **CANCELLED** - Order cancelled

## 📊 Inventory Management

✅ **Stock Validation**: System prevents purchase when quantity exceeds available stock
✅ **Automatic Update**: Stock quantity decreases when order is placed
✅ **Real-time Availability**: Shows current stock for each product

## 🔐 Session Management

- Each customer has a unique session when logged in
- Cart is maintained per customer
- Orders are tracked per customer
- Logout clears the session

## 📝 Error Handling Examples

```
✗ Product with ID 105 does not exist.
✗ Customer with ID 2001 does not exist.
✗ Insufficient stock for Product ID 1001. Requested: 15, Available: 10
✗ Invalid quantity: -5. Quantity must be greater than 0.
✗ Cannot cancel order with status: DELIVERED
```

## 🎓 Learning Outcomes

This project covers:
- Object-Oriented Programming concepts
- Java Collections Framework
- Stream API for functional programming
- Exception handling and custom exceptions
- Design patterns (Repository, Service layer)
- Console-based UI with menu-driven interface
- Inventory and order management
- Data persistence in memory

## 📌 Notes

- All data is stored in-memory and will be lost when the application closes
- No external database is used (as per requirements)
- No Spring/Spring Boot framework used (as per requirements)
- Pure Core Java implementation

## 🤝 Contributing

This is a learning project demonstrating Java fundamentals. Feel free to enhance it with:
- File-based persistence (Serialization)
- Database integration
- GUI with JavaFX/Swing
- REST API using Spring Boot
- Additional features like reviews, wishlists, etc.

## 📄 License

This project is for educational purposes.

---

**Happy Coding! 🚀**
