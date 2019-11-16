import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

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
            return null;
        }
        return hostname;
    }

    private static int handlePort() {
        String portString = GUIMethods.getPort();
        try {
            return Integer.parseInt(portString);
        } catch (NumberFormatException e) {
            System.out.println("error 1");
            //show error for incorrect digits
            return handlePort();
        } catch (NullPointerException e) {
            return -1;
        }
    }

    private ReservationClient() {
        String hostname;
        int port;
        Socket socket;

        try {
            hostname = handleHostName();
            port = handlePort();
            if (hostname == null || port == -1)
                return;
            socket = new Socket(hostname, port);
            new ReservationClientRunner(socket);
        } catch (IOException e) {
            //show Error Message on Failure to Connect.
            System.out.println("cannot connect");
            new ReservationClient();
        }
    }

    public static void main(String[] args) {
        new ReservationClient();
    }
}
