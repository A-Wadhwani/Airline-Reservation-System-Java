import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class hello {


    public static void main(String[] args) throws Exception {
        Frame f1 = new Frame();
        f1.setSize(500,500);
        hello1 test = new hello1();
        f1.add(test.showGUI());
        f1.setVisible(true);
        while(true) {
            System.out.println(test.response());
        }
    }

}
