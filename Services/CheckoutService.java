package Services;

import java.util.ArrayList;
import java.util.List;
import Models.*;

public class CheckoutService {
    
    public static void checkout(Cart cart, Customer customer) {
        try {
                processCheckout (cart, customer);
        } catch (RuntimeException e) {
                System.err.println("Checkout failed: " + e.getMessage());
        }
    }

    private static void processCheckout (Cart cart, Customer customer) {
        if (cart == null) {
            throw new RuntimeException("Cart is not provided!");
        }
        if(customer == null) {
            throw new RuntimeException("Customer is not provided!");
        }
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        double subtotal = 0;
        List<CartItem> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            subtotal += item.getCost();

            if (item.isShippable()) {
                shippableItems.add(item);
            }
        }

        double shippingFee = shippableItems.isEmpty() ? 0 : 30;
        double total = subtotal + shippingFee;

        if (!customer.purchase(total))
            throw new RuntimeException("Insufficient balance!");
        
        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems);
        }

        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            p.reduceQuantity(item.getQuantity());
        }

        PrintReceipt(cart, customer, subtotal, shippingFee);
    }

    private static void PrintReceipt(Cart cart, Customer customer, double subtotal, double shippingFee) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.2f\n", item.getQuantity(), item.getProduct().getName(), item.getCost());
        }
        System.out.println("------------------");
        System.out.printf("Subtotal %.2f\n", subtotal);
        if (shippingFee > 0) {
            System.out.printf("Shipping %.2f\n", shippingFee);
        }
        System.out.printf("Amount %.2f\n", (subtotal + shippingFee));
    }
}