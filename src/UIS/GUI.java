package UIS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * GUI for use in LBMS R2. A visual representation of our LBMS system that the user can interact with.
 *
 * @author Jake Bashaw
 */
public class GUI extends JPanel {
    private int clientIDCounter = 1;
    private ArrayList<GUICommandDisplay> commandDisplays = new ArrayList<>();

    /**
     * Constructs a new GUI object.
     */
    public GUI() {
        super(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Client " + clientIDCounter++, new GUICommandDisplay().getCommandDisplay());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.addTab("Client " + clientIDCounter++, new GUICommandDisplay().getCommandDisplay());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        add(tabbedPane);
    }

    /**
     * Main method for testing
     */
    public static void main(String args[]) {
        //Create and set up the window.
        JFrame frame = new JFrame("LBMS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new GUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
