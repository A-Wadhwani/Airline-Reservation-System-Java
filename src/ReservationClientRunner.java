import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

/**
 * Reservation Client Handler - CS 180 Project 5
 *
 * @author Aryan Wadhwani, Gowri Harish, CS 18000
 * @version 15th November 2019
 */
public class ReservationClientRunner implements KeyListener {
    Socket socket;
    boolean initialized = false;
    boolean canAccess = false;
    BufferedReader readFromServer = null;
    BufferedWriter writeToServer = null;

    public ReservationClientRunner(Socket socket) {
        this.socket = socket;
        //Stage 1 - 4
        canAccess = true;
        System.out.println("HELLO");
        System.out.println("HELLO NO MORE");
        JFrame jf = new JFrame();
        jf.setSize(1650, 1080);
        jf.addKeyListener(this);
        jf.setVisible(true);
        try {
            readFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeToServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writeToServer.write("TEST");
            writeToServer.newLine();
            writeToServer.flush();
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (initialized && canAccess) {
            if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                try {
                    writeToServer.write("\\");
                    writeToServer.newLine();
                    writeToServer.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
