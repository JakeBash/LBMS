package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Begins a new visit at the library for a registered visitor.
 *
 * Command format: clientID,arrive,visitor ID;
 * 
 * @author Nikolas Tilley
 */
public class BeginVisit implements LBMSCommand
{
    private LibrarySubject proxy;
    private Long clientID;
    private Long visitorID;

    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////
    /**
     * Creates a BeginVisit command object.
     *
     * @param proxy - The proxy library the visitor is visiting.
     * @param clientID -
     * @param visitorID - The ID of the visitor who is attempting to visit.
     */
    public BeginVisit(LibrarySubject proxy, Long clientID, Long visitorID)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.visitorID = visitorID;
    }


    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////

    /**
     * Executes the BeginVisit command on the library.
     */
    public void execute()
    {
        proxy.beginVisit(clientID, visitorID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {
        proxy.endVisit(clientID,visitorID);
    }

}
