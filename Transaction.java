import java.util.HashMap;
import java.util.Map;

/**
 * The Transaction class represents a record of a vending machine transaction.
 * It stores the purchased items, total price, and total calories of the transaction.
 */
public class Transaction {
    private Map<Item, Integer> items;
    private double totalPrice;
    private int totalCalories;

    /**
     * Constructs a Transaction object representing a vending machine transaction.
     *
     * @param cart           A map containing the items and their quantities in the cart.
     * @param totalPrice     The total price of the transaction.
     * @param vendingMachine The VendingMachine instance associated with the transaction.
     */
    public Transaction(Map<String, Integer> cart, double totalPrice, VendingMachine vendingMachine) {
        this.items = new HashMap<>();

        // Convert the cart items to Item objects and populate the transaction items.
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();

            ItemWithStock itemWithStock = vendingMachine.getItemsWithStock().get(itemName);
            if (itemWithStock != null) {
                this.items.put(itemWithStock.getItem(), quantity);
            }
        }

        // Calculate the total price and total calories for the transaction.
        this.totalPrice = items.entrySet().stream()
                               .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                               .sum();

        this.totalCalories = items.entrySet().stream()
                                  .mapToInt(entry -> entry.getKey().getCalories() * entry.getValue())
                                  .sum();
    }

    /**
     * Get the map of items and their quantities in the transaction.
     *
     * @return A map of Item objects and their quantities.
     */
    public Map<Item, Integer> getItems() {
        return items;
    }

    /**
     * Get the total price of the transaction.
     *
     * @return The total price of the transaction.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Get the total calories of the transaction.
     *
     * @return The total calories of the transaction.
     */
    public int getTotalCalories() {
        return totalCalories;
    }

    /**
     * Returns a string representation of the transaction, including purchased items, quantities,
     * total price, and total calories.
     *
     * @return A string representation of the transaction.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction:\n");

        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            sb.append("Item: ").append(entry.getKey().getName());
            sb.append(", Quantity: ").append(entry.getValue()).append("\n");
        }

        sb.append("Total Price: ").append(totalPrice).append("\n");
        sb.append("Total Calories: ").append(totalCalories);

        return sb.toString();
    }
}

