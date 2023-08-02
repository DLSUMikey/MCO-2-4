import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {

    private MainView mainView;
    private VendingMachineModel model;
    private TestingView testView;
    private MaintenanceView maintenanceView;
    private ItemsView itemsView;
    private CurrencyView currView;
    private OrderView ordView;

    public MainController() {
        mainView = new MainView();
        testView = new TestingView();
        maintenanceView = new MaintenanceView(mainView);
        model = new VendingMachineModel(mainView, testView, maintenanceView, currView, ordView );

        // Add listeners to the view's buttons
        mainView.addRegularVendingMachineBtnListener(new RegularVendingMachineListener());
        mainView.addSpecialVendingMachineBtnListener(new SpecialVendingMachineListener());
        mainView.addTestVendingMachineBtnListener(new TestVendingMachineListener());
        mainView.addExitBtnListener(new ExitBtnListener());

        // Add listeners to the testing view's buttons
        testView.addOrderBtnListener(new OrderBtnListener());
        testView.addMaintenanceBtnListener(new MaintenanceBtnListener());
        testView.addBackBtnListener(new BackBtnListener());

        // Add listeners to the maintenance view's buttons
        maintenanceView.getItemManagementButton().addActionListener(new ItemManagementBtnListener());
        maintenanceView.getCurrencyStorageButton().addActionListener(new CurrencyStorageBtnListener());
        maintenanceView.getTransactionsButton().addActionListener(new TransactionsBtnListener());
        maintenanceView.getBackButton().addActionListener(new BackBtn1Listener());

        mainView.setVisible(true);
    }

    class RegularVendingMachineListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle Regular Vending Machine button click
            // Call the model to process the action
            VendingMachine vendingMachine = model.createRegularVendingMachine();
            ItemsView itemsView = new ItemsView(vendingMachine);
            CurrencyView currView = new CurrencyView(vendingMachine);
            OrderView ordView = new OrderView(vendingMachine);
            model.setItemsView(itemsView);
            model.setCurrView(currView);
            model.setOrdView(ordView);
        }
    }
    
    class SpecialVendingMachineListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle Special Vending Machine button click
            // Call the model to process the action
            VendingMachine vendingMachine = model.createSpecialVendingMachine();
            ItemsView itemsView = new ItemsView(vendingMachine);
            CurrencyView currView = new CurrencyView(vendingMachine);
            OrderView ordView = new OrderView(vendingMachine);
            model.setItemsView(itemsView);
            model.setCurrView(currView);
            model.setOrdView(ordView);
        }
    }

    class TestVendingMachineListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle Special Vending Machine button click
            // Call the model to process the action
            model.testVendingMachine();
            
        }
    }

    class ExitBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle Exit button click
            // Call the model to process the action
            model.exitProgram();
        }
    }

    // Add action listeners for buttons in the TestingView
    class OrderBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement the logic for Order button
            model.orderVendingMachine();
        }
    }

    class MaintenanceBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement the logic for Maintenance button
            model.maintainVendingMachine();

        }
    }

    class ItemManagementBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Item Management clicked.");
            model.manageItems();
        }
    }

    class CurrencyStorageBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Call the model to process the action
            model.manageCurrencyStorage();
        }
    }

    class TransactionsBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Call the model to process the action
            model.viewTransactions();
        }
    }

    class BackBtn1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Call the model to process the action
            model.goBack();
        }
    }

    class BackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement the logic for Back button
            // Close the TestingView and reopen the MainView
            model.testingViewBack();
        }
    }
}
