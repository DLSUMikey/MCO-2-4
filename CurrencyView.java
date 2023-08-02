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

    private void initUI() {
        setTitle("Currency View - " + vendingMachine.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));  // Increased size of the frame

        String[] columnNames = {"Denomination", "Quantity"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        currencyTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(currencyTable);

        JButton addCurrencyButton = new JButton("Add Currency");
        addCurrencyButton.addActionListener(new AddCurrencyBtnListener());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackBtnListener());

        denominationField = new JTextField(15);
        JLabel denominationLabel = new JLabel("Enter Denomination:");

        quantityField = new JTextField(5);
        JLabel quantityLabel = new JLabel("Enter Quantity:");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(denominationLabel);
        buttonPanel.add(denominationField);
        buttonPanel.add(quantityLabel);
        buttonPanel.add(quantityField);
        buttonPanel.add(addCurrencyButton);
        buttonPanel.add(backButton);

        updateTableData();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void updateTableData() {
        DefaultTableModel model = (DefaultTableModel) currencyTable.getModel();
        model.setRowCount(0);

        Map<String, Integer> currency = vendingMachine.getCurrency().getDenominations();

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
