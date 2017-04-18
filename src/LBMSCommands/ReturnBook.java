package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Returns a book borrowed by a library visitor.
 *
 * Command format: clientID,return,visitor ID,id[,ids];
 *
 * @author Nikolas Tilley
 * @author Kyler Freas
 */
public class ReturnBook implements LBMSCommand
{
    private LibrarySubject proxy;
    private Long clientID;
    private Long visitorID;
    private ArrayList<String> bookID;

    /**
     * Creates a ReturnBook command, which will return a book for a registered visitor.
     *
     * @param proxy - The proxy library that the book is being returned to.
     * @param clientID - The ID of the client that is making the ReturnBook Request.
     * @param visitorID - The ID of the visitor that is returning the book.
     * @param bookID - The temporary ID of the book that is to be returned.
     */
    public ReturnBook(LibrarySubject proxy, Long clientID, Long visitorID, ArrayList<String> bookID)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.visitorID = visitorID;
        this.bookID = bookID;
    }


    /**
     * Executes the ReturnBook command on the library.
     */
    public void execute()
    {
        proxy.returnBooks(clientID, visitorID, bookID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {
        proxy.borrowBook(clientID,bookID,visitorID);
    }
}
