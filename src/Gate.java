import java.util.Random;

/**
 * Gate - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version Date Month 2019
 */
public class Gate {
    private char gateLetter;
    private int gateNumber;

    public Gate() {
        Random random = new Random();
        this.gateLetter = new char[]{'A', 'B', 'C'}[random.nextInt(3)];
        this.gateNumber = random.nextInt(18) + 1;
    }

    public String getGate() {
        return String.format("%s%02d", gateLetter, gateNumber);
    }

    public Gate(char gateLetter, char gateNumber) {
        this.gateLetter = gateLetter;
        this.gateNumber = gateNumber;
    }
}
