import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Welcome Page GUI - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class WelcomePageGUI {

    JPanel mainPanel = new JPanel(new BorderLayout());
    Boolean isUsed;

    public WelcomePageGUI() {
        this.isUsed = false;
    }


    public JPanel getPanel() {
        mainPanel.setLayout(null);
        mainPanel.setSize(600, 400);
        ImageIcon icon = new ImageIcon("PurdueLogo.png");
        JLabel label = new JLabel(icon);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel label1 = new JLabel("Welcome to the Purdue University Airline Reservation Management System!");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(label1.getFont().deriveFont(16.0f));


        JButton bookFlight = new JButton("Book a Flight");
        JButton exit = new JButton("Exit");

        bookFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                setIsUsed(true);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIMethods.thankYouMessage();
            }
        });

        label1.setBounds(0, 10, 600, 30);
        label.setBounds(30, 30, 540, 270);
        exit.setBounds(160, 300, 100, 30);
        bookFlight.setBounds(270, 300, 180, 30);


        mainPanel.add(label1);
        mainPanel.add(label);
        mainPanel.add(exit);
        mainPanel.add(bookFlight);
        mainPanel.repaint();
        return mainPanel;
    }

    public void setMainPanel(Boolean b) {
        mainPanel.setVisible(b);
    }

    public void setIsUsed(Boolean b) {
        isUsed = b;
    }

    public synchronized void waitUp() throws InterruptedException {
        while (!isUsed) {
            wait(10);
        }
    }


}
