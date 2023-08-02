import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * The ItemsView class represents a graphical user interface for displaying and managing items in a vending machine.
 * It allows the user to add new items to the vending machine and view the existing items with their details.
 */
@SuppressWarnings("unused")
public class ItemsView extends JFrame implements ChangeListener {
    private VendingMachine vendingMachine;
    private JTable itemsTable;
    private JTextField itemNameField;

    /**
     * Creates a new ItemsView instance associated with the given VendingMachine.
     *
     * @param vendingMachine The VendingMachine to be associated with this view.
     */
    public ItemsView(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        vendingMachine.addChangeListener(this);
        initUI();
    }

    private void initUI() {
        setTitle("Items View - " + vendingMachine.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));

        String[] columnNames = {"Index", "Name", "Calories", "Price", "Saleability", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        itemsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(itemsTable);

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new AddItemBtnListener());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackBtnListener());

        itemNameField = new JTextField(15);
        JLabel itemNameLabel = new JLabel("Enter Item Name:");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(itemNameLabel);
        buttonPanel.add(itemNameField);
        buttonPanel.add(addItemButton);
        buttonPanel.add(backButton);

        updateTableData();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void updateTableData() {
        DefaultTableModel model = (DefaultTableModel) itemsTable.getModel();
        model.setRowCount(0);

        int index = 1;
        for (Map.Entry<String, ItemWithStock> entry : vendingMachine.getItemsWithStock().entrySet()) {
            String itemName = entry.getKey();
            ItemWithStock itemWithStock = entry.getValue();
            Item item = itemWithStock.getItem();

            String[] rowData = {
                    String.valueOf(index),
                    item.getName(),
                    String.valueOf(item.getCalories()),
                    String.format("$%.2f", item.getPrice()),
                    item.isSaleable() ? "Yes" : "No",
                    String.valueOf(itemWithStock.getStock())
            };

            model.addRow(rowData);
            index++;
        }
    }

    /**
     * ActionListener for the "Add Item" button.
     * This listener adds a new item to the vending machine based on the user input.
     */
    class AddItemBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = itemNameField.getText();
            if (!itemName.isEmpty()) {
                Item newItem = createItem(itemName);
                if (newItem != null) {
                    vendingMachine.addItem(newItem);
                    updateTableData();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid item name.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter an item name.");
            }
        }
    }

    /**
     * Creates an Item instance based on the given item name.
     * This method is used to add new items to the vending machine.
     *
     * @param itemName The name of the item to create.
     * @return The newly created Item instance, or null if the item name is not recognized.
     */
    public Item createItem(String itemName) {
        switch (itemName) {
            case "Garlic Bread":
                return new Item("Garlic Bread", 120, 30.00, true);
            case "Wheat Bread":
                return new Item("Wheat Bread", 90, 35.00, true);
            case "White Bread":
                return new Item("White Bread", 150, 20.50, true);
            case "Cheese":
                return new Item("Cheese", 100, 20.00, true);
            case "BaconStrips":
                return new Item("BaconStrips", 90, 50.00, true);
            case "HamStrips":
                return new Item("HamStrips", 90, 40.50, true);
            case "Lettuce":
                return new Item("Lettuce", 10, 40.00, true);
            case "Pickles":
                return new Item("Pickles", 10, 15.50, true);
            case "Mustard":
                return new Item("Mustard", 5, 5.00, false);
            case "Ketchup":
                return new Item("Ketchup", 20, 20.00, false);
            case "Onion":
                return new Item("Onion", 10, 10.00, false);
            case "TurkeyStrips":
                return new Item("TurkeyStrips", 30, 40.00, false);
            case "Sausage":
                return new Item("Sausage", 90, 30.00, false);
            case "Cucumber":
                return new Item("Cucumber", 15, 20.00, false);
            case "Mayonnaise":
                return new Item("Mayonnaise", 90, 5.00, false);
            default:
                return null;
        }
    }

    /**
     * ActionListener for the "Back" button.
     * This listener hides the ItemsView frame.
     */
    class BackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }

    @Override
    public void stateChanged() {
        updateTableData();
    }
}
