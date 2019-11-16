import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Reservation Server - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class ReservationServer {
    private static ServerSocket serverSocket;
    public static Object obj = new Object();

    private ReservationServer() throws IOException {
        serverSocket = new ServerSocket(0);
    }

    private static void runServer() {
        Socket clientSocket;
        int clientCount = 0;
        System.out.println("Server is running on port: " + serverSocket.getLocalPort());

        while (true) {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("bye");
                break;
            }
            ReservationRequestHandler requestHandler = new ReservationRequestHandler(clientSocket);
            Thread mainHandlerThread = new Thread(requestHandler);
            mainHandlerThread.start();
            System.out.printf("Client %d is connected!\n", clientCount++);
        }
    }

    public static void main(String[] args) {
        try {
            new ReservationServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        runServer();
    }
}
