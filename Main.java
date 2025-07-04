import java.time.LocalDate;
import Models.*;
import Services.CheckoutService;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Laptop", 1000.00, 5);
        Product product2 = new ExpirableProduct("Banana", 13.2, 20, LocalDate.now().plusDays(30));
        Product product3 = new ShippableProduct("Book", 20.00, 10, 0.2);
        Product product4 = new ShippableExpirableProduct("Milk", 5.00, 15, LocalDate.now().plusDays(30), 1.0);
        Product product5 = new Product("Pen", 1.50, 100);
        Product product6 = new ShippableProduct("Notebook", 3.00, 50, 0.5);
        
        Customer customer = new Customer("Mohammed", "Mohammedsaid341@gmail.com", 5000.00);

        Cart cart = new Cart();
        try {
            cart.add(product1, 2);
            cart.add(product2, 3);
            cart.add(product3, 5);
            cart.add(product4, 2);
            cart.add(product5, 10);
            cart.add(product6, 4);

            CheckoutService.checkout(cart, customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}