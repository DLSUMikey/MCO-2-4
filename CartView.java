import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class CartView extends JFrame {
    private Customer customer;
    private VendingMachine vendingMachine;
    private JTable cartTable;

    public CartView(Customer customer, VendingMachine vendingMachine) {
        this.customer = customer;
        this.vendingMachine = vendingMachine;
        initUI();
    }

    private void initUI() {
        setTitle("Cart View - " + customer.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));

        String[] columnNames = {"Name", "Quantity", "Calories", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(cartTable);

        updateCartTableData();

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel totalLabel = new JLabel("Total Price:");
        totalPanel.add(totalLabel);

        double totalPrice = calculateTotalPrice();
        JLabel totalPriceLabel = new JLabel(String.format("$%.2f", totalPrice));
        totalPanel.add(totalPriceLabel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(totalPanel, BorderLayout.SOUTH);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackBtnListener());
        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new PayBtnListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(payButton);

        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<String, Integer> entry : customer.getCart().entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            ItemWithStock itemWithStock = vendingMachine.getItemsWithStock().get(itemName);
            if (itemWithStock != null) {
                totalPrice += itemWithStock.getItem().getPrice() * quantity;
            }
        }
        return totalPrice;
    }

    private void updateCartTableData() {
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.setRowCount(0);

        for (Map.Entry<String, Integer> entry : customer.getCart().entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            ItemWithStock itemWithStock = vendingMachine.getItemsWithStock().get(itemName);

            if (itemWithStock != null) {
                Item item = itemWithStock.getItem();
                String[] rowData = {
                        itemName,
                        String.valueOf(quantity),
                        String.valueOf(item.getCalories()),
                        String.format("$%.2f", item.getPrice())
                };
                model.addRow(rowData);
            }
        }
    }

    class BackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the current view and go back to the last OrderView
            dispose();
        }
    }

    class PayBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create and display the PayView passing the necessary data
            double totalPrice = calculateTotalPrice();
            PayView payView = new PayView(customer, vendingMachine.getCurrency(), vendingMachine, totalPrice); // Pass totalPrice to PayView
            payView.setVisible(true);
            CartView.this.setVisible(false);
        }
    }
}
