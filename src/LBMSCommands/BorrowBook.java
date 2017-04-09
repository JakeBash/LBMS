package LBMSCommands;

import java.util.ArrayList;
import Library.Library;

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
    // Todo private Long clientID
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

    /**
     * Executes the BorrowBook command on the library.
     */
    public void execute()
    {
        this.library.borrowBooks(bookID,this.visitorID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }

}
