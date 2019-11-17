import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * _____ - CS 180 Homework _
 * <p>
 * <p>
 * Sources:
 *
 * </p>
 *
 * @author Aryan Wadhwani, wadhwani@purdue.edu, CS 18000
 * @version Date Month 2019
 */
public class hello1 {

    String x = null;

    public Panel returnPanel() {
        Panel f = new Panel();
        Button b1 = new Button("Hello");
        Button b2 = new Button("Exit");
        f.setSize(500, 500);
        f.setLayout(new FlowLayout());
        f.add(b1);
        f.add(b2);
        f.setVisible(true);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                x = "hi";
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return f;
    }

    public String response() throws InterruptedException {
        if(x==null) {
            Thread.sleep(10);
            return response();
        }
        String returnThisBitch = x;
        x = null;
        return returnThisBitch;
    }

}
