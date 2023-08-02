import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MaintenanceView extends JFrame {

    private JTable itemTable;
    private JTable currencyTable;
    private JComboBox<String> denominationComboBox;
    private JTextField refillQuantityField;
    private JButton confirmRefillButton;
    private JButton restockButton;
    private JTextField restockIndexField;
    private JTextField restockQuantityField;
    private DefaultTableModel itemTableModel;
    private DefaultTableModel currencyTableModel;
    private VendingMachine vendingMachine;

    public MaintenanceView(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        setTitle("Maintenance View");
        setSize(800, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        updateTables();
    }

    private void initializeComponents() {
        // Create table models for items and currency
        itemTableModel = new DefaultTableModel(new Object[]{"Index", "Name", "Calories", "Price", "Saleability", "Stock"}, 0);
        currencyTableModel = new DefaultTableModel(new Object[]{"Denomination", "Quantity"}, 0);

        // Create tables
        itemTable = new JTable(itemTableModel);
        currencyTable = new JTable(currencyTableModel);

        // Create scroll panes for tables
        JScrollPane itemScrollPane = new JScrollPane(itemTable);
        JScrollPane currencyScrollPane = new JScrollPane(currencyTable);

        // Create refill components
        denominationComboBox = new JComboBox<>();
        refillQuantityField = new JTextField(10);
        confirmRefillButton = new JButton("Confirm Refill");
        refillCurrency();

        // Create restock components
        restockIndexField = new JTextField(5);
        restockQuantityField = new JTextField(5);
        restockButton = new JButton("Restock Item");
        restockItem();

        // Create refill panel and add refill components to it
        JPanel refillPanel = new JPanel();
        refillPanel.setLayout(new FlowLayout());
        refillPanel.add(new JLabel("Refill Currency:"));
        refillPanel.add(denominationComboBox);
        refillPanel.add(new JLabel("Quantity:"));
        refillPanel.add(refillQuantityField);
        refillPanel.add(confirmRefillButton);

        // Create restock panel and add restock components to it
        JPanel restockPanel = new JPanel();
        restockPanel.setLayout(new FlowLayout());
        restockPanel.add(new JLabel("Restock Item (Index):"));
        restockPanel.add(restockIndexField);
        restockPanel.add(new JLabel("Quantity:"));
        restockPanel.add(restockQuantityField);
        restockPanel.add(restockButton);

        // Set layout for the MaintenanceView
        setLayout(new BorderLayout());

        // Add the item table to the center of the frame
        add(itemScrollPane, BorderLayout.CENTER);

        // Create a JPanel for currency data and add it to the south of the frame
        JPanel currencyPanel = new JPanel();
        currencyPanel.setLayout(new BorderLayout());
        currencyPanel.add(currencyScrollPane, BorderLayout.CENTER);
        currencyPanel.add(refillPanel, BorderLayout.EAST);
        currencyPanel.add(restockPanel, BorderLayout.SOUTH);
        add(currencyPanel, BorderLayout.SOUTH);
    }

    private void refillCurrency() {
        String[] denominations = {
            "Thousands",
            "Five Hundreds",
            "Hundreds",
            "Fifties",
            "Tens",
            "Fives",
            "Ones",
            "Half Peso",
            "Quarter",
            "Dime",
            "Nickel",
            "Penny"
        };

        denominationComboBox.setModel(new DefaultComboBoxModel<>(denominations));
        refillQuantityField.setText("0"); // Set default refill quantity to 0

        // Add action listener for the confirmation button
        confirmRefillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String denomination = denominationComboBox.getSelectedItem().toString();
                String quantityString = refillQuantityField.getText();

                try {
                    int quantityToRefill = Integer.parseInt(quantityString);
                    if (quantityToRefill < 0) {
                        JOptionPane.showMessageDialog(null, "Quantity must be a positive integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Update the currency data with the refilled quantity
                        vendingMachine.refillCurrency(denomination, quantityToRefill);
                        updateCurrency(vendingMachine.getCurrency());
                        refillQuantityField.setText("0"); // Reset refill quantity to 0
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void restockItem() {
        restockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String indexString = restockIndexField.getText();
                String quantityString = restockQuantityField.getText();

                try {
                    int itemIndex = Integer.parseInt(indexString);
                    int quantityToRestock = Integer.parseInt(quantityString);

                    vendingMachine.restockItem(itemIndex, quantityToRestock);
                    updateTables();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid integers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void updateTables() {
        updateItems(vendingMachine.getItemsWithStock());
        updateCurrency(vendingMachine.getCurrency());
    }

    public void updateItems(Map<String, ItemWithStock> itemsWithStock) {
        itemTableModel.setRowCount(0); // Clear the table before adding the updated items
        int index = 1;

        for (ItemWithStock itemWithStock : itemsWithStock.values()) {
            Item item = itemWithStock.getItem();
            Object[] rowData = {
                    index++, // Increment the counter and use it as the item number
                    item.getName(), // Name
                    item.getCalories(), // Calories
                    item.getPrice(), // Price
                    item.isSaleable(), // Saleability
                    itemWithStock.getStock() // Stock
            };
            itemTableModel.addRow(rowData);
        }
    }

    public void updateCurrency(Currency currency) {
        currencyTableModel.setRowCount(0); // Clear the table before adding the updated currency data

        Object[] rowData1 = {"Thousands", currency.getThousands()};
        Object[] rowData2 = {"Five Hundreds", currency.getFiveHundreds()};
        Object[] rowData3 = {"Hundreds", currency.getHundreds()};
        Object[] rowData4 = {"Fifties", currency.getFifties()};
        Object[] rowData5 = {"Tens", currency.getTens()};
        Object[] rowData6 = {"Fives", currency.getFives()};
        Object[] rowData7 = {"Ones", currency.getOnes()};
        Object[] rowData8 = {"Half Peso", currency.getHalfPeso()};
        Object[] rowData9 = {"Quarter", currency.getQuarter()};
        Object[] rowData10 = {"Dime", currency.getDime()};
        Object[] rowData11 = {"Nickel", currency.getNickel()};
        Object[] rowData12 = {"Penny", currency.getPenny()};

        currencyTableModel.addRow(rowData1);
        currencyTableModel.addRow(rowData2);
        currencyTableModel.addRow(rowData3);
        currencyTableModel.addRow(rowData4);
        currencyTableModel.addRow(rowData5);
        currencyTableModel.addRow(rowData6);
        currencyTableModel.addRow(rowData7);
        currencyTableModel.addRow(rowData8);
        currencyTableModel.addRow(rowData9);
        currencyTableModel.addRow(rowData10);
        currencyTableModel.addRow(rowData11);
        currencyTableModel.addRow(rowData12);
    }

     public JButton getConfirmRefillButton() {
        return confirmRefillButton;
    }

    public JButton getRestockButton() {
        return restockButton;
    }

    public JComboBox<String> getDenominationComboBox() {
        return denominationComboBox;
    }

    public JTextField getRefillQuantityField() {
        return refillQuantityField;
    }

    public JTextField getRestockIndexField() {
        return restockIndexField;
    }

    public JTextField getRestockQuantityField() {
        return restockQuantityField;
    }

    public void clearRefillFields() {
        refillQuantityField.setText("0"); // Reset refill quantity field to 0
    }

    public void clearRestockFields() {
        restockIndexField.setText(""); // Clear restock index field
        restockQuantityField.setText(""); // Clear restock quantity field
    }

    public void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}

