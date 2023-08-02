import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class ItemsView extends JFrame {
    private VendingMachine vendingMachine;
    private JTable itemsTable;

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

        updateTableData();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

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
}
