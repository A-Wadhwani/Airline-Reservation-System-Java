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
        return getHostnameAndPort("What is the HostName you'd like to connect to?", "Hostname?");
    }

    public static String getPort() {
        return getHostnameAndPort("What is the port you'd like to connect to?", "Port?");
    }

    public static void thankYouMessage() {
        ImageIcon icon = new ImageIcon("JavaCup.png");
        JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University " +
                "Airline Management System!");
    }

    public static int confirmPassengerInfo(String message) {
        ImageIcon icon = new ImageIcon("JavaCup.png");
        int x = JOptionPane.showConfirmDialog(null, message, "Confirm Info",
                JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon);
        return x;
    }

    public static void showBackslashPopup(Airline airline){
        JFrame newPopUp = getPopUp(airline);
        newPopUp.setSize(300, 300);
        newPopUp.revalidate();
        newPopUp.repaint();
        newPopUp.setVisible(true);
    }

    private static JFrame getPopUp(Airline airline){
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
        return popUp;
    }
}
