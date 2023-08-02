import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TestingView extends JFrame {

    private JButton orderBtn;
    private JButton maintenanceBtn;
    private JButton backBtn;

    public TestingView() {
        setTitle("Testing Vending Machine");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        initializeComponents();
    }

    private void initializeComponents() {
        // Create buttons
        orderBtn = new JButton("Order");
        maintenanceBtn = new JButton("Maintenance");
        backBtn = new JButton("Back");

        // Set layout
        setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column, with 10px vertical and horizontal gaps

        // Add buttons to the frame
        add(orderBtn);
        add(maintenanceBtn);
        add(backBtn);

        // Center-align buttons
        orderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        maintenanceBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // Add action listeners for buttons in the TestingView
    public void addOrderBtnListener(ActionListener listener) {
        orderBtn.addActionListener(listener);
    }

    public void addMaintenanceBtnListener(ActionListener listener) {
        maintenanceBtn.addActionListener(listener);
    }

    public void addBackBtnListener(ActionListener listener) {
        backBtn.addActionListener(listener);
    }
}
