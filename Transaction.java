import java.util.HashMap;
import java.util.Map;

public class Transaction {
    private Map<Item, Integer> items;
    private double totalPrice;
    private int totalCalories;

    public Transaction(Map<String, Integer> cart, double totalPrice2, VendingMachine vendingMachine) {
        this.items = new HashMap<>();
        
        // convert cart items from Map<String, Integer> to Map<Item, Integer>
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();

            ItemWithStock itemWithStock = vendingMachine.getItemsWithStock().get(itemName);
            if (itemWithStock != null) {
                this.items.put(itemWithStock.getItem(), quantity);
            }
        }

        this.totalPrice = items.entrySet().stream()
                               .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                               .sum();
                               
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction:\n");
    
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            sb.append("Item: ")
              .append(entry.getKey().getName())
              .append(", Quantity: ")
              .append(entry.getValue())
              .append("\n");
        }
    
        sb.append("Total Price: ").append(totalPrice);
        sb.append("Total Calories: ").append(totalCalories);
    
        return sb.toString();
    }
}
