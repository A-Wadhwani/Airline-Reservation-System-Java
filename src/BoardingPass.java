import java.io.Serializable;

/**
 * Boarding Pass - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class BoardingPass implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private Gate gate;

    public BoardingPass(String firstName, String lastName, int age, Gate gate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gate = gate;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }
}
