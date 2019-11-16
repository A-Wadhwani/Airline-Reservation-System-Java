import java.io.*;
import java.net.Socket;

/**
 * Reservation Server Handler- CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class ReservationServerMainRequestHandler implements Runnable {
    private Socket clientSocket;

    public ReservationServerMainRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bfrFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bfwToClient = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            bfwToClient.flush();
            while (true) {
                String line = bfrFromClient.readLine();
                System.out.println(line);
                if(line.equals("\\"));
                    //Call the Special Window here
                else;
                    //Don't call the Special Window here
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
