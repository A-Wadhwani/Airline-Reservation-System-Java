import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class beforeBookingPanelChange {
    String x = null;
    JPanel mainPanel = new JPanel(new BorderLayout());
    Boolean isUsed;

    public beforeBookingPanelChange(){
        this.isUsed = false;
    }

    public JPanel getPanel() {

        JPanel beforeBookingWithoutPurdueImage = new JPanel();
        JLabel label1 = new JLabel("Do you want to book a flight today?");
        label1.setFont(label1.getFont().deriveFont(16.0f));
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        JButton bookFlight = new JButton("Yes, I want to book a Flight");
        JButton exit = new JButton("Exit");

        beforeBookingWithoutPurdueImage.add(bookFlight);
        beforeBookingWithoutPurdueImage.add(exit);

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
                System.exit(0);
            }
        });

        mainPanel.setSize(500, 600);
        mainPanel.add(label1, BorderLayout.NORTH);
        beforeBookingWithoutPurdueImage.setVisible(true);
        mainPanel.add(beforeBookingWithoutPurdueImage, BorderLayout.SOUTH);
        mainPanel.repaint();
        return mainPanel;
    }

    public synchronized String response() throws InterruptedException {
        if (x == null) {
            Thread.sleep(10);
            return response();
        }
        String returnThisBitch = x;
        x = null;
        return returnThisBitch;
    }

    public void setMainPanel(Boolean b) {
        mainPanel.setVisible(b);
    }

    public void setIsUsed(Boolean b){
        isUsed = b;
    }

    public synchronized void waitUp() throws InterruptedException {
        while (!isUsed){
            wait(10);
        }
    }

}
