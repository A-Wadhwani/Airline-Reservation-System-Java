import java.io.IOException;
import java.net.Socket;

/**
 * Reservation Client - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class ReservationClient {

    private static String handleHostName() {
        String hostname = GUIMethods.getHostName();
        if (hostname == null) {
            GUIMethods.thankYouMessage();
            System.exit(0);
        }
        return hostname;
    }

    private static int handlePort() {
        String portString = GUIMethods.getPort();
        if (portString == null) {
            GUIMethods.thankYouMessage();
            System.exit(0);
        }
        try {
            return Integer.parseInt(portString);
        } catch (NumberFormatException e) {
            GUIMethods.showErrorMessage("Please put correct numbers!");
            return handlePort();
        }
    }

    private ReservationClient() {
        String hostname;
        int port;
        Socket socket;

        try {
            hostname = handleHostName();
            port = handlePort();
            socket = new Socket(hostname, port);
            new ReservationClientRunner(socket);
        } catch (IOException e) {
            GUIMethods.showErrorMessage("Failed to Connect!");
            new ReservationClient();
        }
    }

    public static void main(String[] args) {
        new ReservationClient();
    }
}
