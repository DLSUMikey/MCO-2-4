import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MaintenanceView class represents the maintenance menu view in the vending machine application.
 * It allows the user to access different maintenance options for the vending machine.
 */
public class MaintenanceView extends JFrame {
    private JButton itemManagementButton;
    private JButton currencyStorageButton;
    private JButton transactionsButton;
    private JButton backButton;

    /**
     * Constructs a MaintenanceView instance with the specified mainView as the parent view.
     *
     * @param mainView The parent view (MainView) to return to when the "Back" button is clicked.
     */
    public MaintenanceView(MainView mainView) {
        this.setTitle("Maintenance Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        itemManagementButton = new JButton("Item Management");
        currencyStorageButton = new JButton("Currency Storage");
        transactionsButton = new JButton("Transactions");
        backButton = new JButton("Back");

        this.add(itemManagementButton);
        this.add(currencyStorageButton);
        this.add(transactionsButton);
        this.add(backButton);

        itemManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Item Management clicked (from MaintenanceView).");
            }
        });
    }

    /**
     * Gets the button for accessing the item management view.
     *
     * @return The JButton for item management.
     */
    public JButton getItemManagementButton() {
        return itemManagementButton;
    }

    /**
     * Gets the button for accessing the currency storage view.
     *
     * @return The JButton for currency storage.
     */
    public JButton getCurrencyStorageButton() {
        return currencyStorageButton;
    }

    /**
     * Gets the button for accessing the transactions view.
     *
     * @return The JButton for transactions.
     */
    public JButton getTransactionsButton() {
        return transactionsButton;
    }

    /**
     * Gets the button for navigating back to the previous view (MainView).
     *
     * @return The JButton for going back.
     */
    public JButton getBackButton() {
        return backButton;
    }
}
