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
    private Library library;
    private JTextField tField;
    private JButton removeClient;
    private JTextArea tArea;
    private JTabbedPane tabbedPane;
    private JPanel commandDisplay;
    private CommandParser parser;
    private LibraryProtectionProxy proxy; // need to do this to implement responses when not connected
    private boolean checkChange;

    /**
     * Description
     *
     * @param library -
     * @param tabbedPane -
     * @param clientID -
     */
    public GUICommandDisplay(Library library, JTabbedPane tabbedPane, Long clientID)
    {
        this.proxy =  new LibraryProtectionProxy(library); // need to do this to implement responses when not connected
        this.parser = new CommandParser(this.proxy);

        this.clientID = clientID;

        this.library = library;

        this.tabbedPane = tabbedPane;
        this.tField = new JTextField(50);
        tField.addActionListener(this);

        this.tArea = new JTextArea(25, 50);
        tArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tArea);

        this.removeClient = new RemoveClient();

        this.commandDisplay = new JPanel(new BorderLayout());
        commandDisplay.add(tField, BorderLayout.CENTER);
        commandDisplay.add(removeClient, BorderLayout.SOUTH);
        commandDisplay.add(scrollPane, BorderLayout.NORTH);

        checkChange = false;
    }

    /**
     * Description
     *
     * @param evt -
     */
    public void actionPerformed(ActionEvent evt)
    {
        checkChange = true;

        String command = tField.getText();
        tArea.append(command + "\n");


        // Hacky way to deal with not being able to update UI if not connected
        boolean wasConnected = true;
        if (!proxy.isConnected())
            wasConnected = false;

        parser.parseCommand(clientID + "," + command);

        if(!proxy.isConnected() && !wasConnected) // This is really hacky and does not fulfil requirements totally
            tArea.append("invalid-client-id,client-not-connected;\n");
        if(!proxy.isConnected() && wasConnected)
            tArea.append(clientID + ",disconnect;" + "\n");
        tField.setText("");

    }

    /**
     * Description
     */
    private class RemoveClient extends JButton implements ActionListener
    {
        /**
         * Description
         */
        public RemoveClient()
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
            proxy.logout(clientID);
            proxy.clientDisconnect(clientID);
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
        if(checkChange)
        {
            tArea.append(this.library.getClientStatus(clientID) + "\n");
            checkChange = false;
        }
    }
}
