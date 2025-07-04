package Services;

import java.util.List;
import Models.CartItem;
import Interfaces.Shippable;

public class ShippingService {
    public static void ship(List<CartItem> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (CartItem item : items) {
            Shippable p = (Shippable) item.getProduct();
            System.out.printf("%dx %s %.0fg\n", item.getQuantity(), item.getProduct().getName(), p.getWeight()*item.getQuantity()* 1000);
            totalWeight += p.getWeight()*item.getQuantity();
        }
        System.out.printf("Total package weight %.1fkg\n\n", totalWeight);
    }
}
