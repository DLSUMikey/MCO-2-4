import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * The CurrencyView class represents a graphical user interface for managing the currency denominations in a VendingMachine.
 * It allows the user to view and update the quantity of each denomination.
 */
public class CurrencyView extends JFrame {
    private VendingMachine vendingMachine;
    private JTable currencyTable;
    private JTextField denominationField, quantityField;

    /**
     * Creates a new CurrencyView instance associated with the given VendingMachine.
     *
     * @param vendingMachine The VendingMachine to be associated with this view.
     */
    public CurrencyView(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        initUI();
    }

    /**
     * Refreshes the CurrencyView by updating the table data.
     * This method should be called after any changes to the currency in the VendingMachine.
     */
    public void refresh() {
        updateTableData();
    }

    /**
 * Initializes the user interface components for the currency view.
 * This method sets up the window title, dimensions, and various UI elements
 * such as table, buttons, and input fields required for managing the vending machine's currency.
 * It also adds action listeners to the buttons to handle user interactions.
 */
private void initUI() {
    setTitle("Currency View - " + vendingMachine.getName());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(800, 600)); // Increased size of the frame

    // Create the column names for the currency table
    String[] columnNames = {"Denomination", "Quantity"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    currencyTable = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(currencyTable);

    // Create and set up the "Add Currency" button to allow adding currency
    JButton addCurrencyButton = new JButton("Add Currency");
    addCurrencyButton.addActionListener(new AddCurrencyBtnListener());

    // Create and set up the "Back" button to allow returning to the previous view
    JButton backButton = new JButton("Back");
    backButton.addActionListener(new BackBtnListener());

    // Create input fields and labels for denomination and quantity
    denominationField = new JTextField(15);
    JLabel denominationLabel = new JLabel("Enter Denomination:");

    quantityField = new JTextField(5);
    JLabel quantityLabel = new JLabel("Enter Quantity:");

    // Create a panel to hold the input fields and buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(denominationLabel);
    buttonPanel.add(denominationField);
    buttonPanel.add(quantityLabel);
    buttonPanel.add(quantityField);
    buttonPanel.add(addCurrencyButton);
    buttonPanel.add(backButton);

    // Update the table with the current currency data
    updateTableData();

    // Set up the main content pane and add UI components to it
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(scrollPane, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    pack(); // Pack the components to fit the preferred size
    setLocationRelativeTo(null); // Center the window on the screen
}

/**
 * Updates the data displayed in the currency table.
 * This method retrieves the current currency data from the vending machine
 * and populates the table with the denomination and corresponding quantity.
 */
private void updateTableData() {
    DefaultTableModel model = (DefaultTableModel) currencyTable.getModel();
    model.setRowCount(0); // Clear the existing table data

    // Get the current currency data from the vending machine
    Map<String, Integer> currency = vendingMachine.getCurrency().getDenominations();

    // Add each denomination and its quantity as a new row in the table
    for (Map.Entry<String, Integer> entry : currency.entrySet()) {
        String denomination = entry.getKey();
        Integer quantity = entry.getValue();
        String[] rowData = {denomination, quantity.toString()};
        model.addRow(rowData);
    }
}


    /**
     * ActionListener for the "Add Currency" button.
     * This listener adds the specified quantity of a denomination to the VendingMachine's currency.
     */
    class AddCurrencyBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String denomination = denominationField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            if (!denomination.isEmpty() && quantity > 0) {
                vendingMachine.refillCurrency(denomination, quantity);
                updateTableData();
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a denomination and quantity.");
            }
        }
    }

    /**
     * ActionListener for the "Back" button.
     * This listener hides the CurrencyView window when the "Back" button is clicked.
     */
    class BackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
