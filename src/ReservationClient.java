import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Reservation Client Handler - CS 180 Project 5
 * <p>
 * The reservation client is responsible for handling
 * stages 2 through 8 of the program.
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class ReservationClient {
    private Socket socket;
    private static String serverStopListeningString = "DONE";
    Airline airlineChoice = null;
    ObjectOutputStream objos;
    ObjectInputStream objis;
    boolean canDo = false;

    JFrame f1 = new JFrame();

    public ReservationClient(Socket socket) {
        this.socket = socket;
        f1.setSize(600, 400);
        f1.setTitle("Purdue Flight Reservation System");
        f1.setLocationRelativeTo(null);
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
        WelcomePageGUI welcomePageGUI = new WelcomePageGUI();
        f1.add(welcomePageGUI.getPanel());
        f1.repaint();
        f1.revalidate();
        welcomePageGUI.waitUp();
    }

    private void confirmFlightBooking() throws InterruptedException {
        AskToBookFlightGUI confirmFlightBookingGUI = new AskToBookFlightGUI();
        f1.add(confirmFlightBookingGUI.getPanel());
        f1.repaint();
        f1.revalidate();
        confirmFlightBookingGUI.waitUp();
    }

    private void flightSelection(ObjectOutputStream oos, ObjectInputStream ois) throws IOException,
            ClassNotFoundException, InterruptedException {
        ChooseFlightFromDropDownGUI flightSelection = new ChooseFlightFromDropDownGUI();
        JPanel panel = flightSelection.getPanel();

        ActionMap actionMap = panel.getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = panel.getInputMap(condition);
        String vkBackSlash = "VK_BACK_SLASH";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), vkBackSlash);
        actionMap.put(vkBackSlash, new KeyAction(vkBackSlash));
        panel.requestFocus();

        f1.add(panel);
        flightSelection.setMainPanel(true);
        f1.repaint();
        f1.revalidate();
        while (!flightSelection.isUsed) {
            String selection = flightSelection.getFlightName();
            oos.writeObject(selection);
            oos.flush();
            canDo = true;
            oos.flush();
            boolean hasSpots = ois.readBoolean();
            if (hasSpots) {
                airlineChoice = (Airline) ois.readObject();
                flightSelection.setDescription(airlineChoice.getDescription());
                flightSelection.setButtonEnabled(true);
            } else {
                flightSelection.airlineDropDown.setSelectedIndex(-1);
                flightSelection.getFlightName();
                flightSelection.setButtonEnabled(false);
                JOptionPane.showMessageDialog(null, "The selected flight is full",
                        "Error in Flight Selection.", JOptionPane.ERROR_MESSAGE);
                flightSelection.setDescription("Please choose a flight. Please.");
                continue;
            }
            f1.repaint();
            f1.revalidate();
        }
        f1.revalidate();
        oos.writeObject(serverStopListeningString);
        oos.flush();
        confirmFlightSelection(oos, ois);
    }

    private void confirmFlightSelection(ObjectOutputStream oos, ObjectInputStream ois) throws IOException,
            ClassNotFoundException, InterruptedException {

        ConfirmFlightSelectionGUI confirmFlightChoice = new ConfirmFlightSelectionGUI();
        JPanel panel = confirmFlightChoice.getPanel(airlineChoice);

        ActionMap actionMap = panel.getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = panel.getInputMap(condition);
        String vkBackSlash = "VK_BACK_SLASH";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), vkBackSlash);
        actionMap.put(vkBackSlash, new KeyAction(vkBackSlash));
        panel.requestFocus();

        f1.add(panel);
        confirmFlightChoice.setMainPanel(true);
        f1.repaint();
        f1.revalidate();

        boolean confirm;
        int check = confirmFlightChoice.getResponseFromButtonClick();
        if (check == 2) {
            confirm = true;
        } else if (check == 1) {
            confirm = false;
        } else {
            throw new IOException("Unknown Error.");
        }
        oos.writeObject(confirm);
        oos.flush();
        if (confirm) {
            passengerInformation(oos, ois);
        } else {
            canDo = false;
            flightSelection(oos, ois);
        }
    }

    private void passengerInformation(ObjectOutputStream oos, ObjectInputStream ois) throws InterruptedException,
            IOException, ClassNotFoundException {
        InputInformationWindowGUI getDetails = new InputInformationWindowGUI();

        JPanel panel = getDetails.getPanel();
        ActionMap actionMap = panel.getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = panel.getInputMap(condition);
        String vkBackSlash = "VK_BACK_SLASH";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), vkBackSlash);
        actionMap.put(vkBackSlash, new KeyAction(vkBackSlash));
        panel.requestFocus();

        f1.add(panel);
        f1.repaint();
        f1.revalidate();
        oos.writeObject(getDetails.getPassenger());
        oos.flush();
        Passenger passenger = (Passenger) ois.readObject();
        canDo = false;
        finalConfirmationWindow(oos, ois, passenger);
    }

    private void finalConfirmationWindow(ObjectOutputStream oos, ObjectInputStream ois, Passenger passenger)
            throws IOException, ClassNotFoundException, InterruptedException {
        do {
            FinalConfirmationScreenGUI finalWindow = new FinalConfirmationScreenGUI();
            Airline airline = (Airline) ois.readObject();
            ArrayList<String> arrayList = (ArrayList<String>) ois.readObject();
            airline.setPassengerDetails(arrayList);
            airline.setNumPassengers(arrayList.size());
            f1.add(finalWindow.getPanel(airline, passenger));
            finalWindow.waitUp();
            f1.repaint();
            f1.revalidate();
            oos.writeBoolean(true);
            oos.flush();
        } while (true);
    }

    private void specialWindow() throws IOException, ClassNotFoundException {
        if (canDo) {
            objos.writeObject("\\");
            objos.flush();
            ArrayList<String> arrayList = (ArrayList) objis.readObject();
            Airline airline = (Airline) objis.readObject();
            airline.setPassengerDetails(arrayList);
            GUIMethods.showBackslashPopup(airline);
        }
    }

    private class KeyAction extends AbstractAction {

        public KeyAction(String actionCommand) {
            putValue(ACTION_COMMAND_KEY, actionCommand);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                specialWindow();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
