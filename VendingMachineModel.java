import javax.swing.JOptionPane;

public class VendingMachineModel {

    private MainView mainView;
    private TestingView testingView;
    private MaintenanceView maintenanceView;
    private VendingMachine vendingMachine; // Added field to store the VendingMachine instance

    public VendingMachineModel(MainView mainView, TestingView testingView) {
        this.mainView = mainView;
        this.testingView = testingView;
    }

    public RegularVendingMachine createRegularVendingMachine() {
        // Create a new RegularVendingMachine instance with the specified name and default currency
        RegularVendingMachine regularVendingMachine = new RegularVendingMachine("GoDio's Regular Vending Machine", 15, 10);

        // Show a GUI pop-up to inform the user about the creation
        JOptionPane.showMessageDialog(null, "GoDio's Regular Vending Machine has been created.");

        // Set the created vending machine to the field
        this.vendingMachine = regularVendingMachine;

        return regularVendingMachine;
    }

    public SpecialVendingMachine createSpecialVendingMachine() {
        // Create a new SpecialVendingMachine instance
        SpecialVendingMachine specialVendingMachine = new SpecialVendingMachine("GoDio's Special Vending Machine", 15, 10);

        // Show a GUI pop-up to inform the user about the creation
        JOptionPane.showMessageDialog(null, "GoDio's Special Vending Machine has been created.");

        // Set the created vending machine to the field
        this.vendingMachine = specialVendingMachine;

        return specialVendingMachine;
    }

    public void testVendingMachine() {
        // Show the existing TestingView
        testingView.setVisible(true);
        mainView.setVisible(false);
    }

    public void orderVendingMachine(){
        System.out.println("Ordering...");
    }

    public void maintainVendingMachine(){
        // Check if the VendingMachine is not created yet
        if (vendingMachine == null) {
            // Create a default RegularVendingMachine instance if it's not created yet
            vendingMachine = createRegularVendingMachine();
        }

        // Show the MaintenanceView and pass the itemsWithStock map to it
        maintenanceView = new MaintenanceView(vendingMachine); // Create an instance of MaintenanceView
        maintenanceView.setVisible(true);
        mainView.setVisible(false);

        // Update the item table in MaintenanceView with the current items
        maintenanceView.updateItems(vendingMachine.getItemsWithStock());
    }

    public void refillCurrency(Currency currency) {
        if (vendingMachine != null) {
            vendingMachine.setCurrency(currency);
        }
    }

    public void restockItem(String itemName, int quantityToAdd) {
        if (vendingMachine != null) {
            vendingMachine.restockItem(itemName, quantityToAdd);
            maintenanceView.updateItems(vendingMachine.getItemsWithStock());
        }
    }

    public void testingViewBack(){
        testingView.dispose();
        mainView.setVisible(true);
    }

    public void exitProgram() {
        // Implement the logic to exit the program
        System.out.println("Exiting the program.");
        System.exit(0);
    }

     public MaintenanceView getMaintenanceView() {
        return maintenanceView;
    }
}

