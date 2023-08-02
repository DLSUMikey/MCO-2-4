import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * CartView class represents a graphical user interface for displaying the customer's cart
 * and providing options for payment.
 */
public class CartView extends JFrame {
    private Customer customer; // The current customer using the cart view.
    private VendingMachine vendingMachine; // The vending machine associated with the cart view.
    private JTable cartTable; // JTable to display the cart items.
    private TransactionHistoryView transactionHistoryView; // Transaction history view for the customer.

    /**
     * Creates a new CartView instance with the given customer, vending machine, and transaction history view.
     *
     * @param customer               The Customer object representing the current customer.
     * @param vendingMachine         The VendingMachine object for accessing items and their stock.
     * @param transactionHistoryView The TransactionHistoryView object for viewing transaction history.
     */
    public CartView(Customer customer, VendingMachine vendingMachine, TransactionHistoryView transactionHistoryView) {
        this.customer = customer;
        this.vendingMachine = vendingMachine;
        this.transactionHistoryView = transactionHistoryView;
        initUI();
    }

    /**
     * Initializes the CartView user interface components and sets up the GUI layout.
     */
    private void initUI() {
        setTitle("Cart View - " + customer.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));

        // Create the table to display cart items.
        String[] columnNames = {"Name", "Quantity", "Calories", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(cartTable);

        // Update the cart table with the current cart items.
        updateCartTableData();

        // Create a panel to display the total price of the items in the cart.
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel totalLabel = new JLabel("Total Price:");
        totalPanel.add(totalLabel);

        double totalPrice = calculateTotalPrice();
        JLabel totalPriceLabel = new JLabel(String.format("$%.2f", totalPrice));
        totalPanel.add(totalPriceLabel);

        // Set up the main layout of the CartView.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(totalPanel, BorderLayout.SOUTH);

        // Create and add buttons to a panel for navigation and payment.
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackBtnListener());
        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new PayBtnListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(payButton);

        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        // Set the final layout and pack the components.
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Calculates the total price of all items in the customer's cart.
     *
     * @return The total price of all items in the cart.
     */
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

    /**
     * Updates the JTable in the view with the current cart items and their details.
     */
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

    /**
     * ActionListener implementation for the "Back" button.
     */
    class BackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    /**
     * ActionListener implementation for the "Pay" button.
     */
    class PayBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double totalPrice = calculateTotalPrice();
            PayView payView = new PayView(customer, vendingMachine.getCurrency(), vendingMachine, totalPrice, transactionHistoryView);
            payView.setVisible(true);
            CartView.this.setVisible(false);
        }
    }
}

