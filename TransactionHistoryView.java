import javax.swing.*;
import java.awt.*;

public class TransactionHistoryView extends JFrame {
    private VendingMachine vendingMachine;
    private JTextArea transactionArea; // Make this a field so it can be accessed in the refresh method

    public TransactionHistoryView(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;

        initUI();
    }

    private void initUI() {
        setTitle("Transaction History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));

        transactionArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(transactionArea);
        transactionArea.setEditable(false);

        refresh(); // Call refresh here to populate the text area

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(backButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public void refresh() {
        transactionArea.setText(""); // Clear the text area

        for (Transaction transaction : vendingMachine.getTransactions()) {
            transactionArea.append(transaction.toString() + "\n");
        }
    }
}

