package UIS;

import Library.Library;
import LibraryProtectionProxy.LibraryProtectionProxy;
import LibraryProtectionProxy.LibrarySubject;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

/**
 * This class handles the input of user commands and represents the "view" of our LBMS application. The accepting and
 * distribution of commands is handled here.
 *
 * @author Kyle Kaniecki
 * @author Tyler
 */
public class PTUI implements Observer
{
    private CommandParser commandparser;
    private BufferedReader reader;
    private Long clientID;
    private LibraryProtectionProxy proxy;
    private Library lib;

    /**
     * Creates a new PTUI that accepts input from the user.
     */
    public PTUI()
    {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.lib = new Library();
        this.lib.addObserver(this);

        this.clientID = Long.valueOf(657584);

        this.proxy = new LibraryProtectionProxy(lib);
        this.commandparser = new CommandParser(proxy);
    }

    /**
     * Gets keyboard input from the user.
     *
     * @throws IOException
     */
    public void getCommand() throws IOException
    {
        System.out.println("Enter Command");
        try
        {

            String command = reader.readLine();

            // Hacky way to deal with not being able to update UI if not connected
            boolean wasConnected = true;
            if (!proxy.isConnected())
                wasConnected = false;

            commandparser.parseCommand(clientID + "," + command);

            if(!proxy.isConnected() && wasConnected)
                System.out.println(clientID + ",disconnect;" + "\n");

        }
        catch(IOException io)
        {
            System.out.println("Incorrect input");
        }
    }

    /**
     * Updates the status variable which displays command output from completed commands to the user.
     */
    @Override
    public void update(Observable observable, Object o)
    {
        System.out.println(this.lib.getClientStatus(this.clientID));
    }

    /**
     * Main method for testing.
     */
    public static void main(String[] args)
    {
        PTUI test = new PTUI();
        while(true)
        {
            try {
                test.getCommand();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}