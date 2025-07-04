package Models;

import java.time.LocalDate;
import Interfaces.Shippable;

public class ShippableExpirableProduct extends ExpirableProduct implements Shippable {
    private double weight;
    public ShippableExpirableProduct(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        if(weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}