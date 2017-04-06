package LBMSCommands;

import Library.Library;

import java.util.ArrayList;

/**
 * A new client establishes a connection with the Library.
 *
 * Client ID is a unique ID generated for this client. The client ID
 * will be transmitted with all subsequent requests submitted by this client. The client ID
 * represents on LBMS client program and should not be confused with a visitor or employee ID
 *
 * connect;
 *
 * @author Nikolas Tilley
 */
public class Connect implements LBMSCommand
{
    private Library library;
    private long clientID;

    /**
     *
     * @param library - The library that the client is connecting with.
     * @param clientID - The unique ID of the client connecting
     */
    public Connect(Library library, long clientID)
    {
        this.library = library;
        this.clientID = clientID;
    }

    /**
     * Executes the Connect command on the library.
     */
    @Override
    public void execute() {

    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    @Override
    public void undo() {

    }





    private void parse(String s)
    {

        ArrayList<String> args = new ArrayList<String>();

        String arg = "";


        for(char c : s.toCharArray())
        {

            if (c == ',' || c == ';') {
                args.add(arg);
                arg = "";
            }
            else
                arg += c;
        }

    }
}
