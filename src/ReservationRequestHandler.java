import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Reservation Server Handler- CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class ReservationRequestHandler implements Runnable {
    private Socket clientSocket;
    private Airline airline;
    private static String serverStopListeningString = "DONE";

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
        while (!receivedInput.equals(serverStopListeningString)) {
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
                if (airline.getNumPassengers() == airline.getMaxPassengers()) {
                    oos.writeBoolean(false);
                } else {
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
        ServerMethods.updatePassengerDetails(airline);
        oos.writeObject(passenger);
        oos.flush();
        finalConfirmation(oos, ois);
    }

    private void finalConfirmation(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        while (true) {
            Airline save = ServerMethods.updateAirline(airline);
            ArrayList arrayList = save.getPassengerDetails();
            oos.writeObject(save);
            oos.writeObject(arrayList);
            oos.flush();
            ois.readBoolean();
        }
    }

    private void handleSpecialCase(ObjectOutputStream oos, ObjectInputStream ois) throws IOException {
        ServerMethods.updatePassengerDetails(airline);
        oos.writeObject(ServerMethods.getPassengerDetails(airline));
        oos.writeObject(airline);
        oos.flush();
        oos.flush();
    }
}
