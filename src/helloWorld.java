import javax.swing.*;

public class helloWorld {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(600, 400);
        jf.setLocationRelativeTo(null);
        GUIMethods.showBackslashPopup(new Delta());
        jf.setVisible(true);
        ;
    }
}
