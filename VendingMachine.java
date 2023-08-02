import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
    public abstract void addItem(Item item);

    // Create default placeholder items with stock 0
    private void createDefaultItems() {
        Item placeholder1 = new Item("GarlicBread", 120, 30.00, true);
        Item placeholder2 = new Item("WheatBread", 90, 35.00, true);
        Item placeholder3 = new Item("WhiteBread", 150, 20.50, true);
        Item placeholder4 = new Item("Cheese", 100, 20.00, true);
        Item placeholder5 = new Item("BaconStrips", 90, 50.00, true);
        Item placeholder6 = new Item("HamStrips", 90, 40.50, true);
        Item placeholder7 = new Item("Lettuce", 10, 40.00, true);
        Item placeholder8 = new Item("Pickles", 10, 15.50, true);
        Item placeholder9 = new Item("Mustard", 5, 5.00, false);
        Item placeholder10 = new Item("Ketchup", 20, 20.00, false);
        Item placeholder11 = new Item("Onion",10, 10.00, false);
        Item placeholder12 = new Item("TurkeyStrips", 30, 40.00, false);
        Item placeholder13 = new Item("Sausage", 90, 30.00, false);
        Item placeholder14 = new Item("Cucumber", 15, 20.00, false);
        Item placeholder15 = new Item("Mayonnaise", 90, 5.00, false);

        // Store placeholder items in the map with stock 0
        itemsWithStock.put("Item 1", new ItemWithStock(placeholder1, 0));
        itemsWithStock.put("Item 2", new ItemWithStock(placeholder2, 0));
        itemsWithStock.put("Item 3", new ItemWithStock(placeholder3, 0));
        itemsWithStock.put("Item 4", new ItemWithStock(placeholder4, 0));
        itemsWithStock.put("Item 5", new ItemWithStock(placeholder5, 0));
        itemsWithStock.put("Item 6", new ItemWithStock(placeholder6, 0));
        itemsWithStock.put("Item 7", new ItemWithStock(placeholder7, 0));
        itemsWithStock.put("Item 8", new ItemWithStock(placeholder8, 0));
        itemsWithStock.put("Item 9", new ItemWithStock(placeholder9, 0));
        itemsWithStock.put("Item 10", new ItemWithStock(placeholder10, 0));
        itemsWithStock.put("Item 11", new ItemWithStock(placeholder11, 0));
        itemsWithStock.put("Item 12", new ItemWithStock(placeholder12, 0));
        itemsWithStock.put("Item 13", new ItemWithStock(placeholder13, 0));
        itemsWithStock.put("Item 14", new ItemWithStock(placeholder14, 0));
        itemsWithStock.put("Item 15", new ItemWithStock(placeholder15, 0));

    }

    public Map<String, ItemWithStock> getItemsWithStock() {
        return itemsWithStock;
    }

    // Restock an item by adding the specified quantity to its stock or add new item if not found
    public void restockItem(int itemIndex, int quantityToAdd) {
    List<ItemWithStock> itemList = new ArrayList<>(itemsWithStock.values());

    if (itemIndex >= 0 && itemIndex < itemList.size()) {
        ItemWithStock itemWithStock = itemList.get(itemIndex);
        itemWithStock.setStock(itemWithStock.getStock() + quantityToAdd);
        System.out.println(itemWithStock.getItem().getName() + " has been restocked. Current stock: " + itemWithStock.getStock());
    } else {
        System.out.println("Invalid item index: " + itemIndex);
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
