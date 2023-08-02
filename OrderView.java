import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class OrderView extends JFrame implements ChangeListener {
    private VendingMachine vendingMachine;
    private JTable itemsTable;
    private JTextField orderTextField;
    private CustomerService customerService; // Use the interface instead of the concrete Customer class

    public OrderView(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        vendingMachine.addChangeListener(this);
        initUI();
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
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
        backButton.addActionListener(new BackBtnListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

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

    class BackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }

    @Override
    public void stateChanged() {
        updateTableData(); // Called when the state of VendingMachine changes
    }
}
