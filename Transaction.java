import java.util.HashMap;
import java.util.Map;

public class Transaction {
    private Map<Item, Integer> items; // Changed from String to Item
    private double totalPrice;
    private int totalCalories;

    public Transaction(Map<Item, Integer> items, double totalPrice) { // Changed from String to Item
        this.items = new HashMap<>(items);
        this.totalPrice = totalPrice;

        // calculate total calories
        this.totalCalories = items.entrySet().stream()
                                  .mapToInt(entry -> entry.getKey().getCalories() * entry.getValue())
                                  .sum();
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "items=" + items +
                ", totalPrice=" + totalPrice +
                ", totalCalories=" + totalCalories +
                '}';
    }
}
