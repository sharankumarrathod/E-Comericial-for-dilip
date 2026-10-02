package com.kodewala.ecommerce.repository;

import com.kodewala.ecommerce.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Optional<Product> findById(int productId) {
        return products.stream()
                .filter(p -> p.getProductId() == productId)
                .findFirst();
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public List<Product> findByName(String productName) {
        return products.stream()
                .filter(p -> p.getProductName().toLowerCase().contains(productName.toLowerCase()))
                .toList();
    }

    public List<Product> findByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public List<Product> findByBrand(String brand) {
        return products.stream()
                .filter(p -> p.getBrand().equalsIgnoreCase(brand))
                .toList();
    }

    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        return products.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .toList();
    }

    public boolean updatePrice(int productId, double newPrice) {
        return products.stream()
                .filter(p -> p.getProductId() == productId)
                .peek(p -> p.setPrice(newPrice))
                .findFirst()
                .isPresent();
    }

    public boolean updateQuantity(int productId, int newQuantity) {
        return products.stream()
                .filter(p -> p.getProductId() == productId)
                .peek(p -> p.setQuantity(newQuantity))
                .findFirst()
                .isPresent();
    }

    public boolean delete(int productId) {
        return products.removeIf(p -> p.getProductId() == productId);
    }

    public boolean decreaseQuantity(int productId, int quantity) {
        Optional<Product> product = findById(productId);
        if (product.isPresent() && product.get().getQuantity() >= quantity) {
            product.get().setQuantity(product.get().getQuantity() - quantity);
            return true;
        }
        return false;
    }
}
