import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaintenanceController {

    private MaintenanceView maintenanceView;
    private VendingMachine vendingMachine;

    public MaintenanceController(MaintenanceView maintenanceView, VendingMachine vendingMachine) {
        this.maintenanceView = maintenanceView;
        this.vendingMachine = vendingMachine;

        // Add listeners to the MaintenanceView buttons or other components, if required
        maintenanceView.getConfirmRefillButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRefillButton();
            }
        });

        maintenanceView.getRestockButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRestockButton();
            }
        });
    }

    private void handleRefillButton() {
        String denomination = maintenanceView.getDenominationComboBox().getSelectedItem().toString();
        String quantityString = maintenanceView.getRefillQuantityField().getText();

        try {
            int quantityToRefill = Integer.parseInt(quantityString);
            if (quantityToRefill < 0) {
                // Show an error message if the quantity is negative
                maintenanceView.showMessage("Quantity must be a positive integer.", "Invalid Input");
            } else {
                // Update the currency data with the refilled quantity
                vendingMachine.refillCurrency(denomination, quantityToRefill);
                maintenanceView.updateCurrency(vendingMachine.getCurrency());
                maintenanceView.clearRefillFields(); // Reset refill quantity field to 0
            }
        } catch (NumberFormatException ex) {
            // Show an error message for invalid input
            maintenanceView.showMessage("Invalid input. Please enter a valid integer.", "Invalid Input");
        }
    }

    private void handleRestockButton() {
        String indexString = maintenanceView.getRestockIndexField().getText();
        String quantityString = maintenanceView.getRestockQuantityField().getText();

        try {
            int itemIndex = Integer.parseInt(indexString);
            int quantityToRestock = Integer.parseInt(quantityString);

            if (quantityToRestock < 0) {
                // Show an error message if the quantity is negative
                maintenanceView.showMessage("Quantity must be a positive integer.", "Invalid Input");
            } else {
                // Restock the item with the specified quantity
                vendingMachine.restockItem(itemIndex, quantityToRestock);
                maintenanceView.updateItems(vendingMachine.getItemsWithStock());
                maintenanceView.clearRestockFields(); // Reset restock fields
            }
        } catch (NumberFormatException ex) {
            // Show an error message for invalid input
            maintenanceView.showMessage("Invalid input. Please enter valid integers.", "Invalid Input");
        }
    }
}
