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

    public String getAirplaneFullName() {
        return airplaneFullName;
    }

    protected void UpdatePassengerDetails(String airplaneName) throws IOException {
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

    public ArrayList<String> getPassengerDetails() {
        return passengerDetails;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    protected void addPassengerDetails(String passenger) {
        passengerDetails.add(passenger);
    }

    protected void addPassengers(Passenger passenger, String airplaneName) throws Exception {
        if (numPassengers >= maxPassengers) {
            throw new Exception("You should not be here.");
        }
        numPassengers++;
        passenger.setBoardingPass(gate, airplaneFullName);
        passenger.writeToFile(airplaneName);
    }

    public abstract void UpdatePassengerDetails() throws IOException;

    public abstract void addPassengers(Passenger p) throws Exception;
}

