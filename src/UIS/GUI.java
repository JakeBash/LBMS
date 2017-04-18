package UIS;

import Library.Library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * GUI for use in LBMS R2. A visual representation of our LBMS system that the user can interact with.
 *
 * @author Jake Bashaw
 */
public class GUI extends JPanel
{
    private int clientIDCounter;
    private ArrayList<GUICommandDisplay> commandDisplays;
    private JTabbedPane tabbedPane;
    private Library lib;

    /**
     * Constructs a new GUI object.
     */
    public GUI()
    {
        super(new GridLayout(1, 1));
        this.setPreferredSize(new Dimension(500,500));

        this.clientIDCounter = 1;
        this.commandDisplays = new ArrayList<>();
        this.lib = new Library();
        tabbedPane = new JTabbedPane();
        GUICommandDisplay initDisplay = new GUICommandDisplay(lib, tabbedPane, Long.valueOf(clientIDCounter));
        lib.addObserver(initDisplay);
        tabbedPane.add(initDisplay.getCommandDisplay(), "Client " + clientIDCounter);
        clientIDCounter++;
        JButton newClientButton = new NewClient();
        tabbedPane.add(newClientButton, "New Client");
        add(tabbedPane);
    }

    /**
     * Description
     */
    private class NewClient extends JButton implements ActionListener
    {
        /**
         * Description
         */
        public NewClient()
        {
            setText("Create a new client");
            setToolTipText("Create a new client connection");
            setFocusable(false);
            setRolloverEnabled(true);
            addActionListener(this);
        }

        /**
         * Description
         *
         * @param e - 
         */
        public void actionPerformed(ActionEvent e)
        {
            GUICommandDisplay newDisplay = new GUICommandDisplay(lib, tabbedPane, Long.valueOf(clientIDCounter));
            lib.addObserver(newDisplay);
            tabbedPane.add(newDisplay.getCommandDisplay(), "Client " + clientIDCounter, tabbedPane.getTabCount()-1);
            commandDisplays.add(newDisplay);
            clientIDCounter++;
        }
    }

    /**
     * Main method for testing
     */
    public static void main(String args[])
    {
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
