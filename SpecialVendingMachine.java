import java.util.HashMap;
import java.util.Map;

public class SpecialVendingMachine extends VendingMachine {
    public SpecialVendingMachine(String name, int maxSlots, int maxStocks) {
        super(name, maxSlots, maxStocks);
    }

    @Override
    public void performVending() {
        System.out.println("Special Vending Machine is vending.");
    }
    public boolean isValidSandwichCart(Map<String, Integer> cart) {
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

        return selectedBread != null && selectedMeat != null;
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

    private void openToppingsSelectionInterface(Map<String, ItemWithStock> availableToppings) {
        // Create an instance of ProductView and display it
        ProductView productView = new ProductView(availableToppings);
        productView.setVisible(true);
    }
}
