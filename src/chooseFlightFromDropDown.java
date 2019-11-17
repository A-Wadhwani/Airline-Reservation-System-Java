import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class chooseFlightFromDropDown {
    String flightName = null;
    String description = "Delta Airlines is proud to be one of the five premier Airlines at" + "\n" + "Purdue University." +
            " We are offering exceptional services, with free limited WiFI for " +
            "all customers." + "\n" + "Passengers which use T-Mobile as a cell phone get additional benefits." + "\n" +
            "We are also happy to offer power outlets in each seat for passenger use." + "\n" +
            "nWe hope you choose to fly Delta as your next Airline.";
    JPanel mainPanel = new JPanel(new GridLayout(3,1));
    JTextArea descriptionText = new JTextArea(description);


    public JPanel getPanel() {
        descriptionText.setWrapStyleWord(true);
        descriptionText.setLineWrap(true);
        descriptionText.setBackground(UIManager.getColor("Label.background"));
        descriptionText.setEditable(false);
        descriptionText.setBounds(10,10,200,60);
        descriptionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel titleText = new JLabel("Choose a flight from the dropdown menu");
        String[] airlineNames = new String[]{"Delta", "Southwest", "Alaska"};
        JComboBox<String> dropdownNames = new JComboBox<>(airlineNames);
        JPanel panelWithDropdown = new JPanel();
        panelWithDropdown.add(dropdownNames);

        ActionListener comboBox = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flightName = (String) dropdownNames.getSelectedItem();
                mainPanel.setVisible(false);
                System.out.println("hi");
            }
        };

        Button bookFlight = new Button("Book a Flight");
        Button exit = new Button("Exit");

        bookFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                System.exit(0);
            }
        });


        mainPanel.add(titleText);
        mainPanel.add(panelWithDropdown);
        mainPanel.add(descriptionText);
        mainPanel.add(bookFlight, BorderLayout.SOUTH);
        mainPanel.add(exit, BorderLayout.SOUTH);
        mainPanel.setVisible(true);

        return mainPanel;
    }

    public String response() throws InterruptedException {
        if (flightName == null) {
            Thread.sleep(30);
            return response();
        }
        String returnThisBitch = flightName;
        flightName = null;
        return returnThisBitch;
    }

    public void setDescription(String description) {
        descriptionText.setText(description);
        mainPanel.repaint();
    }

    public JFrame getPopUp(Airline airline){
        JPanel popUpMain = new JPanel(new BorderLayout());
        JFrame popUp = new JFrame("");

        popUp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel heading = new JLabel(airline.getAirplaneFullName() + ". " + airline.getNumPassengers() +
                "/" + airline.getMaxPassengers());

        popUpMain.add(heading, BorderLayout.NORTH);

        StringBuilder text = new StringBuilder();
        for (String passengerDetail : airline.getPassengerDetails()) {
            text.append(passengerDetail).append("\n");
        }

        JTextArea passengerList = new JTextArea(text.toString());
        passengerList.setEditable(false);
        passengerList.setBackground(UIManager.getColor("Label.background"));

        JScrollPane scroll = new JScrollPane(passengerList);

        popUpMain.add(scroll, BorderLayout.CENTER);
        System.out.println("added scroll bar");
        popUpMain.setVisible(true);
        JButton exit = new JButton("Exit");
        exit.setBounds(40, 0, 50, 50);
        exit.setVisible(true);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUp.dispose();
            }
        });

        JPanel button = new JPanel(new BorderLayout());
        button.add(exit, BorderLayout.WEST);

        popUpMain.add(button, BorderLayout.SOUTH);
        popUp.add(popUpMain);
        popUp.setSize(300, 300);
        return popUp;
    }
}
