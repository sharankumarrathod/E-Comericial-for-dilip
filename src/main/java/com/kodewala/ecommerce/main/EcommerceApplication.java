package com.kodewala.ecommerce.main;

import com.kodewala.ecommerce.model.CartItem;
import com.kodewala.ecommerce.model.Customer;
import com.kodewala.ecommerce.model.Order;
import com.kodewala.ecommerce.model.Product;
import com.kodewala.ecommerce.repository.CustomerRepository;
import com.kodewala.ecommerce.repository.OrderRepository;
import com.kodewala.ecommerce.repository.ProductRepository;
import com.kodewala.ecommerce.service.CartService;
import com.kodewala.ecommerce.service.CustomerService;
import com.kodewala.ecommerce.service.OrderService;
import com.kodewala.ecommerce.service.ProductService;
import com.kodewala.ecommerce.exception.*;

import java.util.List;
import java.util.Scanner;

public class EcommerceApplication {
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    
    private ProductService productService;
    private CustomerService customerService;
    private CartService cartService;
    private OrderService orderService;
    
    private Scanner scanner;
    private int loggedInCustomerId = -1;

    public EcommerceApplication() {
        this.productRepository = new ProductRepository();
        this.customerRepository = new CustomerRepository();
        this.orderRepository = new OrderRepository();
        
        this.productService = new ProductService(productRepository);
        this.customerService = new CustomerService(customerRepository);
        this.cartService = new CartService();
        this.orderService = new OrderService(orderRepository);
        
        this.scanner = new Scanner(System.in);
        initializeSampleData();
    }

    private void initializeSampleData() {
        productService.addProduct(1001, "iPhone 16", "Electronics", 79999, 10, "Apple");
        productService.addProduct(1002, "Samsung Galaxy S25", "Electronics", 74999, 8, "Samsung");
        productService.addProduct(1003, "iPad Pro", "Electronics", 89999, 5, "Apple");
        productService.addProduct(1004, "Sony WH-1000XM5", "Accessories", 34999, 15, "Sony");
        productService.addProduct(1005, "USB-C Cable", "Accessories", 799, 50, "Generic");
    }

    public void start() {
        System.out.println("\n========== Welcome to E-Commerce Platform ==========\n");
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    running = false;
                    System.out.println("\nThank you for using E-Commerce Platform. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n========== Main Menu ==========");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    // ============ ADMIN MENU ============
    private void adminMenu() {
        boolean adminRunning = true;
        while (adminRunning) {
            displayAdminMenu();
            int choice = getIntInput();
            
            try {
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        viewAllProducts();
                        break;
                    case 3:
                        searchProduct();
                        break;
                    case 4:
                        updateProduct();
                        break;
                    case 5:
                        deleteProduct();
                        break;
                    case 6:
                        viewAllCustomers();
                        break;
                    case 7:
                        viewAllOrders();
                        break;
                    case 8:
                        adminRunning = false;
                        System.out.println("Exiting Admin Menu...\n");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void displayAdminMenu() {
        System.out.println("\n========== Admin Menu ==========");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. View Customers");
        System.out.println("7. View All Orders");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addProduct() {
        System.out.println("\n========== Add Product ==========");
        System.out.print("Enter Product ID: ");
        int productId = getIntInput();
        
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        
        System.out.print("Enter Price: ");
        double price = getDoubleInput();
        
        System.out.print("Enter Quantity: ");
        int quantity = getIntInput();
        
        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();
        
        productService.addProduct(productId, productName, category, price, quantity, brand);
        System.out.println("✓ Product added successfully!");
    }

    private void viewAllProducts() {
        System.out.println("\n========== All Products ==========");
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        products.forEach(p -> System.out.println(p));
    }

    private void searchProduct() {
        System.out.println("\n========== Search Product ==========");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Category");
        System.out.println("4. Search by Brand");
        System.out.println("5. Search by Price Range");
        System.out.print("Enter your choice: ");
        
        int choice = getIntInput();
        try {
            switch (choice) {
                case 1:
                    searchById();
                    break;
                case 2:
                    searchByName();
                    break;
                case 3:
                    searchByCategory();
                    break;
                case 4:
                    searchByBrand();
                    break;
                case 5:
                    searchByPriceRange();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (ProductNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    private void searchById() throws ProductNotFoundException {
        System.out.print("Enter Product ID: ");
        int productId = getIntInput();
        Product product = productService.getProductById(productId);
        System.out.println("\n" + product);
    }

    private void searchByName() {
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        List<Product> products = productService.searchByName(name);
        displayProductList(products);
    }

    private void searchByCategory() {
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        List<Product> products = productService.searchByCategory(category);
        displayProductList(products);
    }

    private void searchByBrand() {
        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();
        List<Product> products = productService.searchByBrand(brand);
        displayProductList(products);
    }

    private void searchByPriceRange() {
        System.out.print("Enter Minimum Price: ");
        double minPrice = getDoubleInput();
        System.out.print("Enter Maximum Price: ");
        double maxPrice = getDoubleInput();
        List<Product> products = productService.searchByPriceRange(minPrice, maxPrice);
        displayProductList(products);
    }

    private void displayProductList(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("\nSearch Results:");
            products.forEach(p -> System.out.println(p));
        }
    }

    private void updateProduct() {
        System.out.println("\n========== Update Product ==========");
        System.out.print("Enter Product ID: ");
        int productId = getIntInput();
        
        System.out.println("1. Update Price");
        System.out.println("2. Update Quantity");
        System.out.print("Enter your choice: ");
        int choice = getIntInput();
        
        try {
            if (choice == 1) {
                System.out.print("Enter New Price: ");
                double newPrice = getDoubleInput();
                productService.updatePrice(productId, newPrice);
                System.out.println("✓ Price updated successfully!");
            } else if (choice == 2) {
                System.out.print("Enter New Quantity: ");
                int newQuantity = getIntInput();
                productService.updateQuantity(productId, newQuantity);
                System.out.println("✓ Quantity updated successfully!");
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (ProductNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    private void deleteProduct() {
        System.out.println("\n========== Delete Product ==========");
        System.out.print("Enter Product ID: ");
        int productId = getIntInput();
        
        try {
            productService.deleteProduct(productId);
            System.out.println("✓ Product deleted successfully!");
        } catch (ProductNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    private void viewAllCustomers() {
        System.out.println("\n========== All Customers ==========");
        var customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }
        customers.forEach(c -> System.out.println(c));
    }

    private void viewAllOrders() {
        System.out.println("\n========== All Orders ==========");
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet.");
            return;
        }
        orders.forEach(o -> {
            System.out.println(o);
            System.out.println("  Items: " + o.getCartItems().size());
            o.getCartItems().forEach(item -> System.out.println("    - " + item.getProductName() + " x" + item.getQuantity()));
            System.out.println();
        });
    }

    // ============ CUSTOMER MENU ============
    private void customerMenu() {
        System.out.println("\n========== Customer Portal ==========");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Enter your choice: ");
        int choice = getIntInput();
        
        if (choice == 1) {
            registerCustomer();
        } else if (choice == 2) {
            loginCustomer();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void registerCustomer() {
        System.out.println("\n========== Customer Registration ==========");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Mobile: ");
        String mobile = scanner.nextLine();
        
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        
        int customerId = customerService.registerCustomer(name, email, mobile, address);
        System.out.println("✓ Registration successful! Your Customer ID: " + customerId);
        
        loggedInCustomerId = customerId;
        customerPortal();
    }

    private void loginCustomer() {
        System.out.println("\n========== Customer Login ==========");
        System.out.print("Enter Customer ID: ");
        int customerId = getIntInput();
        
        try {
            customerService.getCustomerById(customerId);
            loggedInCustomerId = customerId;
            System.out.println("✓ Login successful!");
            customerPortal();
        } catch (CustomerNotFoundException e) {
            System.out.println("✗ Login failed: " + e.getMessage());
        }
    }

    private void customerPortal() {
        if (loggedInCustomerId == -1) return;
        
        boolean portalRunning = true;
        while (portalRunning) {
            displayCustomerMenu();
            int choice = getIntInput();
            
            try {
                switch (choice) {
                    case 1:
                        viewProducts();
                        break;
                    case 2:
                        searchProducts();
                        break;
                    case 3:
                        addToCart();
                        break;
                    case 4:
                        viewCart();
                        break;
                    case 5:
                        removeFromCart();
                        break;
                    case 6:
                        placeOrder();
                        break;
                    case 7:
                        viewMyOrders();
                        break;
                    case 8:
                        cancelOrder();
                        break;
                    case 9:
                        portalRunning = false;
                        loggedInCustomerId = -1;
                        System.out.println("✓ Logged out successfully.\n");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void displayCustomerMenu() {
        System.out.println("\n========== Customer Menu ==========");
        System.out.println("1. View Products");
        System.out.println("2. Search Product");
        System.out.println("3. Add Product to Cart");
        System.out.println("4. View Cart");
        System.out.println("5. Remove Product from Cart");
        System.out.println("6. Place Order");
        System.out.println("7. View My Orders");
        System.out.println("8. Cancel Order");
        System.out.println("9. Logout");
        System.out.print("Enter your choice: ");
    }

    private void viewProducts() {
        System.out.println("\n========== Available Products ==========");
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        products.forEach(p -> System.out.println(p));
    }

    private void searchProducts() {
        System.out.println("\n========== Search Products ==========");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by Category");
        System.out.println("3. Search by Brand");
        System.out.println("4. Search by Price Range");
        System.out.print("Enter your choice: ");
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                searchByName();
                break;
            case 2:
                searchByCategory();
                break;
            case 3:
                searchByBrand();
                break;
            case 4:
                searchByPriceRange();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void addToCart() throws ProductNotFoundException, InvalidQuantityException {
        System.out.println("\n========== Add to Cart ==========");
        System.out.print("Enter Product ID: ");
        int productId = getIntInput();
        
        Product product = productService.getProductById(productId);
        
        System.out.print("Enter Quantity: ");
        int quantity = getIntInput();
        
        if (quantity > product.getQuantity()) {
            System.out.println("✗ Insufficient stock. Available: " + product.getQuantity());
            return;
        }
        
        cartService.addProductToCart(loggedInCustomerId, product, quantity);
        System.out.println("✓ Product added to cart!");
    }

    private void viewCart() {
        System.out.println("\n========== Your Cart ==========");
        List<CartItem> cart = cartService.getCart(loggedInCustomerId);
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        
        cart.forEach(item -> System.out.println(item));
        double total = cartService.getCartTotal(loggedInCustomerId);
        System.out.println("\nCart Total: ₹" + total);
    }

    private void removeFromCart() {
        System.out.println("\n========== Remove from Cart ==========");
        System.out.print("Enter Product ID: ");
        int productId = getIntInput();
        
        cartService.removeProductFromCart(loggedInCustomerId, productId);
        System.out.println("✓ Product removed from cart!");
    }

    private void placeOrder() throws InvalidOrderException, InsufficientStockException {
        System.out.println("\n========== Place Order ==========");
        List<CartItem> cart = cartService.getCart(loggedInCustomerId);
        
        if (cart.isEmpty()) {
            System.out.println("✗ Your cart is empty. Add products before placing an order.");
            return;
        }
        
        for (CartItem item : cart) {
            if (!productService.decreaseQuantity(item.getProductId(), item.getQuantity())) {
                throw new InsufficientStockException(item.getProductId(), item.getQuantity(), 0);
            }
        }
        
        double totalAmount = cartService.getCartTotal(loggedInCustomerId);
        Order order = orderService.placeOrder(loggedInCustomerId, cart, totalAmount);
        
        cartService.clearCart(loggedInCustomerId);
        System.out.println("✓ Order placed successfully!");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Total Amount: ₹" + totalAmount);
    }

    private void viewMyOrders() {
        System.out.println("\n========== My Orders ==========");
        List<Order> orders = orderService.getCustomerOrders(loggedInCustomerId);
        if (orders.isEmpty()) {
            System.out.println("You have no orders.");
            return;
        }
        
        orders.forEach(o -> {
            System.out.println("\n" + o);
            System.out.println("  Items:");
            o.getCartItems().forEach(item -> System.out.println("    - " + item.getProductName() + " x" + item.getQuantity() + " @ ₹" + item.getPrice()));
        });
    }

    private void cancelOrder() throws InvalidOrderException {
        System.out.println("\n========== Cancel Order ==========");
        System.out.print("Enter Order ID: ");
        int orderId = getIntInput();
        
        orderService.cancelOrder(orderId);
        System.out.println("✓ Order cancelled successfully!");
    }

    // ============ UTILITY METHODS ============
    private int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.print("Invalid input. Please enter a number: ");
            return getIntInput();
        }
    }

    private double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.print("Invalid input. Please enter a valid number: ");
            return getDoubleInput();
        }
    }

    public static void main(String[] args) {
        EcommerceApplication app = new EcommerceApplication();
        app.start();
    }
}
