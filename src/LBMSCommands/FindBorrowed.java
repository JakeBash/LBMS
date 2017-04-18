package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Queries for a list of books currently borrowed by a specific visitor.
 * Command format: clientID,borrowed,visitor ID;
 * 
 * @author Nikolas Tilley
 */
public class FindBorrowed implements LBMSCommand
{
    private LibrarySubject proxy;
    private Long clientID;
    private Long visitorID;


    /**
     * A command that asks the library to find a visitor's checked out books.
     *
     * @param proxy - A proxy library that has books checked out by visitors.
     * @param clientID - The ID of the client making the FindBorrowed request.
     * @param visitorID - The visitor that is being queried for checked out books.
     */
    public FindBorrowed(LibrarySubject proxy, Long clientID, Long visitorID)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.visitorID = visitorID;
    }


    /**
     * Executes the FindBorrowed command on the library.
     */
    public void execute()
    {
        this.proxy.getVisitorCheckedOutBooks(clientID, visitorID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }
}
