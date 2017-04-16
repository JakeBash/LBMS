package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Ends a visit in progress.
 * Command format: clientID,depart,visitor ID.
 * 
 * @author Nikolas Tilley
 */
public class EndVisit implements LBMSCommand
{
    private Library library;

    private LibrarySubject proxy;
    private Long clientID;
    private Long visitorID;

    /**
     * Constructs an EndVisit command to be executed.
     * 
     * @param library - The library that the visitor was visiting.
     * @param visitorID - The ID of an active visitor.
     */
    public EndVisit(Library library, Long visitorID)
    {
        this.library = library;
        this.visitorID = visitorID;
    }




    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////
    /**
     * Constructs an EndVisit command to be executed.
     *
     * @param proxy - The proxy library that the visitor was visiting.
     * @param clientID - The ID of the client making the EndVisit request.
     * @param visitorID - The ID of an active visitor.
     */
    public EndVisit(LibrarySubject proxy, Long clientID, Long visitorID)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.visitorID = visitorID;
    }



    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////

    /**
     * Executes the EndVisit command on the library.
     */
    public void execute()
    {
        this.proxy.endVisit(clientID, visitorID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }
}
