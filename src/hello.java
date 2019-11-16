import javax.swing.*;
import java.awt.*;


public class hello {

    public static String getHostnameAndPort(String message, String title) {
        ImageIcon icon = new ImageIcon("JavaCup.png");
        return (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION, icon,
                null, "");
    }

    public static void goToBookFlight() {
    }


    public static void main(String[] args) throws Exception {
        String hostname = getHostnameAndPort("What is the hostname you's like to connect to?",
                "Hostname?"); //hostname cannot be null
        String port = getHostnameAndPort("What is the port you'd like to connect to?",
                "Port?");
        goToBookFlight();
    }

}
