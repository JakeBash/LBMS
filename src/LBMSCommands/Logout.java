package LBMSCommands;

import Library.Library;

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
    private long clientID;

    /**
     *
     * @param library - The library the Logout command is being executed on.
     * @param clientID - The ID of the client preforming the Logout command.
     */
    public Logout(Library library, long clientID)
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
}
