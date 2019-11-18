import javax.swing.*;

/**
 * Practice Test Cases - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */

public class hello {

    public static void main(String[] args) throws Exception {

        JFrame f1 = new JFrame();
        f1.setResizable(false);
        f1.setSize(600, 400);

        beforeBooking beforeBooking = new beforeBooking();
        beforeBooking.setMainPanel(true);

        f1.setTitle("Purdue Flight Reservation System");
        f1.setLocationRelativeTo(null);

        f1.add(beforeBooking.getPanel());
        f1.setVisible(true);
        beforeBooking.setMainPanel(true);
        f1.repaint();
        beforeBooking.waitUp();
        beforeBooking.setMainPanel(false);

        beforeBookingPanelChange beforeBookingPanelChange = new beforeBookingPanelChange();
        f1.add(beforeBookingPanelChange.getPanel());
        beforeBookingPanelChange.setMainPanel(true);
        f1.revalidate();
        beforeBookingPanelChange.waitUp();
        beforeBookingPanelChange.setMainPanel(false);


        chooseFlightFromDropDown chooseFlightFromDropDown = new chooseFlightFromDropDown();
        f1.add(chooseFlightFromDropDown.getPanel());
        f1.setVisible(true);
        chooseFlightFromDropDown.setMainPanel(true);
        f1.revalidate();
        chooseFlightFromDropDown.waitUp();
        chooseFlightFromDropDown.setMainPanel(false);

//
//        Delta delta = new Delta();
//        ServerMethods.updatePassengerDetails(delta);
//        JFrame jFrame;
//        jFrame = test2.getPopUp(delta);
//        jFrame.setVisible(true);
    }

}
