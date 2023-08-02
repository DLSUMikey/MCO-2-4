import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.Timer;

public class PreparingMealView extends JDialog {
    private JLabel messageLabel;
    private JButton continueButton;
    private Queue<String> messages;
    private Timer timer;

    public PreparingMealView(java.util.List<String> messages) {
        this.messages = new LinkedList<>(messages);

        setTitle("Preparing your meal...");
        setSize(300, 200);
        setModal(true);

        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        continueButton = new JButton("Continue to payment");
        continueButton.setEnabled(false);  // The button is initially disabled
        continueButton.addActionListener(e -> dispose());

        getContentPane().add(messageLabel, BorderLayout.CENTER);
        getContentPane().add(continueButton, BorderLayout.SOUTH);

        int delay = 1000;  // The delay in milliseconds
        timer = new Timer(delay, e -> updateMessage());
        timer.start();
    }

    private void updateMessage() {
        if (messages.isEmpty()) {
            timer.stop();
            continueButton.setEnabled(true);
        } else {
            messageLabel.setText(messages.remove());
        }
    }
}
