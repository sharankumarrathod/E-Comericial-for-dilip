package com.kodewala.ecommerce.service;

import com.kodewala.ecommerce.model.CartItem;
import com.kodewala.ecommerce.model.Product;
import com.kodewala.ecommerce.exception.InvalidQuantityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CartService {
    private Map<Integer, List<CartItem>> customerCarts;

    public CartService() {
        this.customerCarts = new HashMap<>();
    }

    public void addProductToCart(int customerId, Product product, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException(quantity);
        }

        List<CartItem> cart = customerCarts.computeIfAbsent(customerId, k -> new ArrayList<>());

        Optional<CartItem> existingItem = cart.stream()
                .filter(item -> item.getProductId() == product.getProductId())
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem(product.getProductId(), product.getProductName(), product.getPrice(), quantity);
            cart.add(cartItem);
        }
    }

    public void removeProductFromCart(int customerId, int productId) {
        List<CartItem> cart = customerCarts.get(customerId);
        if (cart != null) {
            cart.removeIf(item -> item.getProductId() == productId);
        }
    }

    public void increaseQuantity(int customerId, int productId, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException(quantity);
        }

        List<CartItem> cart = customerCarts.get(customerId);
        if (cart != null) {
            cart.stream()
                    .filter(item -> item.getProductId() == productId)
                    .findFirst()
                    .ifPresent(item -> item.setQuantity(item.getQuantity() + quantity));
        }
    }

    public void decreaseQuantity(int customerId, int productId, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException(quantity);
        }

        List<CartItem> cart = customerCarts.get(customerId);
        if (cart != null) {
            cart.stream()
                    .filter(item -> item.getProductId() == productId)
                    .findFirst()
                    .ifPresent(item -> {
                        int newQuantity = item.getQuantity() - quantity;
                        if (newQuantity > 0) {
                            item.setQuantity(newQuantity);
                        } else {
                            cart.remove(item);
                        }
                    });
        }
    }

    public List<CartItem> getCart(int customerId) {
        return customerCarts.getOrDefault(customerId, new ArrayList<>());
    }

    public double getCartTotal(int customerId) {
        List<CartItem> cart = getCart(customerId);
        return cart.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void clearCart(int customerId) {
        customerCarts.put(customerId, new ArrayList<>());
    }

    public boolean hasCart(int customerId) {
        return customerCarts.containsKey(customerId);
    }
}
