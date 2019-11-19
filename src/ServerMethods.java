import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Server Methods - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class ServerMethods {

    public static synchronized void updatePassengerDetails(Airline airline) throws IOException {
        String airplaneName = airline.getAirplaneName().toUpperCase();
        BufferedReader bfr = new BufferedReader(new FileReader("reservations.txt"));
        airline.setPassengerDetails(new ArrayList<>());
        String line;
        while (!(line = bfr.readLine()).equals("EOF")) {
            if (line.equals(airplaneName)) {
                break;
            }
        }
        line = bfr.readLine();
        int numPassengers = Integer.parseInt(line.substring(0, line.indexOf("/")));
        int maxPassengers = Integer.parseInt(line.substring(line.indexOf("/") + 1));
        airline.setNumPassengers(numPassengers);
        airline.setMaxPassengers(maxPassengers);
        bfr.readLine();
        while (!(line = bfr.readLine()).isBlank()) {
            airline.addPassengerDetails(line);
            bfr.readLine();
        }
    }

    public static synchronized Airline updateAirline(Airline airline) throws IOException {
        updatePassengerDetails(airline);
        return airline;
    }

    public static synchronized void addPassengers(Airline airline, Passenger passenger) throws IOException {
        ServerMethods.updatePassengerDetails(airline);
        if (airline.getNumPassengers() >= airline.getMaxPassengers()) {
            throw new IndexOutOfBoundsException("You should not be here.");
        }
        int num = airline.getNumPassengers() + 1;
        airline.setNumPassengers(num);
        passenger.setBoardingPass(airline.getGate(), airline.getAirplaneFullName());
        ServerMethods.writeToFile(airline.getAirplaneName().toUpperCase(), passenger);
    }

    public static synchronized ArrayList<String> getPassengerDetails(Airline airline) {
        try {
            ServerMethods.updatePassengerDetails(airline);
        } catch (IOException e) {
            GUIMethods.showErrorMessage("SOMETHING WENT REALLY WRONG WITH AIRLINE");
        }
        return airline.getPassengerDetails();
    }

    public static synchronized void writeToFile(String airplaneName, Passenger passenger) throws IOException {

        //reading and updating ArrayList
        String airplaneNameInProperCase = airplaneName.charAt(0) + airplaneName.substring(1).toLowerCase();
        ArrayList<String> temp = new ArrayList<>();
        String toAdd = passenger.getFullName() + ", " + passenger.getAge();
        File file = new File("reservations.txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            temp.add(in.nextLine());
        }
        int findFirstAirplaneName = temp.indexOf(airplaneName);
        int placeToAdd = temp.lastIndexOf("---------------------" + airplaneName);
        if (placeToAdd == -1) {
            placeToAdd = temp.lastIndexOf(airplaneNameInProperCase + " passenger list") + 1;
            temp.add(placeToAdd, "---------------------" + airplaneName);
            temp.add(placeToAdd, toAdd);
        } else {
            temp.add(placeToAdd, toAdd);
            temp.add(placeToAdd, "---------------------" + airplaneName);
        }
        String passNum = temp.get(temp.indexOf(airplaneName) + 1).split("/")[0];
        String totalPassengers = temp.get(temp.indexOf(airplaneName) + 1).split("/")[1];
        int toUpdatePassNum = Integer.parseInt(passNum);
        toUpdatePassNum++;
        temp.set(findFirstAirplaneName + 1, toUpdatePassNum + "/" + totalPassengers);
        in.close();

        //writing ArrayList into file
        FileOutputStream fos = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(fos);
        BufferedWriter bw = new BufferedWriter(pw);
        for (String s : temp) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();
        pw.close();
        fos.close();
    }
}
