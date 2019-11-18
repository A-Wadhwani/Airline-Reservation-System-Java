import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
    Boolean isUsed;

    public chooseFlightFromDropDown() {
        this.isUsed = false;
    }

    public JPanel getPanel() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(600, 400);

        JLabel title = new JLabel("Choose a flight from the drop down menu");


        String[] airlinesList = new String[]{"Delta", "Southwest", "Alaska"};
        JComboBox<String> airlineDropDown = new JComboBox<>(airlinesList);
        airlineDropDown.setVisible(true);
        airlineDropDown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                flightName = (String) e.getItem();
            }
        });



        title.setFont(title.getFont().deriveFont(12.0f));



        title.setHorizontalAlignment(SwingConstants.CENTER);


        title.setBounds(0, 10, 600, 30);
        airlineDropDown.setBounds(240, 40, 100, 30);


        mainPanel.add(title);
        mainPanel.add(airlineDropDown);

        mainPanel.setVisible(true);

        mainPanel.repaint();
        return mainPanel;

    }

    public synchronized String response() throws InterruptedException {
        if (flightName == null) {
            Thread.sleep(10);
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
