package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

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
    private LibrarySubject proxy;
    private Long clientID;

    /**
     *
     * @param proxy - The proxy library that the client is currently connected with.
     * @param clientID - The unique ID of the client disconnecting
     */
    public Disconnect(LibrarySubject proxy, Long clientID)
    {
        this.proxy = proxy;
        this.clientID = clientID;
    }

    /**
     * Executes the Connect command on the library.
     */
    @Override
    public void execute() {
        // Todo returning a string to the user will be tricky
        this.proxy.clientDisconnect(clientID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    @Override
    public void undo() {

    }
}
