import javax.swing.JOptionPane;

/**
 * The {@code VendingMachineModel} class represents the model of the vending machine application.
 * It handles the logic and operations related to the different views and vending machines.
 */
public class VendingMachineModel {

    private MainView mainView;
    private TestingView testingView;
    private MaintenanceView maintenanceView;
    private VendingMachine vendingMachine;
    private ItemsView itemsView;
    private CurrencyView currView;
    private OrderView ordView;
    private Customer customer;
    private TransactionHistoryView transView;

    /**
     * Constructs a new {@code VendingMachineModel} with the specified views.
     *
     * @param mainView       The main view of the application.
     * @param testingView    The testing view of the application.
     * @param maintenanceView The maintenance view of the application.
     * @param currView       The currency view of the application.
     * @param ordView        The order view of the application.
     * @param transView      The transaction history view of the application.
     */
    public VendingMachineModel(MainView mainView, TestingView testingView, MaintenanceView maintenanceView, CurrencyView currView, OrderView ordView, TransactionHistoryView transView) {
        this.mainView = mainView;
        this.testingView = testingView;
        this.maintenanceView = maintenanceView;
        this.transView = transView;
    }

    /**
     * Sets the {@code ItemsView} for the application.
     *
     * @param itemsView The ItemsView to set.
     */
    public void setItemsView(ItemsView itemsView) {
        this.itemsView = itemsView;
    }

    /**
     * Sets the {@code CurrencyView} for the application.
     *
     * @param currView The CurrencyView to set.
     */
    public void setCurrView(CurrencyView currView) {
        this.currView = currView;
    }

    /**
     * Sets the {@code OrderView} for the application.
     *
     * @param ordView The OrderView to set.
     */
    public void setOrdView(OrderView ordView) {
        this.ordView = ordView;
    }

    /**
     * Sets the {@code TransactionHistoryView} for the application.
     *
     * @param transView The TransactionHistoryView to set.
     */
    public void setTransView(TransactionHistoryView transView) {
        this.transView = transView;
    }

    /**
     * Creates a regular vending machine and sets it as the current vending machine.
     *
     * @return The created RegularVendingMachine.
     */
    public RegularVendingMachine createRegularVendingMachine() {
        RegularVendingMachine regularVendingMachine = new RegularVendingMachine("GoDio's Regular Vending Machine", 15, 10);
        JOptionPane.showMessageDialog(null, "GoDio's Regular Vending Machine has been created.");
        this.vendingMachine = regularVendingMachine;
        return regularVendingMachine;
    }

    /**
     * Creates a special vending machine and sets it as the current vending machine.
     *
     * @return The created SpecialVendingMachine.
     */
    public SpecialVendingMachine createSpecialVendingMachine() {
        SpecialVendingMachine specialVendingMachine = new SpecialVendingMachine("GoDio's Special Vending Machine", 15, 10, customer);
        JOptionPane.showMessageDialog(null, "GoDio's Special Vending Machine has been created.");
        this.vendingMachine = specialVendingMachine;
        return specialVendingMachine;
    }

    /**
     * Activates the testing view of the vending machine application.
     * Sets the customer to a new instance of the Customer class with the name "User".
     */
    public void testVendingMachine() {
        testingView.setVisible(true);
        mainView.setVisible(false);
        customer = new Customer("User");
    }

    /**
     * Activates the order view of the vending machine application.
     *
     * @param orderView The OrderView instance to set as visible.
     */
    public void orderVendingMachine(OrderView orderView) {
        ordView.setVisible(true);
        System.out.println("Ordering...");
    }

    /**
     * Activates the maintenance view of the vending machine application.
     * Hides the testing view.
     */
    public void maintainVendingMachine() {
        maintenanceView.setVisible(true);
        testingView.setVisible(false);
    }

    /**
     * Activates the items view of the vending machine application.
     * Prints "Managing items..." to the console.
     */
    public void manageItems() {
        itemsView.setVisible(true);
        System.out.println("Managing items...");
    }

    /**
     * Activates the currency view of the vending machine application.
     * Refreshes the currency view and prints "Managing currency storage..." to the console.
     */
    public void manageCurrencyStorage() {
        currView.setVisible(true);
        currView.refresh();
        System.out.println("Managing currency storage...");
    }

    /**
     * Activates the transaction history view of the vending machine application.
     * If the transaction history view is null, it creates a new instance and sets the current vending machine.
     * Otherwise, it refreshes the view.
     */
    public void viewTransactions() {
        if (transView == null) {
            transView = new TransactionHistoryView(vendingMachine);
        } else {
            transView.refresh();
        }

        transView.setVisible(true);
        System.out.println("Viewing transactions...");
    }

    /**
     * Sets the maintenance view as not visible and the testing view as visible.
     * Prints "Going back to Test Menu..." to the console.
     */
    public void goBack() {
        System.out.println("Going back to Test Menu...");
        maintenanceView.setVisible(false);
        testingView.setVisible(true);
    }

    /**
     * Refills the vending machine currency with the provided {@code Currency} object.
     *
     * @param currency The {@code Currency} object to refill the vending machine currency.
     */
    public void refillCurrency(Currency currency) {
        if (vendingMachine != null) {
            vendingMachine.setCurrency(currency);
        }
    }

    /**
     * Disposes of the testing view, sets the main view as visible, and sets the customer to null.
     */
    public void testingViewBack() {
        testingView.dispose();
        mainView.setVisible(true);
        customer = null;
    }

    /**
     * Exits the program by terminating the application.
     * Prints "Exiting the program." to the console.
     */
    public void exitProgram() {
        System.out.println("Exiting the program.");
        System.exit(0);
    }

    /**
     * Returns the current vending machine.
     *
     * @return The current {@code VendingMachine} instance.
     */
    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }
}
