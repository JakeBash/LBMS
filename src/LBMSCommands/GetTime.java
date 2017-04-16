package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Displays the current date and time in the simulation. This should include any days that have been added to the
 * calendar using the command to advance time.
 *
 * Command format: clientID,datetime;
 *
 * @author Nikolas Tilley
 */
public class GetTime implements LBMSCommand
{
    private Library library;

    private LibrarySubject proxy;
    private Long clientID;

    /**
     * Creates a new GetTime command object.
     *
     * @param library - The library that the GetTime command will be executed on.
     */
    public GetTime(Library library)
    {
        this.library = library;
    }



    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////
    /**
     * Creates a new GetTime command object.
     *
     * @param proxy - The library proxy that the GetTime command will be executed on.
     * @param clientID - The ID of the client making the getTime request
     */
    public GetTime(LibrarySubject proxy, Long clientID)
    {
        this.proxy = proxy;
        this.clientID = clientID;
    }



    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////

    /**
     * Executes the GetTime command on the library.
     */
    public void execute()
    {
        proxy.getFormattedDateTime(clientID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }
}
