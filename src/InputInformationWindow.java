import javax.swing.*;
import java.awt.*;

/**
 * Practice Test Cases - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class InputInformationWindow {
    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(600, 400);

        JLabel title = new JLabel("Please input your information below.");
        JLabel l1 = new JLabel("What is your first name?");
        JLabel l2 = new JLabel("What is your last name?");
        JLabel l3 = new JLabel("What is your age?");

        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField age = new JTextField();

        firstName.setEditable(true);
        lastName.setEditable(true);
        age.setEditable(true);

        title.setFont(title.getFont().deriveFont(25.0f));
        l1.setFont(l1.getFont().deriveFont(16.0f));
        l2.setFont(l2.getFont().deriveFont(16.0f));
        l3.setFont(l3.getFont().deriveFont(16.0f));

        title.setHorizontalAlignment(SwingConstants.CENTER);

        title.setBounds(0, 10, 600, 30);
        l1.setBounds(10, 50, 600, 30);
        firstName.setBounds(10, 80, 550, 60);
        l2.setBounds(10, 140, 600, 30);
        lastName.setBounds(10, 170, 550, 60);
        l3.setBounds(10, 230, 600, 30);
        age.setBounds(10, 260, 550, 60);

        panel.add(title);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(firstName);
        panel.add(lastName);
        panel.add(age);

        panel.setVisible(true);
        return panel;
    }
}
