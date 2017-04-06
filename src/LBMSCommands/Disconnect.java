package LBMSCommands;

import Library.Library;

import java.util.ArrayList;

/**
 * Disconnects the specified client. Any subsequent requests that use the same client ID should be considered invalid.
 *
 * client ID,disconnect;
 *
 * @author Nikolas Tilley
 */
public class Disconnect implements LBMSCommand
{
    private Library library;
    private long clientID;

    /**
     *
     * @param library - The library that the client is currently connected with.
     * @param clientID - The unique ID of the client disconnecting
     */
    public Disconnect(Library library, long clientID)
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
