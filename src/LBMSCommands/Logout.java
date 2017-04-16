package LBMSCommands;

import LibraryProtectionProxy.LibrarySubject;

/**
 * Logs out a currently logged in user
 *
 * client ID,logout;
 *
 * @author Nikolas Tilley
 * @author Jake Bashaw
 */
public class Logout implements LBMSCommand
{

    private LibrarySubject proxy;
    private Long clientID;

    /**
     * Description
     *
     * @param proxy - The proxy library the Logout command is being executed on.
     * @param clientID - The ID of the client preforming the Logout command.
     */
    public Logout(LibrarySubject proxy, Long clientID)
    {
        this.proxy = proxy;
        this.clientID = clientID;
    }

    /**
     * Executes the Logout command on the library.
     */
    @Override
    public void execute()
    {
        this.proxy.logout(clientID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    @Override
    public void undo()
    {

    }
}
