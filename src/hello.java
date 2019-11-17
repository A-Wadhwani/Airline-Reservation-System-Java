import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class hello {


    public static void main(String[] args) throws Exception {
        Frame mainFrame = new Frame();
        mainFrame.setSize(300, 300);
        hello1 test = new hello1();
        mainFrame.add(test.returnPanel());
        mainFrame.setVisible(true);
        System.out.println(test.response());
        System.out.println("bye");
    }

}
