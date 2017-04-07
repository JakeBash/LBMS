package LBMSCommands;

import Library.Library;

import java.util.ArrayList;

/**
 * Logs out a currently logged in user
 *
 * client ID,logout;
 *
 * @author Nikolas Tilley
 */
public class Logout implements LBMSCommand
{

    private Library library;
    private Long clientID;

    /**
     *
     * @param library - The library the Logout command is being executed on.
     * @param clientID - The ID of the client preforming the Logout command.
     */
    public Logout(Library library, Long clientID)
    {
        this.library = library;
        this.clientID = clientID;
    }

    /**
     * Executes the Logout command on the library.
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
