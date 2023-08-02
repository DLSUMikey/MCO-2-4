import java.util.HashMap;
import java.util.Map;

public class SpecialVendingMachine extends VendingMachine {
    private Customer customer;

    public SpecialVendingMachine(String name, int maxSlots, int maxStocks, Customer customer) {
        super(name, maxSlots, maxStocks);
        this.customer = customer;
    }

    @Override
    public void performVending() {
        System.out.println("Special Vending Machine is vending.");
    }

    public Map<String, String> isValidSandwichCart(Map<String, Integer> cart) {
        String selectedBread = null;
        String selectedMeat = null;
    
        // Iterate through cart items
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemName = entry.getKey();
    
            // Check if item is a kind of bread
            if (itemName.equalsIgnoreCase("Garlic Bread") || 
                itemName.equalsIgnoreCase("Wheat Bread") || 
                itemName.equalsIgnoreCase("White Bread")) {
                    selectedBread = itemName;
            }
    
            // Check if item is a kind of meat
            if (itemName.equalsIgnoreCase("Bacon Strips") || 
                itemName.equalsIgnoreCase("Ham Strips") || 
                itemName.equalsIgnoreCase("Turkey Strips") || 
                itemName.equalsIgnoreCase("Sausage")) {
                    selectedMeat = itemName;
            }
        }
    
        if (selectedBread != null && selectedMeat != null) {
            Map<String, String> result = new HashMap<>();
            result.put("selectedBread", selectedBread);
            result.put("selectedMeat", selectedMeat);
            return result;
        } else {
            return null;
        }
    }
    

    

    public Map<String, ItemWithStock> getAvailableToppings() {
        Map<String, ItemWithStock> availableToppings = new HashMap<>();

        for (Map.Entry<String, ItemWithStock> entry : this.getItemsWithStock().entrySet()) {
            String itemName = entry.getKey();
            ItemWithStock itemWithStock = entry.getValue();

            if (!itemWithStock.getItem().isSaleable() && itemWithStock.getStock() > 0) {
                availableToppings.put(itemName, itemWithStock);
            }
        }

        return availableToppings;
    }

    public void openToppingsSelectionInterface(Map<String, ItemWithStock> availableToppings, String selectedBread, String selectedMeat) {
        // Create an instance of ProductView and display it
        ProductView productView = new ProductView(availableToppings, customer, selectedMeat, selectedMeat);
        productView.setVisible(true);
    }
}
