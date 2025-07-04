package Models;

import Interfaces.Shippable;

public class ShippableProduct extends Product implements Shippable {
    private double weight;
    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}