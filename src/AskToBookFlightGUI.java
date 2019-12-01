import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ask to Book Flight GUI - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */

public class AskToBookFlightGUI {
    JPanel mainPanel = new JPanel(new BorderLayout());
    Boolean isUsed;

    public AskToBookFlightGUI() {
        this.isUsed = false;
    }

    public JPanel getPanel() {
        mainPanel.setLayout(null);
        JPanel beforeBookingWithoutPurdueImage = new JPanel();
        JLabel label1 = new JLabel("Do you want to book a flight today?");
        label1.setFont(label1.getFont().deriveFont(16.0f));
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        JButton bookFlight = new JButton("Yes, I want to book a Flight");
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
        exit.setBounds(150, 300, 100, 30);
        bookFlight.setBounds(260, 300, 200, 30);

        mainPanel.setSize(600, 400);
        mainPanel.add(label1);
        mainPanel.add(exit);
        mainPanel.add(bookFlight);
        mainPanel.repaint();
        return mainPanel;
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
