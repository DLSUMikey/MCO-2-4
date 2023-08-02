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
    private final List<ChangeListener> changeListeners = new ArrayList<>();
    private List<Transaction> transactions;

    public void addChangeListener(ChangeListener changeListener) {
        changeListeners.add(changeListener);
    }
    
    private void notifyChangeListeners() {
        for (ChangeListener listener : changeListeners) {
            listener.stateChanged();
        }
    }

    public VendingMachine(String name, int maxSlots, int maxStocks) {
        this(name, maxSlots, maxStocks, new Currency(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        createDefaultItems(); // Call a method to create default placeholder items
    }

    public VendingMachine(String name, int maxSlots, int maxStocks, Currency currency) {
        this.name = name;
        this.maxSlots = maxSlots;
        this.maxStocks = maxStocks;
        this.currency = currency;
        this.itemsWithStock = new HashMap<>();
        this.transactions = new ArrayList<>();
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


    public Currency getCurrency() {
        return this.currency;
    };
    

    public void refillCurrency(String denomination, int quantityToRefill) {
        switch (denomination) {
            case "Thousands":
                currency.setThousands(currency.getThousands()+ quantityToRefill);
                break;
            case"FiveHundreds":
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

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public VendingMachine() {
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public void removeCurrency(String denomination, int quantityToRemove) throws Exception{
        switch (denomination) {
            case "Thousands":
                if (currency.getThousands() >= quantityToRemove) {
                    currency.setThousands(currency.getThousands() - quantityToRemove);
                } else {
                    System.out.println("Not enough Thousands in stock");
                }
                break;
            case"FiveHundreds":
                if (currency.getFiveHundreds() >= quantityToRemove) {
                    currency.setFiveHundreds(currency.getFiveHundreds() - quantityToRemove);
                } else {
                    System.out.println("Not enough Thousands in stock");
                }
            case "Hundreds":
                if (currency.getHundreds() >= quantityToRemove) {
                    currency.setHundreds(currency.getHundreds() - quantityToRemove);
                } else {
                    System.out.println("Not enough Hundreds in stock");
                }
                break;
            case "Fifties":
                if (currency.getFifties() >= quantityToRemove) {
                    currency.setFifties(currency.getFifties() - quantityToRemove);
                } else {
                    System.out.println("Not enough Fifties in stock");
                }
                break;
            case "Twenties":
                if (currency.getTwenties() >= quantityToRemove) {
                    currency.setTwenties(currency.getTwenties() - quantityToRemove);
                } else {
                    System.out.println("Not enough Twenties in stock");
                }
                break;
            case "Tens":
                if (currency.getTens() >= quantityToRemove) {
                    currency.setTens(currency.getTens() - quantityToRemove);
                } else {
                    System.out.println("Not enough Tens in stock");
                }
                break;
            case "Fives":
                if (currency.getFives() >= quantityToRemove) {
                    currency.setFives(currency.getFives() - quantityToRemove);
                } else {
                    System.out.println("Not enough Fives in stock");
                }
                break;
            case "Ones":
                if (currency.getOnes() >= quantityToRemove) {
                    currency.setOnes(currency.getOnes() - quantityToRemove);
                } else {
                    System.out.println("Not enough Ones in stock");
                }
                break;
            case "HalfPeso":
                if (currency.getHalfPeso() >= quantityToRemove) {
                    currency.setHalfPeso(currency.getHalfPeso() - quantityToRemove);
                } else {
                    System.out.println("Not enough Dimes in stock");
                }
                break;
            case "Quarter":
                if (currency.getQuarter() >= quantityToRemove) {
                    currency.setQuarter(currency.getQuarter() - quantityToRemove);
                } else {
                    System.out.println("Not enough Quarters in stock");
                }
                break;
            case "Dime":
                if (currency.getDime() >= quantityToRemove) {
                    currency.setDime(currency.getDime() - quantityToRemove);
                } else {
                    System.out.println("Not enough Dimes in stock");
                }
                break;
            case "Nickel":
                if (currency.getNickel() >= quantityToRemove) {
                    currency.setNickel(currency.getNickel() - quantityToRemove);
                } else {
                    System.out.println("Not enough Nickels in stock");
                }
                break;
            case "Penny":
                if (currency.getPenny() >= quantityToRemove) {
                    currency.setPenny(currency.getPenny() - quantityToRemove);
                } else {
                    System.out.println("Not enough Pennies in stock");
                }
                break;
            default:
                throw new Exception("Invalid denomination: " + denomination);
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
        notifyChangeListeners();
    }

    public void removeItem(String itemName) {
        // Get the item with stock from the map
        ItemWithStock itemWithStock = itemsWithStock.get(itemName);
        if (itemWithStock != null) {
            // Check if the stock is more than 1
            if (itemWithStock.getStock() > 1) {
                // If the stock is more than 1, reduce the stock
                itemWithStock.setStock(itemWithStock.getStock() - 1);
                System.out.println(itemName + " removed. Current stock: " + itemWithStock.getStock());
            } else {
                // If the stock is 1 or less, remove the item from the map
                itemsWithStock.remove(itemName);
                System.out.println(itemName + " removed. The item is now out of stock.");
            }
            notifyChangeListeners();
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }

    public void processCart(Map<String, Integer> cart) {
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            try {
                for (int i = 0; i < quantity; i++) {
                    removeItem(itemName);
                }
            } catch (Exception e) {
                // Handle exception if the item can't be removed
                e.printStackTrace();
            }
        }
    }
    

    // Create default placeholder items with stock 0
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
        Item placeholder11 = new Item("Onion",10, 10.00, false);
        Item placeholder12 = new Item("Turkey Strips", 30, 40.00, true);
        Item placeholder13 = new Item("Sausage", 90, 30.00, true);
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


}
