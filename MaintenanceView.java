import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaintenanceView extends JFrame {
    private JButton itemManagementButton;
    private JButton currencyStorageButton;
    private JButton transactionsButton;
    private JButton backButton;

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
                // Here, you can call any method in MainView or MainController to handle the action.
                // For more complex actions, it's better to let the MaintenanceController handle them.
            }
        });

    }


    public JButton getItemManagementButton() {
        return itemManagementButton;
    }

    public JButton getCurrencyStorageButton() {
        return currencyStorageButton;
    }

    public JButton getTransactionsButton() {
        return transactionsButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
