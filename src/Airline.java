import java.util.ArrayList;

public abstract class Airline {

    private String description;
    private String airplaneName;
    private int numPassengers;
    private int maxPassengers;
    private Gate gate;
    private ArrayList<Passenger> passenger;

    public Airline(String description, String airplaneName, int numPassengers, int maxPassengers, Gate gate,
                   ArrayList<Passenger> passenger) {

        this.description = description;
        this.airplaneName = airplaneName;
        this.numPassengers = numPassengers;
        this.maxPassengers = maxPassengers;
        this.gate = gate;
        this.passenger = passenger;
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

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public ArrayList<Passenger> getPassenger() {
        return passenger;
    }

    public void setPassengers(ArrayList<Passenger> passenger) {
        this.passenger = passenger;
    }


}

