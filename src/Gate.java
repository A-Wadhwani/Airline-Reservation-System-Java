import java.io.Serializable;
import java.util.Random;

/**
 * Gate - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class Gate implements Serializable {
    private char gateLetter;
    private int gateNumber;

    public Gate() {
        Random random = new Random();
        this.gateLetter = new char[]{'A', 'B', 'C'}[random.nextInt(3)];
        this.gateNumber = random.nextInt(18) + 1;
    }

    @Override
    public String toString(){
        return String.format("%s%02d",gateLetter,gateNumber);
    }

    public Gate(char gateLetter, char gateNumber) {
        this.gateLetter = gateLetter;
        this.gateNumber = gateNumber;
    }
}
