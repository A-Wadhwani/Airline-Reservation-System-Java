import java.io.Serializable;

/**
 * Boarding Pass - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class BoardingPass implements Serializable {
    private String airlineName;
    private String flightNumber;
    private String firstName;
    private String lastName;
    private int age;
    private Gate gate;

    public BoardingPass(String firstName, String lastName, int age, Gate gate, String airlineName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gate = gate;
        this.flightNumber = "18000";
        this.airlineName = airlineName;
    }

    public synchronized Gate getGate() {
        return gate;
    }

    public synchronized void setGate(Gate gate) {
        this.gate = gate;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", firstName, lastName, age, gate, flightNumber, airlineName);
    }

    public synchronized String getBoardingPass() {
        return String.format("BOARDING PASS FOR FLIGHT %s WITH %s" +
                        "\nPASSENGER FIRST NAME : %s" +
                        "\nPASSENGER LAST NAME : %s" +
                        "\nPASSENGER AGE : %d" +
                        "\nYou can now begin boarding at Gate %s",
                flightNumber, airlineName, firstName.toUpperCase(), lastName.toUpperCase(), age, getGate().toString());
    }
}
