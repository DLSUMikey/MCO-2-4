import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The PayView class represents the view for the payment process in the vending machine application.
 * It allows the customer to make payments and complete the transaction.
 */
public class PayView extends JFrame {

    private Customer customer;
    private Currency currency;
    private VendingMachine vendingMachine;
    private double totalPrice;
    private Map<String, Integer> paidAmounts;
    private JLabel totalPaidLabel;
    private CurrencyView currencyView;
    private TransactionHistoryView transactionHistoryView;

    /**
     * Constructs a PayView instance representing the payment view of the vending machine application.
     *
     * @param customer               The Customer instance representing the current user.
     * @param currency               The Currency instance representing the available denominations.
     * @param vendingMachine         The VendingMachine instance associated with this view.
     * @param totalPrice            The total price of the items in the cart.
     * @param transactionHistoryView The TransactionHistoryView instance for recording transactions.
     */
    public PayView(Customer customer, Currency currency, VendingMachine vendingMachine,
                   double totalPrice, TransactionHistoryView transactionHistoryView) {
        this.customer = customer;
        this.currency = currency;
        this.vendingMachine = vendingMachine;
        this.totalPrice = totalPrice;
        this.paidAmounts = new HashMap<>();
        this.currencyView = new CurrencyView(vendingMachine);
        this.transactionHistoryView = transactionHistoryView;

        initUI();
    }

    /**
     * Initializes the graphical user interface components and sets up the layout.
     */
    private void initUI() {
        setTitle("Payment View");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        JLabel titleLabel = new JLabel("Total Amount: $" + String.format("%.2f", totalPrice));
        totalPaidLabel = new JLabel("Total Paid: $0.00");

        JLabel denominationLabel = new JLabel("Choose denomination:");
        JComboBox<String> denominationComboBox = new JComboBox<>(currency.getAllDenominations());

        JLabel amountLabel = new JLabel("Enter amount:");
        JTextField amountField = new JTextField(10);

        JButton addButton = new JButton("Add Money");
        addButton.addActionListener(e -> {
            String denomination = (String) denominationComboBox.getSelectedItem();
            int amount;
            try {
                amount = Integer.parseInt(amountField.getText());
                paidAmounts.merge(denomination, amount, Integer::sum);
                totalPaidLabel.setText("Total Paid: $" + String.format("%.2f", calculateTotalPaid()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount!");
            }

        });

        JButton finishButton = new JButton("Finish Payment");
        finishButton.addActionListener(e -> {
            if (calculateTotalPaid() >= totalPrice) {
                double excess = calculateTotalPaid() - totalPrice;

                for (Map.Entry<String, Integer> entry : paidAmounts.entrySet()) {
                    String denomination = entry.getKey();
                    int count = entry.getValue();
                    try {
                        vendingMachine.refillCurrency(denomination, count);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(this, "Error processing payment: " + exception.getMessage());
                        return;
                    }
                }

                Map<String, Integer> changeInDenominations = calculateChange(excess);

                for (Map.Entry<String, Integer> entry : changeInDenominations.entrySet()) {
                    String denomination = entry.getKey();
                    int count = entry.getValue();
                    try {
                        vendingMachine.removeCurrency(denomination, count);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(this, "Error dispensing change: " + exception.getMessage());
                        return;
                    }
                }

                Transaction transaction = new Transaction(customer.getCart(), totalPrice, vendingMachine);
                vendingMachine.addTransaction(transaction);
                JOptionPane.showMessageDialog(this, "Payment Successful! Change: $" + String.format("%.2f", excess));
                vendingMachine.processCart(customer.getCart());
                customer.clearCart();
                dispose();
                transactionHistoryView.refresh();
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient payment!");
            }
            currencyView.refresh();
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2));
        formPanel.add(denominationLabel);
        formPanel.add(denominationComboBox);
        formPanel.add(amountLabel);
        formPanel.add(amountField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        buttonPanel.add(finishButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titleLabel, BorderLayout.NORTH);
        getContentPane().add(totalPaidLabel, BorderLayout.CENTER);
        getContentPane().add(formPanel, BorderLayout.WEST);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Calculates the change to be given to the customer based on the excess amount.
     *
     * @param excess The excess amount after payment.
     * @return A map representing the change in denominations.
     */
    private Map<String, Integer> calculateChange(double excess) {
        Map<String, Integer> change = new HashMap<>();
        double remaining = excess;

        // Calculate the change in denominations
        // ...

        return change;
    }

    /**
     * Calculates the total amount paid by the customer.
     *
     * @return The total amount paid by the customer.
     */
    private double calculateTotalPaid() {
        double totalPaid = 0.0;

        // Calculate the total amount paid by the customer
        // ...

        return totalPaid;
    }
}
