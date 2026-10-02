package com.kodewala.ecommerce.service;

import com.kodewala.ecommerce.model.Product;
import com.kodewala.ecommerce.repository.ProductRepository;
import com.kodewala.ecommerce.exception.ProductNotFoundException;
import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(int productId, String productName, String category, double price, int quantity, String brand) {
        Product product = new Product(productId, productName, category, price, quantity, brand);
        productRepository.addProduct(product);
    }

    public Product getProductById(int productId) throws ProductNotFoundException {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchByName(String productName) {
        return productRepository.findByName(productName);
    }

    public List<Product> searchByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> searchByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice);
    }

    public List<Product> getAllProductsSortedByPrice() {
        return productRepository.findAll().stream()
                .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .toList();
    }

    public void updatePrice(int productId, double newPrice) throws ProductNotFoundException {
        if (!productRepository.updatePrice(productId, newPrice)) {
            throw new ProductNotFoundException(productId);
        }
    }

    public void updateQuantity(int productId, int newQuantity) throws ProductNotFoundException {
        if (!productRepository.updateQuantity(productId, newQuantity)) {
            throw new ProductNotFoundException(productId);
        }
    }

    public void deleteProduct(int productId) throws ProductNotFoundException {
        if (!productRepository.delete(productId)) {
            throw new ProductNotFoundException(productId);
        }
    }

    public boolean decreaseQuantity(int productId, int quantity) {
        return productRepository.decreaseQuantity(productId, quantity);
    }
}
