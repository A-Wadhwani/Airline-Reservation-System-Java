import java.io.IOException;

public class hello {
    public static void main(String[] args) throws IOException {
        Passenger p = new Passenger("Hello", "World", 20);
        p.writeToFile("ALASKA");
    }
}
