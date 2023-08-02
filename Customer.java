import java.util.HashMap;
import java.util.Map;

/**
 * The Customer class represents a customer who can add items to their shopping cart.
 * It provides methods to add items to the cart, retrieve the customer's name, and clear the cart.
 */
public class Customer {

    private String name;
    private Map<String, Integer> cart;

    /**
     * Creates a new Customer instance with the specified name.
     *
     * @param name The name of the customer.
     */
    public Customer(String name) {
        this.name = name;
        this.cart = new HashMap<>();
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the shopping cart of the customer.
     * The cart is represented as a map of item names to their quantities.
     *
     * @return The customer's shopping cart.
     */
    public Map<String, Integer> getCart() {
        return this.cart;
    }

    /**
     * Adds an item to the customer's shopping cart.
     * If the item is already in the cart, the quantity will be incremented by one.
     *
     * @param itemName The name of the item to add to the cart.
     */
    public void addItemToCart(String itemName) {
        cart.put(itemName, cart.getOrDefault(itemName, 0) + 1);
    }

    /**
     * Clears the customer's shopping cart, removing all items.
     */
    public void clearCart() {
        cart.clear();
    }
}
