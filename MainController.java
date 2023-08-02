import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The MainController class acts as the controller in the MVC architecture for the vending machine application.
 * It manages the interactions between the model (VendingMachineModel) and the views (MainView, TestingView, MaintenanceView, ItemsView, CurrencyView, OrderView, TransactionHistoryView).
 * It handles button click events and initializes the main components of the application.
 */
@SuppressWarnings("unused")
public class MainController {

    private MainView mainView;
    private VendingMachineModel model;
    private TestingView testView;
    private MaintenanceView maintenanceView;
    private ItemsView itemsView;
    private CurrencyView currView;
    private OrderView ordView;
    private Customer customer;
    private TransactionHistoryView transactionHistoryView;

    /**
     * Constructs a MainController instance and initializes the main components of the application.
     */
    public MainController() {
        mainView = new MainView();
        testView = new TestingView();
        maintenanceView = new MaintenanceView(mainView);
        model = new VendingMachineModel(mainView, testView, maintenanceView, currView, ordView, transactionHistoryView);
        customer = new Customer("User");

        // MainView button listeners
        mainView.addRegularVendingMachineBtnListener(new RegularVendingMachineListener());
        mainView.addSpecialVendingMachineBtnListener(new SpecialVendingMachineListener());
        mainView.addTestVendingMachineBtnListener(new TestVendingMachineListener());
        mainView.addExitBtnListener(new ExitBtnListener());

        // TestingView button listeners
        testView.addOrderBtnListener(new OrderBtnListener());
        testView.addMaintenanceBtnListener(new MaintenanceBtnListener());
        testView.addBackBtnListener(new BackBtnListener());

        // MaintenanceView button listeners
        maintenanceView.getItemManagementButton().addActionListener(new ItemManagementBtnListener());
        maintenanceView.getCurrencyStorageButton().addActionListener(new CurrencyStorageBtnListener());
        maintenanceView.getTransactionsButton().addActionListener(new TransactionsBtnListener());
        maintenanceView.getBackButton().addActionListener(new BackBtn1Listener());

        mainView.setVisible(true);
    }

    /**
     * ActionListener for the "Regular Vending Machine" button in the MainView.
     * Creates a regular vending machine, associated views, and sets them in the model.
     */
    class RegularVendingMachineListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            VendingMachine vendingMachine = model.createRegularVendingMachine();
            ItemsView itemsView = new ItemsView(vendingMachine);
            CurrencyView currView = new CurrencyView(vendingMachine);
            OrderView ordView = new OrderView(vendingMachine, customer);
            TransactionHistoryView transView = new TransactionHistoryView(vendingMachine);
            model.setItemsView(itemsView);
            model.setCurrView(currView);
            model.setOrdView(ordView);
            model.setTransView(transView);
        }
    }
    
    /**
     * ActionListener for the "Special Vending Machine" button in the MainView.
     * Creates a special vending machine, associated views, and sets them in the model.
     */
    class SpecialVendingMachineListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            VendingMachine vendingMachine = model.createSpecialVendingMachine();
            ItemsView itemsView = new ItemsView(vendingMachine);
            CurrencyView currView = new CurrencyView(vendingMachine);
            OrderView ordView = new OrderView(vendingMachine, customer);
            TransactionHistoryView transView = new TransactionHistoryView(vendingMachine);
            model.setItemsView(itemsView);
            model.setCurrView(currView);
            model.setOrdView(ordView);
            model.setTransView(transView);
        }
    }

    /**
     * ActionListener for the "Test Vending Machine" button in the MainView.
     * Initiates the testing mode for the vending machine.
     */
    class TestVendingMachineListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.testVendingMachine();
        }
    }

    /**
     * ActionListener for the "Exit" button in the MainView.
     * Exits the vending machine program.
     */
    class ExitBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.exitProgram();
        }
    }

    /**
     * ActionListener for the "Order" button in the TestingView.
     * Initiates the order process for the vending machine.
     */
    class OrderBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.orderVendingMachine(ordView);
        }
    }

    /**
     * ActionListener for the "Maintenance" button in the TestingView.
     * Initiates the maintenance process for the vending machine.
     */
    class MaintenanceBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.maintainVendingMachine();
        }
    }

    /**
     * ActionListener for the "Item Management" button in the MaintenanceView.
     * Initiates the item management process for the vending machine.
     */
    class ItemManagementBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Item Management clicked.");
            model.manageItems();
        }
    }

    /**
     * ActionListener for the "Currency Storage" button in the MaintenanceView.
     * Initiates the currency storage management process for the vending machine.
     */
    class CurrencyStorageBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.manageCurrencyStorage();
        }
    }

    /**
     * ActionListener for the "Transactions" button in the MaintenanceView.
     * Initiates the transaction view process for the vending machine.
     */
    class TransactionsBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.viewTransactions();
        }
    }

    /**
     * ActionListener for the "Back" button in the MaintenanceView.
     * Navigates back to the previous view.
     */
    class BackBtn1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.goBack();
        }
    }

    /**
     * ActionListener for the "Back" button in the TestingView.
     * Navigates back to the previous view.
     */
    class BackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.testingViewBack();
        }
    }
}

