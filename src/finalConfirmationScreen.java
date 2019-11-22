import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class finalConfirmationScreen {
    JPanel mainPanel;
    Boolean isUsed;
    int ans;

    public finalConfirmationScreen() {
        this.mainPanel = new JPanel();
        isUsed = false;
        ans = 0;
    }

    public JPanel getPanel(Airline airline, Passenger passenger){

        mainPanel.setLayout(null);
        mainPanel.setSize(600, 400);

        JLabel title1 = new JLabel("<html>Flight data displaying for " + airline.getAirplaneFullName() +
                "</html>");
        JLabel title2 = new JLabel("<html> Enjoy your flight! </html>");
        JLabel title3 = new JLabel("<html> Flight is now boarding in " + airline.getGate() +  "</html>");

        title1.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        title3.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea passengerList = new JTextArea();
        JTextArea boardingPassDetails = new JTextArea();

        JButton exit = new JButton("Exit");
        JButton refresh = new JButton("Refresh Flight status");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIMethods.thankYouMessage();
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setIsUsed(true);
                mainPanel.setVisible(false);
                ans = 1;
            }
        });

        passengerList.setEditable(false);
        boardingPassDetails.setEditable(false);
        passengerList.setBackground(title1.getBackground());
        boardingPassDetails.setBackground(title1.getBackground());


        String firstLine = "                                                                                        " +
                airline.getNumPassengers() + " : " + airline.getMaxPassengers();
        JLabel firstLineLabel = new JLabel(firstLine);
        firstLineLabel.setHorizontalAlignment(SwingConstants.CENTER);



        StringBuilder text = new StringBuilder();
        text.append(firstLine).append("\n");
        for (String passengerDetail : airline.getPassengerDetails()) {
            text.append(passengerDetail).append("\n");
        }

        passengerList.setText(text.toString());
        JScrollPane scroll = new JScrollPane(passengerList);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        title1.setBounds(0, 10, 600, 22);
        title2.setBounds(0, 25, 600, 22);
        title3.setBounds(0, 40, 600, 22);
        scroll.setBounds(1, 65, 587, 120);
        passengerList.setBounds(10, 65, 580, 130);
        boardingPassDetails.setBounds(30, 200, 550, 110);
        exit.setBounds(150, 320, 100, 30);
        refresh.setBounds(270, 320, 200, 30);


        title1.setFont(title1.getFont().deriveFont(16.0f));
        title2.setFont(title1.getFont().deriveFont(16.0f));
        title3.setFont(title1.getFont().deriveFont(16.0f));
        exit.setFont(title1.getFont().deriveFont(12.0f));
        refresh.setFont(title1.getFont().deriveFont(12.0f));


        title1.setVisible(true);
        title2.setVisible(true);
        title3.setVisible(true);
        passengerList.setVisible(true);
        scroll.setVisible(true);
        exit.setVisible(true);
        refresh.setVisible(true);

        boardingPassDetails.setText(passenger.getBoardingPass().getBoardingPass());
        boardingPassDetails.setLineWrap(true);
        boardingPassDetails.setWrapStyleWord(true);
        boardingPassDetails.setVisible(true);


        mainPanel.add(title1);
        mainPanel.add(title2);
        mainPanel.add(title3);
        mainPanel.add(scroll);
        mainPanel.add(boardingPassDetails);
        mainPanel.add(exit);
        mainPanel.add(refresh);

        mainPanel.repaint();
        mainPanel.revalidate();
        return mainPanel;
    }

    public void setIsUsed(Boolean b) {
        isUsed = b;
    }

    public void setMainPanel(Boolean b) {
        mainPanel.setVisible(b);
    }

    public synchronized void waitUp() throws InterruptedException {
        while (!isUsed) {
            wait(10);
        }
        setIsUsed(false);
    }

    public int getAns() {
        return ans;
    }

    public synchronized int response() throws InterruptedException {
        if (ans == 0) {
            Thread.sleep(10);
            return response();
        }
        int thisBitch = ans;
        ans = 0;
        return thisBitch;
    }
}
