import javax.swing.*;

public class helloWorld {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(600, 400);
        jf.setLocationRelativeTo(null);
        beforeBookingPanelChange beforeBookingPanelChange = new beforeBookingPanelChange();
        jf.add(beforeBookingPanelChange.getPanel());
        jf.setVisible(true);
    }
}
