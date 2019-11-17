import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Alaska - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */

public class Alaska extends Airline {

    public Alaska() {
        super("Alaska", "Alaska Airlines is proud to serve the strong and knowledgeable " +
                "Boilermakers from Purdue University." +
                "\nWe primarily fly westward, and often have stops in Alaska and California." +
                "\nWe have first class amenities, even in coach class." +
                "\nWe provide fun snacks, such as pretzels and goldfish." +
                "\nWe also have comfortable seats, and free WiFi." +
                "\nWe hope you choose Alaska Airlines for your next itinerary!");
        try {
            UpdatePassengerDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void UpdatePassengerDetails() throws IOException {
        UpdatePassengerDetails("ALASKA");
    }

    public synchronized void addPassengers(Passenger passenger) throws Exception {
        addPassengers(passenger, "ALASKA");
    }
}
