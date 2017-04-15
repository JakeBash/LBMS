package UIS;

import Library.Library;
import LibraryProtectionProxy.LibraryProtectionProxy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Description
 *
 * @author Jake Bashaw
 */
public class GUICommandDisplay extends Component implements ActionListener, Observer
{
    private Long clientID;
    private JTextField tField;
    private JButton removeClient;
    private JTextArea tArea;
    private JTabbedPane tabbedPane;
    private JPanel commandDisplay;
    private CommandParser parser;

    /**
     * Description
     *
     * @param library -
     * @param tabbedPane -
     * @param clientID -
     */
    public GUICommandDisplay(Library library, JTabbedPane tabbedPane, Long clientID)
    {
        this.parser = new CommandParser(new LibraryProtectionProxy(library));

        this.clientID = clientID;

        this.tabbedPane = tabbedPane;

        this.tField = new JTextField(20);
        tField.addActionListener(this);

        this.tArea = new JTextArea(5, 20);
        tArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tArea);

        this.removeClient = new removeClient();

        this.commandDisplay = new JPanel(new BorderLayout());
        commandDisplay.add(tField, BorderLayout.CENTER);
        commandDisplay.add(removeClient, BorderLayout.SOUTH);
        commandDisplay.add(scrollPane, BorderLayout.NORTH);
    }

    /**
     * Description
     *
     * @param evt -
     */
    public void actionPerformed(ActionEvent evt)
    {
        String command = tField.getText();
        tArea.append(command + "\n");
        parser.parseCommand(clientID + "," +command);
        tField.setText("");
    }

    /**
     * Description
     */
    private class removeClient extends JButton implements ActionListener
    {
        /**
         * Description
         */
        public removeClient()
        {
            setText("Close Client");
            setPreferredSize(new Dimension(125, 25));
            setToolTipText("Close this client connection");
            setFocusable(false);
            setRolloverEnabled(true);
            addActionListener(this);
        }

        /**
         * Description
         *
         * @param e  -
         */
        public void actionPerformed(ActionEvent e)
        {
            int i = tabbedPane.indexOfTab("Client " + clientID);
            tabbedPane.remove(i);
        }
    }

    public JTextField gettField()
    {
        return tField;
    }

    public JTextArea gettArea()
    {
        return tArea;
    }

    public Long getClientID()
    {
        return clientID;
    }

    public JPanel getCommandDisplay()
    {
        return commandDisplay;
    }

    @Override
    public void update(Observable observable, Object o)
    {
        //TODO Has to go into the Client class in library and update whatever GUI things are dependent on it.
    }
}
