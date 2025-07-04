package Models;

import java.util.ArrayList;
import java.util.List;
import Interfaces.Shippable;

public class Cart {
    private List<CartItem> items = new ArrayList<>();
    private double totalWeight = 0.0;

    public void add(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        }
        if( product instanceof ExpirableProduct) {
            ExpirableProduct expirableProduct = (ExpirableProduct) product;
            if (expirableProduct.isExpired()) {
                throw new IllegalArgumentException("Cannot add expired product: " + expirableProduct.getName());
            }
        }
        CartItem NewItem = new CartItem(product, quantity);
        items.add(NewItem);
        if(NewItem.isShippable()) {
            Shippable shippable = (Shippable) product;
            totalWeight += shippable.getWeight() * quantity;
        }
    }

    public Boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotalCost() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getCost();
        }
        return total;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
}