import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reservation Client Handler - CS 180 Project 5
 * <p>
 * The reservation client is responsible for handling
 * stages 2 through 8 of the program.
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class ReservationClientRunner implements KeyListener {
    private Socket socket;
    private boolean initialized = false;
    private boolean canAccess = false;
    private BufferedReader readFromServer = null;
    private BufferedWriter writeToServer = null;
    private ObjectOutputStream writeObjToServer = null;
    private ObjectInputStream readObjFromServer = null;
    private String selectedAirline = null;

    private static String SERVER_STOP_LISTENING_STRING = "DONE";
    private static String CLIENT_STOP_LISTENING_STRING = "FINISH";

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

    public ReservationClientRunner(Socket socket) {
        this.socket = socket;
        JFrame jf = new JFrame();
        jf.setSize(1650, 1080);
        jf.addKeyListener(this);
        jf.setVisible(true);
        try {
            readFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeToServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writeToServer.flush();
            initialized = true;
            runStages(writeToServer, readFromServer);
            readFromServer.close();
            writeToServer.close();
        /*  writeObjToServer = new ObjectOutputStream(socket.getOutputStream());
            readObjFromServer = new ObjectInputStream(socket.getInputStream());
            handleStageEight(writeObjToServer, readObjFromServer);
            */
        } catch (IOException e) {
            GUIMethods.showErrorMessage("Client Disconnect");
        }
    }

    private void runStages(BufferedWriter writeToServer, BufferedReader readFromServer) throws IOException {
        handleStageTwo();
        handleStageThree();
        handleStageThree();
        handleStageFour(writeToServer, readFromServer);
    }

    private void handleStageTwo() {

    }

    private void handleStageThree() {

    }

    private void handleStageFour(BufferedWriter bw, BufferedReader br) throws IOException {
        Scanner sc = new Scanner(System.in);
        String scLine = sc.next();
        //TODO: Get Selected Item in GUI as a string and pass it through.
        while (!scLine.equals("EXIT")) { //TODO: Until it's time to exit.
            canAccess = true;

            bw.write(scLine);
            bw.newLine();
            bw.flush();

            String line;
            StringBuilder description = new StringBuilder();

            while (!(line = br.readLine()).equals(CLIENT_STOP_LISTENING_STRING)) {
                description.append(line).append("\n");
            }

            selectedAirline = scLine;
            scLine = sc.next();
            //TODO: Pass Description through as a Parameter in the GUI
        }//TODO: End Loop when the button is clicked and continuation occurs

        bw.write(SERVER_STOP_LISTENING_STRING);
        bw.newLine();
        bw.flush();

        handleStageFive(writeToServer, readFromServer);
    }

    private void handleStageFive(BufferedWriter bw, BufferedReader br) throws IOException {
        boolean confirmSelection = false; //TODO: Get yes or no right here.
        if (confirmSelection) {
            bw.write("CONTINUE");
            bw.newLine();
            bw.flush();

            bw.write(SERVER_STOP_LISTENING_STRING);
            bw.newLine();
            bw.flush();

            handleStageSix(bw, br);
        } else {
            bw.write("RETURN");
            bw.newLine();
            bw.flush();

            bw.write(SERVER_STOP_LISTENING_STRING);
            bw.newLine();
            bw.flush();

            handleStageFour(bw, br);
        }
    }

    private void handleStageSix(BufferedWriter bw, BufferedReader br) {
        handleStageSeven(writeToServer, readFromServer);
    }

    private void handleStageSeven(BufferedWriter bw, BufferedReader br) {
        canAccess = false;
    }

    private void handleStageEight(ObjectOutputStream bw, ObjectInputStream br) {
    }

    private void handleSpecialWindow(BufferedWriter bw, BufferedReader br) {
        try {
            writeToServer.write("\\");
            writeToServer.newLine();
            writeToServer.flush();

            String receivedLines;
            ArrayList<String> passengerDetails = new ArrayList<>();
            while (!(receivedLines = br.readLine()).equals(CLIENT_STOP_LISTENING_STRING)) {
                passengerDetails.add(receivedLines);
            }
            //TODO: Pass the Array list as a Parameter and pop up another GUI
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Don't do anything
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Don't do anything
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (initialized && canAccess) {
            if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                handleSpecialWindow(writeToServer, readFromServer);
            }
        }
    }
}
