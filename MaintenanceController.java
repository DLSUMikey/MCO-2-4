import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaintenanceController {

    private MaintenanceView maintenanceView;
    private MaintenanceModel model; 
    
    public MaintenanceController(MaintenanceView maintenanceView) {
        this.maintenanceView = maintenanceView;

        // Add listeners to the view's buttons
        this.maintenanceView.getItemManagementButton().addActionListener(new ItemManagementBtnListener());
        this.maintenanceView.getCurrencyStorageButton().addActionListener(new CurrencyStorageBtnListener());
        this.maintenanceView.getTransactionsButton().addActionListener(new TransactionsBtnListener());
        this.maintenanceView.getBackButton().addActionListener(new BackBtnListener());
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

    class BackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Call the model to process the action
            model.goBack();
        }
    }

    // Getters for listeners (optional, only if you want to access them externally)
    public ActionListener getItemManagementBtnListener() {
        return new ItemManagementBtnListener();
    }

    public ActionListener getCurrencyStorageBtnListener() {
        return new CurrencyStorageBtnListener();
    }

    public ActionListener getTransactionsBtnListener() {
        return new TransactionsBtnListener();
    }

    public ActionListener getBackBtnListener() {
        return new BackBtnListener();
    }
}
