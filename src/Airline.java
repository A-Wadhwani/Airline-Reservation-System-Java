import java.io.*;
import java.util.ArrayList;

/**
 * Airline - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public abstract class Airline implements Serializable {

    private String description;
    private String airplaneName;
    private String airplaneFullName;
    private int numPassengers;
    private int maxPassengers;
    private Gate gate;
    private ArrayList<String> passengerDetails;


    public Airline(String airplaneName, String description) {
        this.airplaneName = airplaneName;
        this.description = description;
        this.airplaneFullName = String.format("%s Airlines", airplaneName);
        this.numPassengers = 0;
        this.gate = new Gate();
        this.passengerDetails = new ArrayList<>();
    }

   /*protected synchronized void UpdatePassengerDetails(String airplaneName) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader("reservations.txt"));
        passengerDetails = new ArrayList<>();
        String line;
        while (!(line = bfr.readLine()).equals("EOF")) {
            if (line.equals(airplaneName)) {
                break;
            }
        }
        line = bfr.readLine();
        int numPassengers = Integer.parseInt(line.substring(0, line.indexOf("/")));
        int maxPassengers = Integer.parseInt(line.substring(line.indexOf("/") + 1));
        this.setNumPassengers(numPassengers);
        this.setMaxPassengers(maxPassengers);
        bfr.readLine();
        while (!(line = bfr.readLine()).isBlank()) {
            addPassengerDetails(line);
            bfr.readLine();
        }
    }

    */

    public void setPassengerDetails(ArrayList<String> passengerDetails) {
        this.passengerDetails = passengerDetails;
    }

    public synchronized String getAirplaneFullName() {
        return airplaneFullName;
    }

    /*protected synchronized void UpdatePassengerDetails(String airplaneName) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader("reservations.txt"));
        passengerDetails = new ArrayList<>();
        String line;
        while (!(line = bfr.readLine()).equals("EOF")) {
            if (line.equals(airplaneName)) {
                break;
            }
        }
        line = bfr.readLine();
        int numPassengers = Integer.parseInt(line.substring(0, line.indexOf("/")));
        int maxPassengers = Integer.parseInt(line.substring(line.indexOf("/") + 1));
        this.setNumPassengers(numPassengers);
        this.setMaxPassengers(maxPassengers);
        bfr.readLine();
        while (!(line = bfr.readLine()).isBlank()) {
            addPassengerDetails(line);
            bfr.readLine();
        }
    }

     */

    public ArrayList<String> getPassengerDetails() {
        return passengerDetails;
    }

    public synchronized void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public synchronized String getDescription() {
        return description;
    }

    public synchronized void setDescription(String description) {
        this.description = description;
    }

    public synchronized String getAirplaneName() {
        return airplaneName;
    }

    public synchronized void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public synchronized int getNumPassengers() {
        return numPassengers;
    }

    public synchronized void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }

    public synchronized int getMaxPassengers() {
        return maxPassengers;
    }

    public synchronized Gate getGate() {
        return gate;
    }

    public synchronized void setGate(Gate gate) {
        this.gate = gate;
    }

    protected synchronized void addPassengerDetails(String passenger) {
        passengerDetails.add(passenger);
    }

    protected synchronized void addPassengers(Passenger passenger, String airplaneName) throws Exception {
        if (numPassengers >= maxPassengers) {
            throw new Exception("You should not be here.");
        }
        numPassengers++;
        passenger.setBoardingPass(gate, airplaneFullName);
        ServerMethods.writeToFile(airplaneName, passenger);
    }
}

