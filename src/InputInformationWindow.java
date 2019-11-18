import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * Practice Test Cases - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class InputInformationWindow {
    private Passenger passenger = null;

    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(600, 400);

        JLabel title = new JLabel("Please input your information below.");
        JLabel l1 = new JLabel("What is your first name?");
        JLabel l2 = new JLabel("What is your last name?");
        JLabel l3 = new JLabel("What is your age?");

        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField ageField = new JTextField();

        JButton nextWindow = new JButton("Next");
        JButton exit = new JButton("Exit");

        firstNameField.setEditable(true);
        lastNameField.setEditable(true);
        ageField.setEditable(true);

        title.setFont(title.getFont().deriveFont(25.0f));
        l1.setFont(l1.getFont().deriveFont(14.0f));
        l2.setFont(l2.getFont().deriveFont(14.0f));
        l3.setFont(l3.getFont().deriveFont(14.0f));

        title.setHorizontalAlignment(SwingConstants.CENTER);

        title.setBounds(0, 10, 600, 30);
        l1.setBounds(10, 35, 600, 30);
        firstNameField.setBounds(10, 65, 550, 60);
        l2.setBounds(10, 125, 600, 30);
        lastNameField.setBounds(10, 155, 550, 60);
        l3.setBounds(10, 215, 600, 30);
        ageField.setBounds(10, 245, 550, 60);
        nextWindow.setBounds(290, 320, 100, 30);
        exit.setBounds(160, 320, 100, 30);

        panel.add(title);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(firstNameField);
        panel.add(lastNameField);
        panel.add(ageField);
        panel.add(nextWindow);
        panel.add(exit);

        panel.setVisible(true);

        nextWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();

                    if (firstName.isBlank()) {
                        throw new NullPointerException();
                    }
                    if (lastName.isBlank()) {
                        throw new NullPointerException();
                    }
                    int age = Integer.parseInt(ageField.getText());

                    String confirmMessage = String.format("Are all the details you entered correct?" +
                            "\nThe passenger's name is %s %s and their age is %d." +
                            "\nIf all the information shown is correct, select the Yes" +
                            "\nbutton below, otherwise, select the No button", firstName, lastName, age);
                    if (GUIMethods.confirmPassengerInfo(confirmMessage) == JOptionPane.YES_OPTION) {
                        passenger = new Passenger(firstName, lastName, age);
                        panel.setVisible(false);
                    }
                } catch (NullPointerException e1) {
                    GUIMethods.showErrorMessage("Please enter your name and age properly!");
                } catch (NumberFormatException e1) {
                    GUIMethods.showErrorMessage("Wrong format for age");
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                System.exit(0);
            }
        });

        return panel;
    }

    public synchronized Passenger getPassenger() throws InterruptedException {
        while (passenger == null) {
            wait(10);
        }
        Passenger copy = passenger;
        passenger = null;
        return copy;
    }
}
