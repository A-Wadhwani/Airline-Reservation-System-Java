import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Passenger - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class Passenger implements Serializable {
    private String firstName;
    private String lastName;
    private String fullName;
    private int age;
    private BoardingPass boardingPass;

    public Passenger(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.fullName = firstName.substring(0, 1).toUpperCase() + ". " + lastName.toUpperCase();
        this.boardingPass = null;
    }

    public BoardingPass getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(Gate gate, String airlineName) {
        this.boardingPass = new BoardingPass(firstName, lastName, age, gate, airlineName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public void writeToFile(String airplaneName) throws IOException {

        //reading and updating arraylist
        ArrayList<String> temp = new ArrayList<>();
        String toAdd = this.fullName + ", " + this.age;
        File file = new File("reservations.txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            temp.add(in.nextLine());
        }
        int findFirstAirplaneName = temp.indexOf(airplaneName);
        int placeToAdd = temp.lastIndexOf("---------------------" + airplaneName);
        temp.add(placeToAdd, toAdd);
        temp.add(placeToAdd, "---------------------" + airplaneName);
        String passNum = temp.get(temp.indexOf(airplaneName) + 1).split("/")[0];
        String totalPassengers = temp.get(temp.indexOf(airplaneName) + 1).split("/")[1];
        int toUpdatePassNum = Integer.parseInt(passNum);
        toUpdatePassNum++;
        temp.set(findFirstAirplaneName + 1, Integer.toString(toUpdatePassNum) + "/" + totalPassengers);
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
