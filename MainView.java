import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private JButton regularVendingMachineBtn;
    private JButton specialVendingMachineBtn;
    private JButton testVendingMachineBtn;
    private JButton exitBtn;

    public MainView() {
        setTitle("Vending Machine");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        initializeComponents();
    }

    private void initializeComponents() {
        // Create buttons
        regularVendingMachineBtn = new JButton("Create a Regular Vending Machine");
        specialVendingMachineBtn = new JButton("Create a Special Vending Machine");
        testVendingMachineBtn = new JButton("Test a Vending Machine");
        exitBtn = new JButton("Exit Program");

        // Set layout
        setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column, with 10px vertical and horizontal gaps

        // Add buttons to the frame
        add(regularVendingMachineBtn);
        add(specialVendingMachineBtn);
        add(testVendingMachineBtn);
        add(exitBtn);

        // Center-align buttons
        regularVendingMachineBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        specialVendingMachineBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        testVendingMachineBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void addRegularVendingMachineBtnListener(ActionListener listener) {
        regularVendingMachineBtn.addActionListener(listener);
    }

    public void addSpecialVendingMachineBtnListener(ActionListener listener) {
        specialVendingMachineBtn.addActionListener(listener);
    }

    public void addTestVendingMachineBtnListener(ActionListener listener) {
        testVendingMachineBtn.addActionListener(listener);
    }

    public void addExitBtnListener(ActionListener listener) {
        exitBtn.addActionListener(listener);
    }
}
