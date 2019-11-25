import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * GUI Methods - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class GUIMethods {
    private static String getHostnameAndPort(String message, String title) {
        ImageIcon icon = new ImageIcon("JavaCup.png");
        return (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION,
                icon, null, "");
    }

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error in Program", JOptionPane.ERROR_MESSAGE,
                null);
    }

    public static String getHostName() {
        return getHostnameAndPort("What is the Host name you'd like to connect to?", "Hostname?");
    }

    public static String getPort() {
        return getHostnameAndPort("What is the port you'd like to connect to?",
                "Port?");
    }

    public static void thankYouMessage() {
        ImageIcon icon = new ImageIcon("JavaCup.png");
        JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University " +
                "Airline Management System!");
        Runtime.getRuntime().exit(0);
    }

    public static int confirmPassengerInfo(String message) {
        ImageIcon icon = new ImageIcon("JavaCup.png");
        int x = JOptionPane.showConfirmDialog(null, message, "Confirm Info",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
        return x;
    }

    public static void showBackslashPopup(Airline airline) {
        JFrame newPopUp = getPopUp(airline);
        newPopUp.setSize(300, 220);
        newPopUp.revalidate();
        newPopUp.repaint();
        newPopUp.setVisible(true);
    }

    private static JFrame getPopUp(Airline airline) {
        JPanel popUpMain = new JPanel();
        popUpMain.setLayout(null);
        JFrame popUp = new JFrame("");

        popUp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel heading = new JLabel(airline.getAirplaneFullName() + ". " + airline.getNumPassengers() +
                " : " + airline.getMaxPassengers());



        Font f = heading.getFont();

// bold
        heading.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        heading.setFont(heading.getFont().deriveFont(16.0f));

        StringBuilder text = new StringBuilder();
        for (String passengerDetail : airline.getPassengerDetails()) {
            text.append(passengerDetail).append("\n");
        }

        JTextArea passengerList = new JTextArea(text.toString());
        passengerList.setEditable(false);
        passengerList.setBackground(UIManager.getColor("Label.background"));

        JScrollPane scroll = new JScrollPane(passengerList);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUp.dispose();
            }

        });
        exit.setFont(heading.getFont().deriveFont(12.0f));

        heading.setBounds(2, 10, 300, 20);
        passengerList.setBounds(200, 100, 150, 100);
        scroll.setBounds(2, 40, 287, 100);
        exit.setBounds(100, 150, 70, 25);


        scroll.setVisible(true);

        popUpMain.add(heading);
        popUpMain.add(exit);
        popUp.add(popUpMain);
        popUpMain.add(scroll);
        popUp.setResizable(false);
        return popUp;
    }
}
