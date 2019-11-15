public class BoardingPass {
    private String firstName;
    private String lastName;
    private int age;
    private Gate gate;

    public BoardingPass(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }
}
