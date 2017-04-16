package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Creates a Shutdown command that will attempt to call methods in the Library in order
 * to safely shut down the system
 *
 * Command format: clientID,shutdown;
 *
 * @author Nikolas Tilley
 */
public class Shutdown implements LBMSCommand
{
    private Library library;

    private LibrarySubject proxy;
    private Long clientID;

    /**
     *  Creates a new Shutdown command object
     *
     * @param library - The library that the Shutdown command will execute on.
     */
    public Shutdown(Library library)
    {
        this.library = library;
    }


    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////

    /**
     *  Creates a new GetTime command object.
     *
     * @param proxy - The proxy library that the Shutdown command will be executed on.
     * @param clientID - The ID of the client issuing the Shutdown command.
     */
    public Shutdown(LibrarySubject proxy, Long clientID)
    {
        this.proxy = proxy;
        this.clientID = clientID;
    }

    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////

    /**
     * Executes the Shutdown command on the library.
     */
    public void execute()
    {
        proxy.shutdown(clientID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }
}
