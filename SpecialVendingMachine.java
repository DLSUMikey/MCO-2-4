import java.util.HashMap;
import java.util.Map;

/**
 * The SpecialVendingMachine class represents a type of vending machine with special features for sandwich customization.
 * It is a subclass of the VendingMachine class and provides additional methods for sandwich cart validation and toppings selection.
 */
public class SpecialVendingMachine extends VendingMachine {
    private Customer customer;

    /**
     * Constructs a SpecialVendingMachine instance with the specified name, maximum slots, maximum stocks, and associated customer.
     *
     * @param name       The name of the special vending machine.
     * @param maxSlots   The maximum number of slots in the vending machine.
     * @param maxStocks  The maximum number of stocks per slot in the vending machine.
     * @param customer   The Customer instance associated with this vending machine.
     */
    public SpecialVendingMachine(String name, int maxSlots, int maxStocks, Customer customer) {
        super(name, maxSlots, maxStocks);
        this.customer = customer;
    }

    /**
     * Performs special vending operations specific to the SpecialVendingMachine.
     * This method is an implementation of the abstract method in the parent class.
     * It prints a message indicating that the Special Vending Machine is vending.
     */
    @Override
    public void performVending() {
        System.out.println("Special Vending Machine is vending.");
    }

    /**
     * Checks if the given sandwich cart contains valid selections for bread and meat.
     * The method iterates through the cart and identifies the selected bread and meat items.
     *
     * @param cart The sandwich cart containing item names and quantities.
     * @return A map containing the selected bread and meat items if both are present, null otherwise.
     */
    public Map<String, String> isValidSandwichCart(Map<String, Integer> cart) {
        String selectedBread = null;
        String selectedMeat = null;

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemName = entry.getKey();

            if (itemName.equalsIgnoreCase("Garlic Bread") ||
                    itemName.equalsIgnoreCase("Wheat Bread") ||
                    itemName.equalsIgnoreCase("White Bread")) {
                selectedBread = itemName;
            }

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

    /**
     * Retrieves the available toppings from the vending machine.
     * The method iterates through the items with stock and selects non-saleable toppings with available stock.
     *
     * @return A map containing the available toppings and their associated ItemWithStock objects.
     */
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

    /**
     * Opens the toppings selection interface for customizing the sandwich.
     * The method creates a ProductView instance with available toppings and customer information and displays it.
     *
     * @param availableToppings A map containing the available toppings and their associated ItemWithStock objects.
     * @param selectedBread     The selected bread item name.
     * @param selectedMeat      The selected meat item name.
     */
    public void openToppingsSelectionInterface(Map<String, ItemWithStock> availableToppings, String selectedBread, String selectedMeat) {
        ProductView productView = new ProductView(availableToppings, customer, selectedMeat, selectedMeat);
        productView.setVisible(true);
    }
}
