import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Practice Test Cases - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */

public class hello {

    public static void main(String[] args) throws Exception {

        JFrame f1 = new JFrame();
        f1.setSize(500,600);

        beforeBooking test = new beforeBooking();

        f1.setTitle("Purdue Flight Reservation System");
        f1.setLocationRelativeTo(null);

        f1.add(test.getPanel());
        f1.setVisible(true);

        beforeBookingPanelChange test1 = new beforeBookingPanelChange();
        f1.add(test1.getPanel());

        chooseFlightFromDropDown test2 = new chooseFlightFromDropDown();
        f1.add(test2.getPanel());

        Delta delta = new Delta();
        JFrame jFrame;
        jFrame = test2.getPopUp(delta);
        jFrame.setVisible(true);
    }

}
