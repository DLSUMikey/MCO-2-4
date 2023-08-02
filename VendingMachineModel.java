import java.util.Collections;
import java.util.Map;

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

    public VendingMachineModel(MainView mainView, TestingView testingView, MaintenanceView maintenanceView, CurrencyView currView, OrderView ordView) {
        this.mainView = mainView;
        this.testingView = testingView;
        this.maintenanceView = maintenanceView;
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

    public RegularVendingMachine createRegularVendingMachine() {
        RegularVendingMachine regularVendingMachine = new RegularVendingMachine("GoDio's Regular Vending Machine", 15, 10);
        JOptionPane.showMessageDialog(null, "GoDio's Regular Vending Machine has been created.");
        this.vendingMachine = regularVendingMachine;
        return regularVendingMachine;
    }

    public SpecialVendingMachine createSpecialVendingMachine() {
        SpecialVendingMachine specialVendingMachine = new SpecialVendingMachine("GoDio's Special Vending Machine", 15, 10);
        JOptionPane.showMessageDialog(null, "GoDio's Special Vending Machine has been created.");
        this.vendingMachine = specialVendingMachine;
        return specialVendingMachine;
    }

    public void testVendingMachine(OrderView orderView) {
        testingView.setVisible(true);
        mainView.setVisible(false);
        customer = new Customer("User");
        orderView.setCustomer(customer);  // Create a new Customer instance
    }
    

    public void orderVendingMachine() {
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
        System.out.println("Managing currency storage...");
    }

    public void viewTransactions() {
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
