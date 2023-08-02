import java.util.HashMap;
import java.util.Map;

public class Customer {

    private String name;
    private Map<String, Integer> cart;

    public Customer(String name) {
        this.name = name;
        this.cart = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getCart() {
        return cart;
    }

    public void addItemToCart(String itemName) {
        // Add item to the cart. 
        // If the item already exists in the cart, increment the quantity.
        cart.put(itemName, cart.getOrDefault(itemName, 0) + 1);
    }

    public void clearCart() {
        cart.clear();
    }

    // ... Additional methods as needed.
}