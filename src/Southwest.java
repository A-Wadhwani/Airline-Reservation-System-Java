import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Southwest - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */

public class Southwest extends Airline {

    public Southwest() {
        super("Southwest", "Southwest Airlines is proud to offer flights to Purdue University." +
                "\nWe are happy to offer free in-flight WiFi, as well as our amazing snacks." +
                "\nIn addition, we offer flights for much cheaper than other airlines, " +
                "and offer two free checked bags." +
                "\nWe hope you choose Southwest for your next flight.");
        try {
            UpdatePassengerDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void UpdatePassengerDetails() throws IOException {
        UpdatePassengerDetails("SOUTHWEST");
    }

    public synchronized void addPassengers(Passenger passenger) throws Exception {
        addPassengers(passenger, "SOUTHWEST");
    }
}
