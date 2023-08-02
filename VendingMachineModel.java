import javax.swing.JOptionPane;

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

    public VendingMachineModel(MainView mainView, TestingView testingView, MaintenanceView maintenanceView, CurrencyView currView, OrderView ordView, TransactionHistoryView transView) {
        this.mainView = mainView;
        this.testingView = testingView;
        this.maintenanceView = maintenanceView;
        this.transView = transView;
    }

    // Method to set the ItemsView after the vending machine is created
    public void setItemsView(ItemsView itemsView) {
        this.itemsView = itemsView;
    }

    public void setCurrView(CurrencyView currView) {
        this.currView = currView;
    }

    public void setOrdView(OrderView ordView) {
        this.ordView = ordView;
    }

    public void setTransView(TransactionHistoryView transView) {
        this.transView = transView;
    }

    public RegularVendingMachine createRegularVendingMachine() {
        RegularVendingMachine regularVendingMachine = new RegularVendingMachine("GoDio's Regular Vending Machine", 15, 10);
        JOptionPane.showMessageDialog(null, "GoDio's Regular Vending Machine has been created.");
        this.vendingMachine = regularVendingMachine;
        return regularVendingMachine;
    }

    public SpecialVendingMachine createSpecialVendingMachine() {

        SpecialVendingMachine specialVendingMachine = new SpecialVendingMachine("GoDio's Special Vending Machine", 15, 10, customer);
        JOptionPane.showMessageDialog(null, "GoDio's Special Vending Machine has been created.");
        this.vendingMachine = specialVendingMachine;
        return specialVendingMachine;
    }

    public void testVendingMachine() {
        testingView.setVisible(true);
        mainView.setVisible(false);
        customer = new Customer("User");
    }
    

    public void orderVendingMachine(OrderView orderView) {
        ordView.setVisible(true);
        System.out.println("Ordering...");
    }

    public void maintainVendingMachine() {
        maintenanceView.setVisible(true);
        testingView.setVisible(false);
    }

    public void manageItems() {
        itemsView.setVisible(true);
        System.out.println("Managing items...");
    }

    public void manageCurrencyStorage() {
        currView.setVisible(true);
        currView.refresh();
        System.out.println("Managing currency storage...");
    }

    public void viewTransactions() {
        if (transView == null) {
            transView = new TransactionHistoryView(vendingMachine);
        } else {
            transView.refresh(); // If the view already exists, refresh it before displaying
        }

        transView.setVisible(true);
        System.out.println("Viewing transactions...");
    }

    public void goBack() {
        System.out.println("Going back to Test Menu...");
        maintenanceView.setVisible(false);
        testingView.setVisible(true);
    }

    public void refillCurrency(Currency currency) {
        if (vendingMachine != null) {
            vendingMachine.setCurrency(currency);
        }
    }

    public void testingViewBack() {
        testingView.dispose();
        mainView.setVisible(true);
        customer = null;
    }

    public void exitProgram() {
        System.out.println("Exiting the program.");
        System.exit(0);
    }

    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }



}
