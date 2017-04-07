package UIS;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * GUI for use in LBMS R2. A visual representation of our LBMS system that the user can interact with.
 *
 * @author Jake Bashaw
 */
public class GUI extends JPanel implements ActionListener
{
    private int clientIDCounter = 1;
    private ArrayList<GUICommandDisplay> commandDisplays = new ArrayList<>();
    private JTabbedPane tabbedPane;

    /**
     * Constructs a new GUI object.
     */
    public GUI()
    {
        super(new GridLayout(1, 1));
        tabbedPane = new JTabbedPane();
        tabbedPane.add(new GUICommandDisplay(tabbedPane).getCommandDisplay(), clientIDCounter-2);
        clientIDCounter++;
        JButton button = new TabButton();
        tabbedPane.add(button);
        add(tabbedPane);
    }

    public void actionPerformed(ActionEvent evt)
    {

    }

    private class TabButton extends JButton implements ActionListener
    {
        public TabButton()
        {
            int size = 17;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("Create a new tab");
            setContentAreaFilled(false);
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            setRolloverEnabled(true);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            tabbedPane.add(new GUICommandDisplay().getCommandDisplay(tabbedPane), clientIDCounter-1);
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
