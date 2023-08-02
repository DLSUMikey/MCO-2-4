import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The MainView class represents the main menu view in the vending machine application.
 * It provides options for creating regular, special vending machines, testing a vending machine, and exiting the program.
 */
public class MainView extends JFrame {

    private JButton regularVendingMachineBtn;
    private JButton specialVendingMachineBtn;
    private JButton testVendingMachineBtn;
    private JButton exitBtn;

    /**
     * Constructs a MainView instance representing the main menu of the vending machine application.
     * The view includes buttons for creating regular, special vending machines, testing a vending machine, and exiting the program.
     */
    public MainView() {
        setTitle("Vending Machine");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
    }

    /**
     * Initializes the GUI components and sets up the layout.
     */
    private void initializeComponents() {
        regularVendingMachineBtn = new JButton("Create a Regular Vending Machine");
        specialVendingMachineBtn = new JButton("Create a Special Vending Machine");
        testVendingMachineBtn = new JButton("Test a Vending Machine");
        exitBtn = new JButton("Exit Program");

        setLayout(new GridLayout(4, 1, 10, 10));

        add(regularVendingMachineBtn);
        add(specialVendingMachineBtn);
        add(testVendingMachineBtn);
        add(exitBtn);

        regularVendingMachineBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        specialVendingMachineBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        testVendingMachineBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Adds an ActionListener to the "Create a Regular Vending Machine" button.
     *
     * @param listener The ActionListener to be added.
     */
    public void addRegularVendingMachineBtnListener(ActionListener listener) {
        regularVendingMachineBtn.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the "Create a Special Vending Machine" button.
     *
     * @param listener The ActionListener to be added.
     */
    public void addSpecialVendingMachineBtnListener(ActionListener listener) {
        specialVendingMachineBtn.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the "Test a Vending Machine" button.
     *
     * @param listener The ActionListener to be added.
     */
    public void addTestVendingMachineBtnListener(ActionListener listener) {
        testVendingMachineBtn.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the "Exit Program" button.
     *
     * @param listener The ActionListener to be added.
     */
    public void addExitBtnListener(ActionListener listener) {
        exitBtn.addActionListener(listener);
    }
}
