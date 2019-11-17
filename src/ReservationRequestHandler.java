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
    private static String SERVER_STOP_LISTENING_STRING = "DONE";
    private static String CLIENT_STOP_LISTENING_STRING = "FINISH";

    private Airline airline;

    public ReservationRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private class OBJECT_TRANSMITTER {
        private int response;

        OBJECT_TRANSMITTER(int response) {
            this.response = response;
        }

        public String giveResponse() {
            switch (response) {
                case 1:
                    return CLIENT_STOP_LISTENING_STRING;
                case 2:
                    return SERVER_STOP_LISTENING_STRING;
                default:
                    return "";
            }
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader bfrFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bfwToClient = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            bfwToClient.flush();
            runEverything(bfwToClient, bfrFromClient);
        } catch (IOException e) {
            System.out.println("Connection Terminated.");
        }
    }

    private void runEverything(BufferedWriter bw, BufferedReader br) throws IOException {
        handleStageFour(bw, br);
    }

    private void handleStageFour(BufferedWriter bw, BufferedReader br) throws IOException {
        String receivedInput = br.readLine();
        while (!receivedInput.equals(SERVER_STOP_LISTENING_STRING)) {
            if (receivedInput.equals("\\")) {
                //write the new window stuff
                handleSpecialWindow(bw, br);
            } else {
                if (receivedInput.equals("Delta")) {
                    airline = new Delta();
                }
                if (receivedInput.equals("Southwest")) {
                    airline = new Southwest();
                }
                if (receivedInput.equals("Alaska")) {
                    airline = new Alaska();
                }
                bw.write(airline.getDescription());
                bw.newLine();
                bw.flush();
                bw.write(CLIENT_STOP_LISTENING_STRING);
                bw.newLine();
                bw.flush();
            }
            receivedInput = br.readLine();
        }
        handleStageFive(bw, br);
    }

    private void handleStageFive(BufferedWriter bw, BufferedReader br) throws IOException {
        String receivedInput = br.readLine();
        boolean canProceed = true;
        while (!receivedInput.equals(SERVER_STOP_LISTENING_STRING)) {
            if (receivedInput.equals("\\")) {
                handleSpecialWindow(bw, br);
            } else {
                if (receivedInput.contains("CONTINUE")) {
                    canProceed = true;
                } else if (receivedInput.contains("RETURN")) {
                    canProceed = false;
                }
            }
            receivedInput = br.readLine();
        }
        if (canProceed)
            handleStageSix(bw, br);
        else
            handleStageFour(bw, br);
    }

    private void handleStageSix(BufferedWriter bw, BufferedReader br) {

    }

    private void handleStageSeven(BufferedWriter bw, BufferedReader br) {
    }

    private void handleSpecialWindow(BufferedWriter bw, BufferedReader br) throws IOException {
        for (String input : airline.getPassengerDetails()) {
            bw.write(input);
            bw.newLine();
            bw.flush();
        }
        bw.write(CLIENT_STOP_LISTENING_STRING);
        bw.newLine();
        bw.flush();
    }

    private void handleStageEight(ObjectOutputStream bw, ObjectInputStream br) {
    }


}
