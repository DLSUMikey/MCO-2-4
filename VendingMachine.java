import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class VendingMachine {
    private String name;
    private int maxSlots;
    private int maxStocks;
    private Map<String, ItemWithStock> itemsWithStock = new LinkedHashMap<>(); // Use a map to store items with placeholder names and stock
    protected Currency currency;

    public VendingMachine(String name, int maxSlots, int maxStocks) {
        this(name, maxSlots, maxStocks, new Currency(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        createDefaultItems(); // Call a method to create default placeholder items
    }

    public VendingMachine(String name, int maxSlots, int maxStocks, Currency currency) {
        this.name = name;
        this.maxSlots = maxSlots;
        this.maxStocks = maxStocks;
        this.currency = currency;
        this.itemsWithStock = new HashMap<>();
    }

        // Getters
    public String getName() {
        return name;
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public int getMaxStocks() {
        return maxStocks;
    }


    public abstract Currency getCurrency();

    public void refillCurrency(String denomination, int quantityToRefill) {
        switch (denomination) {
            case "Thousands":
                currency.setThousands(currency.getThousands() + quantityToRefill);
                break;
            case "Five Hundreds":
                currency.setFiveHundreds(currency.getFiveHundreds() + quantityToRefill);
                break;
            case "Hundreds":
                currency.setHundreds(currency.getHundreds() + quantityToRefill);
                break;
            // Add cases for other denominations
            default:
                System.out.println("Invalid denomination: " + denomination);
        }
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }

    public void setMaxStocks(int maxStocks) {
        this.maxStocks = maxStocks;
    }


    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public abstract void performVending();
    
    // Additional method to add an item to the vending machine
    public void addItem(Item item) {
        // Check if an item with the same name already exists
        ItemWithStock existingItemWithStock = itemsWithStock.get(item.getName());
        if (existingItemWithStock != null) {
            // If the item already exists, increase its stock
            existingItemWithStock.setStock(existingItemWithStock.getStock() + 1);
        } else {
            // If the item does not exist, add it to the map
            itemsWithStock.put(item.getName(), new ItemWithStock(item, 1));
        }
    }

    // Create default placeholder items with stock 0
    private void createDefaultItems() {
        Item placeholder1 = new Item("Garlic Bread", 120, 30.00, true);
        Item placeholder2 = new Item("Wheat Bread", 90, 35.00, true);
        Item placeholder3 = new Item("White Bread", 150, 20.50, true);
        Item placeholder4 = new Item("Cheese", 100, 20.00, true);
        Item placeholder5 = new Item("Bacon Strips", 90, 50.00, true);
        Item placeholder6 = new Item("Ham Strips", 90, 40.50, true);
        Item placeholder7 = new Item("Lettuce", 10, 40.00, true);
        Item placeholder8 = new Item("Pickles", 10, 15.50, true);
        Item placeholder9 = new Item("Mustard", 5, 5.00, false);
        Item placeholder10 = new Item("Ketchup", 20, 20.00, false);
        Item placeholder11 = new Item("Onion",10, 10.00, false);
        Item placeholder12 = new Item("Turkey Strips", 30, 40.00, false);
        Item placeholder13 = new Item("Sausage", 90, 30.00, false);
        Item placeholder14 = new Item("Cucumber", 15, 20.00, false);
        Item placeholder15 = new Item("Mayonnaise", 90, 5.00, false);
    
        // Store placeholder items in the map with stock 0
        itemsWithStock.put("Garlic Bread", new ItemWithStock(placeholder1, 0));
        itemsWithStock.put("Wheat Bread", new ItemWithStock(placeholder2, 0));
        itemsWithStock.put("White Bread", new ItemWithStock(placeholder3, 0));
        itemsWithStock.put("Cheese", new ItemWithStock(placeholder4, 0));
        itemsWithStock.put("Bacon Strips", new ItemWithStock(placeholder5, 0));
        itemsWithStock.put("Ham Strips", new ItemWithStock(placeholder6, 0));
        itemsWithStock.put("Lettuce", new ItemWithStock(placeholder7, 0));
        itemsWithStock.put("Pickles", new ItemWithStock(placeholder8, 0));
        itemsWithStock.put("Mustard", new ItemWithStock(placeholder9, 0));
        itemsWithStock.put("Ketchup", new ItemWithStock(placeholder10, 0));
        itemsWithStock.put("Onion", new ItemWithStock(placeholder11, 0));
        itemsWithStock.put("Turkey Strips", new ItemWithStock(placeholder12, 0));
        itemsWithStock.put("Sausage", new ItemWithStock(placeholder13, 0));
        itemsWithStock.put("Cucumber", new ItemWithStock(placeholder14, 0));
        itemsWithStock.put("Mayonnaise", new ItemWithStock(placeholder15, 0));
    }


    public Map<String, ItemWithStock> getItemsWithStock() {
        return itemsWithStock;
    }

    // Restock an item by adding the specified quantity to its stock or add new item if not found
    public void restockItem(String itemName, int quantityToAdd) {
        ItemWithStock itemWithStock = itemsWithStock.get(itemName);
        if (itemWithStock != null) {
            itemWithStock.setStock(itemWithStock.getStock() + quantityToAdd);
            System.out.println(itemWithStock.getItem().getName() + " has been restocked. Current stock: " + itemWithStock.getStock());
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }

    // Display the items available in the vending machine along with their stocks
    public void displayItems() {
        System.out.println("Items available in " + name + ":");
        for (Map.Entry<String, ItemWithStock> entry : itemsWithStock.entrySet()) {
            String itemName = entry.getKey();
            ItemWithStock itemWithStock = entry.getValue();
            System.out.println(itemName + " - Price: $" + itemWithStock.getItem().getPrice() +
                    " - Stock: " + itemWithStock.getStock());
        }
    }

}
