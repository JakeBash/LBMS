package UIS;

import Library.Library;
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
 */
public class PTUI implements Observer
{
    private CommandParser commandparser;
    private BufferedReader reader;
    private Library lib;

    /**
     * Creates a new PTUI that accepts input from the user.
     */
    public PTUI()
    {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.lib = new Library();
        this.lib.addObserver(this);
        this.commandparser = new CommandParser(lib);
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
            this.commandparser.parseCommand(reader.readLine());
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
        System.out.println(this.lib.getStatus());
    }

    /**
     * Main method for testing.
     */
    public static void main(String[] args)
    {
        //TODO: Remove later
        PTUI test = new PTUI();
        try
        {
            test.getCommand();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}