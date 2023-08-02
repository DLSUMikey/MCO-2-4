import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code PayView} class represents the view for handling the payment process in the vending machine.
 * It allows the customer to add money, calculate the change, and finish the payment.
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
     * Creates a new {@code PayView} for handling payment.
     *
     * @param customer               The customer making the payment.
     * @param currency               The currency used for the vending machine.
     * @param vendingMachine         The vending machine for processing the payment.
     * @param totalPrice             The total price to be paid.
     * @param transactionHistoryView The view to display transaction history.
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

    // (Existing code)

    /**
     * Initializes the UI components of the PayView.
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
     * Calculates the change to be dispensed based on the excess amount paid by the customer.
     *
     * @param excess The excess amount paid by the customer.
     * @return A map containing denominations of change to be dispensed with their respective counts.
     */
    private Map<String, Integer> calculateChange(double excess) {
        Map<String, Integer> change = new HashMap<>();
        double remaining = excess;
    
        if (remaining >= 1000) {
            int thousands = (int) (remaining / 1000);
            change.put("Thousands", thousands);
            remaining -= thousands * 1000;
        }
        if (remaining >= 500) {
            int fiveHundreds = (int) (remaining / 500);
            change.put("FiveHundreds", fiveHundreds);
            remaining -= fiveHundreds * 500;
        }
        if (remaining >= 100) {
            int hundreds = (int) (remaining / 100);
            change.put("Hundreds", hundreds);
            remaining -= hundreds * 100;
        }
        if (remaining >= 50) {
            int fifties = (int) (remaining / 50);
            change.put("Fifties", fifties);
            remaining -= fifties * 50;
        }
        if (remaining >= 20) {
            int twenties = (int) (remaining / 20);
            change.put("Twenties", twenties);
            remaining -= twenties * 20;
        }
        if (remaining >= 10) {
            int tens = (int) (remaining / 10);
            change.put("Tens", tens);
            remaining -= tens * 10;
        }
        if (remaining >= 5) {
            int fives = (int) (remaining / 5);
            change.put("Fives", fives);
            remaining -= fives * 5;
        }
        if (remaining >= 1) {
            int ones = (int) remaining;
            change.put("Ones", ones);
            remaining -= ones;
        }
        if (remaining >= 0.5) {
            int halfPesos = (int) (remaining / 0.5);
            change.put("HalfPeso", halfPesos);
            remaining -= halfPesos * 0.5;
        }
        if (remaining >= 0.25) {
            int quarters = (int) (remaining / 0.25);
            change.put("Quarter", quarters);
            remaining -= quarters * 0.25;
        }
        if (remaining >= 0.10) {
            int dimes = (int) (remaining / 0.10);
            change.put("Dime", dimes);
            remaining -= dimes * 0.10;
        }
        if (remaining >= 0.05) {
            int nickels = (int) (remaining / 0.05);
            change.put("Nickel", nickels);
            remaining -= nickels * 0.05;
        }
        if (remaining >= 0.01) {
            int pennies = (int) (remaining / 0.01);
            change.put("Penny", pennies);
            remaining -= pennies * 0.01;
        }
    
        return change;
    }

    /**
     * Calculates the total amount paid by the customer.
     *
     * @return The total amount paid by the customer.
     */
    private double calculateTotalPaid() {
        double totalPaid = 0.0;

        for (Map.Entry<String, Integer> entry : paidAmounts.entrySet()) {
            String denomination = entry.getKey();
            int count = entry.getValue();
            switch (denomination) {
                case "Thousands":
                    totalPaid += count * 1000;
                    break;
                case "FiveHundreds":
                    totalPaid += count * 500;
                    break;
                case "Hundreds":
                    totalPaid += count * 100;
                    break;
                case "Fifties":
                    totalPaid += count * 50;
                    break;
                case "Twenties":
                    totalPaid += count * 20;
                    break;
                case "Tens":
                    totalPaid += count * 10;
                    break;
                case "Fives":
                    totalPaid += count * 5;
                    break;
                case "Ones":
                    totalPaid += count * 1;
                    break;
                case "HalfPeso":
                    totalPaid += count * 0.5;
                    break;
                case "Quarter":
                    totalPaid += count * 0.25;
                    break;
                case "Dime":
                    totalPaid += count * 0.10;
                    break;
                case "Nickel":
                    totalPaid += count * 0.05;
                    break;
                case "Penny":
                    totalPaid += count * 0.01;
                    break;
            }
        }

        return totalPaid;
    }
}
