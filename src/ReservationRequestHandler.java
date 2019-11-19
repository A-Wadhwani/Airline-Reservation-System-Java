import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

/**
 * Reservation Server Handler- CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class ReservationRequestHandler implements Runnable {
    private Socket clientSocket;
    private Airline airline;
    private static String SERVER_STOP_LISTENING_STRING = "DONE";
    private static String CLIENT_STOP_LISTENING_STRING = "FINISH";

    public static Delta delta;
    public static Southwest southwest;
    public static Alaska alaska;

    public ReservationRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.flush();
            flightSelection(oos, ois);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection Terminated.");
        }
    }

    private void flightSelection(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        String receivedInput = (String) ois.readObject();
        while (!receivedInput.equals(SERVER_STOP_LISTENING_STRING)) {
            if (receivedInput.equals("\\")) {
                handleSpecialCase(oos, ois);
            } else {
                if (receivedInput.equals("Delta")) {
                    airline = delta;
                }
                if (receivedInput.equals("Southwest")) {
                    airline = southwest;
                }
                if (receivedInput.equals("Alaska")) {
                    airline = alaska;
                }
                ServerMethods.updatePassengerDetails(airline);
                if(airline.getNumPassengers()==airline.getMaxPassengers()){
                    oos.writeBoolean(false);
                }
                else{
                    oos.writeBoolean(true);
                    oos.writeObject(airline);
                }
                oos.flush();
            }
            receivedInput = (String) ois.readObject();
        }
        confirmFlightSelection(oos, ois);
    }

    private void confirmFlightSelection(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Object obj = ois.readObject();
        while (obj instanceof String) {
            if ("\\".equals((String) obj)) {
                handleSpecialCase(oos, ois);
            }
            obj = ois.readObject();
        }
        boolean confirm = (boolean) obj;
        if (confirm) {
            generateBoardingPass(oos, ois);
        } else {
            flightSelection(oos, ois);
        }
    }

    private void generateBoardingPass(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Object obj = ois.readObject();
        while (obj instanceof String) {
            if ("\\".equals((String) obj)) {
                handleSpecialCase(oos, ois);
            }
            obj = ois.readObject();
        }
        Passenger passenger = (Passenger) obj;
        ServerMethods.addPassengers(airline, passenger);
        oos.writeObject(passenger);
        oos.flush();
    }

    private void handleSpecialCase(ObjectOutputStream oos, ObjectInputStream ois) throws IOException {
        ServerMethods.updatePassengerDetails(airline);
        oos.writeObject(ServerMethods.getPassengerDetails(airline));
        oos.writeObject(airline);
        oos.flush();
    }

}
