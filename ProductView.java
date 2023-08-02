import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class ProductView extends JDialog {
    private Map<String, ItemWithStock> availableToppings;
    private Customer customer;
    private String selectedBread;
    private String selectedMeat;

    public ProductView(Map<String, ItemWithStock> availableToppings, Customer customer, String selectedBread, String selectedMeat) {
        this.availableToppings = availableToppings;
        this.customer = customer;
        this.selectedBread = selectedBread;
        this.selectedMeat = selectedMeat;

        setTitle("Create a meal");
        setSize(300, 200);
        setModal(true);

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        Map<JCheckBox, Item> checkBoxes = new HashMap<>();
        for (Map.Entry<String, ItemWithStock> entry : availableToppings.entrySet()) {
            JCheckBox checkBox = new JCheckBox(entry.getKey());
            checkBoxes.put(checkBox, entry.getValue().getItem());
            checkBoxPanel.add(checkBox);
        }

        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(e -> {
            // Compute the price and calories of the meal
            double price = availableToppings.get(selectedBread).getItem().getPrice() +
                availableToppings.get(selectedMeat).getItem().getPrice();
            int calories = availableToppings.get(selectedBread).getItem().getCalories() +
                availableToppings.get(selectedMeat).getItem().getCalories();
            for (Map.Entry<JCheckBox, Item> entry : checkBoxes.entrySet()) {
                JCheckBox checkBox = entry.getKey();
                Item topping = entry.getValue();
                // If the topping is selected, add its price and calories to the meal
                if (checkBox.isSelected()) {
                    price += topping.getPrice();
                    calories += topping.getCalories();
                    customer.addItemToCart(topping.getName());
                }
            }
            System.out.println("The meal's price is: " + price);
            System.out.println("The meal's calories are: " + calories);
            dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            // User decided not to make the meal, so just close the dialog
            dispose();
        });

        getContentPane().add(checkBoxPanel, BorderLayout.CENTER);
        getContentPane().add(finishButton, BorderLayout.SOUTH);
        getContentPane().add(cancelButton, BorderLayout.NORTH);
        pack();
    }
}
