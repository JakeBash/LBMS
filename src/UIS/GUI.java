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
    private int clientIDCounter = 1;
    private ArrayList<GUICommandDisplay> commandDisplays = new ArrayList<>();
    private JTabbedPane tabbedPane;
    private Library lib;

    /**
     * Constructs a new GUI object.
     */
    public GUI()
    {
        super(new GridLayout(1, 1));
        this.lib = new Library();
        tabbedPane = new JTabbedPane();
        tabbedPane.add(new GUICommandDisplay(tabbedPane, Long.valueOf(clientIDCounter)).getCommandDisplay(), "Client " + clientIDCounter);
        clientIDCounter++;
        JButton button = new newClient();
        tabbedPane.add(button, "New Client");
        add(tabbedPane);
    }

    /**
     * Description
     */
    private class newClient extends JButton implements ActionListener
    {
        /**
         * Description
         */
        public newClient()
        {
            int size = 17;
            setPreferredSize(new Dimension(size, size));
            setText("Create a new client");
            setToolTipText("Create a new client connection");
            setContentAreaFilled(false);
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
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
            GUICommandDisplay newDisplay = new GUICommandDisplay(tabbedPane, Long.valueOf(clientIDCounter));
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
