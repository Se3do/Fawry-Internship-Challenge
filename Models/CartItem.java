package Models;

import Interfaces.Shippable;

public class CartItem {
    private final Product product;
    private final int quantity;
    
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getCost() {
        return (product.getPrice() * (double)quantity);
    }

    public boolean isShippable() {
        return product instanceof Shippable;
    }
}

