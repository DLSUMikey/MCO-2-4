import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.Timer;

/**
 * The PreparingMealView class represents a dialog for displaying messages during the meal preparation process.
 * It shows a series of messages to indicate the progress of meal preparation and allows the user to continue to payment after completion.
 */
public class PreparingMealView extends JDialog {

    private JLabel messageLabel;
    private JButton continueButton;
    private Queue<String> messages;
    private Timer timer;

    /**
     * Constructs a PreparingMealView instance.
     *
     * @param messages A list of messages to be displayed during the meal preparation process.
     */
    public PreparingMealView(java.util.List<String> messages) {
        this.messages = new LinkedList<>(messages);

        setTitle("Preparing your meal...");
        setSize(300, 200);
        setModal(true);

        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        continueButton = new JButton("Continue to payment");
        continueButton.setEnabled(false);
        continueButton.addActionListener(e -> dispose());

        getContentPane().add(messageLabel, BorderLayout.CENTER);
        getContentPane().add(continueButton, BorderLayout.SOUTH);

        int delay = 1000;
        timer = new Timer(delay, e -> updateMessage());
        timer.start();
    }

    /**
     * Updates the message displayed in the dialog.
     * If all messages have been displayed, the continue button is enabled to allow the user to proceed to payment.
     */
    private void updateMessage() {
        if (messages.isEmpty()) {
            timer.stop();
            continueButton.setEnabled(true);
        } else {
            messageLabel.setText(messages.remove());
        }
    }
}
