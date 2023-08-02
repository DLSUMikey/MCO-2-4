import javax.swing.*;
import java.awt.*;

/**
 * The TransactionHistoryView class represents the view for displaying the transaction history of a vending machine.
 * It shows a list of past transactions with their details.
 */
public class TransactionHistoryView extends JFrame {
    private VendingMachine vendingMachine;
    private JTextArea transactionArea;

    /**
     * Constructs a TransactionHistoryView object.
     *
     * @param vendingMachine The VendingMachine instance associated with the transaction history view.
     */
    public TransactionHistoryView(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        initUI();
    }

    /**
     * Initializes the graphical user interface components and sets up the layout.
     */
    private void initUI() {
        setTitle("Transaction History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));

        transactionArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(transactionArea);
        transactionArea.setEditable(false);

        // Populate the transactionArea with the initial transaction history.
        refresh();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(backButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Refreshes the transaction history view by updating the displayed transactions.
     */
    public void refresh() {
        transactionArea.setText("");

        // Append the details of each transaction to the transactionArea.
        for (Transaction transaction : vendingMachine.getTransactions()) {
            transactionArea.append(transaction.toString() + "\n");
        }
    }
}
