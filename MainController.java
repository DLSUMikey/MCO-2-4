import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {

    private MainView mainView;
    private VendingMachineModel model;
    private TestingView testView;

    public MainController(MainView view, VendingMachineModel model, TestingView testView) {
        this.mainView = view;
        this.model = model;
        this.testView = testView;
    
        // Add listeners to the view's buttons
        mainView.addRegularVendingMachineBtnListener(new RegularVendingMachineListener());
        mainView.addSpecialVendingMachineBtnListener(new SpecialVendingMachineListener());
        mainView.addTestVendingMachineBtnListener(new TestVendingMachineListener());
        mainView.addExitBtnListener(new ExitBtnListener());
    
        // Add listeners to the testing view's buttons
        testView.addOrderBtnListener(new OrderBtnListener());
        testView.addMaintenanceBtnListener(new MaintenanceBtnListener());
        testView.addBackBtnListener(new BackBtnListener());
    }

    class RegularVendingMachineListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle Regular Vending Machine button click
            // Call the model to process the action
            model.createRegularVendingMachine();
        }
    }

    class SpecialVendingMachineListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle Special Vending Machine button click
            // Call the model to process the action
            model.createSpecialVendingMachine();
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
            MaintenanceView maintenanceView = model.getMaintenanceView();
            maintenanceView.setVisible(true);
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
