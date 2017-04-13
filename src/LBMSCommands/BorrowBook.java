package LBMSCommands;

import java.util.ArrayList;
import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

/**
 * Borrows a book for a visitor; uses the ID of a specific book or books returned in the most recent library book search.
 *
 * Signature - clientID,borrow,visitor ID,{id};
 * 
 * @author Nikolas Tilley
 */
public class BorrowBook implements LBMSCommand
{
    private Library library;

    private LibrarySubject proxy;
    private Long clientID;
    private Long visitorID;
    private ArrayList<String> bookID;

    /**
     * Constructs a borrow book command object.
     * 
     * @param library - The library that the book will be borrowed from.
     * @param visitorID - The unique 10-digit ID of the visitor.
     * @param bookID - The comma-separated list of IDs for the books to be borrowed by the visitor. 5 at most.
     */
    public BorrowBook(Library library, Long visitorID, ArrayList<String> bookID)
    {
        this.library = library;
        this.visitorID = visitorID;
        this.bookID = bookID;
    }







    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////
    /**
     * Constructs a borrow book command object.
     *
     * @param proxy - The proxy library that the book will be borrowed from.
     * @param clientID - The ID of the client making the BorrowBook request.
     * @param visitorID - The unique 10-digit ID of the visitor.
     * @param bookID - The comma-separated list of IDs for the books to be borrowed by the visitor. 5 at most.
     */
    public BorrowBook(LibrarySubject proxy, Long clientID, Long visitorID, ArrayList<String> bookID)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.visitorID = visitorID;
        this.bookID = bookID;
    }

    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////

    /**
     * Executes the BorrowBook command on the library.
     */
    public void execute()
    {
        this.proxy.borrowBook(clientID, bookID, visitorID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }

}
