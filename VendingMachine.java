import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code VendingMachine} abstract class represents a generic vending machine with common properties and operations.
 * It provides methods to manage items, currency, and perform vending operations.
 */
public abstract class VendingMachine {

    private String name;
    private int maxSlots;
    private int maxStocks;
    private Map<String, ItemWithStock> itemsWithStock = new LinkedHashMap<>();
    protected Currency currency;
    private final List<ChangeListener> changeListeners = new ArrayList<>();
    private List<Transaction> transactions;

    /**
     * Adds a {@code ChangeListener} to the vending machine.
     *
     * @param changeListener The {@code ChangeListener} to be added.
     */
    public void addChangeListener(ChangeListener changeListener) {
        changeListeners.add(changeListener);
    }

    /**
     * Notifies all registered change listeners about a state change.
     */
    private void notifyChangeListeners() {
        for (ChangeListener listener : changeListeners) {
            listener.stateChanged();
        }
    }

    /**
     * Creates a new {@code VendingMachine} with the given name, maxSlots, and maxStocks, and initializes the default items.
     *
     * @param name      The name of the vending machine.
     * @param maxSlots  The maximum number of slots the vending machine can have.
     * @param maxStocks The maximum quantity of items a slot can hold.
     */
    public VendingMachine(String name, int maxSlots, int maxStocks) {
        this(name, maxSlots, maxStocks, new Currency(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        createDefaultItems();
    }

    /**
     * Creates a new {@code VendingMachine} with the given name, maxSlots, maxStocks, and currency.
     *
     * @param name      The name of the vending machine.
     * @param maxSlots  The maximum number of slots the vending machine can have.
     * @param maxStocks The maximum quantity of items a slot can hold.
     * @param currency  The currency for the vending machine.
     */
    public VendingMachine(String name, int maxSlots, int maxStocks, Currency currency) {
        this.name = name;
        this.maxSlots = maxSlots;
        this.maxStocks = maxStocks;
        this.currency = currency;
        this.itemsWithStock = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    /**
     * Gets the name of the vending machine.
     *
     * @return The name of the vending machine.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the maximum number of slots in the vending machine.
     *
     * @return The maximum number of slots.
     */
    public int getMaxSlots() {
        return maxSlots;
    }

    /**
     * Gets the maximum quantity of items a slot can hold.
     *
     * @return The maximum quantity of items a slot can hold.
     */
    public int getMaxStocks() {
        return maxStocks;
    }

    /**
     * Gets the currency of the vending machine.
     *
     * @return The currency of the vending machine.
     */
    public Currency getCurrency() {
        return this.currency;
    }

    /**
     * Refills the currency storage of the vending machine for a given denomination and quantity.
     *
     * @param denomination    The denomination of currency to refill.
     * @param quantityToRefill The quantity of currency to add.
     */
    public void refillCurrency(String denomination, int quantityToRefill) {
        switch (denomination) {
            case "Thousands":
                currency.setThousands(currency.getThousands() + quantityToRefill);
                break;
            case "FiveHundreds":
                currency.setFiveHundreds(currency.getFiveHundreds() + quantityToRefill);
                break;
            case "Hundreds":
                currency.setHundreds(currency.getHundreds() + quantityToRefill);
                break;
            case "Fifties":
                currency.setFifties(currency.getFifties() + quantityToRefill);
                break;
            case "Twenties":
                currency.setTwenties(currency.getTwenties() + quantityToRefill);
                break;
            case "Tens":
                currency.setTens(currency.getTens() + quantityToRefill);
                break;
            case "Fives":
                currency.setFives(currency.getFives() + quantityToRefill);
                break;
            case "Ones":
                currency.setOnes(currency.getOnes() + quantityToRefill);
                break;
            case "HalfPeso":
                currency.setHalfPeso(currency.getHalfPeso() + quantityToRefill);
                break;
            case "Quarter":
                currency.setQuarter(currency.getQuarter() + quantityToRefill);
                break;
            case "Dime":
                currency.setDime(currency.getDime() + quantityToRefill);
                break;
            case "Nickel":
                currency.setNickel(currency.getNickel() + quantityToRefill);
                break;
            case "Penny":
                currency.setPenny(currency.getPenny() + quantityToRefill);
                break;
            default:
                System.out.println("Invalid denomination: " + denomination);
        }
    }

    /**
     * Adds a transaction to the vending machine.
     *
     * @param transaction The transaction to add.
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Gets a list of all transactions made on the vending machine.
     *
     * @return A list of all transactions.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Removes currency from the vending machine for a given denomination and quantity.
     *
     * @param denomination    The denomination of currency to remove.
     * @param quantityToRemove The quantity of currency to remove.
     * @throws Exception If there is not enough currency of the given denomination in stock.
     */
    public void removeCurrency(String denomination, int quantityToRemove) throws Exception {
        switch (denomination) {
            case "Thousands":
                if (currency.getThousands() >= quantityToRemove) {
                    currency.setThousands(currency.getThousands() - quantityToRemove);
                } else {
                    throw new Exception("Not enough Thousands in stock");
                }
                break;
            case "FiveHundreds":
                if (currency.getFiveHundreds() >= quantityToRemove) {
                    currency.setFiveHundreds(currency.getFiveHundreds() - quantityToRemove);
                } else {
                    throw new Exception("Not enough FiveHundreds in stock");
                }
                break;
            case "Hundreds":
                if (currency.getHundreds() >= quantityToRemove) {
                    currency.setHundreds(currency.getHundreds() - quantityToRemove);
                } else {
                    throw new Exception("Not enough Hundreds in stock");
                }
                break;
            // ... (remaining cases for other denominations)
            default:
                throw new Exception("Invalid denomination: " + denomination);
        }
    }

    /**
     * Sets the name of the vending machine.
     *
     * @param name The name of the vending machine.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the maximum number of slots in the vending machine.
     *
     * @param maxSlots The maximum number of slots.
     */
    public void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }

    /**
     * Sets the maximum quantity of items a slot can hold.
     *
     * @param maxStocks The maximum quantity of items a slot can hold.
     */
    public void setMaxStocks(int maxStocks) {
        this.maxStocks = maxStocks;
    }

    /**
     * Sets the currency of the vending machine.
     *
     * @param currency The currency of the vending machine.
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Abstract method to perform a vending operation specific to each vending machine type.
     */
    public abstract void performVending();

    /**
     * Adds an item to the vending machine.
     *
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        ItemWithStock existingItemWithStock = itemsWithStock.get(item.getName());
        if (existingItemWithStock != null) {
            existingItemWithStock.setStock(existingItemWithStock.getStock() + 1);
        } else {
            itemsWithStock.put(item.getName(), new ItemWithStock(item, 1));
        }
        notifyChangeListeners();
    }

    /**
     * Removes an item from the vending machine.
     *
     * @param itemName The name of the item to be removed.
     */
    public void removeItem(String itemName) {
        ItemWithStock itemWithStock = itemsWithStock.get(itemName);
        if (itemWithStock != null) {
            if (itemWithStock.getStock() > 1) {
                itemWithStock.setStock(itemWithStock.getStock() - 1);
                System.out.println(itemName + " removed. Current stock: " + itemWithStock.getStock());
            } else {
                itemsWithStock.remove(itemName);
                System.out.println(itemName + " removed. The item is now out of stock.");
            }
            notifyChangeListeners();
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }

    /**
     * Processes the cart by removing the items and updating the stock based on the quantity.
     *
     * @param cart A map containing item names as keys and their respective quantities as values.
     */
    public void processCart(Map<String, Integer> cart) {
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            try {
                for (int i = 0; i < quantity; i++) {
                    removeItem(itemName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates default items for the vending machine and adds them to the stock.
     */
    private void createDefaultItems() {
        Item placeholder1 = new Item("Garlic Bread", 120, 30.00, true);
        Item placeholder2 = new Item("Wheat Bread", 90, 35.00, true);
        Item placeholder3 = new Item("White Bread", 150, 20.50, true);
        Item placeholder4 = new Item("Cheese", 100, 20.00, false);
        Item placeholder5 = new Item("Bacon Strips", 90, 50.00, true);
        Item placeholder6 = new Item("Ham Strips", 90, 40.50, true);
        Item placeholder7 = new Item("Lettuce", 10, 40.00, false);
        Item placeholder8 = new Item("Pickles", 10, 15.50, false);
        Item placeholder9 = new Item("Mustard", 5, 5.00, false);
        Item placeholder10 = new Item("Ketchup", 20, 20.00, false);
        Item placeholder11 = new Item("Onion", 10, 10.00, false);
        Item placeholder12 = new Item("Turkey Strips", 30, 40.00, true);
        Item placeholder13 = new Item("Sausage", 90, 30.00, true);
        Item placeholder14 = new Item("Cucumber", 15, 20.00, false);
        Item placeholder15 = new Item("Mayonnaise", 90, 5.00, false);

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

    /**
     * Gets the map of items with their corresponding stock in the vending machine.
     *
     * @return A map of items with their stock.
     */
    public Map<String, ItemWithStock> getItemsWithStock() {
        return itemsWithStock;
    }

    /**
     * Restocks the specified item in the vending machine by the given quantity.
     *
     * @param itemName      The name of the item to be restocked.
     * @param quantityToAdd The quantity to be added to the item stock.
     */
    public void restockItem(String itemName, int quantityToAdd) {
        ItemWithStock itemWithStock = itemsWithStock.get(itemName);
        if (itemWithStock != null) {
            itemWithStock.setStock(itemWithStock.getStock() + quantityToAdd);
            System.out.println(itemWithStock.getItem().getName() + " has been restocked. Current stock: " + itemWithStock.getStock());
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }
}
