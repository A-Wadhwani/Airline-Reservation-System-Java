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
        String s;
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
        do {
            s = chooseFlightFromDropDown.response();
            switch (s) {
                case "Southwest":
                    chooseFlightFromDropDown.setDescription("Southwest Airlines is proud to offer flights to Purdue" +
                            "University. We are happy to offer free flight WiFI, as well as amazing snacks. In" +
                            "addition, we offer flights at cheaper rates than other airlines, and offer two free " +
                            "checked bags. We hope you choose Southwest for your next flight");
                    f1.repaint();
                    f1.revalidate();
                    break;
                case "Alaska":
                    chooseFlightFromDropDown.setDescription("Alaska Airlines is proud to serve the string and " +
                            "knowledgeable Boilermakers from Purdue University. We Primarily fly westward and often" +
                            "have stops in Alaska and California. We have first class amenities even in coach class. " +
                            "We also have comfortable seats, and free WiFi. We hope you choose Alaska Airlines for" +
                            " your next itinerary");
                    f1.repaint();
                    f1.revalidate();
                    break;
                case "Delta":
                    chooseFlightFromDropDown.setDescription("Delta Airlines is proud to be one of the five premier " +
                            "Airlines at Purdue University. We offer exceptional services, with free limited WiFi for " +
                            "all customers. Passengers who use TMobile as cell phones get additional benefits." +
                            "We are also happy to offer power outlets in each seat for passenger use." +
                            "We hope you choose to fly Delta in your next flight.");
                    f1.repaint();
                    f1.revalidate();
                    break;
            }
        } while (!chooseFlightFromDropDown.isUsed);
        System.out.println(s);
        f1.revalidate();

        chooseFlightFromDropDown.setMainPanel(false);

//
//        Delta delta = new Delta();
//        ServerMethods.updatePassengerDetails(delta);
//        JFrame jFrame;
//        jFrame = test2.getPopUp(delta);
//        jFrame.setVisible(true);
    }

}
