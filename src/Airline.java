import java.io.Serializable;
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
    private int numPassengers;
    private final int MAX_PASSENGERS = 100;
    private Gate gate;
    private ArrayList<Passenger> passengers;

    public Airline(String airplaneName, String description) {
        this.airplaneName = airplaneName;
        this.description = description;
        this.numPassengers = 0;
        this.gate = new Gate();
        this.passengers = new ArrayList<>();
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
        return MAX_PASSENGERS;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassengers(Passenger passenger) {
        numPassengers++;
        passenger.setBoardingPass(gate);
        passengers.add(passenger);
    }

}

