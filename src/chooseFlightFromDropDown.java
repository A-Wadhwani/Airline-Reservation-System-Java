import javax.crypto.spec.PSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class chooseFlightFromDropDown {
    String flightName;
    String lastSelectedFlight;
    String description;
    JPanel mainPanel;
    Boolean isUsed;
    JTextArea content;


    public chooseFlightFromDropDown() {
        this.flightName = null;
        this.mainPanel = new JPanel();
        this.isUsed = false;
        this.description = "Please choose a flight. Please.";
        this.content = new JTextArea(description);
        content.setEditable(false);
        content.setVisible(true);
    }

    public JPanel getPanel() {
        mainPanel.setLayout(null);
        mainPanel.setSize(600, 400);

        JLabel title = new JLabel("Choose a flight from the drop down menu");


        String[] airlinesList = new String[]{"Delta", "Southwest", "Alaska"};
        JComboBox<String> airlineDropDown = new JComboBox<>(airlinesList);
        airlineDropDown.setSelectedIndex(-1);
        airlineDropDown.setVisible(true);
        airlineDropDown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                flightName = (String) e.getItem();
            }
        });

        JButton exit = new JButton("Exit");
        JButton chooseThisFlight = new JButton("Choose this flight");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        chooseThisFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setIsUsed(true);
                mainPanel.setVisible(false);
            }
        });


        content.setLayout(null);
        content.setBackground(title.getBackground());
        content.setWrapStyleWord(true);
        content.setLineWrap(true);
        content.setVisible(true);


        title.setFont(title.getFont().deriveFont(14.0f));
        content.setFont(title.getFont());
        exit.setFont(title.getFont().deriveFont(12.0f));


        title.setHorizontalAlignment(SwingConstants.CENTER);


        title.setBounds(0, 10, 600, 30);
        airlineDropDown.setBounds(250, 40, 100, 30);
        content.setBounds(30, 100, 540, 200);
        exit.setBounds(150, 300, 100, 30);
        chooseThisFlight.setBounds(270, 300, 200, 30);

        exit.setVisible(true);
        chooseThisFlight.setVisible(true);


        mainPanel.add(title);
        mainPanel.add(airlineDropDown);
        mainPanel.add(content);
        mainPanel.add(exit);
        mainPanel.add(chooseThisFlight);

        mainPanel.setVisible(true);

        mainPanel.repaint();
        return mainPanel;

    }

    public synchronized String response() throws InterruptedException {

        while (flightName == null && !isUsed) {
            wait(10);
        }
        if (isUsed) {
            return lastSelectedFlight;
        }
        String returnThisBitch = flightName;
        lastSelectedFlight = flightName;
        flightName = null;
        //  System.out.println(returnThisBitch);
        return returnThisBitch;
    }

    public void setDescription(String description) {
        this.description = description;
        this.content.setText(description);
        content.repaint();
        mainPanel.repaint();
        mainPanel.revalidate();
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

    public void printDescription() {
        System.out.println(description);
    }
}
