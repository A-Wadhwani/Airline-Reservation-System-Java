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
    public static void main(String[] args){
        String hostname = "localhost";
        String portString;
        int port;
        System.out.println("here");
        Socket socket;

        try {
            hostname = "localhost";
            //Get Hostname from GUI
            portString = "55011";
            //Get PortString from GUI
            port = Integer.parseInt(portString);
            socket = new Socket(hostname, port);
            new ReservationClientRunner(socket);
            //Display Main Page

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
