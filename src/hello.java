import java.io.IOException;
import java.util.Scanner;

public class hello {
    public static void main(String[] args) throws Exception {
        Airline airline = new Southwest();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your details: ");
        String firstName = sc.next();
        String lastName = sc.next();
        int age = sc.nextInt();
        Passenger p = new Passenger(firstName, lastName, age);
        airline.addPassengers(p);
        airline.UpdatePassengerDetails();
        for (String passenger : airline.getPassengerDetails()) {
            System.out.println(passenger);
        }
    }
}
