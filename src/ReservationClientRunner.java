import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
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
    private static String SERVER_STOP_LISTENING_STRING = "DONE";
    private static String CLIENT_STOP_LISTENING_STRING = "FINISH";
    ObjectOutputStream objos;
    ObjectInputStream objis;

    JFrame f1 = new JFrame();

    public ReservationClientRunner(Socket socket) {
        this.socket = socket;
        f1.setSize(600, 400);
        f1.addKeyListener(this);
        f1.setVisible(true);
        f1.setResizable(false);

        try {
            objos = new ObjectOutputStream(socket.getOutputStream());
            objis = new ObjectInputStream(socket.getInputStream());
            objos.flush();
            welcomePage();
            confirmFlightBooking();
            flightSelection(objos, objis);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void welcomePage() throws InterruptedException {
        beforeBooking welcomePageGUI = new beforeBooking();
        f1.add(welcomePageGUI.getPanel());
        f1.repaint();
        f1.revalidate();
        welcomePageGUI.waitUp();
    }

    private void confirmFlightBooking() throws InterruptedException {
        beforeBookingPanelChange confirmFlightBookingGUI = new beforeBookingPanelChange();
        f1.add(confirmFlightBookingGUI.getPanel());
        f1.repaint();
        f1.revalidate();
        confirmFlightBookingGUI.waitUp();
    }

    private void flightSelection(ObjectOutputStream oos, ObjectInputStream ois) throws IOException,
            ClassNotFoundException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        String scLine = sc.next();
        while (!scLine.equals("EXIT")) {
            oos.writeObject(scLine);
            oos.flush();
            Airline airline = (Airline) ois.readObject();
            System.out.println(airline.getDescription());
            scLine = sc.next();
        }
        System.out.println("exited");
        oos.writeObject(SERVER_STOP_LISTENING_STRING);
        oos.flush();
        confirmFlightSelection(oos, ois);
    }

    private void confirmFlightSelection(ObjectOutputStream oos, ObjectInputStream ois) throws IOException,
            ClassNotFoundException, InterruptedException {
        boolean confirm = true;
        oos.writeBoolean(confirm);
        oos.flush();
        if (confirm) {
            passengerInformation(oos, ois);
        } else {
            flightSelection(oos, ois);
        }
    }

    private void passengerInformation(ObjectOutputStream oos, ObjectInputStream ois) throws InterruptedException,
            IOException, ClassNotFoundException {
        InputInformationWindow getDetails = new InputInformationWindow();
        f1.add(getDetails.getPanel());
        f1.repaint();
        f1.revalidate();
        oos.writeObject(getDetails.getPassenger());
        oos.flush();
        Passenger passenger = (Passenger) ois.readObject();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            objos.writeObject("\\");
            ArrayList<String> arrayList = (ArrayList) objis.readObject();
            Airline airline = (Airline) objis.readObject();
            airline.setPassengerDetails(arrayList);
            GUIMethods.showBackslashPopup(airline);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
