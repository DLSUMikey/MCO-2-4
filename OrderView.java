import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class OrderView extends JFrame implements ChangeListener {
    private VendingMachine vendingMachine;
    private JTable itemsTable;
    private JTextField orderTextField; // Text field for user input
    private Customer customer; // Reference to the customer
    private String selectedBread;
    private String selectedMeat;

    public OrderView(VendingMachine vendingMachine, Customer customer) {
        this.vendingMachine = vendingMachine;
        this.customer = customer;
        vendingMachine.addChangeListener(this); // Register this view as a change listener
        initUI();
    }

    

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
        goToCartButton.addActionListener(new GoToCartBtnListener(selectedBread, selectedMeat));

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

    private void updateTableData() {
        DefaultTableModel model = (DefaultTableModel) itemsTable.getModel();
        model.setRowCount(0);

        int index = 1;
        for (Map.Entry<String, ItemWithStock> entry : vendingMachine.getItemsWithStock().entrySet()) {
            String itemName = entry.getKey();
            ItemWithStock itemWithStock = entry.getValue();
            Item item = itemWithStock.getItem();

            if(item.isSaleable()){
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

    public void updateView() {
        updateTableData();
    }

    class GoToCartBtnListener implements ActionListener {
        private String selectedBread;
        private String selectedMeat;

        public GoToCartBtnListener(String selectedBread, String selectedMeat) {
            this.selectedBread = selectedBread;
            this.selectedMeat = selectedMeat;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if the cart is valid for sandwich making
            if (vendingMachine instanceof SpecialVendingMachine) {
                SpecialVendingMachine specialVendingMachine = (SpecialVendingMachine) vendingMachine;
                if (specialVendingMachine.isValidSandwichCart(customer.getCart())) {
                    // Cart is valid for sandwich making, show toppings selection interface
                    Map<String, ItemWithStock> availableToppings = specialVendingMachine.getAvailableToppings();
                    if (!availableToppings.isEmpty()) {
                        // Open the toppings selection interface
                        ProductView productView = new ProductView(availableToppings, customer, selectedBread, selectedMeat);
                        productView.setVisible(true);
                    } else {
                        System.out.println("No toppings available for sandwich making.");
                    }
                }
            }
            
            // Go to the cart as usual
            TransactionHistoryView transactionHistoryView = new TransactionHistoryView(vendingMachine);
            CartView cartView = new CartView(customer, vendingMachine, transactionHistoryView);
            cartView.setVisible(true);
        }
    }
    

    class BackBtnListener implements ActionListener {
        private Customer customer;
    
        public BackBtnListener(Customer customer) {
            this.customer = customer;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            // Empty out the customer's cart
            customer.clearCart();
    
            // Hide or close the current view
            setVisible(false);
        }
    }

    @Override
    public void stateChanged() {
        updateTableData(); // Called when the state of VendingMachine changes
    }
}
