import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ItemsView extends JFrame {
    private VendingMachine vendingMachine;
    private JTable itemsTable;
    private JTextField itemNameField;

    public ItemsView(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
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

    public void updateView() {
        updateTableData();
    }

    class AddItemBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = itemNameField.getText();
            if (!itemName.isEmpty()) {
                Item newItem = createItem(itemName);
                if (newItem != null) {
                    vendingMachine.addItem(newItem);  // Add item directly
                    updateTableData(); // Refresh the table to show the updated items
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid item name.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter an item name.");
            }
        }
    }
    
    // Method to create an item based on its name
    public Item createItem(String itemName) {
        // Here, you can define the item properties based on their names
        switch(itemName) {
            case "GarlicBread":
                return new Item("GarlicBread", 120, 30.00, true);
            case "WheatBread":
                return new Item("WheatBread", 90, 35.00, true);
            case "WhiteBread":
                return new Item("WhiteBread", 150, 20.50, true);
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
                return new Item("Onion",10, 10.00, false);
            case "TurkeyStrips":
                return new Item("TurkeyStrips", 30, 40.00, false);
            case "Sausage":
                return new Item("Sausage", 90, 30.00, false);
            case "Cucumber":
                return new Item("Cucumber", 15, 20.00, false);
            case "Mayonnaise":
                return new Item("Mayonnaise", 90, 5.00, false);
            default:
                return null; // Return null if the item name doesn't match any of the predefined items
        }
    }
    

    class BackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
