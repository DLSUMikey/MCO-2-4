public class MaintenanceModel {
    private VendingMachine vendingMachine; // This is assumed as an example, replace with your actual VendingMachine implementation
    private MaintenanceView maintenanceView;
    private MainView mainView;
    private ItemsView itemsView;
    


    public MaintenanceModel(VendingMachine vendingMachine, MaintenanceView maintenanceView, MainView mainView, ItemsView itemsView) {
        this.vendingMachine = vendingMachine;
        this.maintenanceView = maintenanceView;
        this.mainView = mainView;
        this.itemsView = itemsView;
    }

    public void manageItems() {
        itemsView.setVisible(true);
        System.out.println("Managing items...");
    }

    public void manageCurrencyStorage() {
        // Implement your logic here for managing currency storage
        System.out.println("Managing currency storage...");
    }

    public void viewTransactions() {
        // Implement your logic here for viewing transactions
        System.out.println("Viewing transactions...");
    }

    public void goBack() {
        // Implement your logic here for going back
        System.out.println("Going back to main view...");
        maintenanceView.setVisible(false);
        mainView.setVisible(true);
    }
}
