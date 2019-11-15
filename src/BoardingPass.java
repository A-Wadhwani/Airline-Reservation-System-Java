public class BoardingPass {
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
