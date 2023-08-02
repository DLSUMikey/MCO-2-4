import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The TestingView class represents a graphical user interface for testing a vending machine.
 * It provides buttons for ordering items, performing maintenance, and navigating back.
 */
public class TestingView extends JFrame {

    private JButton orderBtn;
    private JButton maintenanceBtn;
    private JButton backBtn;

    /**
     * Constructs a TestingView instance to test the vending machine.
     * It sets up the graphical user interface components.
     */
    public TestingView() {
        setTitle("Testing Vending Machine");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        initializeComponents();
    }

    /**
     * Initializes the graphical user interface components and sets up the layout.
     */
    private void initializeComponents() {
        orderBtn = new JButton("Order");
        maintenanceBtn = new JButton("Maintenance");
        backBtn = new JButton("Back");

        setLayout(new GridLayout(3, 1, 10, 10));

        add(orderBtn);
        add(maintenanceBtn);
        add(backBtn);

        orderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        maintenanceBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Adds an ActionListener to the Order button.
     *
     * @param listener The ActionListener to be added.
     */
    public void addOrderBtnListener(ActionListener listener) {
        orderBtn.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the Maintenance button.
     *
     * @param listener The ActionListener to be added.
     */
    public void addMaintenanceBtnListener(ActionListener listener) {
        maintenanceBtn.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the Back button.
     *
     * @param listener The ActionListener to be added.
     */
    public void addBackBtnListener(ActionListener listener) {
        backBtn.addActionListener(listener);
    }
}
