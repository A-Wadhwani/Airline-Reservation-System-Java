import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmFlightSelectionGUI {
    private JPanel mainPanel;
    private int answer;

    public ConfirmFlightSelectionGUI() {
        mainPanel = new JPanel();
        answer = 0;
    }

    public JPanel getPanel(Airline airline) {
        mainPanel.setLayout(null);
        mainPanel.setSize(600, 400);
        JLabel title = new JLabel("Are you sure you want to book a flight on " +
                airline.getAirplaneFullName() + "?");

        JButton exit = new JButton("Exit");
        JButton diffFlight = new JButton("No, I want a different flight");
        JButton yesFlight = new JButton("Yes, I want this flight");


        title.setFont(title.getFont().deriveFont(14.0f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        exit.setFont(title.getFont().deriveFont(12.0f));
        diffFlight.setFont(title.getFont().deriveFont(12.0f));
        yesFlight.setFont(title.getFont().deriveFont(12.0f));

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIMethods.thankYouMessage();
            }
        });

        diffFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = 1;
                setIsUsed(true);
                mainPanel.setVisible(false);
            }
        });

        yesFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = 2;
                setIsUsed(true);
                mainPanel.setVisible(false);

            }
        });

        title.setBounds(0, 10, 600, 30);
        exit.setBounds(30, 300, 100, 30);
        diffFlight.setBounds(140, 300, 200, 30);
        yesFlight.setBounds(350, 300, 200, 30);


        mainPanel.add(title);
        mainPanel.add(exit);
        mainPanel.add(diffFlight);
        mainPanel.add(yesFlight);
        mainPanel.repaint();
        mainPanel.revalidate();
        return mainPanel;
    }

    public synchronized int getResponseFromButtonClick() throws InterruptedException {
        while (answer == 0) {
            wait(10);
        }
        int responseFromClient = answer;
        answer = 0;
        return responseFromClient;
    }

    public void setIsUsed(Boolean b) {
    }

    public void setMainPanel(Boolean b) {
        mainPanel.setVisible(b);
    }

}
