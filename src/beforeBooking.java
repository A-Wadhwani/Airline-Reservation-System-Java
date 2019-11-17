import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * _____ - CS 180 Homework _
 * <p>
 * <p>
 * Sources:
 *
 * </p>
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version Date Month 2019
 */
public class beforeBooking {

    String x = null;

    public JPanel getPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel beforeBookingWithPurdueImage = new JPanel();
        ImageIcon icon = new ImageIcon("PurdueLogo.png");
        JLabel label = new JLabel(icon);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel label1 = new JLabel("Welcome to the Purdue University Airline Reservation Management System!");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(label1.getFont().deriveFont(16.0f));


        Button bookFlight = new Button("Book a Flight");
        Button exit = new Button("Exit");

        beforeBookingWithPurdueImage.add(bookFlight);
        beforeBookingWithPurdueImage.add(exit);
        beforeBookingWithPurdueImage.setVisible(true);

        bookFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainPanel.add(label1, BorderLayout.NORTH);
        mainPanel.add(label, BorderLayout.CENTER);
        mainPanel.add(beforeBookingWithPurdueImage, BorderLayout.SOUTH);
        return mainPanel;
    }

    public String response() throws InterruptedException {
        if (x == null) {
            Thread.sleep(10);
            return response();
        }
        String returnThisBitch = x;
        x = null;
        return returnThisBitch;
    }

}
