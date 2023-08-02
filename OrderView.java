import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * The OrderView class represents the view for placing orders in the vending machine application.
 * It allows the user to select items to add to the cart and proceed to the cart view.
 */
@SuppressWarnings("unused")
public class OrderView extends JFrame implements ChangeListener {

    private VendingMachine vendingMachine;
    private JTable itemsTable;
    private JTextField orderTextField;
    private Customer customer;

    /**
     * Constructs an OrderView instance representing the order view of the vending machine application.
     *
     * @param vendingMachine The VendingMachine instance associated with this view.
     * @param customer       The Customer instance representing the current user.
     */
    public OrderView(VendingMachine vendingMachine, Customer customer) {
        this.vendingMachine = vendingMachine;
        this.customer = customer;
        vendingMachine.addChangeListener(this);
        initUI();
    }

    /**
     * Initializes the graphical user interface components and sets up the layout.
     */
    private void initUI() {
        setTitle("Order View - " + vendingMachine.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));

        String[] columnNames = {"Index", "Name", "Calories", "Price", "Saleability", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        itemsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(itemsTable);

        orderTextField = new JTextField(15);
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new AddToCartBtnListener());

        JPanel orderPanel = new JPanel();
        orderPanel.add(orderTextField);
        orderPanel.add(addToCartButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackBtnListener(customer));

        JButton goToCartButton = new JButton("Go To Cart");
        goToCartButton.addActionListener(new GoToCartBtnListener());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(goToCartButton);

        updateTableData();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(orderPanel, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * ActionListener for the "Add to Cart" button.
     * Adds the selected item to the customer's cart if it is saleable and has available stock.
     */
    class AddToCartBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = orderTextField.getText();
            ItemWithStock itemWithStock = vendingMachine.getItemsWithStock().get(itemName);

            if (itemWithStock != null && itemWithStock.getItem().isSaleable()) {
                int stock = itemWithStock.getStock();
                int orderedQuantity = customer.getCart().getOrDefault(itemName, 0) + 1;

                if (orderedQuantity <= stock) {
                    customer.addItemToCart(itemName);
                    System.out.println("Item added to cart: " + itemName);
                } else {
                    System.out.println("Ordered quantity exceeds available stock for: " + itemName);
                }
            } else {
                System.out.println("Item not found or not saleable: " + itemName);
            }
        }
    }

    /**
     * Updates the JTable data with the available saleable items and their stock.
     */
    private void updateTableData() {
        DefaultTableModel model = (DefaultTableModel) itemsTable.getModel();
        model.setRowCount(0);

        int index = 1;
        for (Map.Entry<String, ItemWithStock> entry : vendingMachine.getItemsWithStock().entrySet()) {
            String itemName = entry.getKey();
            ItemWithStock itemWithStock = entry.getValue();
            Item item = itemWithStock.getItem();

            if (item.isSaleable()) {
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
    }

    /**
     * ActionListener for the "Go To Cart" button.
     * Redirects the user to the cart view to finalize the order.
     */
    class GoToCartBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (vendingMachine instanceof SpecialVendingMachine) {
                SpecialVendingMachine specialVendingMachine = (SpecialVendingMachine) vendingMachine;
                Map<String, String> sandwichComponents = specialVendingMachine.isValidSandwichCart(customer.getCart());
                if (sandwichComponents != null) {
                    String selectedBread = sandwichComponents.get("selectedBread");
                    String selectedMeat = sandwichComponents.get("selectedMeat");
                    Map<String, ItemWithStock> availableToppings = specialVendingMachine.getAvailableToppings();
                    if (!availableToppings.isEmpty()) {
                        ProductView productView = new ProductView(availableToppings, customer, selectedBread, selectedMeat);
                        productView.setVisible(true);
                    } else {
                        System.out.println("No toppings available for sandwich making.");
                    }
                }
            }

            TransactionHistoryView transactionHistoryView = new TransactionHistoryView(vendingMachine);
            CartView cartView = new CartView(customer, vendingMachine, transactionHistoryView);
            cartView.setVisible(true);
        }
    }

    /**
     * ActionListener for the "Back" button.
     * Clears the customer's cart and hides the view.
     */
    class BackBtnListener implements ActionListener {
        private Customer customer;

        public BackBtnListener(Customer customer) {
            this.customer = customer;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            customer.clearCart();
            setVisible(false);
        }
    }

    /**
     * Invoked when a change is detected in the vending machine model.
     * Updates the table data when the vending machine's stock changes.
     */
    @Override
    public void stateChanged() {
        updateTableData();
    }
}
