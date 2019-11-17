import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class beforeBookingPanelChange {
    String x = null;

    public JPanel getPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel beforeBookingWithoutPurdueImage = new JPanel();
        JLabel label1 = new JLabel("Do you want to book a flight today?");
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        Button bookFlight = new Button("Yes, I want to book a Flight");
        Button exit = new Button("Exit");

        beforeBookingWithoutPurdueImage.add(bookFlight);
        beforeBookingWithoutPurdueImage.add(exit);

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

        mainPanel.setSize(500, 600);
        mainPanel.add(label1, BorderLayout.NORTH);
        beforeBookingWithoutPurdueImage.setVisible(true);
        mainPanel.add(beforeBookingWithoutPurdueImage, BorderLayout.SOUTH);
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
